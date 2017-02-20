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



    public int changerEmail(String pseudo, String newEmail){
        String query = "UPDATE CompteJoueur SET mailCompte ='"+newEmail+"' WHERE Pseudo LIKE '"+pseudo+"';";

        if(Manager.getManager().sendRequestUpdate(query,connection)){
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.UPDATE_EMAIL_SUCCESS;
        }
        else{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.UPDATE_EMAIL_FAILED;
        }

    }



    public int changerSkinMap(String pseudo, String newMap){
        String query = "UPDATE CompteJoueur SET id_SkinMap ='"+newMap+"' WHERE Pseudo LIKE '"+pseudo+"';";
        if(Manager.getManager().sendRequestUpdate(query,connection)){
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.UPDATE_MAP_SUCCESS;
        }
        else{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.UPDATE_MAP_FAILED;
        }
    }

    public int changerSkinCarton(String pseudo, String newCarton){
        String query = "UPDATE CompteJoueur SET id_SkinCartonCarte ='"+newCarton+"' WHERE Pseudo LIKE '"+pseudo+"';";
        if(Manager.getManager().sendRequestUpdate(query,connection)){
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.UPDATE_CARTON_SUCCESS;
        }
        else{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.UPDATE_CARTON_FAILED;
        }
    }

    //Pour l'instant, c'est gratuit !
    public int acheterMonnaieIRL(String pseudo, String quantite){
        String query = "UPDATE CompteJoueur SET monnaieIRL=monnaieIRL+"+quantite+" WHERE Pseudo LIKE '"+pseudo+"';";
        if(Manager.getManager().sendRequestUpdate(query,connection)){
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.ACHAT_MONNAIE_SUCCESS;
        }
        else{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.ACHAT_MONNAIE_FAILED;
        }

    }


    public int changeIcon(String pseudo, String id_newIcon){

        String query = "UPDATE CompteJoueur SET id_IconeJoueur=\'"+id_newIcon+"\' WHERE (Pseudo LIKE \'"+pseudo+"\')";

        if(Manager.getManager().sendRequestUpdate(query,connection)){
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.UPDATE_ICON_SUCCESS;
        }
        else{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.UPDATE_ICON_FAILED;
        }
    }

    public int changePassword(String pseudo, String newPassword){

        /* le pseudo sera passé en param par la servlet de controle ce qui garanti que le pseudo se trouve
            dans la variable de session et par conséquent que celui existe (verifié par la servlet de connexion)
         */

        String query = "UPDATE CompteJoueur SET mdpCompte=\'"+newPassword+"\' WHERE (Pseudo LIKE \'"+pseudo+"\')";

        if(Manager.getManager().sendRequestUpdate(query,connection)){
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.UPDATE_MDP_SUCCESS;
        }
        else{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return RequestStatus.UPDATE_MDP_FAILED;
        }


    }

}
