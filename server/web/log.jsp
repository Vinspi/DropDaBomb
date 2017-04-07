<%@ page import="javax.swing.undo.AbstractUndoableEdit" %>
<%@ page import="Manager.RequestStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="materialize/css/materialize.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"  media="screen,projection"/>

    <script type="text/javascript" src="materialize/js/materialize.js"></script>

    <title>DropDaBomb</title>
</head>
<body class="grey lighten-3">

<nav>
    <div class="nav-wrapper blue darken-3">
        <a href="#" class="brand-logo">DropDaBomb</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="boutique.jsp">Boutique</a></li>
            <li><a href="account.jsp">Inscription</a></li>
            <li><a href="log.jsp">Connexion</a></li>
        </ul>
    </div>
</nav>

    <div class="container">
        <div class="row">
            <div class="col s4 m4 l4 offset-l4 offset-m4 offset-s4">
                <div class="card log-card center-align">
                    <div class="card-content title-card">
                        <div class="center-align amber title-connexion">
                            <span class="card-title white-text">Connectez vous</span>
                            <div class="bottom-arrow-log"></div>
                        </div>
                        <div class="row content-connexion">
                            <div class="col s8 m8 l8 offset-l2 offset-m2 offset-s2">
                                <% System.out.println(request.getParameter("AUTH_STATUS"));
                                    if(session.getAttribute("STATUS") != null){
                                        out.print("<h8 class='red-text'>Mauvais identifiants</h8>");
                                    }
                                %>
                                <form action="Auth" method="post">
                                    <input placeholder="pseudo" id="pseudo" name ="pseudo" type="text" class="validate">

                                    <input placeholder="mot de passe" id="password" type="password" name="password" class="validate">
                                    <input class="btn-large btn-index waves-effect waves-light amber" type="submit" value="Connexion">
                                </form>
                            </div>
                        </div>
                        <p>or</p>
                        <a class="btn btn-index waves-effect waves-light amber darken-2" href="account.jsp" >Je n'ai pas de compte</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
