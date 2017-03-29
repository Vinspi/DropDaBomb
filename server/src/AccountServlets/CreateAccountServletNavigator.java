package AccountServlets;

import Manager.RequestStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vinspi on 09/02/17.
 */
public class CreateAccountServletNavigator extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pseudo = req.getParameter("pseudo");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password_conf = req.getParameter("password_confirm");

        if(!password.equals(password_conf)){
            req.setAttribute("STATUS",RequestStatus.ERR_MDP_NOT_MATCH);
        }
        else {
            HttpSession session = req.getSession();
            System.out.println("je suis le navigator");

            Manager.AccountManager accountManager = new Manager.AccountManager();
            int result = accountManager.createAccount(pseudo, email, password);

            if (result == RequestStatus.CREATE_ACCOUNT_SUCCES) {
                req.setAttribute("STATUS", RequestStatus.CREATE_ACCOUNT_SUCCES);
            } else if (result == RequestStatus.CREATE_ACCOUNT_FAILED_PSEUDO) {
                req.setAttribute("STATUS", RequestStatus.CREATE_ACCOUNT_FAILED_PSEUDO);
            } else if (result == RequestStatus.ERR_MDP) {
                req.setAttribute("STATUS", RequestStatus.ERR_MDP);
            } else req.setAttribute("STATUS", RequestStatus.CREATE_ACCOUNT_FAILED_EMAIL);
        }
        this.getServletContext().getRequestDispatcher("/account.jsp").forward(req,resp);



    }
}
