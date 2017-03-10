<%--
  Created by IntelliJ IDEA.
  User: deutsch
  Date: 29/01/17
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %><%-- Created by IntelliJ IDEA. --%>
<%@ page import="java.util.LinkedList" %>
<%@ page import="View.*" %>
<%@ page import="com.google.gson.Gson"%>


<html>
<head>
    <title>TestShop.jsp</title>
    <meta charset="utf-8">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="materialize/css/materialize.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/shop.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/style.css" media="screen,projection"/>
    <script type="text/javascript" src="materialize/js/materialize.js"></script>
    <script>
        $(document).ready(function(){
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
            $('.modal-trigger').leanModal();
            $('#modal-form').on('submit', function(e) {
                e.preventDefault(); // J'empêche le comportement par défaut du navigateur, c-à-d de soumettre le formulaire
                var $this = $(this); // L'objet jQuery du formulaire
                // Je récupère les valeurs
                var mdp = $('#password').val();
                var confmdp = $('#confpassword').val();

                // Je vérifie une première fois pour ne pas lancer la requête HTTP
                // si je sais que mon PHP renverra une erreur
                if (mdp === '' || confmdp === ''){
                    $('.modal-trigger').leanModal();
                    alert("Mot(s) de passe(s) vide(s) !");
                }
                if(mdp != confmdp) {
                    $('.modal-trigger').leanModal();
                    alert('Mots de passe différents !');
                } else {
                    var pseudo = $('#pseudo').val();
                    var password = $('#password').val();
                    var id_Offre = $('#id_Offre').val();
                    var monnaie = $('#monnaie').val();

                    // Envoi de la requête HTTP en mode asynchrone
                    $.ajax({
                        url: "/ShopAchat", // Le nom du fichier indiqué dans le formulaire
                        type: "post", // La méthode indiquée dans le formulaire (get ou post)
                        //data: $('#modal-form').serialize(), // Je sérialise les données (j'envoie toutes les valeurs présentes dans le formulaire)
                        data: {"pseudo": pseudo, "password": password, "id_Offre": id_Offre, "monnaie": monnaie},
                        dataType: 'json', // JSON
                        success: function(json) { // Je récupère la réponse du fichier PHP
                            alert(json);
                        }
                    });
                }
            });


        });

        function clickModal(Offre){
            d = document.getElementById("offreFormAchat");
            var id = Offre.match(/\d+/)[0];
            d.innerHTML = "                       <input readonly value=\""+id+"\" id=\"id_Offre\" name=\"id_Offre\" type=\"text\" class=\"validate\">" +
                "                       <label for=\"disabled\">Offre choisie</label>";
        }


        /*function callback(Offre) {
                d = document.getElementById(Offre);
                var id = Offre.match(/\d+/)[0];
                d.innerHTML = "<div id=\"offreFormAchat\" class=\"input-field col s12 m12 l12\">" +
            "                       <input readonly value=\""+id+"\" id=\"id_Offre\" name=\"id_Offre\" type=\"text\" class=\"validate\">" +
            "                       <label for=\"disabled\">Offre choisie</label>" +
            "                  </div>";
        }*/



    </script>

    <!-- <link type="text/css" rel="stylesheet" href="css/style.css"  media="screen,projection"/> -->
</head>
<body>

<%

    String pseudo = (String) session.getAttribute("pseudo");
    String icone = (String) session.getAttribute("iconeJoueur");

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

