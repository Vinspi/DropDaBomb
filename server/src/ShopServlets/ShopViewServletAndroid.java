package ShopServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import Manager.*;
import View.ShopView;

/**
 * Created by vinspi on 27/01/17.
 */
public class ShopViewServletAndroid extends HttpServlet {

    ShopManager shopManager;
    @Override
    public void init() throws ServletException {
        super.init();
        /* chargement du driver mysql */
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        shopManager = new ShopManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PrintWriter out = resp.getWriter();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        ShopView shopView = new ShopView();
        shopView.getAllOffers(); //Mets tout les Offres dans les attributs de shopView.

        //Faire un JSON et l'envoyer.


    }
}
