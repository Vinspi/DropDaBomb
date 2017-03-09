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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter printWriter = resp.getWriter();
        AccountManager accountManager = new AccountManager();

        String pseudo = (String) session.getAttribute("pseudo");

        String typeRequest = req.getParameter("typeRequest");

        String update = req.getParameter("update");

        String option = req.getParameter("option");

        switch (Integer.parseInt(typeRequest)){
            case RequestStatus.UPDATE_CARTON:
                printWriter.print(accountManager.changerSkinCarton(pseudo,update));
                break;

            case RequestStatus.UPDATE_ICON:
                System.out.println("j'ai changé l'icone par "+update);
                printWriter.print(accountManager.changeIcon(pseudo,update));
                session.setAttribute("iconeJoueur",option);
                break;

            case RequestStatus.UPDATE_EMAIL:
                printWriter.print(accountManager.changerEmail(pseudo,update));
                session.setAttribute("mailJoueur",update);
                break;

            case RequestStatus.UPDATE_MDP:
                String mdp = (String) req.getParameter("password");
                System.out.println(mdp+" == "+session.getAttribute("password"));
                if(mdp.equals(session.getAttribute("password"))) {
                    printWriter.print(accountManager.changePassword(pseudo, update));
                    session.setAttribute("password", update);
                }
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
