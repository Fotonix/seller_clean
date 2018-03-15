package seller.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Осуществляет связь с БД через технологию <code>jdbc</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public class JdbcSupport {

    private final JdbcProperties jdbcProperties;

    /**
     * Конструктор. Инициализирует настройки БД.
     */
    public JdbcSupport() {
        JdbcPropertiesReader propertiesReader = new JdbcPropertiesReader();
        jdbcProperties = propertiesReader.read();
        try {
            Class.forName(jdbcProperties.getDriverClassName()).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error initialize DB driver", e);
        }
    }

    /**
     * Создает и возвращает соединение с БД.
     *
     * @return соединение с БД
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    jdbcProperties.getUrl(),
                    jdbcProperties.getUsername(),
                    jdbcProperties.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException("Error open DB connection", e);
        }
    }
}
