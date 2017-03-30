package ShopServlets;

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
    //Pour tester :
    //localhost:8080/ShopAchat?pseudo=AA&password=bb&id_Offre=0&monnaie=monnaieIG

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        Connection connection = null;
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String pseudo = req.getParameter("pseudo");
        String password = req.getParameter("password");

        String id_Offre = req.getParameter("id_Offre");

        String money = req.getParameter("monnaie");     //Savoir si on a choisi la monnaie IG ou IRL à l'achat.
        System.out.println(pseudo+" / "+password+" / "+id_Offre+" / "+money);

        ShopManager.Doublet s = shopManager.doAchatDoublet(pseudo,id_Offre,money);
        System.out.println("s = "+s);
        System.out.println(gson.toJson(s));
        out.print(gson.toJson(s));
        //req.setAttribute("achat",gson);
        //this.getServletContext().getRequestDispatcher( "/ShopView.jsp" ).forward( req, resp );
        //out.print(shopManager.doAchat(pseudo,password,id_Offre,money));
        //Possibilité d'envoyer un JSON avec l(es/') élément(s) achetés (et ouais mgl).
        */

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
        //out.print(shopManager.doAchat(pseudo,password,id_Offre,money));


        //Possibilité d'envoyer un JSON avec l(es/') élément(s) achetés (et ouais mgl).

        ShopManager.Doublet s;
        String json;
        switch (request) {

            case 1 :    //Achat avec monnaieIG
                s = shopManager.doAchatDoublet(pseudo, id_Offre,"monnaieIG");
                json = gson.toJson(s);
                System.out.println(json);
                System.out.println("Achat IG terminé");
                out.print(json);
                break;
            case 2 :    //Achat avec monnaieIRL
                s = shopManager.doAchatDoublet(pseudo, id_Offre,"monnaieIRL");
                json = gson.toJson(s);
                System.out.println(json);
                System.out.println("Achat IRL terminé");
                out.print(json);
                break;
        }
        //req.setAttribute("achat",json);
        //this.getServletContext().getRequestDispatcher( "/ShopView.jsp" ).forward( req, resp );


    }
}
