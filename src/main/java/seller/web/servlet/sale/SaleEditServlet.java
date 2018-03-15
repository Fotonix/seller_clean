package seller.web.servlet.sale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.entity.reference.ProductGroup;
import seller.entity.sale.Sale;
import seller.entity.sale.SaleItem;
import seller.service.ReferenceService;
import seller.service.SaleService;
import seller.service.ServiceFactory;
import seller.util.ParseUtils;

/**
 * Осуществляет отображение, создание и редактирование реализации товара.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/sale/edit.html")
public class SaleEditServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 3236212251341625014L;

    private SaleService saleService = ServiceFactory.getInstance().getSaleService();

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    /**
     * Отображает реализацию товара.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ProductGroup> productGroups = referenceService.getProductGroups();
        request.setAttribute("productGroups", productGroups);

        Integer saleId = ParseUtils.stringToInteger(request.getParameter("id"));
        // Если в адресной строке есть id, значит это редактирование
        // уже существующего объекта.
        if (saleId != null) {
            request.setAttribute("sale", saleService.getSale(saleId));
        }

        Integer groupId = ParseUtils.stringToInteger(request.getParameter("groupId"));
        if (groupId == null) {
            groupId = productGroups.get(0).getId();
        }
        request.setAttribute("groupId", groupId);
        request.setAttribute("saleItems", saleService.getProductSaleItems(groupId, saleId));

        request.getRequestDispatcher("/WEB-INF/jsp/sale/edit.jsp").forward(request, response);
    }

    /**
     * Сохраняет реализацию товара.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Sale sale = new Sale();
        Integer saleId = ParseUtils.stringToInteger(request.getParameter("id"));
        sale.setId(saleId);
        sale.setHeld(true);
        // Получает значение формата даты из контекста сервлета.
        final String dateFormat = (String) getServletContext().getAttribute("DATE_FORMAT");
        Date date = ParseUtils.stringToDate(request.getParameter("date"), dateFormat);
        sale.setDate(date);
        String[] itemIds = request.getParameterValues("itemId");
        String[] quantities = request.getParameterValues("quantity");
        String[] prices = request.getParameterValues("price");
        String[] productIds = request.getParameterValues("productId");
        List<SaleItem> items = new ArrayList<SaleItem>(prices.length);
        for (int i = 0; i < prices.length; i++) {
            SaleItem item = new SaleItem();
            item.setId(ParseUtils.stringToInteger(itemIds[i]));
            item.setQuantity(ParseUtils.stringToInteger(quantities[i]));
            item.setPrice(ParseUtils.stringToBigDecimal(prices[i]));
            item.setSaleId(sale.getId());
            item.setProductId(ParseUtils.stringToInteger(productIds[i]));
            items.add(item);
        }
        saleService.saveSaleWithItems(sale, items);
        Integer groupId = ParseUtils.stringToInteger(request.getParameter("groupId"));
        StringBuilder url = new StringBuilder("edit.html?id=").append(sale.getId());
        if (groupId != null) {
            url.append("&groupId=").append(groupId);
        }
        response.sendRedirect(url.toString());
    }

}
