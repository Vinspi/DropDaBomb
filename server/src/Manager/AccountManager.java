package Manager;

import javax.servlet.http.HttpSession;
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

        String query = "SELECT Pseudo, mdpCompte FROM CompteJoueur WHERE (Pseudo LIKE '"+pseudo+"' AND mdpCompte LIKE '"+password+"')";
        int status = RequestStatus.AUTH_FAILED;

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query, connection);

        try {
            if (resultSet.next()) {
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

    public String getPlayerIcon(String pseudo){


        String query = "SELECT imageIcone \n" +
                "FROM CompteJoueur\n" +
                "JOIN IconeJoueur USING(id_IconeJoueur)\n" +
                "WHERE (Pseudo LIKE 'lucaslefou')";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        String retour = "";
        try{
            if(resultSet.next()){
                retour = resultSet.getString("imageIcone");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (connection != null)
                    connection.close();

            }catch (SQLException e){
                /* ignore */
            }
        }

        return retour;
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

        queryInsertion.add("INSERT INTO posséderIconeJoueur(Pseudo,id_IconeJoueur) VALUES (\""+pseudo+"\",\""+1+"\")");



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

    public void changerMotDePasse(String pseudo, String newPassword){
        String query = "UPDATE CompteJoueur SET mdpCompte ='"+newPassword+"' WHERE Pseudo LIKE '"+pseudo+"';";
        Manager.getManager().sendRequestUpdate(query,connection);
    }

    public void changerEmail(String pseudo, String newEmail){
        String query = "UPDATE CompteJoueur SET mailCompte ='"+newEmail+"' WHERE Pseudo LIKE '"+pseudo+"';";
        Manager.getManager().sendRequestUpdate(query,connection);
    }

    public void changerIcone(String pseudo, String newIcone){
        String query = "UPDATE CompteJoueur SET id_IconeJoueur ='"+newIcone+"' WHERE Pseudo LIKE '"+pseudo+"';";
        Manager.getManager().sendRequestUpdate(query,connection);
    }

    public void changerSkinMap(String pseudo, String newMap){
        String query = "UPDATE CompteJoueur SET id_SkinMap ='"+newMap+"' WHERE Pseudo LIKE '"+pseudo+"';";
        Manager.getManager().sendRequestUpdate(query,connection);
    }

    public void changerSkinCarton(String pseudo, String newCarton){
        String query = "UPDATE CompteJoueur SET id_SkinCartonCarte ='"+newCarton+"' WHERE Pseudo LIKE '"+pseudo+"';";
        Manager.getManager().sendRequestUpdate(query,connection);
    }

    //Pour l'instant, c'est gratuit !
    public void acheterMonnaieIRL(String pseudo, String quantite){
        String query = "UPDATE CompteJoueur SET monnaieIRL=monnaieIRL+"+quantite+" WHERE Pseudo LIKE '"+pseudo+"';";
        Manager.getManager().sendRequestUpdate(query,connection);

    }




}
