package Manager;


import View.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import sun.awt.image.ImageWatched;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
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

        //IMAGEPACK/DESCRIPTION PACK A RAJOUTER DANS LA DB !!!

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

}
