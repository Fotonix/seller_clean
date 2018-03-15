package seller.web.servlet.invoice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.service.InvoiceService;
import seller.service.ServiceFactory;

/**
 * Выводит список приходных накладных.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/invoice/list.html")
public class InvoiceListServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 913123028555760655L;

    private InvoiceService invoiceService = ServiceFactory.getInstance().getInvoiceService();

    /**
     * Отображает список приходных накладных.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("invoices", invoiceService.getInvoiceViews());
        request.getRequestDispatcher("/WEB-INF/jsp/invoice/list.jsp").forward(request, response);
    }

}
