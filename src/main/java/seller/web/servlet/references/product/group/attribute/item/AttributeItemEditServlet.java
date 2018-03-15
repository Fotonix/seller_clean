package seller.web.servlet.references.product.group.attribute.item;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.entity.reference.AttributeItem;
import seller.service.ReferenceService;
import seller.service.ServiceFactory;
import seller.util.ParseUtils;

/**
 * Страница, позволяющая создавать/редактировать позицию признака группы товара.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references/product/group/attribute/item/edit.html")
public class AttributeItemEditServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = -4633150209198756664L;

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    /**
     * Отображает позицию признака группы товара.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id = ParseUtils.stringToInteger(request.getParameter("id"));

        // Если в адресной строке есть id, значит это редактирование
        // уже существующего объекта.
        if (id != null) {
            AttributeItem item = referenceService.getAttributeItem(id);
            request.setAttribute("item", item);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/references/product/group/attribute/item/edit.jsp")
                .forward(request, response);
    }

    /**
     * Сохраняет позицию признака группы товара.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AttributeItem item = new AttributeItem();
        item.setAttributeId(ParseUtils.stringToInteger(request.getParameter("attributeId")));
        item.setName(request.getParameter("name"));

        Integer id = ParseUtils.stringToInteger(request.getParameter("id"));
        // Если id не равен null, значит объект редактируется и
        // вызываем update, иначе insert.
        if (id == null) {
            referenceService.createAttributeItem(item);
        } else {
            item.setId(id);
            referenceService.updateAttributeItem(item);
        }
        response.sendRedirect("/seller/references/product/group/list.html");
    }

}
