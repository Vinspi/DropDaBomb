package AccountServlets;

import Manager.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vinspi on 31/01/17.
 */
public class AuthentificationAccountServlet extends HttpServlet {
    private AccountManager accountManager = new AccountManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* servlet d'Authentification */

        String pseudo = req.getParameter("pseudo");
        String password = req.getParameter("password");

        PrintWriter out = resp.getWriter();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");



        out.print(accountManager.authentification(pseudo,password));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pseudo = req.getParameter("pseudo");
        String password = req.getParameter("password");

        System.out.println(pseudo+" "+password);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        HttpSession session = req.getSession();

        if(accountManager.authentification(pseudo,password) == RequestStatus.AUTH_SUCCES){
            session.setAttribute("iconeJoueur",accountManager.getPlayerIcon(pseudo));
            session.setAttribute("mailJoueur",accountManager.getplayerEmail(pseudo));
            session.setAttribute("skinCartonJoueur",accountManager.getPlayerSkinCarton(pseudo));
            session.setAttribute("skinMapJoueur",accountManager.getPlayerSkinMap(pseudo));
            session.setAttribute("pseudo",pseudo);
            session.setAttribute("password",password);
            session.setAttribute("money",accountManager.getPlayerMoney(pseudo));
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
        }
        else{
            //sinon rediriger vers une page d'erreur
            session.setAttribute("STATUS",RequestStatus.AUTH_FAILED);
            System.out.println(session.getAttribute("STATUS"));
            this.getServletContext().getRequestDispatcher("/log.jsp").forward(req,resp);
        }



    }
}
