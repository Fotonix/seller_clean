package seller.web.servlet.references.product;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.entity.reference.Product;
import seller.service.ReferenceService;
import seller.service.ServiceFactory;
import seller.util.ParseUtils;

/**
 * Страница, позволяющая редактировать/создавать товар.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references/product/edit.html")
public class ProductEditServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 7737438620432973181L;

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    /**
     * Отображает товар.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paramId = request.getParameter("id");

        // Если в адресной строке есть id, значит это редактирование
        // уже существующего объекта.
        if (paramId != null) {
            Integer id = Integer.valueOf(paramId);
            Product product = referenceService.getProduct(id);
            request.setAttribute("product", product);
        }
        request.setAttribute("groups", referenceService.getProductGroups());
        request.getRequestDispatcher("/WEB-INF/jsp/references/product/edit.jsp").forward(request, response);
    }

    /**
     * Сохраняет товар.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product product = new Product();
        product.setName(request.getParameter("name"));
        Integer groupId = ParseUtils.stringToInteger(request.getParameter("groupId"));
        product.setGroupId(groupId);
        BigDecimal price = ParseUtils.stringToBigDecimal(request.getParameter("price"));
        product.setPrice(price);

        String paramId = request.getParameter("id");
        // Если id не равен null, значит объект редактируется и
        // вызываем update, иначе insert.
        if (paramId == null || paramId.isEmpty()) {
            referenceService.createProduct(product);
        } else {
            Integer id = Integer.valueOf(paramId);
            product.setId(id);
            referenceService.updateProduct(product);
        }
        response.sendRedirect("list.html");
    }

}
