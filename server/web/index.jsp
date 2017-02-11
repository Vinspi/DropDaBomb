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

    <script type="application/javascript" src="materialize/js/materialize.js"></script>

    <title>DropDaBomb</title>
</head>
<body class="grey lighten-3">
    <nav>
        <div class="nav-wrapper blue darken-3">
            <a href="#" class="brand-logo">DropDaBomb</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="shop.jsp">Boutique</a></li>
                <li><a href="account.jsp">Inscription</a></li>
                <%
                    System.out.println("pseudo = "+session.getAttribute("pseudo"));
                    if(session.getAttribute("pseudo") == null)
                        out.print("<li><a href=\"log.jsp\">Connexion</a></li>");
                    else out.print("<li><a href=\"compte.jsp\">Mon compte</a></li>");

                %>
            </ul>
        </div>
    </nav>
</body>
</html>
