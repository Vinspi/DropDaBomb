<%--
  Created by IntelliJ IDEA.
  User: vinspi
  Date: 21/02/17
  Time: 14:39
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="materialize/js/materialize.js"></script>
    <title>DropDaBomb</title>
</head>
<body>

<%

    String pseudo = (String) session.getAttribute("pseudo");
    String email = (String) session.getAttribute("mailJoueur");
    String icone = (String) session.getAttribute("iconeJoueur");
    String skinCarton = (String) session.getAttribute("skinCartonJoueur");
    String skinMap = (String) session.getAttribute("skinMapJoueur");
    String srcImageIcone = "\"../img/ICONES/"+icone+"\"";
    String srcImageSkinCarton = "\"../img/"+skinCarton+"\"";
    String srcImageSkinMap = "\"../img/"+skinMap+"\"";

%>

    <nav>
        <div class="nav-wrapper nav-perso">
            <a href="#" class="brand-logo">DropDaBomb</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="shop.jsp">Boutique</a></li>
                <%

                    System.out.println("pseudo = "+pseudo);
                    System.out.println("icone = "+icone);
                    if(session.getAttribute("pseudo") == null) {
                        out.print("<li><a href=\"account.jsp\">Inscription</a></li>");
                        out.print("<li><a href=\"log.jsp\">Connexion</a></li>");
                    }
                    else {
                        out.print("<li><a href=\"compte.jsp\">" + pseudo + "</a></li>" +
                                "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" +
                                "<li><img src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                    }

                %>
            </ul>
        </div>
    </nav>

    <div class="card">
        <div class="card-title">
            Mon compte
        </div>
        <div class="card-content">
            <p>pseudo : <%=pseudo%> </p>
            <p>icone : <img class="circle iconeJoueur" src=<%=srcImageIcone%>> <a class="btn-flat">changer</a> </p>
            <p>email : <%=email%><a class="btn-flat">changer</a> </p>
            <p>changer mon mot de passe : <a class="btn-flat">changer</a> </p>
            <p>skin verso carte : <img src=<%=srcImageSkinCarton%>> <a class="btn-flat">changer</a> </p>
            <p>skin de map : <img src=<%=srcImageSkinMap%>> <a class="btn-flat">changer</a> </p>
        </div>
    </div>

</body>
</html>
