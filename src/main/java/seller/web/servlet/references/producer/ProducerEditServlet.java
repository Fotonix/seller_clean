package seller.web.servlet.references.producer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.entity.reference.Producer;
import seller.service.ReferenceService;
import seller.service.ServiceFactory;

/**
 * Страница, позволяющая редактировать/создавать поставщика.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/references/producer/edit.html")
public class ProducerEditServlet extends HttpServlet {

    /** Serial version. */
    private static final long serialVersionUID = -4713033384285908559L;

    private ReferenceService referenceService = ServiceFactory.getInstance().getReverenceService();

    /**
     * Отображает поставщика.
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
            Producer producer = referenceService.getProducer(id);
            request.setAttribute("producer", producer);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/references/producer/edit.jsp").forward(request, response);
    }

    /**
     * Сохраняет поставщика.
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @exception ServletException возникает, если запрос не может быть обработан
     * @exception IOException ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Producer producer = new Producer();
        producer.setName(request.getParameter("name"));

        String paramId = request.getParameter("id");
        // Если id не равен null, значит объект редактируется и
        // вызываем update, иначе insert.
        if (paramId == null || paramId.isEmpty()) {
            referenceService.createProducer(producer);
        } else {
            Integer id = Integer.valueOf(paramId);
            producer.setId(id);
            referenceService.updateProducer(producer);
        }
        response.sendRedirect("list.html");
    }

}
