package Manager;

import View.CardView;
import View.DeckView;
import View.InventoryItem;
import View.InventoryView;
import sun.awt.geom.AreaOp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by vinspi on 29/01/17.
 */
public class InventoryManager {
    //Class destinée à gérer un inventaire : générations des Decks pour l'affichage et modification de ceux-ci.


    private Connection connection = null;
    private LinkedList<InventoryItem> items;



    //Fonction permettant de changer une carte du Deck "id_deck" (entre 1 et 2). Pour cela, enlève une carte "id_carteDeck" du deck en question et insère la carte "id_carte".
    public int swapCardsIntoDeck(String id_deck, String id_carte, String id_carteDeck){

        String query = "UPDATE JoueurCarteDeck SET id_Carte = "+id_carte+" WHERE (id_Carte = "+id_carteDeck+" AND id_deck LIKE '"+id_deck+"');";

        if(Manager.getManager().sendRequestUpdate(query,connection)){
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.SWAP_SUCCESS;
        }else {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.SWAP_FAILED;
        }

    }


    //Fonction créant et retournant une view de l'inventaire du compte "pseudo".
    public InventoryView createInventoryView(String pseudo){

        LinkedList<CardView> playerCards;
        DeckView deck1;
        DeckView deck2;

        deck1 = getDeck(pseudo,1);
        deck2 = getDeck(pseudo, 2);
        playerCards = getPlayerCards(pseudo);

        return new InventoryView(playerCards,deck1,deck2);
    }

    //Fonction retournant une view du deck "num_deck" du compte "pseudo".
    private DeckView getDeck(String pseudo,int num_deck){
        DeckView deck1 = null;
        LinkedList<CardView> cards = new LinkedList<>();
        ResultSet resultSet = Manager.getManager().sendRequestQuery("SELECT Carte.* \n" +
                "FROM CompteJoueur\n" +
                "JOIN JoueurCarteDeck USING (pseudo)\n" +
                "JOIN Deck USING (id_Deck)\n" +
                "JOIN Carte USING (id_Carte)\n" +
                "WHERE (Pseudo LIKE \""+pseudo+"\" AND id_Deck LIKE \""+pseudo+""+num_deck+"\");",connection);
        //ResultSet resultSet = null;
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

            deck1 = new DeckView("imageDeck1","Deck "+num_deck,pseudo+"1",cards);
      //      deck1 = new DeckView(null,null,null,null);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(connection != null)
                    connection.close();

            }catch (SQLException e){
                /* ignore */
            }
        }

        return deck1;
    }

    //Fonction retournant une LinkedList des cartes possédées par sur le compte "pseudo".
    public LinkedList<CardView> getPlayerCards(String pseudo){

        LinkedList<CardView> cards = new LinkedList<>();
        ResultSet resultSet = Manager.getManager().sendRequestQuery("SELECT Carte.*, qteCarteDeck \n" +
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
                        resultSet.getString("coutCarte"),resultSet.getInt("qteCarteDeck")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null)
                    connection.close();
            }catch (SQLException e){
                /* ignore */
            }
        }
        return cards;
    }



}