<div id="container">
    <h1 class="center-align" id="h1_boutique">La Boutique</h1><p>

    <%

        ShopView shopView = new ShopView();
        shopView.getAllOffers(pseudo);
        int i = 0,m = 0;
        int id_Offre = 0;


        out.print(" <!-- Modal d'initialisation d'achat -->\n" +
                "  <div id=\"modal-buy\" class=\"modal\">\n" +
                "    <div class=\"modal-content\">\n" +
                "      <h4>Acheter ?</h4>\n" +
                "    </div>\n" +
                "    <div class=\"modal-footer\">\n" +
                "      <a href=\"#modal-form\" class=\" modal-action modal-close waves-effect waves-green btn-flat modal-trigger\">Acheter avec des IP</a>" +
                "<a href =\"#modal-form\" class=\" modal-action modal-close waves-effect waves-green btn-flat modal-trigger\">Acheter avec des RP</a>\n" +
                "<a  class=\" modal-action modal-close btn-flat\">Non.</a>" +
                "    </div>\n" +
                "  </div>");


        //Problème 1 : récupérer id_Offre.
        //Solution 1.1 : créer un modal pour chaque offre (à première vue c'est moche).
        //Solution 1.2 : envoyer une requête GET à soi-même avec le bouton Acheter (qui nous fournirait l'id_Offre)
        //Solution 1.3 : autre chose.

        //Problème 2 : afficher les résultats (animations toussa)
        //Solution 2.1 : faire renvoyer un JSON à ShopView => Réinitialisation de la page (c'est cool, on affichera plus l'offre si elle n'est plus affichable, et ça reset les modaux) + affichage d'un modal (ou autre) pour l'affichage.
        out.print(" <!-- Modal du formulaire d'achat -->\n" +
                "  <div id=\"modal-form\" class=\"modal\">\n" +
                "    <div class=\"modal-content row\">\n" +
                "      <h4>Veuillez spécifier votre mot de passe :</h4>\n" +
                "      <form class=\"col s12 m12 l12\" method=\"post\" action=\"/ShopAchat\">" +
                "           <div class=\"row\">\n" +
                "               <div class=\"input-field col s12 m12 l12\">\n" +
                "                   <input readonly value=\""+pseudo+"\" id=\"pseudo\" name=\"pseudo\" type=\"text\" class=\"validate\">\n" +
                "                   <label for=\"disabled\">Pseudo</label>\n" +
                "               </div>" +
                "           </div>" +
                "           <div class=\"row\">\n" +
                "               <div class=\"input-field col s12 m12 l12\">\n" +
                "                   <input id=\"password\" type=\"password\" name=\"password\" class=\"validate\">\n" +
                "                   <label for=\"password\">Password</label>\n" +
                "               </div>\n" +
                "           </div>" +
                "           <div class=\"row\">\n" +
                "               <div class=\"input-field col s12 m12 l12\">\n" +
                "                   <input id=\"confpassword\" type=\"password\" name=\"confpassword\" class=\"validate\">\n" +
                "                   <label for=\"password\">Confirm your password</label>\n" +
                "               </div>\n" +
                "           </div>" +
                "           <div class=\"row\">\n" +
                "               <div id=\"offreFormAchat\" class=\"input-field col s12 m12 l12\">\n" +
              /*  "                   <input readonly value=\""+id_Offre+"\" id=\"id_Offre\" name=\"id_Offre\" type=\"text\" class=\"validate\">\n" +
                "                   <label for=\"disabled\">Offre choisie</label>\n" +
              */"               </div>" +
                "           </div>" +
                "           <input type=\"hidden\" id=\"monnaie\" name=\"monnaie\" value=\"monnaieIG\">" +
                "           <div class=\"row\">\n" +
                "               <div class=\"input-submit col s12 m12 l12\">\n" +
                "                 <input type=\"submit\" value=\"Acheter !\" />" +
                "               </div>" +
                "           </div>" +
                "      </form>" +
                "    </div>\n" +
                "    <div class=\"modal-footer\">\n" +
                "   " +
                "    </div>\n" +
                "  </div>");
        out.print("<div class=\"container row\">\n");
        out.print("        <div class=\"col s12 m12 l12\">\n" +
                "        <div class=\"card\">\n" +
               /* "        <div class=\"card-image\">\n" +
                "        <img src=\"img/background_create_account.png\">\n" +
                "        </div>\n" +*/
                "        <div class=\"card-content card-container\">\n" +
                "  <div class=\"row\">\n");
        for(PackView p : shopView.getListPackView()) {
                out.print("\n" +
                        "  <div class=\"col s6 m4 l2\">\n" +
                        "  <div class=\"card sticky-action\" id=\"coffre_card\" >\n" +
                        "    <div class=\"card-image waves-effect waves-block waves-light\">\n" +
                        "      <img id=\"coffre_img\" class=\"activator\" src=\"img/" + p.getImage() +
                        "\" style=\"border:5px solid rgb(" + ((int) Math.round(Math.random()*255)) + "," + ((int)Math.round(Math.random()*255)) + "," + ((int) Math.round(Math.random()*255)) + ")\">\n" +
                        "    </div>\n" +

                            "    <div class=\"card-reveal\" id=\"coffre_card_reveal\">\n <h4 id=\"pack_name\">" +
                                     p.getNom() +
                                    "</h4> \n<span class=\"card-title grey-text text-darken-4\"><i class=\"material-icons right\">close</i></span>\n" +
                                    "<p>" + p.getDescription() + "</p>\n" +
                                    "<a id=\"Offre" + p.getId_Offre() + "\" class=\"waves-effect waves-light amber lighten-1 btn button-buy modal-trigger btn_buy_pack\" href=\"#modal-buy\" onclick=\"clickModal(\'" + p.getId_Offre() + "\')\">Acheter</a>" +
                        "    </div>\n" +
                        "</div>" +
                        "</div>");
            i++;

        }

        out.print("</div>\n" +
                "        </div>\n" +
                "        </div>\n");





        //Bandeau Boost :
        //out.print("<img src=>");

        //Tableau Boost
