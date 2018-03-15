package seller.web.servlet.invoice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Страница поступления товара.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/invoice.html")
public class InvoiceServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 5244800809684611088L;

    /**
     * Перенаправляет на страницу вывода списка приходных накладных.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("invoice/list.html");
    }

}
