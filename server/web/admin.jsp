<%@ page import="View.AdminPackView" %>
<%@ page import="View.MiniatureCarte" %>
<%@ page import="View.Ensemble" %>
<%@ page import="View.LootPack" %>
<%@ page import="View.Pack" %>

<%--
  Created by IntelliJ IDEA.
  User: deutsch
  Date: 09/03/17
  Time: 21:08
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


    <meta charset="utf-8">
    <title>DropDaBomb</title>
</head>
<body class="center-align">
<%
    String pseudo = (String) session.getAttribute("pseudo");
    String icone = (String) request.getSession().getAttribute("iconeJoueur");
%>

<nav>
    <div class="nav-wrapper nav-perso">
        <a href="#" class="brand-logo">DropDaBomb</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="shop.jsp">Boutique</a></li>
            <%

                if(session.getAttribute("pseudo") == null) {
                    out.print("<li><a href=\"log.jsp\">Connexion</a></li>");
                }
                else if((int) session.getAttribute("estAdmin") == 1) {
                    out.print("<li><a href=\"compte.jsp\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("money")+"$</a></li>" +
                            "<li><a href=\"admin.jsp\" id=\"admin\"></a>Admin</li>" +
                            "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" +
                            "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");

                }
                else {
                    out.print("<li><a href=\"compte.jsp\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("money")+"$</a></li>" +
                            "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" +
                            "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                }

            %>
        </ul>
    </div>
</nav>
<%
    AdminPackView apv = new AdminPackView();


%>

<h3>Panneau d'administration des packs</h3>
    <!-- Pack -->
    <div class="container pack">
        <div class="row">
            <div class="col s12 m12 l12">
                <!-- LootPack -->
                <div class="container lootpack">
                    <div class="row">
                        <div class="col s12 m12 l12">

                            <!-- Ensemble -->
                            <div class="container ensemblepack">
                                <div class="row">
                                    <div class="col s12 m12 l12">
                                        <h5>Gestion des ensembles</h5>
                                            <div class="card">
                                                <div class="card-content white-text">
                                                    <p>Gestion des Ensembles</p>
                                                    <div id="listEnsembles" class="row">
                                                        <%
                                                            for(int i = 0; i < apv.getListEnsembles().size();i++){
                                                                out.print("<div class=\"col s2 m2 l2\">");
                                                                out.print("<div id=\"ensemble"+apv.getListEnsembles().get(i).getId()+"\"><p>Ensemble "+apv.getListEnsembles().get(i).getId()+"</p>");
                                                                out.print("</div>");
                                                                if(i%6 == 0) out.print("</div><div class=\"row\">");
                                                            }
                                                        %>
                                                    </div>
                                                    <div id="btnEnsemble"><a class="waves-effect waves-light btn">Add</a><a class="waves-effect waves-light btn">Remove</a></div>
                                                    <p>Ensemble courant</p>
                                                    <div id="currentEnsemble" class="row">
                                                        <%
                                                            int j = 0;
                                                            for(MiniatureCarte c : apv.getCurrentEnsemble().getCartes()){
                                                                j++;
                                                                out.print("<div class=\"col s2 m2 l2\">");
                                                                out.print("<div id=\"cartesEns"+c.getId()+"\">" +
                                                                        "     <div class=\"card-image grow\" id=\"cardCartesEns" + c.getId() + "\">" +
                                                                        "       <img src=\"../img/CARDS/" + c.getImg() + "\">" +
                                                                        "     </div>");
                                                                out.print("</div>");
                                                                if(j%6 == 0){
                                                                    j = 0;
                                                                    out.print("</div><div class=\"row\">");
                                                                }
                                                            }
                                                        %>
                                                    </div>
                                                </div>
                                                <div class="card-action">
                                                    <a class="waves-effect waves-teal btn-flat" onclick=newEnsemble()>New</a>
                                                    <a class="waves-effect waves-teal btn-flat" onclick=createEnsemble()>Create</a>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                            <h5>Gestion des ensembles</h5>
                                <div class="card">
                                    <div class="card-content white-text">
                                        <p>Gestion des Ensembles</p>
                                        <div id="listLP" class="row">
                                            <%
                                                for(int i = 0; i < apv.getListLootPacks().size();i++){
                                                    out.print("<div class=\"col s2 m2 l2\">");
                                                    out.print("<div id=\"lootpack"+i+"\"><p>LootPack "+i+"</p>");
                                                    out.print("</div>");
                                                    if(i%6 == 0) out.print("</div><div class=\"row\">");
                                                }
                                            %>
                                        </div>
                                        <div id="btnLP"><a class="waves-effect waves-light btn">Add</a><a class="waves-effect waves-light btn">Remove</a></div>
                                        <div id="currentLP" class="row">
                                            <%
                                                int j = 0;
                                                for(Ensemble e : apv.getCurrentLootPack().getEnsembles()){
                                                    j++;
                                                    out.print("<div class=\"col s2 m2 l2\">");
                                                    out.print("<div id=\"Ens"+e.getId()+"\"><p></p>" );
                                                    out.print("</div>");
                                                    if(j%6 == 0){
                                                        j = 0;
                                                        out.print("</div><div class=\"row\">");
                                                    }
                                                }
                                            %>
                                        </div>
                                    </div>
                                    <div class="card-action">
                                        <a class="waves-effect waves-teal btn-flat" onclick=newEnsemble()>New</a>
                                        <a class="waves-effect waves-teal btn-flat" onclick=createEnsemble()>Create</a>



                                    </div>
                                </div>
                        </div>

                        <h5>Gestion des ensembles</h5>
                            <div class="card">
                                <div class="card-content white-text">
                                    <p>Gestion des Ensembles</p>
                                    <div id="listPack" class="row">

                                    </div>
                                    <div id="btnPack"><a class="waves-effect waves-light btn">Add</a><a class="waves-effect waves-light btn">Remove</a></div>
                                    <div id="currentPack" class="row">

                                    </div>
                                </div>
                                <div class="card-action">
                                    <a class="waves-effect waves-teal btn-flat" onclick=newEnsemble()>New</a>
                                    <a class="waves-effect waves-teal btn-flat" onclick=createEnsemble()>Create</a>

                                </div>
                            </div>
                    </div>

                </div>
            </div>
        </div>

    </div>

</body>
</html>

