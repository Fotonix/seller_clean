package seller.web.servlet.invoice;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.entity.invoice.Invoice;
import seller.entity.invoice.InvoiceItemView;
import seller.service.InvoiceService;
import seller.service.ReferenceService;
import seller.service.ServiceFactory;

/**
 * Осуществляет отображение, создание и редактирование приходной накладной.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/invoice/edit.html")
public class InvoiceEditServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 8660176308591231154L;

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    private InvoiceService invoiceService = ServiceFactory.getInstance().getInvoiceService();

    /**
     * Отображает приходную накладную.
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
            Invoice invoice = invoiceService.getInvoice(id);
            request.setAttribute("invoice", invoice);
            List<InvoiceItemView> items = invoiceService.getInvoiceItemViews(id);
            request.setAttribute("items", items);
        }
        request.setAttribute("producers", referenceService.getProducers());
        request.getRequestDispatcher("/WEB-INF/jsp/invoice/edit.jsp").forward(request, response);
    }

    /**
     * Сохраняет приходную накладную.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Invoice invoice = new Invoice();
        // Получает значение формата даты из контекста сервлета.
        final String dateFormat = (String) getServletContext().getAttribute("DATE_FORMAT");
        Date date = null;
        try {
            date = new SimpleDateFormat(dateFormat).parse(request.getParameter("date"));
        } catch (ParseException e) {
            // Возникает при ошибке преобразования строки в дату.
            // FIXME: Пока просто кидаем ошибку дальше. Необходимо переделать в вывод ошибки.
            throw new RuntimeException("Error parsing date", e);
        }
        invoice.setDate(date);
        invoice.setSeries(request.getParameter("series"));
        Integer producerId = Integer.valueOf(request.getParameter("producerId"));
        invoice.setProducerId(producerId);
        // FIXME: Временно делаем все накладные проведенными.
        invoice.setHeld(true);

        String paramId = request.getParameter("id");
        // Если id не равен null, значит объект редактируется и
        // вызываем update, иначе insert.
        if (paramId == null || paramId.isEmpty()) {
            invoiceService.createInvoice(invoice);
        } else {
            Integer id = Integer.valueOf(paramId);
            invoice.setId(id);
            invoiceService.updateInvoice(invoice);
        }
        response.sendRedirect("edit.html?id=" + invoice.getId());
    }

}
