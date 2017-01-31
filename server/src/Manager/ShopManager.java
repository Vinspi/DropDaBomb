package Manager;


import View.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import sun.awt.image.ImageWatched;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/*
 * Created by vinspi on 27/01/17.
 */
public class ShopManager {

    Connection connection = null;
    private List<PackView> listPackView = new LinkedList<>();
    private List<BoostView> listBoostView = new LinkedList<>();
    private List<SkinMapView> listSkinMapView = new LinkedList<>();
    private List<SkinCartonView> listSkinCartonView = new LinkedList<>();

    public void addPackView(PackView packView){
        listPackView.add(packView);
    }
    public void addBoostView(BoostView boostView){
        listBoostView.add(boostView);
    }
    public void addSkinMapView(SkinMapView skinMapView){
        listSkinMapView.add(skinMapView);
    }
    public void addSkinCartonView(SkinCartonView skinCartonView){ listSkinCartonView.add(skinCartonView); }

    public List<PackView> getListPackView() {
        return listPackView;
    }
    public void setListPackView(List<PackView> listPackView) {
        this.listPackView = listPackView;
    }

    public List<BoostView> getListBoostView() {
        return listBoostView;
    }
    public void setListBoostView(List<BoostView> listBoostView) {
        this.listBoostView = listBoostView;
    }

    public List<SkinMapView> getListSkinMapView() {
        return listSkinMapView;
    }
    public void setListSkinMapView(List<SkinMapView> listSkinMapView) {
        this.listSkinMapView = listSkinMapView;
    }

    public List<SkinCartonView> getListSkinCartonView() {
        return listSkinCartonView;
    }
    public void setListSkinCartonView(List<SkinCartonView> listSkinCartonView) {
        this.listSkinCartonView = listSkinCartonView;
    }

