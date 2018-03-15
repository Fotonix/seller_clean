package seller.web.servlet.sale;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Реализация товара.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/sale.html")
public class SaleServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = -8980834715864284383L;

    /**
     * Перенаправляет на страницу вывода списка реализации.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("sale/list.html");
    }

}
