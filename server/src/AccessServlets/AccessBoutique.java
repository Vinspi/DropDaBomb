package AccessServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Controleur d'accès à la Boutique
public class AccessBoutique extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        HttpSession session = req.getSession();

        String pseudo = (String) session.getAttribute("pseudo");

        if (pseudo == null) this.getServletContext().getRequestDispatcher("/log.jsp").forward(req,resp);
        else    this.getServletContext().getRequestDispatcher("/WEB-INF/boutique.jsp").forward(req,resp);


    }

}