    public LinkedList<PackView> getAllPackOffers(){

        LinkedList<PackView> listItem = new LinkedList<>();



        String query = "SELECT prixMonnaieIG, prixMonnaieIRL, id_Pack, descriptionPack, imageMiniaturePack FROM Offre" +
                " JOIN Pack USING(id_pack);";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){

                listItem.add((new PackView(resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_Pack"),resultSet.getString("imageMiniaturePack"),resultSet.getString("descriptionPack"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null)
                    connection.close();
            }catch (SQLException e){

            }
        }

        return listItem;
    }
    public LinkedList<BoostView> getAllBoostOffers(){

        LinkedList<BoostView> listItem = new LinkedList<>();


        String query = "SELECT prixMonnaieIG, prixMonnaieIRL, id_Boost, imageMiniatureBoost, descriptionBoost FROM Offre " +

                "JOIN OffreBoost USING (id_Offre)" +
                " JOIN Boost USING(id_Boost);";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){

                listItem.add((new BoostView(resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_Boost"),resultSet.getString("imageMiniatureBoost"),resultSet.getString("descriptionBoost"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null)
                    connection.close();
            }catch (SQLException e){

            }
        }

        return listItem;
    }
    public LinkedList<SkinCartonView> getAllCartonOffers(){

        LinkedList<SkinCartonView> listItem = new LinkedList<>();

        String query =  "SELECT prixMonnaieIRL, prixMonnaieIG, id_SkinCartonCarte, imageMiniatureCarton, descriptionCarton FROM Offre JOIN OffreCartonCarte USING (id_Offre)" +
                " JOIN SkinCartonCarte USING (id_SkinCartonCarte);";
        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                listItem.add((new SkinCartonView(resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_SkinCartonCarte"),resultSet.getString("imageMiniatureCarton"),resultSet.getString("descriptionCarton"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null)
                    connection.close();
            }catch (SQLException e){

            }
        }
        return listItem;
    }
    public LinkedList<SkinMapView> getAllMapOffers(){

        LinkedList<SkinMapView> listItem = new LinkedList<>();

        String query =  "SELECT prixMonnaieIG, prixMonnaieIRL, id_SkinMap, imageMiniatureMap, descriptionMap FROM Offre JOIN OffreMap USING (id_Offre) JOIN Map USING (id_SkinMap);";
        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                listItem.add((new SkinMapView(resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_SkinMap"),resultSet.getString("imageMiniatureMap"),resultSet.getString("descriptionMap"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null)
                    connection.close();
            }catch (SQLException e){

            }
        }
        return listItem;
    }

    private int gererAjoutCarte(String pseudo, int id_Offre){

        ArrayList<Integer> cartesPack = new ArrayList<>();
        ArrayList<Integer> qteCarte = new ArrayList<>();
        String queryAllCards = "SELECT id_Carte, qteCartePack " +
                "FROM Offre " +
                "JOIN Pack USING (id_Pack) " +
                "JOIN CartePack USING (id_Pack) " +
                "WHERE id_Offre = "+id_Offre+" ;"; //AND Pseudo LIKE '"+pseudo+"';";

        ArrayList<Integer> cartesPossedees = new ArrayList<>();
        String queryPossession = "SELECT id_Carte, qteCartePack " +
                "FROM Offre " +
                "JOIN Pack USING (id_Pack) " +
                "JOIN CartePack USING (id_Pack) " +
                "JOIN JoueurCarteDeck USING (id_Carte) " +
                "WHERE id_Offre = "+id_Offre+" AND Pseudo LIKE '"+pseudo+"';";

        //String queryMinus = "";

        String queryUpdate = "UPDATE JoueurCarteDeck SET qteCarteDeck = qteCarteDeck+";
        String queryInsert = "INSERT INTO JoueurCarteDeck (id_Deck,Pseudo,id_Carte,qteCarteDeck) VALUES ('"+pseudo+"0','"+pseudo+"',";

        String queryUpdateFinal = "";
        String queryInsertFinal = "";
        ResultSet testAllCards = Manager.getManager().sendRequestQuery(queryAllCards,connection);
        try {
            while(testAllCards.next()) {
                cartesPack.add(testAllCards.getInt("id_Carte"));
                qteCarte.add(testAllCards.getInt("qteCartePack"));
            }
            ResultSet testPossession = Manager.getManager().sendRequestQuery(queryPossession,connection);
            while(testPossession.next()){
                cartesPossedees.add(testPossession.getInt("id_Carte"));
            }

            for(int i = 0; i < cartesPack.size();i++){
                if (cartesPossedees.contains(cartesPack.get(i))){       //Si le joueur possède la carte i du pack -> UPDATE
                    queryUpdateFinal = queryUpdate.concat(qteCarte.get(i)+" WHERE id_Carte="+cartesPack.get(i)+" && id_Deck='"+pseudo+"0';");
                    Manager.getManager().sendRequestUpdate(queryUpdateFinal,connection);
                }
                else {                                                  //Sinon -> INSERT
                    queryInsertFinal = queryInsert.concat(cartesPack.get(i)+","+qteCarte.get(i)+");");
                    System.out.println();
                    Manager.getManager().sendRequestUpdate(queryInsertFinal,connection);
                }

            }



        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }


    public int doAchat(String pseudo, String mdp, int id_Offre, String money){



        String queryMdp = "SELECT mdpCompte " +
                "FROM CompteJoueur " +
                "WHERE Pseudo LIKE '"+pseudo+"';";
        ResultSet testMdp = Manager.getManager().sendRequestQuery(queryMdp,connection);
        try {
            testMdp.next();
            String mdpCompte = testMdp.getString("mdpCompte");
            if (!(mdpCompte.equals(mdp))){
                return RequestStatus.ACHAT_FAILED_MDP;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        String queryMoney = "SELECT "+money+
                " FROM CompteJoueur " +
                "WHERE Pseudo LIKE '"+pseudo+"';";
        String queryPrix = "SELECT prix"+money+", typeOffre" +
                " FROM Offre" +
                " WHERE id_Offre = "+id_Offre+" ;";


        ResultSet testMoney = Manager.getManager().sendRequestQuery(queryMoney,connection);

        try {
            testMoney.next();
            int moneyCompte = testMoney.getInt(money);
            ResultSet testPrix = Manager.getManager().sendRequestQuery(queryPrix,connection);
            testPrix.next();
            int prixOffre = testPrix.getInt("prix"+money);
            if (moneyCompte < prixOffre){
                return RequestStatus.ACHAT_FAILED_MONEY;
            }
            String typeOffre = testPrix.getString("typeOffre");
            switch (typeOffre){
                case    "Pack" : {
                    if(gererAjoutCarte(pseudo,id_Offre) == 0) break;
                    else return -1;         //Erreur JeSaisPasTropQuoiMaisL'AjoutDesCartesAPlanté
                }

            }
            String queryRendLArgentAuxAbonnes = "UPDATE CompteJoueur SET "+money+"="+(moneyCompte-prixOffre)+" WHERE Pseudo='"+pseudo+"';";
            Manager.getManager().sendRequestUpdate(queryRendLArgentAuxAbonnes,connection);

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




        return RequestStatus.ACHAT_SUCCESS;
    }


}
