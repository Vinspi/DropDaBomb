package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by vinspi on 27/01/17.
 */
public class AccountManager {
    Connection connection = null;



    public int authentification(String pseudo, String password) {

        String query = "SELECT Pseudo, mdpCompte FROM CompteJoueur";
        int status = RequestStatus.AUTH_FAILED;

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query, connection);

        try {
            if (!resultSet.next()) {
                status = RequestStatus.AUTH_SUCCES;
            } else status = RequestStatus.AUTH_FAILED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                /* ignore */
            }
        }

        return status;
    }


    public int createAccount(String pseudo,String email,String password){

        int r = RequestStatus.CREATE_ACCOUNT_FAILED_PSEUDO;
        String query = "SELECT Pseudo, mailCompte FROM CompteJoueur WHERE (Pseudo LIKE \""+pseudo+"\" OR " +
                "mailCompte LIKE \""+email+"\");";


        LinkedList<String> queryInsertion = new LinkedList<>();

        queryInsertion.add("INSERT INTO CompteJoueur (Pseudo, mailCompte, mdpCompte, nom_guilde, id_Division) " +
                    "VALUES (\""+pseudo+"\", \""+email+"\", \""+password+"\", NULL, NULL);");
        queryInsertion.add("INSERT INTO Deck(id_Deck) VALUES ('"+pseudo+"0');");
        queryInsertion.add("INSERT INTO Deck(id_Deck) VALUES ('"+pseudo+"1');");
        queryInsertion.add("INSERT INTO Deck(id_Deck) VALUES ('"+pseudo+"2');");

        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"0', '"+pseudo+"', 0);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"0', '"+pseudo+"', 1);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"0', '"+pseudo+"', 2);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"0', '"+pseudo+"', 3);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"0', '"+pseudo+"', 4);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"0', '"+pseudo+"', 5);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"0', '"+pseudo+"', 6);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"0', '"+pseudo+"', 7);");


        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"1', '"+pseudo+"', 0);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"1', '"+pseudo+"', 1);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"1', '"+pseudo+"', 2);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"1', '"+pseudo+"', 3);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"1', '"+pseudo+"', 4);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"1', '"+pseudo+"', 5);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"1', '"+pseudo+"', 6);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"1', '"+pseudo+"', 7);");


        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"2', '"+pseudo+"', 0);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"2', '"+pseudo+"', 1);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"2', '"+pseudo+"', 2);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"2', '"+pseudo+"', 3);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"2', '"+pseudo+"', 4);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"2', '"+pseudo+"', 5);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"2', '"+pseudo+"', 6);");
        queryInsertion.add("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, '"+pseudo+"2', '"+pseudo+"', 7);");



        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {

            if (!resultSet.next()) {
            /* on ajoute le compte */

                Manager.getManager().sendMultipleRequestUpdate(queryInsertion, connection);

                r = RequestStatus.CREATE_ACCOUNT_SUCCES;
            } else {
            /* sinon on test si c'est le mail qui a match ou le pseudo */
                if (resultSet.getString("Pseudo").equalsIgnoreCase(pseudo)) {
                    r = RequestStatus.CREATE_ACCOUNT_FAILED_PSEUDO;
                }
            /* si le pseudo a pas match c'est forcément l'email (ou les deux au quel cas on a deja traité l'erreur */
                else {
                    r = RequestStatus.CREATE_ACCOUNT_FAILED_EMAIL;
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    /* ignore */
                }
            }
        }
        return r;
    }





}
