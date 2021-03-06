package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vinspi on 27/01/17.
 */
public class AccountManager {
    //Class destinée à gérer et administrer un compteJoueur : connexion, changement d'email, de skins, création de compte ...


    Connection connection = null;


    public class Doublet{
        public int id;
        public String image;

        public Doublet(int id, String image) {
            this.id = id;
            this.image = image;
        }
    }


    //Fonction d'authentification : renvoie AUTH_FAILED si le couple {pseudo,password} ne correspond à aucun compte, sinon, si le compte est administrateur, renvoie AUTH_ADMIN, sinon, renvoie AUTH_SUCCES;
    public int authentification(String pseudo, String password) {

        String query = "SELECT Pseudo, mdpCompte, estAdmin FROM CompteJoueur WHERE (Pseudo LIKE '"+pseudo+"' AND mdpCompte LIKE '"+password+"')";
        int status = RequestStatus.AUTH_FAILED;

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query, connection);

        try {
            if (resultSet.next()) {
                if(resultSet.getBoolean("estAdmin")){
                    status = RequestStatus.AUTH_ADMIN;
                }
                else status = RequestStatus.AUTH_SUCCES;
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



    //Pour toutes les prochaines fonctions, le pseudo sera passé en paramètre par la servlet de contrôle, ce qui garantie que le pseudo se trouve dans la variable de session, et
    //par conséquent que celui existe (verifié par la servlet de connexion au moment de l'authentification).


    //Fonction retournant l'email du compte "pseudo".
    public String getplayerEmail(String pseudo){
        String query = "SELECT mailCompte \n" +
                "FROM CompteJoueur\n" +
                "WHERE (Pseudo LIKE \'"+pseudo+"\')";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        String retour = "";
        try{
            if(resultSet.next()){
                retour = resultSet.getString("mailCompte");
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
    //Fonction retournant le SkinMap utilisé par compte "pseudo".
    public String getPlayerSkinMap(String pseudo){


        String query = "SELECT imageMiniatureMap \n" +
                "FROM CompteJoueur\n" +
                "JOIN SkinMap USING(id_SkinMap)\n" +
                "WHERE (Pseudo LIKE \'"+pseudo+"\')";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        String retour = "";
        try{
            if(resultSet.next()){
                retour = resultSet.getString("imageMiniatureMap");
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

    //Fonction retournant le SkinCarton utilisé par compte "pseudo".
    public String getPlayerSkinCarton(String pseudo){


        String query = "SELECT imageMiniatureCarton \n" +
                "FROM CompteJoueur\n" +
                "JOIN SkinCartonCarte USING(id_SkinCartonCarte)\n" +
                "WHERE (Pseudo LIKE \'"+pseudo+"\')";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        String retour = "";
        try{
            if(resultSet.next()){
                retour = resultSet.getString("imageMiniatureCarton");
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

    //Fonction retournant l'icone utilisée par compte "pseudo".
    public String getPlayerIcon(String pseudo){


        String query = "SELECT imageIcone \n" +
                "FROM CompteJoueur\n" +
                "JOIN IconeJoueur USING(id_IconeJoueur)\n" +
                "WHERE (Pseudo LIKE \'"+pseudo+"\')";

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

    //Fonction de création de compte dans la base de données : vérifie si le pseudo ou l'email ne sont pas utilisés, auquel cas fait les insertions nécessaires (CompteJoueur, les 3 Decks, et les Cartes et compléments (Skins/Icones) de base).
    public int createAccount(String pseudo,String email,String password){


        Pattern pattern = Pattern.compile(RequestStatus.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);


        if(password.length() < RequestStatus.MIN_SIZE_PASSWORD) return RequestStatus.ERR_MDP;

        if(!matcher.matches()){
            return RequestStatus.CREATE_ACCOUNT_FAILED_EMAIL;
        }

        int r = RequestStatus.CREATE_ACCOUNT_FAILED_PSEUDO;
        String query = "SELECT Pseudo, mailCompte FROM CompteJoueur WHERE (Pseudo LIKE \""+pseudo+"\" OR " +
                "mailCompte LIKE \""+email+"\");";


        LinkedList<String> queryInsertion = new LinkedList<>();

        queryInsertion.add("INSERT INTO CompteJoueur (Pseudo, mailCompte, mdpCompte, nomGuilde, id_Division) " +
                    "VALUES (\""+pseudo+"\", \""+email+"\", \""+password+"\", NULL, NULL);");
        queryInsertion.add("INSERT INTO Deck(id_Deck,estActif) VALUES ('"+pseudo+"0',0);");
        queryInsertion.add("INSERT INTO Deck(id_Deck,estActif) VALUES ('"+pseudo+"1',1);");
        queryInsertion.add("INSERT INTO Deck(id_Deck,estActif) VALUES ('"+pseudo+"2',0);");

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

        queryInsertion.add("INSERT INTO posséderIconeJoueur(Pseudo,id_IconeJoueur) VALUES (\""+pseudo+"\",\""+0+"\")");

        queryInsertion.add("INSERT INTO posséderSkinMap(Pseudo,id_SkinMap) VALUES (\""+pseudo+"\",\""+0+"\")");

        queryInsertion.add("INSERT INTO posséderSkinCartonCarte(Pseudo,id_SkinCartonCarte) VALUES (\""+pseudo+"\",\""+0+"\")");



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
            /* si le pseudo n'a pas match, c'est forcément l'email (ou les deux au quel cas on a deja traité l'erreur */
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


    //Fonction de modification de l'email du compte "pseudo".
    public int changerEmail(String pseudo, String newEmail){

        Pattern pattern = Pattern.compile(RequestStatus.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(newEmail);

        if(!matcher.matches()){
            return RequestStatus.UPDATE_EMAIL_FAILED;
        }

        String query = "UPDATE CompteJoueur SET mailCompte ='"+newEmail+"' WHERE Pseudo LIKE '"+pseudo+"';";
        String queryVerif = "SELECT mailCompte FROM CompteJoueur WHERE (mailCompte LIKE \""+newEmail+"\");";
        ResultSet verif = Manager.getManager().sendRequestQuery(queryVerif,connection);
        try {
            if(verif.next()){   //Si il existe déjà un compte associé à l'email choisi :
                if(connection != null){
                    try {
                        connection.close();
                    }catch (SQLException e){
                        //Do nothing.
                    }
                }
                return RequestStatus.UPDATE_EMAIL_FAILED;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

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


    //Fonction de modification du SkinMap du compte "pseudo".
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


    //Fonction de modification du SkinCarton du compte "pseudo".
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



    //Fonction de modification de l'icone du compte "pseudo".
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

    //Fonction de modification du mot de passe du compte "pseudo".
    public int changePassword(String pseudo, String newPassword){



        String query = "UPDATE CompteJoueur SET mdpCompte=\'"+newPassword+"\' WHERE (Pseudo LIKE \'"+pseudo+"\');";
        if(newPassword.length() >= RequestStatus.MIN_SIZE_PASSWORD) {
            if (Manager.getManager().sendRequestUpdate(query, connection)) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return RequestStatus.UPDATE_MDP_SUCCESS;
            } else {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return RequestStatus.UPDATE_MDP_FAILED;
            }
        }
        return RequestStatus.UPDATE_MDP_FAILED;
    }

    //Fonction permettant d'ajouter de la monnaieIRL au compte "pseudo". Pour l'instant, c'est gratuit.
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

    //Fonction retournant une ArrayList de l'ensemble des Icones possédées par le compte "pseudo", sous la forme {id_Icone,Image_Icone}
    public ArrayList<Doublet> getPlayerIcons(String pseudo){

        ArrayList<Doublet> array = new ArrayList<Doublet>();

        String queryIcons = "SELECT id_IconeJoueur, imageIcone FROM IconeJoueur JOIN posséderIconeJoueur USING (id_IconeJoueur) WHERE (Pseudo LIKE \'"+pseudo+"\');";
        ResultSet setIcons = Manager.getManager().sendRequestQuery(queryIcons,connection);
        try {
            while(setIcons.next()){
                array.add(new Doublet(setIcons.getInt("id_IconeJoueur"),setIcons.getString("imageIcone")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(connection != null) connection.close();
            }catch (SQLException e){
                /* do nothing */
            }
        }

        return array;
    }

    //Fonction retournant une ArrayList de l'ensemble des SkinMap possédés par le compte "pseudo", sous la forme {id_SkinMap,Image_SkinMap}
    public ArrayList<Doublet> getPlayerMaps(String pseudo){

        ArrayList<Doublet> array = new ArrayList<Doublet>();

        String query = "SELECT id_SkinMap, imageMiniatureMap FROM SkinMap JOIN posséderSkinMap USING (id_SkinMap) WHERE (Pseudo LIKE \'"+pseudo+"\');";
        ResultSet setMap = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while(setMap.next()){
                array.add(new Doublet(setMap.getInt("id_SkinMap"),setMap.getString("imageMiniatureMap")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(connection != null) connection.close();
            }catch (SQLException e){
                /* do nothing */
            }
        }

        return array;
    }

    //Fonction retournant une ArrayList de l'ensemble des SkinCarton possédés par le compte "pseudo", sous la forme {id_SkinCarton,Image_SkinCarton}
    public ArrayList<Doublet> getPlayerCartons(String pseudo){

        ArrayList<Doublet> array = new ArrayList<Doublet>();

        String query = "SELECT id_SkinCartonCarte, imageMiniatureCarton FROM SkinCartonCarte JOIN posséderSkinCartonCarte USING (id_SkinCartonCarte) WHERE (Pseudo LIKE \'"+pseudo+"\');";
        ResultSet setCarton = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while(setCarton.next()){
                array.add(new Doublet(setCarton.getInt("id_SkinCartonCarte"),setCarton.getString("imageMiniatureCarton")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(connection != null) connection.close();
            }catch (SQLException e){
                /* do nothing */
            }
        }

        return array;
    }

    //Fonction retournant la quantité de monnaieIG que possède le compte "pseudo".
    public int getPlayerMoneyIG(String pseudo){

        String queryMoney = "SELECT monnaieIG" +
                " FROM CompteJoueur " +
                "WHERE (Pseudo LIKE '"+pseudo+"');";

        ResultSet monnaie = Manager.getManager().sendRequestQuery(queryMoney,connection);
        int money = 0;
        try {
            if(monnaie.next()) {
                money = monnaie.getInt("monnaieIG");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(connection != null) connection.close();
            } catch (SQLException e){

            }
        }

        return money;
    }

    //Fonction retournant la quantité de monnaieIRL que possède le compte "pseudo".
    public int getPlayerMoneyIRL(String pseudo){

        String queryMoney = "SELECT monnaieIRL" +
                " FROM CompteJoueur " +
                "WHERE (Pseudo LIKE '"+pseudo+"');";

        ResultSet monnaie = Manager.getManager().sendRequestQuery(queryMoney,connection);
        int money = 0;
        try {
            if(monnaie.next()) {
                money = monnaie.getInt("monnaieIRL");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(connection != null) connection.close();
            } catch (SQLException e){

            }
        }

        return money;
    }

    public void supprimerCompte(String pseudo){

        String querySupprPrc = "DELETE FROM CompteJoueur WHERE Pseudo LIKE '"+pseudo+"';";
        String querySupprAnx = "DELETE FROM Deck WHERE (id_Deck LIKE '"+pseudo+"0' OR id_Deck LIKE '"+pseudo+"1' OR id_Deck LIKE '"+pseudo+"2');";

        Manager.getManager().sendRequestUpdate(querySupprPrc,connection);
        Manager.getManager().sendRequestUpdate(querySupprAnx,connection);
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }


}
