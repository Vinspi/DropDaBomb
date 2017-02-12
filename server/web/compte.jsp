<%@ page import="Manager.InventoryManager" %>
<%@ page import="View.InventoryView" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="View.CardView" %><%--
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

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
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
    <div class="nav-wrapper blue darken-3">
        <a href="#" class="brand-logo">DropDaBomb</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="shop.jsp">Boutique</a></li>
            <li><a href="account.jsp">Inscription</a></li>
            <%

                System.out.println("pseudo = "+session.getAttribute("pseudo"));
                String icone = (String) request.getSession().getAttribute("iconeJoueur");
                System.out.println("icone = "+icone);
                if(session.getAttribute("pseudo") == null) {
                    out.print("<li><a href=\"log.jsp\">Connexion</a></li>");
                }
                else {
                    out.print("<li><a href=\"compte.jsp\">" + pseudo + "</a></li>" +
                            "<li><img src=\"../img/iconeJoueur/"+icone+"\" alt=\"\" class=\"circle\" id=\"iconeJoueur\"></li>");
                }

            %>
        </ul>
    </div>
</nav>

<div class="container">
    <h5 class="center-align">Mes decks</h5>
    <div class="card deck-container">

        <div class="card-tabs">
            <ul class="tabs tabs-fixed-width">
                <li class="tab"><a class="active" href="#deck1">Deck 1</a></li>
                <li class="tab"><a href="#deck2">Deck 2</a></li>
            </ul>
        </div>
        <div class="card-content">
            <div id="deck1">
                <div class="center-align">
                    <div class="row">
                        <%
                         for(int i=0;i<4;i++){
                            out.print("<div class=\"col s3 m3 l3\">" +
                                    "                        <div class=\"card carte-deck\">" +
                                    "                            <div class=\"card-image\"> " +
                                    "                               <img src=\"../img/img-carte/"+inventoryView.getDeck1().getCards().get(i).getImageCarte()+"\">" +
                                    "                              </div>" +
                                    "                           </div>" +
                                    "   </div>");
                         }
                        %>
                    </div>
                    <div class="row">
                        <%
                            for(int i=4;i<8;i++){
                                out.print("<div class=\"col s3 m3 l3\">" +
                                        "                        <div class=\"card carte-deck\">" +
                                        "                            <div class=\"card-image\"> " +
                                        "                               <img src=\"../img/img-carte/"+inventoryView.getDeck1().getCards().get(i).getImageCarte()+"\">" +
                                        "                              </div>" +
                                        "                           </div>" +
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
                                        "                        <div class=\"card carte-deck\">" +
                                        "                            <div class=\"card-image\"> " +
                                        "                               <img src=\"../img/img-carte/"+inventoryView.getDeck2().getCards().get(i).getImageCarte()+"\">" +
                                        "                              </div>" +
                                        "                           </div>" +
                                        "   </div>");
                            }
                        %>
                    </div>
                    <div class="row">
                        <%
                            for(int i=4;i<8;i++){
                                out.print("<div class=\"col s3 m3 l3\">" +
                                        "                        <div class=\"card carte-deck\">" +
                                        "                            <div class=\"card-image\"> " +
                                        "                               <img src=\"../img/img-carte/"+inventoryView.getDeck2().getCards().get(i).getImageCarte()+"\">" +
                                        "                              </div>" +
                                        "                           </div>" +
                                        "   </div>");
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="section-carte-compte">
        <h5 class="center-align">Mes cartes</h5>
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
                            "       <div class=\"card-image\">" +
                            "           <img src=\"../img/img-carte/"+difference1.get(i-1).getImageCarte()+"\">" +
                            "       </div>" +
                            "   </div>");
                    out.print("</div>");
                    if(i%4 == 0) out.print("</div><div class=\"row\">");
                }

            %>
            </div>
        </div>
        <div id="section-carte-compte-2" style="display: none">
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

                    for (int i=1;i<difference1.size()+1;i++){
                        out.print("<div class=\"col s3 m3 l3\">");
                        out.print("<div class=\"card carte-deck\">" +
                                "       <div class=\"card-image\">" +
                                "           <img src=\"../img/img-carte/"+difference1.get(i-1).getImageCarte()+"\">" +
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

</script>

</body>
</html>
