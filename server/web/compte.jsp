<%@ page import="Manager.InventoryManager" %>
<%@ page import="View.CardView" %>
<%@ page import="View.InventoryView" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: vinspi
  Date: 10/02/17
  Time: 14:35
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

    InventoryManager inventoryManager = new InventoryManager();
    InventoryView inventoryView = inventoryManager.createInventoryView(pseudo);


%>

<nav>
    <div class="nav-wrapper nav-perso">
        <a href="#" class="brand-logo"><img src="../img/ICONES/LOGO_DDB.png" class="iconeJoueur"></a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="shop.jsp">Boutique</a></li>
            <%

                System.out.println("pseudo = "+session.getAttribute("pseudo"));
                String icone = (String) request.getSession().getAttribute("iconeJoueur");
                System.out.println("icone = "+icone);
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

<div class="container">
    <div>
        <%--<h5 class="center-align mesdecks white-text amber darken-2 luna">Mes decks</h5>--%>
        <%--<div class="bottom-arrow-mesdecks"></div>--%>
    </div>
    <div class="card deck-container">

        <div class="card-tabs">
            <ul class="tabs tabs-fixed-width wooden-bg">
                <li class="tab"><a class="active text-neon-blue white-text luna" onclick="swapCardsView()" href="#deck1">Deck 1</a></li>
                <li class="tab"><a class="text-neon-blue white-text luna " onclick="swapCardsView()" href="#deck2">Deck 2</a></li>
            </ul>
        </div>
        <div class="card-content wooden-bg">
            <div id="deck1">
                <div class="center-align">
                    <div class="row">
                        <%
                         for(int i=0;i<4;i++){
                            out.print("<div class=\"col s3 m3 l3\">" +
                                    "                            <div class=\"card-image grow\" onclick=\"selectCardDeck($(this).attr('id'))\" id=\"a" + inventoryView.getDeck1().getCards().get(i).getId_carte() + "\"> " +
                                    "                               <img src=\"../img/CARDS/"+inventoryView.getDeck1().getCards().get(i).getImageCarte()+"\">" +
                                    "                              </div>" +
                                    "   </div>");
                         }
                        %>
                    </div>
                    <div class="row">
                        <%
                            for(int i=4;i<8;i++){
                                out.print("<div class=\"col s3 m3 l3\">" +
                                        "                            <div class=\"card-image grow\" onclick=\"selectCardDeck($(this).attr('id'))\"  id=\"a" + inventoryView.getDeck1().getCards().get(i).getId_carte() + "\"> " +
                                        "                               <img src=\"../img/CARDS/"+inventoryView.getDeck1().getCards().get(i).getImageCarte()+"\">" +
                                        "                              </div>" +
                                        "   </div>");
                            }
                        %>
                    </div>
                </div>
            </div>
            <div id="deck2">
                <div class="center-align">
                    <div class="row">
                        <%
                            for(int i=0;i<4;i++){
                                out.print("<div class=\"col s3 m3 l3\">" +
                                        "                            <div class=\"card-image grow\" onclick=\"selectCardDeck($(this).attr('id'))\" id=\"b" + inventoryView.getDeck2().getCards().get(i).getId_carte() + "\"> " +
                                        "                               <img src=\"../img/CARDS/"+inventoryView.getDeck2().getCards().get(i).getImageCarte()+"\">" +
                                        "                              </div>" +
                                        "   </div>");
                            }
                        %>
                    </div>
                    <div class="row">
                        <%
                            for(int i=4;i<8;i++){
                                out.print("<div class=\"col s3 m3 l3\">" +
                                        "                            <div class=\"card-image grow\" onclick=\"selectCardDeck($(this).attr('id'))\" id=\"b" + inventoryView.getDeck2().getCards().get(i).getId_carte() + "\"> " +
                                        "                               <img src=\"../img/CARDS/"+inventoryView.getDeck2().getCards().get(i).getImageCarte()+"\">" +
                                        "                              </div>" +
                                        "   </div>");
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="section-carte-compte">
        <div>
            <h5 class="center-align mesdecks white-text amber darken-2 luna">Mes cartes</h5>
            <div class="bottom-arrow-mesdecks"></div>
        </div>
        <div id="section-carte-compte-1">
            <div class="row">
            <%

                /*
                    faire la difference de deck1 et de deck0 pour afficher les carte que le joueur possède
                    mais qui ne sont pas dans le deck actuel
                 */

                ArrayList<CardView> difference1 = new ArrayList<CardView>();


                boolean trouve = false;
                for (CardView card:inventoryView.getPlayerCards()){
                    trouve = false;
                    for(CardView cardDeck:inventoryView.getDeck1().getCards()){
                        if(card.getId_carte() == cardDeck.getId_carte()){
                            trouve = true;
                            break;
                        }
                    }
                    if(!trouve) difference1.add(card);
                }

                /* ensuite on affiche les cartes qui ne sont pas dans le deck actuel */

                for (int i=1;i<difference1.size()+1;i++){
                    out.print("<div class=\"col s3 m3 l3\">");
                    out.print("<div class=\"card carte-deck\">" +
                            "       <div class=\"card-image grow\" onclick=\"selectCard($(this).attr('id'))\" id=\"a" + difference1.get(i-1).getId_carte() + "\">" +
                            "           <img src=\"../img/CARDS/"+difference1.get(i-1).getImageCarte()+"\">" +
                            "       </div>" +
                            "   </div>");
                    out.print("</div>");
                    if(i%4 == 0) out.print("</div><div class=\"row\">");
                }

            %>
            </div>
        </div>
        <div id="section-carte-compte-2">
            <div class="row">
                <%

                    /*
                        faire la difference de deck1 et de deck0 pour afficher les carte que le joueur possède
                        mais qui ne sont pas dans le deck actuel
                     */

                    ArrayList<CardView> difference2 = new ArrayList<CardView>();

                    trouve = false;
                    for (CardView card:inventoryView.getPlayerCards()){
                        trouve = false;
                        for(CardView cardDeck:inventoryView.getDeck2().getCards()){
                            if(card.getId_carte() == cardDeck.getId_carte()){
                                trouve = true;
                                break;
                            }
                        }
                        if(!trouve) difference2.add(card);
                    }

                /* ensuite on affiche les cartes qui ne sont pas dans le deck actuel */

                    for (int i=1;i<difference2.size()+1;i++){
                        out.print("<div class=\"col s3 m3 l3\">");
                        out.print("<div class=\"card carte-deck\">" +
                                "       <div class=\"card-image grow\" onclick=\"selectCard($(this).attr('id'))\" id=\"b" + difference2.get(i-1).getId_carte() + "\">" +
                                "           <img src=\"../img/CARDS/"+difference2.get(i-1).getImageCarte()+"\">" +
                                "       </div>" +
                                "   </div>");
                        out.print("</div>");
                        if(i%4 == 0) out.print("</div><div class=\"row\">");
                    }

                %>
            </div>
        </div>
    </div>
</div>

<script>
    var deckVisible = 1;
    var lastCardSelected = -1;
    var lastCardDeckSelected = -1;
    var lastSwitched1;
    var lastSwitched2;

    $(document).ready(function () {
        $('#section-carte-compte-2').hide();
    });

    function swapCardsView() {
        if(deckVisible == 1) {
            $('#section-carte-compte-1').hide();
            $('#section-carte-compte-2').show();
            deckVisible = 2;
        }
        else {
            $('#section-carte-compte-1').show();
            $('#section-carte-compte-2').hide();
            deckVisible = 1;
        }
    }

    function selectCard(value) {




        /* selection de la carte pour la premiere fois */
        if(lastCardSelected == -1){
            lastCardSelected = value;

            $('#'+value).addClass("shake");
            $('#'+value).removeClass("grow");
            if(lastCardDeckSelected != -1){
                console.log($('#'+lastCardSelected).children().attr('src'));

                var attrLastCardSelected = $('#'+lastCardSelected).children().attr('src');
                var attrLastCardDeckSelected = $('#'+lastCardDeckSelected).children().attr('src');

                /* swap cards images */

                $('#'+lastCardDeckSelected).addClass('tmpClass1');
                $('#'+lastCardSelected).addClass('tmpClass2');

                $('#'+lastCardSelected).children().attr('src',attrLastCardDeckSelected);
                $('#'+lastCardDeckSelected).children().attr('src',attrLastCardSelected);

                $('.tmpClass2').attr('id',lastCardDeckSelected);
                $('.tmpClass1').attr('id',lastCardSelected);


                $('#'+lastCardSelected).removeClass("tmpClass1");
                $('#'+lastCardDeckSelected).removeClass("tmpClass2");


                /* end swap */

                /* envoyer la requete vive l'ajax */
                $.ajax({

                    url : 'DeckSwap',

                    type : 'GET',

                    data : 'id_deck='+deckVisible+'&id_carte='+lastCardSelected.substring(1)+'&id_carteDeck='+lastCardDeckSelected.substring(1),

                    succes: function (code_html,status) {
                        console.log("succes "+status);
                        alert("coucou");

                    },

                    error: function (code_html,status) {
                        console.log("request failed "+status);
                    }

                });
                $('#'+lastCardSelected).removeClass("shake");
                $('#'+lastCardDeckSelected).removeClass("shake");
                $('#'+lastCardSelected).addClass("grow");
                $('#'+lastCardDeckSelected).addClass("grow");


                $("#"+lastCardSelected).bind('oanimationend animationend webkitAnimationEnd', function() {
                    $('#'+lastSwitched1).removeClass("switch");
                });

                $("#"+lastCardDeckSelected).bind('oanimationend animationend webkitAnimationEnd', function() {

                    $('#'+lastSwitched2).removeClass("switch");
                });


                lastSwitched1 = lastCardSelected;
                lastSwitched2 = lastCardDeckSelected;


                $('#'+lastCardSelected).addClass("switch");
                $('#'+lastCardDeckSelected).addClass("switch");


                lastCardSelected = -1;
                lastCardDeckSelected = -1;


            }
        }
        else {
            $('#'+lastCardSelected).removeClass("shake");
            $('#'+lastCardSelected).addClass("grow");
            lastCardSelected = value;
            $('#'+value).addClass("shake");
            $('#'+value).removeClass("grow");
        }


    }

    function selectCardDeck(value) {


        /* selection de la carte pour la premiere fois */
        if(lastCardDeckSelected == -1){
            lastCardDeckSelected = value;
            console.log('#'+value);

            $('#'+value).addClass("shake");
            $('#'+value).removeClass("grow");
            if(lastCardSelected != -1){

                var attrLastCardSelected = $('#'+lastCardSelected).children().attr('src');
                var attrLastCardDeckSelected = $('#'+lastCardDeckSelected).children().attr('src');

                /* swap cards images */

                $('#'+lastCardDeckSelected).addClass('tmpClass1');
                $('#'+lastCardSelected).addClass('tmpClass2');

                $('#'+lastCardSelected).children().attr('src',attrLastCardDeckSelected);
                $('#'+lastCardDeckSelected).children().attr('src',attrLastCardSelected);

                $('.tmpClass2').attr('id',lastCardDeckSelected);
                $('.tmpClass1').attr('id',lastCardSelected);


                $('#'+lastCardSelected).removeClass("tmpClass1");
                $('#'+lastCardDeckSelected).removeClass("tmpClass2");



                /* end swap */


                /* envoyer la requete vive l'ajax */
                $.ajax({

                    url : 'DeckSwap',

                    type : 'GET',

                    data : 'id_deck='+deckVisible+'&id_carte='+lastCardSelected.substring(1)+'&id_carteDeck='+lastCardDeckSelected.substring(1),

                    succes: function (code_html,status) {
                        console.log("succes "+status);
                        alert("coucou");
                    },

                    error: function (code_html,status) {
                        console.log("request failed "+status);
                    }

                });
                $('#'+lastCardSelected).removeClass("shake");
                $('#'+lastCardDeckSelected).removeClass("shake");
                $('#'+lastCardSelected).addClass("grow");
                $('#'+lastCardDeckSelected).addClass("grow");

                $("#"+lastCardSelected).bind('oanimationend animationend webkitAnimationEnd', function() {
                    $('#'+lastSwitched1).removeClass("switch");
                });

                $("#"+lastCardDeckSelected).bind('oanimationend animationend webkitAnimationEnd', function() {

                    $('#'+lastSwitched2).removeClass("switch");
                });


                lastSwitched1 = lastCardSelected;
                lastSwitched2 = lastCardDeckSelected;

                $('#'+lastCardSelected).addClass("switch");
                $('#'+lastCardDeckSelected).addClass("switch");

                lastCardSelected = -1;
                lastCardDeckSelected = -1;

            }

        }
        else {
            $('#'+lastCardDeckSelected).removeClass("shake");
            $('#'+lastCardDeckSelected).addClass("grow");
            lastCardDeckSelected = value;
            $('#'+value).addClass("shake");
            $('#'+value).removeClass("grow");
        }

    }

</script>

</body>
</html>
