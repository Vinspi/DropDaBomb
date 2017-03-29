package Tests;

import Manager.AccountManager;
import Manager.Manager;
import Manager.RequestStatus;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

/**
 * Created by vinspi on 29/03/17.
 */
public class JUnit_test {


    @Test
    public void tests() {
        /* tests sur AccountManager.java */

        AccountManager AC = new AccountManager();

        int r = AC.createAccount("jean-michel","jean-michel@gmail.com","crapaud");

        assertEquals("should be success",RequestStatus.CREATE_ACCOUNT_SUCCES,r);

        r = AC.authentification("jean-michel", "crapaud");

        assertEquals("should be success",RequestStatus.AUTH_SUCCES, r);

        r =AC.authentification("jean-michou","crapaud");

        assertEquals("should be failed",RequestStatus.AUTH_FAILED, r);

        r =AC.authentification("jean-michel","crapau");

        assertEquals("should be failed",RequestStatus.AUTH_FAILED, r);

        String icone = AC.getPlayerIcon("jean-michel");

        assertEquals("should be icone0.png","icone0.png",icone);

        String carton = AC.getPlayerSkinCarton("jean-michel");

        assertEquals("should be carton1.png","carton1.png",carton);

        String map = AC.getPlayerSkinMap("jean-michel");

        assertEquals("should be map1.png","map1.png",map);

        String email = AC.getplayerEmail("jean-michel");

        assertEquals("should be jean-michel@gmail.com","jean-michel@gmail.com",email);

        AC.changerEmail("jean-michel","jean@gmail.com");

        email =AC.getplayerEmail("jean-michel");

        assertEquals("should be jean@gmail.com","jean@gmail.com",email);

        AC.changerSkinCarton("jean-michel","1");

        carton =AC.getPlayerSkinCarton("jean-michel");

        assertEquals("should be carton2.png","carton2.png",carton);

        AC.changerSkinMap("jean-michel","1");

        map =AC.getPlayerSkinMap("jean-michel");

        assertEquals("should be map2.png","map2.png",map);

        int monnaie = AC.getPlayerMoney("jean-michel");

        assertEquals("should be 0",0,monnaie);

        System.out.println(monnaie);

        AC.changeIcon("jean-michel","1");

        icone =AC.getPlayerIcon("jean-michel");

        assertEquals("should be superIcone.png","superIcone.png",icone);

        AC.changePassword("jean-michel","newpassword");

        r =AC.authentification("jean-michel","newpassword");

        assertEquals("should be success",RequestStatus.AUTH_SUCCES, r);

        r =AC.authentification("jean-michel","crapaud");

        assertEquals("should be fail",RequestStatus.AUTH_FAILED, r);

        /* fin de la session de test */


        /* remi insert code here (and brain) */


        /* suppression de jean-michel */

        Connection connection = null;


        String query6 = "DELETE FROM CompteJoueur WHERE Pseudo LIKE 'jean-michel'";

        String query5 = "DELETE FROM posséderSkinMap WHERE Pseudo LIKE 'jean-michel'";

        String query4 = "DELETE FROM posséderSkinCartonCarte WHERE Pseudo LIKE 'jean-michel'";

        String query3 = "DELETE FROM posséderIconeJoueur WHERE Pseudo LIKE 'jean-michel'";

        String query2 = "DELETE FROM Deck WHERE id_Deck LIKE 'jean-michel0' OR id_Deck LIKE 'jean-michel1' OR id_Deck LIKE 'jean-michel2'";

        String query = "DELETE FROM JoueurCarteDeck WHERE Pseudo LIKE 'jean-michel'";

        Manager.getManager().sendRequestUpdate(query, connection);
        Manager.getManager().sendRequestUpdate(query3, connection);
        Manager.getManager().sendRequestUpdate(query4, connection);
        Manager.getManager().sendRequestUpdate(query5, connection);
        Manager.getManager().sendRequestUpdate(query6, connection);
        Manager.getManager().sendRequestUpdate(query2, connection);


    }


}
