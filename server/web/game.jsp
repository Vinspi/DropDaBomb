<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta title="Index"/>

    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="js/const.js"></script>
    <script type="text/javascript" src="js/init.js"></script>


    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="css/game.css"  media="screen,projection"/>

</head>




<body class="white">
<p id="pseudo1"><%out.print((String) session.getAttribute("pseudo"));%></p>
<div class="" id="zone">

    <div id="zone_barre" class="transparent">

        <div id="zone_barre_container">

            <div id="zone_barre_barre_fond" class="">

                <div id="zone_barre_barre_fond_left" class="light-green darken-3"></div>
                <div id="zone_barre_barre_fond_bouclier_left" class="purple"></div>
                <div id="zone_barre_barre_fond_cursor" class="black"></div>
                <div id="zone_barre_barre_fond_bouclier_right" class="deep-purple"></div>
                <div id="zone_barre_barre_fond_right" class="red accent-3"></div>

            </div>

        </div>

        <div id="zone_barre_timer_container" class="center-align">

            <div id="zone_barre_timer_separator"></div>
            <div id="zone_barre_timer_idjoueur">Vinspi</div>
            <div id="zone_barre_timer_chrono"> <img src="img/timer_white.png"></div>
            <div id="zone_barre_timer_time">00:15</div>
            <div id="zone_barre_timer_separator"></div>


        </div>

    </div>


    <div id="zone_jeu" class="transparent">


        <div id="zone_jeu_cards_adversaire" class="">

            <div class="zone_jeu_separator"></div>

            <img src="img/BOUCLIER_15.png" id="zone_jeu_cards_adversaire_card1" class="zone_jeu_card grey">

            <div class="zone_jeu_cards_separator"></div>

            <img src="img/BOUCLIER_RENVOI.png" id="zone_jeu_cards_adversaire_card2" class="zone_jeu_card grey">

            <div class="zone_jeu_cards_separator"></div>

            <img src="img/BOUCLIER_45.png" id="zone_jeu_cards_adversaire_card3" class="zone_jeu_card grey">

            <div class="zone_jeu_cards_separator"></div>

            <img src="img/CARD_DEFAULT_VERSO.png" id="zone_jeu_cards_adversaire_card4" class="zone_jeu_card grey">

            <div class="zone_jeu_cards_separator"></div>

            <img src="img/CARD_BOMB_VERSO.png" id="zone_jeu_cards_adversaire_card5" class="zone_jeu_card grey">

            <div class="zone_jeu_separator"></div>

        </div>

        <div id="zone_jeu_cards_middle" class="">

            <img src="img/BOMB_50_2TOURS.png" id="zone_jeu_card_middle_card" class="grey">

        </div>

        <div id="zone_jeu_cards_player" class="">


            <div class="zone_jeu_separator"></div>

            <img src="img/BOUCLIER_RENVOI.png" id="zone_jeu_cards_player_card1" class="zone_jeu_card grey">

            <div class="zone_jeu_cards_separator"></div>

            <img src="img/BOUCLIER_45.png" id="zone_jeu_cards_player_card2" class="zone_jeu_card grey zone_jeu_card_marginLeft">

            <div class="zone_jeu_cards_separator"></div>

            <img src="img/CARD_BOMB_VERSO.png" id="zone_jeu_cards_player_card3" class="zone_jeu_card grey zone_jeu_card_marginLeft">

            <div class="zone_jeu_cards_separator"></div>

            <img src="img/CARD_DEFAULT_VERSO.png" id="zone_jeu_cards_player_card4" class="zone_jeu_card grey zone_jeu_card_marginLeft">

            <div class="zone_jeu_cards_separator"></div>

            <img src="img/CARD_DEFAULT_VERSO.png" id="zone_jeu_cards_player_card5" class="zone_jeu_card grey zone_jeu_card_marginLeft">

            <div class="zone_jeu_separator"></div>


        </div>


    </div>



    <div id="zone_deck" class="transparent">

        <div id="zone_deck_container">

            <div id="zone_deck_infos" class="">

                <div id="zone_deck_infos_container">

                    <div id="zone_deck_infos_container_top">

                        <div id="zone_deck_infos_chat" class="center">
                            <img src="img/logo_chat_black.png">
                        </div>

                        <div id="zone_deck_infos_settings" class="center">
                            <img src="img/logo_settings_black.png">
                        </div>

                    </div>

                    <div id="zone_deck_infos_container_bottom">
                        <div id="zone_deck_infos_powder" class="center">
                            <img src="img/powder.png">
                            <div id="zone_deck_infos_bottom_overlay" class="white-text" >25</div>
                        </div>
                    </div>


                </div>

            </div>

            <div id="zone_deck_separator" class="black"></div>

            <div id="zone_deck_cards" class="">

                <div id="zone_deck_cards_container">

                    <div id="zone_deck_cards_card1" class="">
                        <img src="img/BOMB_25.png" class="card_clickable">
                    </div>

                    <div id="zone_deck_cards_card2" class="">
                        <img src="img/BOUCLIER_15.png" class="card_clickable">
                    </div>

                    <div id="zone_deck_cards_card3" class="">
                        <img src="img/BOUCLIER_RENVOI.png" class="card_clickable">
                    </div>

                    <div id="zone_deck_cards_card4" class="">
                        <img src="img/SPELL_BRULURE_DE_POWDER.png" class="card_clickable">
                    </div>


                </div>

            </div>

        </div>

    </div>

</div>

</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/1.7.3/socket.io.js"></script>
<script src="js/cli.js"></script>

</html>

