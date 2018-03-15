package seller.web.servlet.references.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.service.ProductService;
import seller.service.ServiceFactory;

/**
 * Удаляет выбранный товар.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references/product/delete.html")
public class ProductDeleteServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 2184060886144423616L;

    private ProductService productService = ServiceFactory.getInstance().getProductService();

    /**
     * Удаляет товары.
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
            productService.deleteProducts(ids);
        }
        response.sendRedirect("list.html");
    }

}
