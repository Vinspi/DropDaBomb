package ShopServlets;

import Manager.ShopManager;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        ShopManager.Doublet s = shopManager.doAchatDoublet(pseudo,password,id_Offre,money);
        System.out.println("s = "+s);
        System.out.println(gson.toJson(s));
        out.print(gson.toJson(s));
        //req.setAttribute("achat",gson);
        //this.getServletContext().getRequestDispatcher( "/ShopView.jsp" ).forward( req, resp );
        //out.print(shopManager.doAchat(pseudo,password,id_Offre,money));
        //Possibilité d'envoyer un JSON avec l(es/') élément(s) achetés (et ouais mgl).


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{


        Connection connection = null;
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();

        String pseudo = req.getParameter("pseudo");
        String password = req.getParameter("password");
        String id_Offre = (req.getParameter("id_Offre"));
        String money = req.getParameter("monnaie");     //Savoir si on a choisi la monnaie IG ou IRL à l'achat.
        System.out.println(pseudo+" / "+password+" / "+id_Offre+" / "+money);
        System.out.println(req.getRequestURL());
        //out.print(shopManager.doAchat(pseudo,password,id_Offre,money));
        //Possibilité d'envoyer un JSON avec l(es/') élément(s) achetés (et ouais mgl).
        ShopManager.Doublet s = shopManager.doAchatDoublet(pseudo,password,id_Offre,money);
        String json = gson.toJson(s);
        out.print(json);
        //req.setAttribute("achat",json);
        //this.getServletContext().getRequestDispatcher( "/ShopView.jsp" ).forward( req, resp );


    }
}
