package AccessServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by deutsch on 15/04/17.
 */
public class AccessAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        HttpSession session = req.getSession();

        Boolean estAdmin = (Boolean) session.getAttribute("estAdmin");

        if (estAdmin == null) this.getServletContext().getRequestDispatcher("/log.jsp").forward(req, resp);
        else if (estAdmin == true) this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(req, resp);
        else this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}
