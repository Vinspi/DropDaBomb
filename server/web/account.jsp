<%@ page import="Manager.RequestStatus" %>
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
<body class="grey lighten-3">

<nav>
    <div class="nav-wrapper nav-perso">
        <a href="#" class="brand-logo"><img src="../img/ICONES/LOGO_DDB.png" class="iconeJoueur"></a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="shop.jsp">Boutique</a></li>
            <li><a href="account.jsp">Inscription</a></li>
            <li><a href="log.jsp">Connexion</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col s12 m4 l4">
            <div class="card card_signup" id="first-card">


                <% if(request.getAttribute("STATUS") != null && request.getAttribute("STATUS").equals(RequestStatus.CREATE_ACCOUNT_SUCCES)) {%>
                    <%@include file="accountSucces.jsp"%>
                <%}%>
                <% if(request.getAttribute("STATUS") == null || !request.getAttribute("STATUS").equals(RequestStatus.CREATE_ACCOUNT_SUCCES)) { %>
                    <%@include file="accountFailed.jsp"%>
                <%}%>


            </div>
        </div>
        <div class="col s12 m4 l4">
            <div class="card card_signup" id="second-card">
                <div class="card-content title-card">
                    <div class="center-align amber">
                        <span class="card-title white-text">2</span>
                        <div class="bottom-arrow bottom-arrow-telechargement"></div>
                    </div>
                    <div class="center-align titre-telechargement">
                        <span class="card-title white-text">Téléchargez l'application</span>
                    </div>
                    <div class="card-download">
                        <p>
                            Vous avez maintenant un compte, téléchargez l'application DropDaBomb depuis
                            Google play et installez la sur votre smartphone !
                        </p>

                    </div>
                    <div class="center-align" id="btn-download">
                        <a class="btn-large waves-effect waves-light amber">Téléchargement</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col s12 m4 l4">
            <div class="card card_signup" id="third-card">
                <div class="card-content title-card">
                    <div class="center-align blue">
                        <span class="card-title white-text">3</span>
                        <div class="bottom-arrow bottom-arrow-connexion"></div>
                    </div>
                    <div class="center-align titre-connexion">
                        <span class="card-title white-text">Amusez vous !</span>
                    </div>
                    <div class="card-download">
                        <p>
                            Vous etes maintenant prêt à vous mesurer aux autres joueurs, battez vous, grimpez le ladder,
                            soyer malin et stratège pour vaincre vos adversaire,
                            devenez le meilleur joueur de DropDaBomb et n'oubliez pas de passer par notre boutique !
                        </p>

                    </div>
                    <div class="center-align" id="btn-connexion">
                        <a class="btn-large waves-effect waves-light blue" href="log.jsp" >Connexion</a>
                    </div>
                </div>
            </div>
        </div>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script>
            $(document).ready(function(){

                var max_height_card = Math.max($('#first-card').height(),$('#second-card').height(),$('#third-card').height());
                $('#second-card').height(max_height_card);
                $('#third-card').height(max_height_card);
                $('#first-card').height(max_height_card);



            });
        </script>

    </div>
</div>
</body>
</html>