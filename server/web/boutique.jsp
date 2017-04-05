<%--
  Created by IntelliJ IDEA.
  User: deutsch
  Date: 23/03/17
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
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

<nav>
    <div class="nav-wrapper nav-perso">
        <a href="#" class="brand-logo">DropDaBomb</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="boutique.jsp">Boutique</a></li>
            <%

                if(session.getAttribute("pseudo") == null) {
                    out.print("<li><a href=\"log.jsp\">Connexion</a></li>");
                }
                else if(estAdmin != null) {
                    if(estAdmin){

                        out.print("<li><a href=\"game.jsp\">Jouer</a></li>" +
                                "<li><a href=\"compte.jsp\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("monnaieIG")+"$</a></li>" +
                                "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" +
                                "<li><a href=\"admin.jsp\" id=\"admin\">Admin</a></li>" +
                                "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                    }
                    else {
                        out.print("<li><a href=\"compte.jsp\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("monnaieIG")+"$</a></li>" +
                                "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" +


                                "<li><a href=\"game.jsp\">Jouer</a></li>" +


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
            <div class="card">
                <div class="card-content">
                    <div class="row" id="pack">

                    </div>
                    <!--
                    <ul class="collapsible" data-collapsible="accordion">
                        <li>
                            <div class="collapsible-header"></div>
                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
                        </li>
                    </ul>
                    -->




                </div>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col s12 m12 l12">
            <div class="card">
                <div class="card-content">
                    <div class="row" id="boost">

                    </div>
                    <!--
                    <ul class="collapsible" data-collapsible="accordion">
                        <li>
                            <div class="collapsible-header"></div>
                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
                        </li>
                    </ul>
                    -->




                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col s12 m12 l12">
            <div class="card">
                <div class="card-content">
                    <div class="row" id="map">

                    </div>
                    <!--
                    <ul class="collapsible" data-collapsible="accordion">
                        <li>
                            <div class="collapsible-header"></div>
                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
                        </li>
                    </ul>
                    -->




                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col s12 m12 l12">
            <div class="card">
                <div class="card-content">
                    <div class="row" id="carton">

                    </div>

                    <!--
                    <ul class="collapsible" data-collapsible="accordion">
                        <li>
                            <div class="collapsible-header"></div>
                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
                        </li>
                    </ul>
                    -->




                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col s12 m12 l12">
            <div class="card">
                <div class="card-content">
                    <div class="row" id="icone">

                    </div>
                    <!--
                    <ul class="collapsible" data-collapsible="accordion">
                        <li>
                            <div class="collapsible-header"></div>
                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
                        </li>
                    </ul>
                    -->




                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
