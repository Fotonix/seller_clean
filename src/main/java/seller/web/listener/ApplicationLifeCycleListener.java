package seller.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Служит для инициализации контекста приложения.
 * Используется для установки глобальных переменных.
 *
 * @author Aleksei Zabezhinsky
 */
@WebListener
public class ApplicationLifeCycleListener implements ServletContextListener {

    public static final String DATE_FORMAT = "dd.MM.yyyy";

    /**
     * Устанавливает глобальные переменные при старте приложения.
     * Переменные будут доступны во всех сервлетах через {@link javax.servlet.ServletContext}
     * и во всех JSP-страницах (через <code>EL</code>).
     *
     * @param sce {@link ServletContextEvent}
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("DATE_FORMAT", DATE_FORMAT);
    }

    /**
     * Вызывается при уничтожении контекста приложения.
     *
     * @param sce {@link ServletContextEvent}
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
