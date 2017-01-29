package Manager;
import View.*;


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

    public LinkedList<PackView> getAllPackOffers(){

        LinkedList<PackView> listPack = new LinkedList<>();

        //IMAGEPACK/DESCRIPTION PACK A RAJOUTER DANS LA DB !!!

        String query = "SELECT prixMonnaieIG, prixMonnaieIRL, id_Pack, descriptionPack, imageMiniaturePack FROM Offre" +
                " JOIN Pack USING(id_pack);";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){

                listPack.add((new PackView(resultSet.getInt("prixMonnaieIG"),resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("id_Pack"),resultSet.getString("imageMiniaturePack"),resultSet.getString("descriptionPack"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return listPack;
    }
    public LinkedList<BoostView> getAllBoostOffers(){

        LinkedList<BoostView> listBoost = new LinkedList<>();

        String query = "SELECT prixMonnaieIG, prixMonnaieIRL, id_Boost, imageMiniatureBoost, descriptionBoost FROM Offre " +
                "JOIN OffreBoost USING (id_Offre)" +
                " JOIN Boost USING(id_Boost);";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){

                listBoost.add((new BoostView(resultSet.getInt("prixMonnaieIG"),resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("id_Boost"),resultSet.getString("imageMiniatureBoost"),resultSet.getString("descriptionBoost"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return listBoost;
    }
    public LinkedList<SkinCartonView> getAllCartonOffers(){

        LinkedList<SkinCartonView> listCarton = new LinkedList<>();

        String query =  "SELECT prixMonnaieIRL, prixMonnaieIG, id_SkinCartonCarte, imageMiniatureCarton, descriptionCarton FROM Offre JOIN OffreCartonCarte USING (id_Offre)" +
                " JOIN SkinCartonCarte USING (id_SkinCartonCarte);";
        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                listCarton.add((new SkinCartonView(resultSet.getInt("prixMonnaieIG"),resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("id_SkinCartonCarte"),resultSet.getString("imageMiniatureCarton"),resultSet.getString("descriptionCarton"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listCarton;
    }
    public LinkedList<SkinMapView> getAllMapOffers(){

        LinkedList<SkinMapView> listMap = new LinkedList<>();

        String query =  "SELECT prixMonnaieIG, prixMonnaieIRL, id_SkinMap, imageMiniatureMap, descriptionMap FROM Offre JOIN OffreMap USING (id_Offre) JOIN Map USING (id_SkinMap);";
        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                listMap.add((new SkinMapView(resultSet.getInt("prixMonnaieIG"),resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("id_SkinMap"),resultSet.getString("imageMiniatureMap"),resultSet.getString("descriptionMap"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listMap;
    }


}
