package seller.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Устанавливает кодировку запроса на UTF-8.
 *
 * @author Aleksei Zabezhinsky
 */
@WebFilter("/*")
public class CharsetFilter implements Filter {

    private static final String ENCODING = "UTF-8";

    /**
     * Перехватывает все запросы/ответы к программе.
     * Устанавливает кодировку запроса на UTF-8.
     *
     * @param request {@link ServletRequest}
     * @param response {@link ServletResponse}
     * @param chain {@link FilterChain}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);
        chain.doFilter(request, response);
    }

    /**
     * Инициализация фильтра при старте.
     *
     * @param filterConfig конфигурация фильтра
     * @exception ServletException возникает, если запрос не может быть обработан
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Возникает во время остановки фильра.
     * Освобождает ранее инициализированные ресурсы.
     */
    @Override
    public void destroy() {
    }

}
