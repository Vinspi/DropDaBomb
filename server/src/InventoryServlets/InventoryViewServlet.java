package InventoryServlets;

import Manager.InventoryManager;
import View.InventoryView;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by vinspi on 29/01/17.
 */
public class InventoryViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        String pseudo = req.getParameter("pseudo");

        HttpSession session = req.getSession();

        //Vérifie si le client est connecté, auquel cas l'envoie sur son interface d'inventaire, sinon le renvoie sur la page de connexion.
        if(session.getAttribute("pseudo") == null)
            this.getServletContext().getRequestDispatcher("/log.jsp").forward(req,resp);
        else this.getServletContext().getRequestDispatcher("/compte.jsp").forward(req,resp);



    }


}
