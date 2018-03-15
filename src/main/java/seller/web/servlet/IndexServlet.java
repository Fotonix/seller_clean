package seller.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Является первой точкой входа в программу.
 * В зависимости от логики и прав доступа,
 * осуществляет перенаправление на стартовую страницу.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = -7853303937583877936L;


    /**
     * Перенаправляет на главную страницу.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("main.html");
    }

}
