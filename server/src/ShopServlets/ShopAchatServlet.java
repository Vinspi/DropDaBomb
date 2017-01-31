package ShopServlets;

import Manager.ShopManager;

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


    ShopManager shopManager;
    //Pour tester :
    //localhost:8080/ShopAchat?pseudo=moktar&password=yolo&id_Offre=0&monnaie=monnaieIG

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null;
        PrintWriter out = resp.getWriter();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String pseudo = req.getParameter("pseudo");
        String password = req.getParameter("password");
        int id_Offre = Integer.parseInt(req.getParameter("id_Offre"));
        String money = req.getParameter("monnaie");     //Savoir si on a choisi la monnaie IG ou IRL à l'achat.

        out.print(shopManager.doAchat(pseudo,password,id_Offre,money));


    }
}
