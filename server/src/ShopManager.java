import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by vinspi on 27/01/17.
 */
public class ShopManager {
    /* coucou ceci est un test */
    Connection connection = null;
    LinkedList<ItemView> listItem;

    public LinkedList<PackView> getAllPackOffer(){

        listItem = new LinkedList<>();

        String query = "SELECT monnaiIG, monnaiIRL, id_element, description FROM Offre " +
                "JOIN Pack USING(id_element);";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
               //listItem.add(new PackView(resultSet.getInt("monnaieIRL"),resultSet.getString("monnaieIG"),resultSet.getInt("id_Pack")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }


}
