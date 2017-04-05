package ShopServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import Manager.*;
import View.ShopView;
import com.google.gson.Gson;

/**
 * Created by vinspi on 27/01/17.
 */
public class ShopViewServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Connection connection = null;
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();


        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        //Récupérer le pseudo pour n'afficher que les SKinMap/SkinCarton/Icones qu'il ne possède pas.
        String pseudo = (String) session.getAttribute("pseudo");
        int request = Integer.parseInt(req.getParameter("idRequest"));
        System.out.println("doPost");

        switch(request){
            case 0: //Génère et renvoie la view du Shop
                ShopView shopView = new ShopView(pseudo);
                String view = new Gson().toJson(shopView);
                out.print(view);
                break;





        }





    }
}
