package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vinspi on 27/01/17.
 */
public class AccountManager {
    Connection connection = null;



    public int createAccount(String pseudo,String email,String password){
        int r = RequestStatus.CREATE_ACCOUNT_FAILED_PSEUDO;
        String query = "SELECT Pseudo, mailCompte FROM CompteJoueur WHERE (Pseudo LIKE \""+pseudo+"\" OR " +
                "mailCompte LIKE \""+email+"\");";
        String queryInsertion = "INSERT INTO CompteJoueur (Pseudo, mailCompte, mdpCompte, nom_guilde, id_Division) " +
                "VALUES (\""+pseudo+"\", \""+email+"\", \""+password+"\", NULL, NULL);";
        //Ajouter les decks toussa

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {

            if (!resultSet.next()) {
            /* on ajoute le compte */
                Manager.getManager().sendRequestUpdate(queryInsertion, connection);
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
