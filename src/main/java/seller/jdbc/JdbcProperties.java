package seller.jdbc;

/**
 * Свойства, необходимые для инициализации соеинения к БД.
 *
 * @author Aleksei Zabezhinsky
 */
public class JdbcProperties {

    /** Имя драйвера БД. */
    private String driverClassName;

    /** Адрес соединения с БД. */
    private String url;

    /** Имя пользователя БД. */
    private String username;

    /** Пароль пользователя БД. */
    private String password;

    /**
     * Возвращает имя драйвера БД.
     *
     * @return имя драйвера БД
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * Устанавливает имя драйвера БД.
     *
     * @param driverClassName имя драйвера БД
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * Возвращает адрес соединения с БД.
     *
     * @return адрес соединения с БД
     */
    public String getUrl() {
        return url;
    }

    /**
     * Устанавливает адрес соединения с БД.
     *
     * @param url адрес соединения с БД
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Возвращает имя пользователя БД.
     *
     * @return имя пользователя БД
     */
    public String getUsername() {
        return username;
    }

    /**
     * Устанавливает имя пользователя БД.
     *
     * @param username имя пользователя БД
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Возвращает пароль пользователя БД.
     *
     * @return пароль пользователя БД
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя БД.
     *
     * @param password пароль пользователя БД
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
