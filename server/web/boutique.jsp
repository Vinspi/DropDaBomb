<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Boutique - DropDaBomb</title>
    <meta charset="utf-8">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
    <script type="text/javascript" src="js/boutique.js"></script>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/shop.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="materialize/css/materialize.css"  media="screen,projection"/>




</head>
<body >
<%
    String pseudo = (String) session.getAttribute("pseudo");
    String icone = (String) request.getSession().getAttribute("iconeJoueur");
    Boolean estAdmin = (Boolean) (session.getAttribute("estAdmin"));
    int j = 0;

%>

<nav  class=" light-blue darken-4">
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">DropDaBomb</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <%

                if(session.getAttribute("pseudo") == null) {
                    out.print("<li><a href=\"account.jsp\">Inscription</a></li>");
                    out.print("<li><a href=\"log.jsp\">Connexion</a></li>");

                }
                else if(estAdmin != null) {
                    if(estAdmin){

                        out.print("<li><a href=\"game.jsp\">Jouer</a></li>" +
                                "<li><a href=\"compte.jsp\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("monnaieIG")+"$</a></li>" +
                                "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" +
                                "<li><a href=\"boutique.jsp\">Boutique</a></li>" +
                                "<li><a href=\"admin.jsp\" id=\"admin\">Admin</a></li>" +
                                "<li><a href=\"dc\">Déconnexion</a></li>"+
                                "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                    }
                    else {
                        out.print("<li><a href=\"game.jsp\">Jouer</a></li>" +
                                "<li><a href=\"compte.jsp\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("monnaieIG")+"$</a></li>" +
                                "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" +
                                "<li><a href=\"boutique.jsp\">Boutique</a></li>" +
                                "<li><a href=\"dc\">Déconnexion</a></li>"+
                                "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                    }

                }


            %>
        </ul>
    </div>
</nav>

<div class="container center-align">
    <div class="row">
        <div class="col s12 m12 l12">
            <div class="card hoverable">
                <div class="card-content">
                    <div class="row" id="pack">

                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col s12 m12 l12">
            <div class="card hoverable">
                <div class="card-content">
                    <div class="row" id="boost">

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col s12 m12 l12">
            <div class="card hoverable">
                <div class="card-content">
                    <div class="row" id="map">

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col s12 m12 l12">
            <div class="card hoverable">
                <div class="card-content">
                    <div class="row" id="carton">

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col s12 m12 l12">
            <div class="card hoverable">
                <div class="card-content">
                    <div class="row" id="icone">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="modal-loot" class="modal">
    <div id="loot" class="modal-content">

    </div>

</div>

</body>
</html>
