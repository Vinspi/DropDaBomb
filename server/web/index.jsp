<%--
  Created by IntelliJ IDEA.
  User: vinspi
  Date: 04/02/17
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="materialize/css/materialize.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/style.css" media="screen,projection"/>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="materialize/js/materialize.js"></script>

    <title>DropDaBomb</title>
</head>
<body class="grey lighten-3">
    <nav class=" light-blue darken-4">
        <div class="nav-wrapper">
            <a href="#" class="brand-logo"><img src="img/ICONES/LOGO_DDB.png" class="iconeJoueur"></a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">

                <%

                    String pseudo = (String) request.getSession().getAttribute("pseudo");
                    String icone = (String) request.getSession().getAttribute("iconeJoueur");
                    Boolean estAdmin = (Boolean) (session.getAttribute("estAdmin"));

                    if(session.getAttribute("pseudo") == null) {
                        out.print("<li><a href=\"account.jsp\">Inscription</a></li>");
                        out.print("<li><a href=\"log.jsp\">Connexion</a></li>");

                    }
                    else if(estAdmin != null) {
                        if(estAdmin){

                            out.print("<li><a href=\"game.jsp\">Jouer</a></li>" +
                                    "<li><a href=\"Inventaire\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("monnaieIG")+"$</a></li>" +
                                    "<li><a href=\"MonCompte\">Mon compte</a></li>" +
                                    "<li><a href=\"Boutique\">Boutique</a></li>" +
                                    "<li><a href=\"Admin\" id=\"admin\">Admin</a></li>" +
                                    "<li><a href=\"dc\">Déconnexion</a></li>"+
                                    "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                        }
                        else {
                            out.print("<li><a href=\"game.jsp\">Jouer</a></li>" +
                                    "<li><a href=\"Inventaire\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("monnaieIG")+"$</a></li>" +
                                    "<li><a href=\"MonCompte\">Mon compte</a></li>" +
                                    "<li><a href=\"Boutique\">Boutique</a></li>" +
                                    "<li><a href=\"dc\">Déconnexion</a></li>"+
                                    "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                        }

                    }


                %>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="row center-align">

            <h1 style="color:white; font-weight: 600"> Bienvenue sur le site officiel du jeu </h1>
            <h1 style="color:black; font-weight: 800">DROP DA BOMB</h1>

            <img src="img/bomb4.png" class="responsive-img">

        </div>

    </div>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/1.7.3/socket.io.js" ></script>
    <script src="js/clientChat.js"></script>
</body>
</html>
