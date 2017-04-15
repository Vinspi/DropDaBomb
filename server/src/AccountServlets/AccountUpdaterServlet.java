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
    //Servlet de controle des actions des modifications sur le compte :

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //nothing
    }

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

            //Demande de changement de SkinCarton : appel de AccountManager.changerSkinCarton
            case RequestStatus.UPDATE_CARTON:
                printWriter.print(accountManager.changerSkinCarton(pseudo,update));
                break;

            //Demande de changement d'Icone : appel de AccountManager.changerIcon
            case RequestStatus.UPDATE_ICON:
                printWriter.print(accountManager.changeIcon(pseudo,update));
                session.setAttribute("iconeJoueur",option);
                break;

            //Demande de changement d'email : appel de AccountManager.changerEmail
            case RequestStatus.UPDATE_EMAIL:
                printWriter.print(accountManager.changerEmail(pseudo,update));
                session.setAttribute("mailJoueur",update);
                break;

            //Demande de changement de mot de passe : appel de AccountManager.changerPassword
            case RequestStatus.UPDATE_MDP:
                String mdp = (String) req.getParameter("password");
                if(mdp.equals(session.getAttribute("password"))) {
                    printWriter.print(accountManager.changePassword(pseudo, update));
                    session.setAttribute("password", update);
                }
                break;

            //Demande de changement de SkinMap : appel de AccountManager.changerSkinMap
            case RequestStatus.UPDATE_MAP:
                printWriter.print(accountManager.changerSkinMap(pseudo,update));
                break;

            //Achat de monnaie par le joueur : appel de AccountManager.acheterMonnaieIRL
            case RequestStatus.ACHAT_MONNAIE:
                printWriter.print(accountManager.acheterMonnaieIRL(pseudo,update));
                break;

            //Demande de suppression du compte:
            case RequestStatus.SUPP_ACCOUNT :


                String verif = (String) req.getParameter("verif");
                String pwd = (String) session.getAttribute("password");
                int id;
                if (verif.equals(pwd)){

                    //Mot de passe correct, suppression du compte :
                    accountManager.supprimerCompte(pseudo);

                    //Suppression des variables de session :
                    session.invalidate();

                    id = 0;
                    printWriter.print(id);

                }
                else {
                    id = 1;
                    printWriter.print(id);
                }
        }

    }



}