/*        i = 0;
        out.print("<table><tr>");
        for(BoostView p : shopView.getListBoostView()) {

            if (i%5 == 0 && i != 0){
                out.print("</tr><tr>");
            }

            out.print("<td><img src=img/boost1.png></br>"+p.getNom()+"</td>");

            if(i == shopView.getListBoostView().size()){
                out.print("</tr>\n" +
                        "    </table>");
            }
            i++;
        }*/

        out.print("        <div class=\"card\">\n" +
               /* "        <div class=\"card-image\">\n" +
                "        <img src=\"img/background_create_account.png\">\n" +
                "        </div>\n" +*/
                "        <div class=\"card-content\">\n" +
                "  <div class=\"row\">\n");
        i = 0;
        for(BoostView p : shopView.getListBoostView()) {
            if (i % 6 == 0) {
                out.print("<div class=\"col s2 m2 l2\">\n" +
                        "  <div class=\"card sticky-action\">\n" +
                        "    <div class=\"card-image waves-effect waves-block waves-light\">\n" +
                        "      <img class=\"activator\" src=\"img/" + p.getImage() + "\">\n" +
                        "    </div>\n" +
                        "<div class=\"card-content\">\n" +
                        "      <span class=\"card-title activator grey-text text-darken-4\">" + p.getNom() + "<i class=\"material-icons right\">more_vert</i></span>\n" +
                        "</div>" +
                        "    <div class=\"card-reveal\">\n" +
                        p.getNom() + "<span class=\"card-title grey-text text-darken-4\"><i class=\"material-icons right\">close</i></span>\n" +
                        "      <p>" + p.getDescription() + "</p>\n" +
                        "    </div>\n" +
                        "      <a id=\"Offre" + p.getId_Offre() + "\" class=\"waves-effect waves-light btn button-buy modal-trigger\" href=\"#modal-buy\" onclick=\"clickModal(\'" + p.getId_Offre() + "\')\">Acheter</a>" +

                        "</div>" +
                        "</div>");
            }
            else if (i % 6 == 5) {
                out.print("\n" +
                        "  <div class=\"col s2 m2 l2\">\n" +
                        "  <div class=\"card sticky-action\">\n" +
                        "    <div class=\"card-image waves-effect waves-block waves-light\">\n" +
                        "      <img class=\"activator\" src=\"" + p.getImage() + "\">\n" +
                        "    </div>\n" +
                        "<div class=\"card-content\">\n" +
                        "      <span class=\"card-title activator grey-text text-darken-4\">" + p.getNom() + "<i class=\"material-icons right\">more_vert</i></span>\n" +
                        "</div>" +
                        "    <div class=\"card-reveal\">\n" +
                        p.getNom() + "<span class=\"card-title grey-text text-darken-4\"><i class=\"material-icons right\">close</i></span>\n" +
                        "      <p>" + p.getDescription() + "</p>\n" +
                        "    </div>\n" +
                        "      <a id=\"Offre" + p.getId_Offre() + "\" class=\"waves-effect waves-light btn button-buy modal-trigger\" data-target=\"#modal-buy\" onclick=\"clickModal(\'" + p.getId_Offre() + "\')\">Acheter</a>" +
                        "</div>" +
                        "</div>");
            }
            else{
                out.print("\n" +
                        "  <div class=\"col s2 m2 l2\">\n" +
                        "  <div class=\"card sticky-action\">\n" +
                        "    <div class=\"card-image waves-effect waves-block waves-light\">\n" +
                        "      <img class=\"activator\" src=\"" + p.getImage() + "\">\n" +
                        "    </div>\n" +
                        "<div class=\"card-content\">\n" +
                        "      <span class=\"card-title activator grey-text text-darken-4\">" + p.getNom() + "<i class=\"material-icons right\">more_vert</i></span>\n" +
                        "</div>" +
                        "    <div class=\"card-reveal\">\n" +
                        p.getNom() + "<span class=\"card-title grey-text text-darken-4\"><i class=\"material-icons right\">close</i></span>\n" +
                        "      <p>" + p.getDescription() + "</p>\n" +
                        "    </div>\n" +
                        "      <a id=\"Offre" + p.getId_Offre() + "\" class=\"waves-effect waves-light btn button-buy modal-trigger\" data-target=\"#modal-buy\" onclick=\"clickModal(\'" + p.getId_Offre() + "\')\">Acheter</a>" +
                        "</div>" +
                        "</div>");
            }

            i++;

        }
        m = shopView.getListBoostView().size()%6-i;
        for(int j = 0; j < m; j++){
            out.print("\n" +
                    "  <div class=\"col s2 m2 l2\"><div class=\"card\"\n>" +
                    "</div></div>");
        }
        out.print("</div>\n" +
                "        </div>\n" +
                "        </div>\n" +
                "        </div>");


        out.print("</div>");

        //Bandeau Map :
        //out.print("<img src=>");

        //Tableau Map :
        i = 0;
        out.print("<table><tr>");
        for(SkinMapView p : shopView.getListSkinMapView()) {

            if (i%5 == 0 && i != 0){
                out.print("</tr><tr>");
            }

            out.print("<td><img src=img/map1.png></br>"+p.getNom()+"</td>");

            if(i == shopView.getListSkinMapView().size()){
                out.print("</tr>\n" +
                        "    </table>");
            }
            i++;
        }


        //Bandeau Carton :
        //out.print("<img src=>");

        //Tableau Carton
        i = 0;
        out.print("<table><tr>");
        for(SkinCartonView p : shopView.getListSkinCartonView()) {

            if (i%5 == 0 && i != 0){
                out.print("</tr><tr>");
            }

            out.print("<td>"+p.getNom()+"</td>");

            if(i == shopView.getListSkinCartonView().size()){
                out.print("</tr>\n" +
                        "    </table>");
            }
            i++;
        }

        //Bandeau Icone :
        //out.print("<img src=>");

        //Tableau Carton
        i = 0;
        out.print("<table><tr>");
        for(IconeView p : shopView.getListIconeView()) {

            if (i%5 == 0 && i != 0){
                out.print("</tr><tr>");
            }

            out.print("<td>"+p.getNom()+"</td>");

            if(i == shopView.getListIconeView().size()){
                out.print("</tr>\n" +
                        "    </table>");
            }
            i++;
        }


    %>
</p>

</div>
</body>
</html>
