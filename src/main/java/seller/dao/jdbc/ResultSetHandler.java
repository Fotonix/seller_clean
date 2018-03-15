package seller.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Обработчик запроса.<br>
 * Должен реализовывать логику обработки {@link ResultSet}'а.
 *
 * @author Aleksei Zabezhinsky
 */
public class ResultSetHandler<T extends Object> {

    private static class ResultSetHandlerLazy {
        private static ResultSetHandler<?> instance = new ResultSetHandler<Object>();
      }

    /**
     * Возвращает объект с базовым поведением.
     *
     * @return базовый объект
     */
    public static ResultSetHandler<?> getDefaultHandler() {
        return ResultSetHandlerLazy.instance;
    }

    /**
     * Обрабатывает {@link ResultSet} и возвращает итоговый объект.
     *
     * @param rs {@link ResultSet}
     * @return итоговый объект
     * @throws SQLException возникает во время обработки {@link ResultSet}'а
     */
    public T handle(ResultSet rs) throws SQLException {
        return null;
    }

    /**
     * Инициализирует запрос параметрами.
     *
     * @param statement {@link PreparedStatement}
     * @param params параметры запроса
     * @throws SQLException возникает во время добавления параметров
     */
    public void initStatement(PreparedStatement statement, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
        }
    }

}
