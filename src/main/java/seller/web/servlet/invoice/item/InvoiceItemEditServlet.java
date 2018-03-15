package seller.web.servlet.invoice.item;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.entity.invoice.InvoiceItem;
import seller.service.InvoiceService;
import seller.service.ReferenceService;
import seller.service.ServiceFactory;
import seller.util.ParseUtils;

/**
 * Осуществляет отображение, создание и редактирование позиции приходной накладной.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/invoice/item/edit.html")
public class InvoiceItemEditServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 3714110623166981261L;

    private static final String SESSION_ITEM_NAME = "invoiceItem";

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    private InvoiceService invoiceService = ServiceFactory.getInstance().getInvoiceService();

    /**
     * Отображает позицию приходной накладной.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Если ранее была нажата клавиша "Выбрать"/"Сменить", то объект должен быть в сессии.
        InvoiceItem item = (InvoiceItem) request.getSession().getAttribute(SESSION_ITEM_NAME);
        request.getSession().removeAttribute(SESSION_ITEM_NAME);

        String paramId = request.getParameter("itemId");
        if (item == null && paramId != null) {
            // Если в адресной строке есть itemId, значит это редактирование
            // уже существующего объекта.
            Integer itemId = Integer.valueOf(paramId);
            item = invoiceService.getInvoiceItem(itemId);
        }
        if (item != null) {
            request.setAttribute("item", item);
            // Получаем список товара по ID группы.
            request.setAttribute("products",
                    referenceService.getProductsByGroupId(item.getProductGroupId()));
        }
        request.setAttribute("productGroups", referenceService.getProductGroups());
        request.getRequestDispatcher("/WEB-INF/jsp/invoice/item/edit.jsp").forward(request, response);
    }

    /**
     * Сохраняет позицию приходной накладной.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InvoiceItem item = fillItem(request);

        // Если была нажата клавиша "Сохранить".
        if (request.getParameter("changeGroup") == null) {
            // Если itemId не равен null, значит объект редактируется и
            // вызываем update, иначе insert.
            if (item.getId() == null) {
                invoiceService.createInvoiceItem(item);
            } else {
                invoiceService.updateInvoiceItem(item);
            }
        } else { // Если была нажата клавиша "Выбрать"/"Сменить".
            request.getSession().setAttribute(SESSION_ITEM_NAME, item);
        }

        StringBuilder url = new StringBuilder("edit.html?invoiceId=").append(item.getInvoiceId());
        if (item.getId() != null) {
            url.append("&itemId=").append(item.getId());
        }
        response.sendRedirect(url.toString());
    }

    /**
     * Собирает сущность {@link InvoiceItem} из запроса.
     *
     * @param request запрос
     * @return позиция приходной накладной
     */
    private InvoiceItem fillItem(HttpServletRequest request) {
        InvoiceItem item = new InvoiceItem();
        String paramId = request.getParameter("itemId");
        if (paramId != null && !paramId.isEmpty()) {
            item.setId(ParseUtils.stringToInteger(paramId));
        }
        Integer invoiceId = ParseUtils.stringToInteger(request.getParameter("invoiceId"));
        item.setInvoiceId(invoiceId);
        Integer productId = ParseUtils.stringToInteger(request.getParameter("productId"));
        item.setProductId(productId);
        Integer productGroupId = ParseUtils.stringToInteger(request.getParameter("groupId"));
        item.setProductGroupId(productGroupId);
        Integer quantity = ParseUtils.stringToInteger(request.getParameter("quantity"));
        item.setQuantity(quantity);
        item.setCost(ParseUtils.stringToBigDecimal(request.getParameter("cost")));
        item.setPrice(ParseUtils.stringToBigDecimal(request.getParameter("price")));
        return item;
    }

}
