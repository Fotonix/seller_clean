package seller.web.servlet.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Главная страница.
 *
 * @author Aleksei Zabezhinsky
 */
@WebServlet("/main.html")
public class MainServlet extends HttpServlet {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 2672493771211915938L;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/main/main.jsp").forward(request, response);
    }

}
