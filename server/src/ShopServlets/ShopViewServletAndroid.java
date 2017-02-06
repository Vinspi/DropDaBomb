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
import com.google.gson.Gson;

/**
 * Created by vinspi on 27/01/17.
 */
public class ShopViewServletAndroid extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();

        Connection connection = null;
        PrintWriter out = resp.getWriter();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        //Récupérer le pseudo pour n'afficher que les SKinMap/SkinCarton qu'il ne possède pas.
        String pseudo = req.getParameter("pseudo");

        ShopView shopView = new ShopView();
        shopView.getAllOffers(pseudo); //Mets tout les Offres dans les attributs de shopView (toutes, même celles que le Joueur possède déjà /!\)

        out.print(gson.toJson(shopView));


    }
}
