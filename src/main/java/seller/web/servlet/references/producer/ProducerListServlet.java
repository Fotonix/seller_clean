package seller.web.servlet.references.producer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.service.ReferenceService;
import seller.service.ServiceFactory;

/**
 * Страница, выводящая список поставщиков.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references/producer/list.html")
public class ProducerListServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = -4232805442523762054L;

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    /**
     * Отображает список поставщиков.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("producers", referenceService.getProducers());
        request.getRequestDispatcher("/WEB-INF/jsp/references/producer/list.jsp").forward(request, response);
    }

}
