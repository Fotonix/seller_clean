package seller.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Предоставляет инструменты для работы с транзакциями.
 *
 * @author Aleksei Zabezhinsky
 */
public final class TransactionUtil {

    private static final JdbcSupport JDBC_SUPPORT = new JdbcSupport();

    private static final ThreadLocal<Connection> CONNECTIONS = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            return JDBC_SUPPORT.getConnection();
        }
    };

    /** Приватный конструктор. */
    private TransactionUtil() {
        super();
    }

    /**
     * Возвращает соединение с БД.
     *
     * @return соединение с БД
     */
    public static Connection getConnection() {
        return CONNECTIONS.get();
    }

    /** Start transaction. */
    public static void startTransaction() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException("Error DB access", e);
        }
    }

    /** Commit. */
    public static void commitTransaction() {
        try {
            if (!getConnection().getAutoCommit()) {
                getConnection().commit();
            }
            if (!getConnection().isClosed()) {
                getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error DB access", e);
        }
    }

    /** Rollback. */
    public static void rollbackTransaction() {
        try {
            if (!getConnection().getAutoCommit()) {
                getConnection().rollback();
            }
            if (!getConnection().isClosed()) {
                getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error DB access", e);
        }
    }

    /**
     * Возвращает стоит ли автокомит.
     *
     * @return автокомит или нет
     */
    public static boolean isAutoCommit() {
        try {
            return getConnection().getAutoCommit();
        } catch (SQLException e) {
            throw new RuntimeException("Error DB access", e);
        }
    }

}
