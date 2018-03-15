package seller.web.servlet.references;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Страница справочников.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references.html")
public class ReferencesServlet extends HttpServlet {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 7002291219524790521L;

    /**
     * Перенаправляет на страницу вывода списка продукции.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("references/product/list.html");
    }

}
