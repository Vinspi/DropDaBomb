<%@ page import="java.util.ArrayList" %>
<%@ page import="Manager.AccountManager" %>
<%@ page import="Manager.RequestStatus" %><%--
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="css/accountManager.css"  media="screen,projection"/>

    <script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
    <title>DropDaBomb</title>
</head>
<body>

<%
    AccountManager accountManager = new AccountManager();
    String pseudo = (String) session.getAttribute("pseudo");
    String email = (String) session.getAttribute("mailJoueur");
    String icone = (String) session.getAttribute("iconeJoueur");
    System.out.println("attribut iconeJoueur : "+icone);
    String skinCarton = (String) session.getAttribute("skinCartonJoueur");
    String skinMap = (String) session.getAttribute("skinMapJoueur");
    String srcImageIcone = "\"../img/ICONES/"+icone+"\"";
    String srcImageSkinCarton = "\"../img/SKIN_CARTE/"+skinCarton+"\"";
    String srcImageSkinMap = "\"../img/SKIN_MAP/"+skinMap+"\"";
    Boolean estAdmin = (Boolean) (session.getAttribute("estAdmin"));

%>

    <nav>
        <div class="nav-wrapper nav-perso">
            <a href="#" class="brand-logo"><img src="../img/ICONES/LOGO_DDB.png" class="iconeJoueur"></a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="boutique.jsp">Boutique</a></li>
                <%

                    System.out.println("pseudo = "+pseudo);
                    System.out.println("icone = "+icone);
                    if(session.getAttribute("pseudo") == null) {
                        out.print("<li><a href=\"log.jsp\">Connexion</a></li>");
                    }
                    else if(estAdmin != null) {
                        if(estAdmin){
                            System.out.println("coucou");
                            out.print("<li><a href=\"compte.jsp\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("money")+"$</a></li>" +
                                    "<li><a href=\"admin.jsp\" id=\"admin\">Admin</a></li>" +
                                    "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" +
                                    "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                        }

                    }
                    else {
                        out.print("<li><a href=\"compte.jsp\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("money")+"$</a></li>" +
                                "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" +

                                "<li><a href=\"game.jsp\">Jouer</a></li>" +

                                "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                    }

                %>
            </ul>
        </div>
    </nav>

    <div class="container section-mon-compte">
        <div class="card">
            <div class="card-title center-align titre-mon-compte white-text luna">
                Mon compte
            </div>
            <div class="card-content">
                <div class="row">
                    <div class="col s4 m4 l4">
                        <span>pseudo : </span>
                    </div>
                    <div class="col s4 m4 l4">
                        <%=pseudo%>
                    </div>
                </div>
                <div class="row">
                    <div class="col s4 m4 l4">
                        <span>email : </span>
                    </div>
                    <div class="col s4 m4 l4 mail">
                        <%=email%>
                    </div>
                    <div class="s4 m4 l4">
                        <a class="btn amber darken-2 waves-effect white-text" onclick="changeEmail()">changer</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col s4 m4 l4">
                        <span>icone : </span>
                    </div>
                    <div class="col s4 m4 l4">
                        <img class="iconeJoueur" id="icone-joueur-compte" src=<%=srcImageIcone%>>
                    </div>
                    <div class="s4 m4 l4">
                        <a class="btn amber darken-2 waves-effect white-text" onclick="changeIcone()">changer</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col s4 m4 l4">
                        <span>skin de carte : </span>
                    </div>
                    <div class="col s4 m4 l4">
                        <img class="iconeJoueur" id="icone-carte-compte" src=<%=srcImageSkinCarton%>>
                    </div>
                    <div class="s4 m4 l4">
                        <a class="btn amber darken-2 waves-effect white-text" onclick="changeSkinCarte()">changer</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col s4 m4 l4">
                        <span>skin map : </span>
                    </div>
                    <div class="col s4 m4 l4">
                        <img class="iconeJoueur" id="icone-map-compte" src=<%=srcImageSkinMap%>>
                    </div>
                    <div class="s4 m4 l4">
                        <a class="btn amber darken-2 waves-effect white-text" onclick="changeSkinMap()">changer</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col s4 m4 l4 offset-s4 offset-m4 offset-l4">
                        <a class="btn amber darken-2 waves-effect white-text" onclick="changeMdp()">changer mon mot de passe</a>
                    </div>
                </div>
                <%/*
                out.print();
                */%>
                <div class="row">
                    <div class="col s4 m4 l4 offset-s4 offset-m4 offset-l4">
                        <a class="btn amber darken-2 waves-effect white-text" onclick="changeMdp()">changer mon mot de passe</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="modalEmail" class="modal">
        <div class="modal-content ">
            <div class="center-align ">
                <div class="white-text luna titre-modif-email">Modification de votre adresse email</div>
            </div>
            <div id="erreur-email" class="center-align red-text">cet email n'est pas valide</div>
                <div class="container">
                    <div class="row">
                        <div class="col s6 m6 l6">
                            email actuel :
                        </div>
                        <div class="col s6 m6 l6 mail">
                            <%=email%>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s6 m6 l6">
                            nouvel email :
                        </div>
                        <div class="col s6 m6 l6">
                            <input id="new-email" type="text" class="validate">
                        </div>
                    </div>

            </div>
        </div>
        <div class="modal-footer">
            <a href="#!" class=" modal-action waves-effect waves-green btn-flat" onclick="validationChangeEmail()">Agree</a>
        </div>
    </div>
    <div id="modalMdp" class="modal">
        <div class="modal-content ">
            <div class="center-align ">
                <div class="white-text luna titre-modif-email">Modification de votre mot de passe</div>
            </div>
            <div id="erreurMdp" class="center-align red-text">les mots de passe de correspondent pas ou le mot de passe choisit est trop court
                (<%=RequestStatus.MIN_SIZE_PASSWORD%> caractères min)</div>
            <div class="container">

                    <div class="row">
                        <div class="col s6 m6 l6">
                            mot de passe actuel:
                        </div>
                        <div class="col s6 m6 l6">
                            <input id="actual-mdp" type="password" class="validate">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s6 m6 l6">
                            nouveau mot de passe :
                        </div>
                        <div class="col s6 m6 l6">
                            <input id="new-mdp" type="password" class="validate">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s6 m6 l6">
                            confirmation mot de passe :
                        </div>
                        <div class="col s6 m6 l6">
                            <input id="new-mdp-confirm" type="password" class="validate">
                        </div>
                    </div>

            </div>
        </div>
        <div class="modal-footer">
            <a href="#!" class=" modal-action waves-effect waves-green btn-flat" onclick="validationChangeMdp()">Agree</a>
        </div>
    </div>

    <div id="modalIcone" class="modal">
        <div class="modal-content ">
            <div class="center-align ">
                <div class="white-text luna titre-modif-email">Modification de votre icone</div>
            </div>

            <div class="container">

                <%
                    ArrayList<AccountManager.Doublet> listIcons = accountManager.getPlayerIcons(pseudo);

                    out.print("<div class=\"row\">");

                    for(int i = 0;i<listIcons.size();i++){
                        out.print("<div class=\"col s2 m2 l2\">");
                        out.print("<img onclick=\"confirmationChangeIcone($(this).attr(\'id\'),$(this).attr(\'src\'))\" class=\"iconeJoueur\" id=\""+listIcons.get(i).id+"\" src=\"../img/ICONES/"+listIcons.get(i).image+"\">");
                        out.print("</div>");
                        if((i%6) == 0 && i != 0){
                            out.print("</div>");
                            out.print("<div class=\"row\">");
                        }

                    }
                    out.print("</div>");

                %>

            </div>
        </div>
    </div>

    <div id="modalSkinMap" class="modal">
        <div class="modal-content ">
            <div class="center-align ">
                <div class="white-text luna titre-modif-email">Modification de votre skin de map</div>
            </div>

            <div class="container">

                <%
                    ArrayList<AccountManager.Doublet> listSkinMap = accountManager.getPlayerMaps(pseudo);

                    out.print("<div class=\"row\">");

                    for(int i = 0;i<listSkinMap.size();i++){
                        out.print("<div class=\"col s2 m2 l2\">");
                        out.print("<img onclick=\"confirmationChangeSkinMap($(this).attr(\'id\'),$(this).attr(\'src\'))\" class=\"iconeJoueur\" id=\""+listSkinMap.get(i).id+"\" src=\"../img/SKIN_MAP/"+listSkinMap.get(i).image+"\">");
                        out.print("</div>");
                        if((i%6) == 0 && i != 0){
                            out.print("</div>");
                            out.print("<div class=\"row\">");
                        }

                    }
                    out.print("</div>");

                %>

            </div>
        </div>
    </div>

    <div id="modalSkinCarton" class="modal">
        <div class="modal-content ">
            <div class="center-align ">
                <div class="white-text luna titre-modif-email">Modification de votre skin de carte</div>
            </div>

            <div class="container">

                <%
                    ArrayList<AccountManager.Doublet> listSkinCarte = accountManager.getPlayerCartons(pseudo);

                    out.print("<div class=\"row\">");

                    for(int i = 0;i<listSkinMap.size();i++){
                        out.print("<div class=\"col s2 m2 l2\">");
                        out.print("<img onclick=\"confirmationChangeSkinCarte($(this).attr(\'id\'),$(this).attr(\'src\'))\" class=\"iconeJoueur\" id=\""+listSkinCarte.get(i).id+"\" src=\"../img/SKIN_CARTE/"+listSkinCarte.get(i).image+"\">");
                        out.print("</div>");
                        if((i%6) == 0 && i != 0){
                            out.print("</div>");
                            out.print("<div class=\"row\">");
                        }

                    }
                    out.print("</div>");

                %>

            </div>
        </div>
    </div>

    <script src="../js/accountManager.js"></script>
</body>
</html>
