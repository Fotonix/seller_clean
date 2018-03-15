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
 * Выводит список реализации.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/sale/list.html")
public class SaleListServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = 5479383212128858432L;

    private SaleService saleService = ServiceFactory.getInstance().getSaleService();

    /**
     * Отображает список реализации.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("sales", saleService.getSaleViews());
        request.getRequestDispatcher("/WEB-INF/jsp/sale/list.jsp").forward(request, response);
    }

}
