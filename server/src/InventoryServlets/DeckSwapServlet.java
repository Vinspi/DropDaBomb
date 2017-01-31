package InventoryServlets;

import Manager.InventoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vinspi on 31/01/17.
 */
public class DeckSwapServlet extends HttpServlet {
    private InventoryManager inventoryManager = new InventoryManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String id_deck = req.getParameter("id_deck");
        String id_carte = req.getParameter("id_carte");
        String id_carteDeck = req.getParameter("id_carteDeck");

        out.print(inventoryManager.swapCardsIntoDeck(id_deck,id_carte,id_carteDeck));


    }
}
