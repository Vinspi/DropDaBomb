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

        ShopView shopView = new ShopView();
        shopView.getAllOffers(); //Mets tout les Offres dans les attributs de shopView.

        out.print(gson.toJson(shopView));


    }
}
