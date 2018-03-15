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
 * Удаляет выбранные приходные накладные.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/invoice/delete.html")
public class InvoiceDeleteServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 3460115049763537269L;

    private InvoiceService invoiceService = ServiceFactory.getInstance().getInvoiceService();

    /**
     * Удаляет приходные накладные.
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
            invoiceService.deleteInvoices(ids);
        }
        response.sendRedirect("list.html");
    }

}
