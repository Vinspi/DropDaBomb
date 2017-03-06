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
    <link type="text/css" rel="stylesheet" href="materialize/css/materialize.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"  media="screen,projection"/>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="materialize/js/materialize.js"></script>

    <title>DropDaBomb</title>
</head>
<body class="grey lighten-3">
    <nav>
        <div class="nav-wrapper nav-perso">
            <a href="#" class="brand-logo"><img src="../img/ICONES/LOGO_DDB.png" class="circle iconeJoueur"></a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="Shop.jsp">Boutique</a></li>
                <li><a href="account.jsp">Inscription</a></li>
                <%

                    System.out.println("pseudo = "+session.getAttribute("pseudo"));
                    String icone = (String) request.getSession().getAttribute("iconeJoueur");
                    String pseudo = (String) request.getSession().getAttribute("pseudo");
                    System.out.println("icone = "+icone);
                    if(session.getAttribute("pseudo") == null) {
                        out.print("<li><a href=\"log.jsp\">Connexion</a></li>");
                    }
                    else {
                        out.print("<li><a href=\"compte.jsp\" id=\"pseudo\">" + pseudo + "</a></li>" +
                                "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" +
                                "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                    }

                %>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col s3 m3 l3">
                <div class="card brown">
                    <div class="card-content">
                        COUCOU
                    </div>
                </div>
            </div>
            <div class="col s9 m9 l9">
                <div class="card blue">
                    <div class="card-content">
                        COUCOU
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col s12 m12 l12">
                <div class="card orange">
                    <div class="card-content">
                        COUCOU
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row chat">
        <div class="col s3 m3 l3 offset-s9 offset-m9 offset-l9">
            <div class="card">
                <div class="card-title center-align">
                    Chat
                </div>
                <div class="card-content">
                    <ul id="message-zone">
                    </ul>
                    <input id="message" type="text" class="validate"><a class="btn" onclick="sendMessage()" >send</a>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/1.7.3/socket.io.js" ></script>
    <script src="../js/clientChat.js"></script>
</body>
</html>
