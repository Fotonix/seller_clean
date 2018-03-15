package seller.web.servlet.references.product.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.entity.reference.ProductGroup;
import seller.service.ReferenceService;
import seller.service.ServiceFactory;

/**
 * Страница, позволяющая редактировать/создавать группу товара.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references/product/group/edit.html")
public class ProductGroupEditServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 4457716423221488920L;

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    /**
     * Отображает группу товара.
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
            ProductGroup productGroup = referenceService.getProductGroup(id);
            request.setAttribute("productGroup", productGroup);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/references/product/group/edit.jsp")
                .forward(request, response);
    }

    /**
     * Сохраняет группу товара.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductGroup productGroup = new ProductGroup();
        productGroup.setName(request.getParameter("name"));

        String paramId = request.getParameter("id");
        // Если id не равен null, значит объект редактируется и
        // вызываем update, иначе insert.
        if (paramId == null || paramId.isEmpty()) {
            referenceService.createProductGroup(productGroup);
        } else {
            Integer id = Integer.valueOf(paramId);
            productGroup.setId(id);
            referenceService.updateProductGroup(productGroup);
        }
        response.sendRedirect("list.html");
    }

}
