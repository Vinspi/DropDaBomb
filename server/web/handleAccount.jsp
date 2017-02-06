<%@ page import="java.sql.*" %>
<%@ page import="Manager.AccountManager" %>
<%@ page import="Manager.RequestStatus" %><%-- Created by IntelliJ IDEA. --%>
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


          String pseudo = request.getParameter("pseudo");
          String email = request.getParameter("email");
          String password = request.getParameter("password");


        Manager.AccountManager accountManager = new Manager.AccountManager();
        int result = accountManager.createAccount(pseudo,email,password);

        if (result == RequestStatus.CREATE_ACCOUNT_SUCCES){
            out.print(reponseSuccesNavigateur);
        }else if (result == RequestStatus.CREATE_ACCOUNT_FAILED_PSEUDO){
            out.print(reponseFailPseudoNavigateur);
        }else out.print(reponseFailEmailNavigateur);

      %>
    </div>
  </body>
</html>