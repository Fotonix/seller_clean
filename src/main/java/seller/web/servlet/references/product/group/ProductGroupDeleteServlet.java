package seller.web.servlet.references.product.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.service.ReferenceService;
import seller.service.ServiceFactory;

/**
 * Удаляет выбранные группы товара.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references/product/group/delete.html")
public class ProductGroupDeleteServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 6654972181475859682L;

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    /**
     * Удаляет группы товаров.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] paramIds = request.getParameterValues("ids");
        if (paramIds != null && paramIds.length > 0) {
            Integer[] ids = new Integer[paramIds.length];
            for (int i = 0; i < ids.length; i++) {
                ids[i] = Integer.valueOf(paramIds[i]);
            }
            referenceService.deleteProductGroups(ids);
        }
        response.sendRedirect("list.html");
    }

}
