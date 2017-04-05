package ShopServlets;

import Manager.AccountManager;
import Manager.ShopManager;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;


/**
 * Created by deutsch on 30/01/17.
 */
public class ShopAchatServlet extends HttpServlet {


    private ShopManager shopManager = new ShopManager();
    private AccountManager accountManager = new AccountManager();


    //On ne gère pas le GET.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET non voulu");
    }


    //money != prix, money = {monnaieIG;monnaieIRL}
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{


        Connection connection = null;
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        Gson gson = new Gson();

        String pseudo = (String) session.getAttribute("pseudo");
        String id_Offre = req.getParameter("id_Offre");
        System.out.println("Achat de : "+id_Offre);
        System.out.println(req.getRequestURL());
        int request = Integer.parseInt(req.getParameter("idRequest"));


        ShopManager.Doublet s;
        String json;
        switch (request) {

            case 1 :    //Achat de l'offre id_Offre avec monnaieIG
                s = shopManager.doAchat(pseudo, id_Offre,"monnaieIG");
                json = gson.toJson(s);
                System.out.println(json);
                System.out.println("Achat IG terminé");
                session.setAttribute("monnaieIG",accountManager.getPlayerMoneyIG(pseudo));
                out.print(json);
                break;
            case 2 :    //Achat de l'offre id_Offre avec monnaieIRL
                s = shopManager.doAchat(pseudo, id_Offre,"monnaieIRL");
                json = gson.toJson(s);
                System.out.println(json);
                System.out.println("Achat IRL terminé");
                session.setAttribute("monnaieIRL",accountManager.getPlayerMoneyIRL(pseudo));
                out.print(json);
                break;
        }



    }
}
