package Manager;

import View.CardView;
import View.DeckView;
import View.InventoryItem;
import View.InventoryView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by vinspi on 29/01/17.
 */
public class InventoryManager {

    private Connection connection = null;
    private LinkedList<InventoryItem> items;

    public InventoryView createInventoryView(String pseudo){

        LinkedList<CardView> playerCards = new LinkedList<>();
        DeckView deck1;
        DeckView deck2;

        ResultSet resultSet = Manager.getManager().sendRequestQuery("SELECT Carte.*\n" +
                "FROM CompteJoueur\n" +
                "JOIN JoueurCarteDeck USING ("+pseudo+")\n" +
                "JOIN Deck USING (id_Deck)\n" +
                "JOIN Carte USING (id_Carte)\n" +
                "WHERE (Pseudo = "+pseudo+" AND id_Deck LIKE "+pseudo+"1);",connection);

        try {
            while (resultSet.next()) {

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    private DeckView getDeck1(String pseudo){
        DeckView deck1 = null;
        LinkedList<CardView> cards = new LinkedList<>();
        ResultSet resultSet = Manager.getManager().sendRequestQuery("SELECT Carte.* \n" +
                "FROM CompteJoueur\n" +
                "JOIN JoueurCarteDeck USING ("+pseudo+")\n" +
                "JOIN Deck USING (id_Deck)\n" +
                "JOIN Carte USING (id_Carte)\n" +
                "WHERE (Pseudo = "+pseudo+" AND id_Deck LIKE "+pseudo+"1);",connection);
        try {
            while (resultSet.next()) {
                cards.add(new CardView(resultSet.getString("imageCarte"),
                        resultSet.getString("descriptionCarte"),
                        resultSet.getInt("id_carte"),
                        resultSet.getString("nomCarte"),
                        resultSet.getString("rareteCarte"),
                        resultSet.getString("typeCarte"),
                        resultSet.getString("coutCarte")));
            }
            deck1 = new DeckView("imageDeck1","Deck 1",pseudo+"1",cards);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException e){
                /* ignore */
            }
        }
        return deck1;
    }

}
