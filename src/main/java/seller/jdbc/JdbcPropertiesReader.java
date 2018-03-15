package seller.jdbc;

import java.util.Properties;

/**
 * Считывает настройки БД из файла.
 *
 * @author Aleksei Zabezhinsky
 */
public class JdbcPropertiesReader {

    public static final String DEFAULT_RESOURCE_NAME = "jdbc.properties";

    private String fileName;

    /**
     * Создание объекта с привязкой определенного имени файла настроек БД.
     *
     * @param fileName имя файла настроек БД
     */
    public JdbcPropertiesReader(String fileName) {
        setFileName(fileName);
    }

    /**
     * Создание объекта с дефолтным файлом настроек БД.
     */
    public JdbcPropertiesReader() {
        this(DEFAULT_RESOURCE_NAME);
    }

    /**
     * Задание файла настроек БД.
     *
     * @param fileName имя файла настроек БД
     */
    public void setFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Parameter 'filename' can't be empty.");
        }
        this.fileName = fileName;
    }

    /**
     * Считывает настройки БД из файла.
     *
     * @return настройки БД
     */
    public JdbcProperties read() {
        Properties propFile = new Properties();
        try {
            propFile.load(getClass().getClassLoader().getResourceAsStream(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Error reading file '" + fileName + "'", e);
        }
        JdbcProperties props = new JdbcProperties();
        props.setDriverClassName(propFile.getProperty("db.driver.class"));
        props.setUrl(propFile.getProperty("db.url"));
        props.setUsername(propFile.getProperty("db.username"));
        props.setPassword(propFile.getProperty("db.password"));
        return props;
    }

}
