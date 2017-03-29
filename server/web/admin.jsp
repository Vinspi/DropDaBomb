<%@ page import="View.AdminPackView" %>
<%@ page import="View.MiniatureCarte" %>
<%@ page import="View.Ensemble" %>
<%@ page import="View.LootPack" %>
<%@ page import="View.Pack" %>
<%@ page import="java.util.ArrayList" %>

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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="css/admin.css"  media="screen,projection"/>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
    <script type="text/javascript" src="js/admin.js"></script>





    <meta charset="utf-8">
    <title>DropDaBomb</title>
</head>
<body class="center-align">
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
            <li><a href="shop.jsp">Boutique</a></li>
            <%

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
                    else {
                        out.print("<li><a href=\"compte.jsp\" id=\"pseudo\">" + pseudo + " "+session.getAttribute("money")+"$</a></li>" +


                                "<li><a href=\"accountManager.jsp\">Mon compte</a></li>" + "<li><a href=\"game.jsp\">Jouer</a></li>" +


                                "<li><img onClick=\"hideOrShowChat()\" src=\"../img/ICONES/"+icone+"\" alt=\"\" class=\"circle iconeJoueur\"></li>");
                    }

                }


            %>
        </ul>
    </div>
</nav>


<div id="modal-newEns" class="modal">
    <div class="modal-content">
        <div class="row">
            <form class="col s12 m12 l12">
                <div class="row">
                    <div class="input-field col s12 m12 l12">
                        <input id="nomEns" type="text" class="validate">
                        <label for="nomEns">Nom du nouvel ensemble</label>
                    </div>
                </div>
                <div class="input-submit col s12 m12 l12">
                    <input type="submit" value="Valider" />
                </div>
            </form>
        </div>

    </div>
</div>

<div id="modal-newLootPack" class="modal">
    <div class="modal-content">
        <div class="row">
            <form class="col s12 m12 l12">
                <div class="row">
                    <div class="input-field col s12 m12 l12">
                        <input id="nomLootPack" type="text" class="validate">
                        <label for="nomLootPack">Nom du nouveau LootPack</label>
                    </div>
                </div>
                <div class="input-submit col s12 m12 l12">
                    <input type="submit" value="Valider" />
                </div>
            </form>
        </div>

    </div>
</div>

<div id="modal-gestionLootPack" class="modal">
    <div class="modal-content">
        <div id="gestion-LootPack" class="row">

        </div>
    </div>
</div>

<div id="modal-newPack" class="modal">
    <div class="modal-content">
        <div class="row">
            <form class="col s12 m12 l12">
                <div class="row">
                    <div class="input-field col s12 m12 l12">
                        <input id="nomPack" type="text" class="validate">
                        <label for="nomPack">Nom du nouveau Pack</label>
                    </div>
                </div>
<<<<<<< HEAD
=======
                <div class="row">
                    <div class="input-field col s6 m6 l6">
                        <input id="prixIG" type="text" class="validate">
                        <label for="prixIG">PrixIG du nouveau Pack</label>
                    </div>
                    <div class="input-field col s6 m6 l6">
                        <input id="prixIRL" type="text" class="validate">
                        <label for="prixIRL">PrixIRL du nouveau Pack</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 m6 l6">
                        <input id="descriptionPack" type="text" class="validate">
                        <label for="descriptionPack">Description du nouveau Pack</label>
                    </div>
                    <div class="input-field col s6 m6 l6">
                        <input id="imagePack" type="text" class="validate">
                        <label for="imagePack">Lien de l'image du nouveau Pack</label>
                    </div>
                </div>
>>>>>>> 98b2d1c6a819424146cff475afe40033d9d621e7
                <div class="input-submit col s12 m12 l12">
                    <input type="submit" value="Valider" />
                </div>
            </form>
        </div>

    </div>
</div>


<<<<<<< HEAD
=======
<div id="modal-gestionPack" class="modal">
    <div class="modal-content">
        <div id="gestion-Pack" class="row">

        </div>
    </div>
</div>
>>>>>>> 98b2d1c6a819424146cff475afe40033d9d621e7


<h3>Panneau d'administration des packs</h3>
    <div class="container pack">
        <div class="row">

            <ul id="slide-out" class="side-nav">

            </ul>



            <div class="col s12 m12 l12">
                <div class="card">
                    <div class="card-content">


                        <!-- LootPacks -->
                        <div class="container pack">
                            <div class="row">
                                <div class="col s12 m12 l12">
                                    <div class="card">
                                        <div class="card-content">

                                            <!-- Ensembles-->
                                            <div class="container pack">
                                                <div class="row">
                                                    <div class="col s12 m12 l12">
                                                        <div class="card">
                                                            <div class="card-content">
                                                                <p>Liste des Ensembles</p>
                                                                <div id="listEnsembles" class="row">

                                                                </div>
                                                                <p id="idCurrentEnsemble"></p>
                                                                <div id="currentEnsemble" class="row">

                                                                </div>



                                                            </div>
                                                            <div id="actionEns" class="card-action">
                                                                <a href="#modal-newEns" class="waves-effect waves-teal btn-flat modal-action modal-close modal-trigger">New</a>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Mettre LootPack -->
                                            <p>Liste des LootPacks</p>
                                            <div id="listLootPack" class="row">

                                            </div>
                                            <p id="idCurrentLootPack"></p>
                                            <div id="currentLootPack" class="row">

                                            </div>
                                        </div>
                                        <div id="actionLootPack" class="card-action">
                                            <a href="#modal-newLootPack" class="waves-effect waves-teal btn-flat modal-action modal-close modal-trigger">New</a>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            </div>
                        <!-- Mettre Packs -->
                        <p>Liste des Packs</p>
                        <div id="listPack" class="row">

                        </div>
                        <p id="idCurrentPack"></p>
                        <div id="currentPack" class="row">

                        </div>
                    </div>
                    <div id="actionPack" class="card-action">
                        <a href="#modal-newPack" class="waves-effect waves-teal btn-flat modal-action modal-close modal-trigger">New</a>

                    </div>
                </div>
            </div>
        </div>
    </div>


    </div>
<<<<<<< HEAD

=======
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
    <script type="text/javascript" src="js/admin.js"></script>
>>>>>>> 98b2d1c6a819424146cff475afe40033d9d621e7
</body>
</html>

