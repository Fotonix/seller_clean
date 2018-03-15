package seller.web.servlet.reports;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Страница отчетов.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/reports.html")
public class ReportsServlet extends HttpServlet {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = -446560276212272223L;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/reports/reports.jsp").forward(request, response);
    }

}
