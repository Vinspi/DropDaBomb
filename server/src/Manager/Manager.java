package Manager;

import java.sql.*;

/**
 * Created by vinspi on 27/01/17.
 */
public class Manager {


    private Statement statement;
    private ResultSet resultSet = null;
    private static Manager manager = null;


    private Manager(){
        /* chargement du driver mysql */
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        /* fin du chargement */
    }

    public static Manager getManager(){

        if(manager == null){
            manager = new Manager();
        }
        return manager;
    }

    public ResultSet sendRequestQuery(String query,Connection connection){

        try {
            connection = DriverManager.getConnection(RequestStatus.URL_BDD, RequestStatus.BDD_USER, RequestStatus.BDD_PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public void sendRequestUpdate(String query,Connection connection){
        /* chargement du driver mysql */
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        /* fin du chargement */

        try {
            connection = DriverManager.getConnection(RequestStatus.URL_BDD, RequestStatus.BDD_USER, RequestStatus.BDD_PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(query);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    /* ignore */
                }
            }
        }

    }
}
