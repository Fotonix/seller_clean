package seller.web.servlet.references.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.service.ReferenceService;
import seller.service.ServiceFactory;

/**
 * Страница, выводящая список товаров.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references/product/list.html")
public class ProductListServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 4097378346190549816L;

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    /**
     * Отображает список товаров.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products", referenceService.getProductViews());
        request.getRequestDispatcher("/WEB-INF/jsp/references/product/list.jsp").forward(request, response);
    }

}
