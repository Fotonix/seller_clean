package seller.web.servlet.references.product.group;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.service.ReferenceService;

/**
 * Страница, выводящая список групп товаров.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references/product/group/list.html")
public class ProductGroupListServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = -2290249674703512412L;

    @Inject
    private ReferenceService referenceService;
    //private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    /**
     * Отображает список групп товаров.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("productGroups", referenceService.getProductGroupViews());
        request.getRequestDispatcher("/WEB-INF/jsp/references/product/group/list.jsp")
                .forward(request, response);
    }

}
