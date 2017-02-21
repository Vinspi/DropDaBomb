package AccountServlets;

import Manager.AccountManager;
import Manager.RequestStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by vinspi on 20/02/17.
 */
public class AccountUpdaterServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter printWriter = resp.getWriter();
        AccountManager accountManager = new AccountManager();

        String pseudo = (String) session.getAttribute("pseudo");

        String typeRequest = req.getParameter("typeRequest");

        String update = req.getParameter("update");

        switch (Integer.parseInt(typeRequest)){
            case RequestStatus.UPDATE_CARTON:
                printWriter.print(accountManager.changerSkinCarton(pseudo,update));
                break;

            case RequestStatus.UPDATE_ICON:
                printWriter.print(accountManager.changeIcon(pseudo,update));
                break;

            case RequestStatus.UPDATE_EMAIL:
                printWriter.print(accountManager.changerEmail(pseudo,update));
                break;

            case RequestStatus.UPDATE_MDP:
                printWriter.print(accountManager.changePassword(pseudo,update));
                break;

            case RequestStatus.UPDATE_MAP:
                printWriter.print(accountManager.changerSkinMap(pseudo,update));
                break;

            case RequestStatus.ACHAT_MONNAIE:
                printWriter.print(accountManager.acheterMonnaieIRL(pseudo,update));
                break;

        }

    }
}
