<%@ page import="java.sql.*" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="materialize/css/materialize.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"  media="screen,projection"/>

    <title>DropDaBomb</title>
  </head>
  <body>
    <div class="container">
      <%

        String reponseSuccesNavigateur = "<div class=\"col s12 m7\">\n" +
                "      <div class=\"card horizontal card_signup\">\n" +
                "        <div class=\"card-image\">\n" +
                "          <img src=\"../img/signup_succes.png\">\n" +
                "        </div>\n" +
                "        <div class=\"card-stacked\">\n" +
                "          <div class=\"card-content\">\n" +
                "             <h1 class=\"midnight-blue center-align\">Bravo</h1>" +
                "               <p>Compte créé avec succès.</p>" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>";

        String reponseFailPseudoNavigateur = "<div class=\"col s12 m7\">\n" +
                "      <div class=\"card horizontal card_signup\">\n" +
                "        <div class=\"card-image\">\n" +
                "          <img src=\"../img/signup_fail.png\">\n" +
                "        </div>\n" +
                "        <div class=\"card-stacked\">\n" +
                "          <div class=\"card-content\">\n" +
                "             <h1 class=\"midnight-blue center-align\">Désolé</h1>" +
                "               <p>Désolé ce pseudo existe deja.</p>" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>";

        String reponseFailEmailNavigateur = "<div class=\"col s12 m7\">\n" +
                "      <div class=\"card horizontal card_signup\">\n" +
                "        <div class=\"card-image\">\n" +
                "          <img src=\"../img/signup_fail.png\">\n" +
                "        </div>\n" +
                "        <div class=\"card-stacked\">\n" +
                "          <div class=\"card-content\">\n" +
                "             <h1 class=\"midnight-blue center-align\">Désolé</h1>" +
                "               <p>Désolé cet email existe deja.</p>" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>";

        /* chargement du driver mysql */
        try{
          Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
          e.printStackTrace();
        }

        String urlBDD = "jdbc:mysql://109.7.220.208:3306/DropDaBomb";

        String user = "vinspi";
        String mdp = "vinspi13";
        Connection connection = null;



        try {
          connection = DriverManager.getConnection(urlBDD, user, mdp);
          Statement statement = connection.createStatement();
          String pseudo = request.getParameter("pseudo");
          String email = request.getParameter("email");
          String password = request.getParameter("password");

          String query = "SELECT Pseudo, mailCompte FROM CompteJoueur WHERE (Pseudo LIKE \""+pseudo+"\" OR " +
                  "mailCompte LIKE \""+email+"\");";

          String queryInsertion = "INSERT INTO CompteJoueur (Pseudo, mailCompte, mdpCompte, nom_guilde, id_Division) " +
                  "VALUES (\""+pseudo+"\", \""+email+"\", \""+password+"\", NULL, NULL);";

          ResultSet resultSet = statement.executeQuery(query);
          if(!resultSet.next()){
            statement.executeUpdate(queryInsertion);
            out.print(reponseSuccesNavigateur);
          }
          else {
            /* sinon on test si c'est le mail qui a match ou le pseudo */
            if (resultSet.getString("Pseudo").equalsIgnoreCase(pseudo)) {
              out.print(reponseFailPseudoNavigateur);
            }
            /* si le pseudo a pas match c'est forcément l'email (ou les deux au quel cas on a deja traité l'erreur */
            else {
              out.print(reponseFailEmailNavigateur);
            }

          }

       }
        catch (SQLException e){
            e.printStackTrace();
        }

        finally { /* fermeture de la connection a mysql */
          if (connection != null) {
            try {
              connection.close();
            }catch (SQLException e){
                /* ignore */
            }
          }

        }

      %>
    </div>
  </body>
</html>