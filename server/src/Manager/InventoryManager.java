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

        deck1 = getDeck(pseudo,1);
        deck2 = getDeck(pseudo, 2);
        playerCards = getPlayerCards(pseudo);

        return new InventoryView(playerCards,deck1,deck2);
    }

    private DeckView getDeck(String pseudo,int num_deck){
        DeckView deck1 = null;
        LinkedList<CardView> cards = new LinkedList<>();
        /*ResultSet resultSet = Manager.getManager().sendRequestQuery("SELECT Carte.* \n" +
                "FROM CompteJoueur\n" +
                "JOIN JoueurCarteDeck USING (pseudo)\n" +
                "JOIN Deck USING (id_Deck)\n" +
                "JOIN Carte USING (id_Carte)\n" +
                "WHERE (Pseudo LIKE \""+pseudo+"\" AND id_Deck LIKE \""+pseudo+""+num_deck+"\");",connection);*/
        ResultSet resultSet = null;
        try {
            System.out.println("coucou1");

            while (resultSet.next()) {
                System.out.println("coucou2");
                cards.add(new CardView(resultSet.getString("imageCarte"),
                        resultSet.getString("descriptionCarte"),
                        resultSet.getInt("id_carte"),
                        resultSet.getString("nomCarte"),
                        resultSet.getString("rareteCarte"),
                        resultSet.getString("typeCarte"),
                        resultSet.getString("coutCarte")));
            }
            deck1 = new DeckView("imageDeck1","Deck "+num_deck,pseudo+"1",cards);
      //      deck1 = new DeckView(null,null,null,null);

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

    private LinkedList<CardView> getPlayerCards(String pseudo){

        LinkedList<CardView> cards = new LinkedList<>();
        ResultSet resultSet = Manager.getManager().sendRequestQuery("SELECT Carte.* \n" +
                "FROM CompteJoueur\n" +
                "JOIN JoueurCarteDeck USING (pseudo)\n" +
                "JOIN Deck USING (id_Deck)\n" +
                "JOIN Carte USING (id_Carte)\n" +
                "WHERE (Pseudo LIKE \""+pseudo+"\" AND id_Deck LIKE \""+pseudo+"0\");",connection);
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
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException e){
                /* ignore */
            }
        }
        return cards;
    }



}
