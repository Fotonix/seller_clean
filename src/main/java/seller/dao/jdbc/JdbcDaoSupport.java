package seller.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seller.jdbc.JdbcSupport;
import seller.jdbc.TransactionUtil;

/**
 * Осуществляет поддержку основных sql-операций.
 *
 * @author Aleksei Zabezhinsky
 */
public abstract class JdbcDaoSupport extends JdbcSupport {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Выполняет sql-запрос и возвращает итоговый объект.
     *
     * @param query текст запроса
     * @param params параметры запроса
     * @param handler обработчик запроса
     * @return результат выполнения запроса
     */
    public Object executeStatement(String query, Object[] params, ResultSetHandler<?> handler) {
        Connection connection = TransactionUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            if (handler == null) {
                handler = ResultSetHandler.getDefaultHandler();
            }
            handler.initStatement(statement, params);
            return statement.execute() ? handler.handle(statement.getResultSet()) : null;
        } catch (SQLException e) {
            if (TransactionUtil.isAutoCommit()) {
                TransactionUtil.rollbackTransaction();
            }
            throw new RuntimeException("SQL error", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close SQL statement", e);
                }
            }
            if (connection != null) {
                if (TransactionUtil.isAutoCommit()) {
                    TransactionUtil.commitTransaction();
                }
            }
        }
    }

    /**
     * Выполняет sql-запрос без параметров и возвращает итоговый объект.
     *
     * @param query текст запроса
     * @param handler обработчик запроса
     * @return результат выполнения запроса
     */
    public Object executeStatement(String query, ResultSetHandler<?> handler) {
        return executeStatement(query, null, handler);
    }

    /**
     * Выполняет sql-запрос.
     *
     * @param query текст запроса
     * @param params параметры запроса
     */
    public void executeStatement(String query, Object[] params) {
        executeStatement(query, params, null);
    }

    /**
     * Выполняет sql-запрос на вставку данных. Возвращает присвоенный ID объекта.
     *
     * @param query текст запроса
     * @param params параметры запроса
     * @return ID объекта
     */
    public Object executeInsert(String query, Object[] params) {
        Connection connection = TransactionUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSetHandler.getDefaultHandler().initStatement(statement, params);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            return keys.getObject(1);
        } catch (SQLException e) {
            if (TransactionUtil.isAutoCommit()) {
                TransactionUtil.rollbackTransaction();
            }
            throw new RuntimeException("SQL error", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close SQL statement", e);
                }
            }
            if (connection != null) {
                if (TransactionUtil.isAutoCommit()) {
                    TransactionUtil.commitTransaction();
                }
            }
        }
    }

    /**
     * Выполняет sql-запрос на удаление и возвращает количество удаленных объектов.
     *
     * @param query текст запроса
     * @param params параметры запроса
     * @return количество удаленных объектов
     */
    public int executeDelete(String query, Object[] params) {
        return executeUpdate(query, params);
    }

    /**
     * Выполняет sql-запрос на обновление данных и возвращает количество обработанных записей.
     *
     * @param query текст запроса
     * @param params параметры запроса
     * @return количество обработанных записей
     */
    public int executeUpdate(String query, Object[] params) {
        Connection connection = TransactionUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            ResultSetHandler.getDefaultHandler().initStatement(statement, params);
            return statement.executeUpdate();
        } catch (SQLException e) {
            if (TransactionUtil.isAutoCommit()) {
                TransactionUtil.rollbackTransaction();
            }
            throw new RuntimeException("SQL error", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close SQL statement", e);
                }
            }
            if (connection != null) {
                if (TransactionUtil.isAutoCommit()) {
                    TransactionUtil.commitTransaction();
                }
            }
        }
    }

}
