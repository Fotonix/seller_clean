package seller.web.servlet.references.product.group.attribute;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.entity.reference.GroupAttribute;
import seller.service.ReferenceService;
import seller.service.ServiceFactory;
import seller.util.ParseUtils;

/**
 * Страница, позволяющая редактировать/создавать признак группы товара.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references/product/group/attribute/edit.html")
public class GroupAttributeEditServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 3514943481014274017L;

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    /**
     * Отображает признак группы товара.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

/*        Integer groupId = ParseUtils.stringToInteger(request.getParameter("groupId"));
        request.setAttribute("groupId", groupId);*/
        Integer id = ParseUtils.stringToInteger(request.getParameter("id"));

        // Если в адресной строке есть id, значит это редактирование
        // уже существующего объекта.
        if (id != null) {
            GroupAttribute groupAttribute = referenceService.getGroupAttribute(id);
            request.setAttribute("groupAttribute", groupAttribute);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/references/product/group/attribute/edit.jsp")
                .forward(request, response);
    }

    /**
     * Сохраняет признак группы товара.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GroupAttribute groupAttribute = new GroupAttribute();
        groupAttribute.setGroupId(ParseUtils.stringToInteger(request.getParameter("groupId")));
        groupAttribute.setName(request.getParameter("name"));

        Integer id = ParseUtils.stringToInteger(request.getParameter("id"));
        // Если id не равен null, значит объект редактируется и
        // вызываем update, иначе insert.
        if (id == null) {
            referenceService.createGroupAttribute(groupAttribute);
        } else {
            groupAttribute.setId(id);
            referenceService.updateGroupAttribute(groupAttribute);
        }
        response.sendRedirect("/seller/references/product/group/list.html");
    }

}
