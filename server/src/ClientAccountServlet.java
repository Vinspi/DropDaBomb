import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by vinspi on 23/01/17.
 */
public class ClientAccountServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        /* chargement du driver mysql */
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String urlBDD = "jdbc:mysql://176.132.21.198:9091/DropDaBomb";

        String user = "vinspi";
        String mdp = "vinspi13";
        Connection connection = null;
        int id_carte = 0;
        String nomCarte = "pas de requete";
        String rareteCarte = "pouf";
        String typeCarte = "pouf";
        String descriptionCarte = "pouf";
        String imageCarte = "pouf";
        int coutCarte = 0;

        try {
            connection = DriverManager.getConnection(urlBDD,user,mdp);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Carte;");
            while(resultSet.next()) {
                id_carte = resultSet.getInt("id_Carte");
                nomCarte = resultSet.getString("nomCarte");
                rareteCarte = resultSet.getString("rareteCarte");
                typeCarte = resultSet.getString("typeCarte");
                descriptionCarte = resultSet.getString("descriptionCarte");
                imageCarte = resultSet.getString("imageCarte");
                coutCarte = resultSet.getInt("coutCarte");


            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException ignore){

                }
            }
        }


        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>"+id_carte+" </h1>");
        out.println("<h1>"+nomCarte+" </h1>");
        out.println("<h1>"+rareteCarte+" </h1>");
        out.println("<h1>"+typeCarte+" </h1>");
        out.println("<h1>"+descriptionCarte+" </h1>");
        out.println("<h1>"+imageCarte+" </h1>");
        out.println("<h1>"+coutCarte+" </h1>");


        out.println("</body>");
        out.println("</html>");
    }
}
