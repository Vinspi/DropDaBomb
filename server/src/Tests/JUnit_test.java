package Tests;

import Manager.AccountManager;
import Manager.ShopManager;
import Manager.Manager;
import Manager.InventoryManager;
import Manager.RequestStatus;

import View.CardView;
import org.junit.Test;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by vinspi on 29/03/17.
 */


public class JUnit_test {


    public class DoubletDeux{
        public int id;
        public String image;

        public DoubletDeux(int id, String image) {
            this.id = id;
            this.image = image;
        }
    }


    @Test
    public void tests_accountManager() {
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

        int monnaie = AC.getPlayerMoneyIG("jean-michel");

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

    @Test
    public void tests_shop(){
        /*Tests sur le shop */



        Connection connection = null;
        AccountManager am = new AccountManager();
        ShopManager sm = new ShopManager();
        InventoryManager im = new InventoryManager();

        am.createAccount("jean-michel","jean-michel@gmail.com","crapaud");

        ArrayList<AccountManager.Doublet> tabD = new ArrayList<AccountManager.Doublet>();

        ArrayList<AccountManager.Doublet> testIcons;
        LinkedList<CardView> testDeck;      //Deck0 avant achat.
        LinkedList<CardView> testDeckAA;    //Deck0 après achat.

        testDeck = im.getPlayerCards("jean-michel");

        String queryMonnaie = "UPDATE CompteJoueur set monnaieIG = 500 WHERE Pseudo LIKE 'jean-michel'";
        Manager.getManager().sendRequestUpdate(queryMonnaie,connection);

        //Achat de l'Offre 7 contenant l'icone "superIcone"
        sm.doAchatDoublet("jean-michel","7","monnaieIG");

        testIcons = am.getPlayerIcons("jean-michel");
        assertEquals("should be superIcone.png","superIcone.png",testIcons.get(1).image);

        int r = am.getPlayerMoneyIG("jean-michel");
        assertEquals("should be 499",499,r);

        //Achat de l'Offre 8 contenant le Pack 3, contenant 5 cartes piochées dans l'ensemble 0 (Cartes {5,6,7}).
        ShopManager.Doublet s = sm.doAchatDoublet("jean-michel","8","monnaieIG");

        testDeckAA = im.getPlayerCards("jean-michel");
        for(int j = 0; j < testDeckAA.size(); j++){
            for(ShopManager.Triplet t : (ArrayList<ShopManager.Triplet>) s.value){
                if(testDeckAA.get(j).getId_carte() == t.id){
                    assertEquals("qteCarte non conforme",testDeck.get(j).getQte()+t.value,testDeckAA.get(j).getQte());
                    break;
                }
            }
        }




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
