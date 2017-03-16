package Manager;

import java.sql.*;
import java.util.List;

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

    public Boolean sendRequestUpdate(String query,Connection connection){


        try {
            connection = DriverManager.getConnection(RequestStatus.URL_BDD, RequestStatus.BDD_USER, RequestStatus.BDD_PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public void sendDeleteUpdate(String query,Connection connection){
        try {
            connection = DriverManager.getConnection(RequestStatus.URL_BDD, RequestStatus.BDD_USER, RequestStatus.BDD_PASSWORD);
            Statement fkc = connection.createStatement();
            fkc.execute("SET FOREIGN_KEY_CHECKS=0");
            fkc.close();

            statement = connection.createStatement();
            statement.executeUpdate(query);

            fkc = connection.createStatement();
            fkc.execute("SET FOREIGN_KEY_CHECKS=1");
            fkc.close();

        }catch (SQLException e){
            e.printStackTrace();

        }

    }

    public void sendMultipleRequestUpdate(List<String> queries, Connection connection){


        try {
            connection = DriverManager.getConnection(RequestStatus.URL_BDD, RequestStatus.BDD_USER, RequestStatus.BDD_PASSWORD);
            for(String query : queries) {
                statement = connection.createStatement();
                statement.executeUpdate(query);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
