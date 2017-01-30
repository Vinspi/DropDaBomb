package InventoryServlets;

import Manager.InventoryManager;
import View.InventoryView;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by vinspi on 29/01/17.
 */
public class InventoryViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();

        String pseudo = req.getParameter("pseudo");
        InventoryManager inventoryManager = new InventoryManager();
        InventoryView inventoryView = inventoryManager.createInventoryView(pseudo);

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(inventoryView));

    }


}
