package seller.web.servlet.sale;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.service.SaleService;
import seller.service.ServiceFactory;

/**
 * Удаляет выбранные реализации товара.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/sale/delete.html")
public class SaleDeleteServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = -4822801658191560110L;

    private SaleService saleService = ServiceFactory.getInstance().getSaleService();

    /**
     * Удаляет реализацию товара.
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
            saleService.deleteSales(ids);
        }
        response.sendRedirect("list.html");
    }

}
