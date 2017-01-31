package AccountServlets;

import Manager.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


}
