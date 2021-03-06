package AccountServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import Manager.*;

/**
 * Created by vinspi on 26/01/17.
 */
public class CreateAccountServletAndroid extends HttpServlet {

    private AccountManager accountManager = new AccountManager();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String pseudo = req.getParameter("pseudo");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        out.print(accountManager.createAccount(pseudo,email,password));
    }

}
