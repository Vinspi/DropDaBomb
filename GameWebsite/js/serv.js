

/* definir toutes les cartes sous forme de constantes */

var CARD_BOMB_25 = 0;
var CARD_BOMB_50 = 1;
var CARD_BOMB_50_2TOUR = 2;
var CARD_BOMB_100_2TOUR = 3;
var CARD_BOUCLIER_15 = 4;
var CARD_BOUCLIER_45 = 5;
var CARD_BOUCLIER_GEN_POUDRE = 6;
var CARD_ECHANGE_FORCE = 7;
var CARD_DESENVOUTEMENT = 8;
var CARD_MELANGE = 9;

/* fin des definitions de cartes */

var NB_MAX_SALON = 6;
var NB_MAX_PDV = 250;
var NB_CARTE_MAIN_MAX = 4;
var NB_POUDRE_PAR_TOUR = 5;

var fifoJoueurs = [];
var listSalons = [];

var http = require('http');
var url = require('url');
var fs = require('fs');
var express = require("express");


var mysql = require('mysql');

var connection = mysql.createConnection({
  host : '217.182.69.175',
  user : 'Vinspi',
  password : 'vinspi13',
  database : 'DropDaBomb',
});

connection.connect();

/* ******************************************************************* */

function initListSalon(){
  for(var i=0;i<NB_MAX_SALON;i++){
    listSalons[i] = "LIBRE";
  }
}

function insereSalon(room){
  for(var i=0;i<NB_MAX_SALON;i++){
    if(listSalons[i] == "LIBRE"){
      listSalons[i] = room;
      /* insertion réussie */
      return i;
    }
  }
  return -1;
}

/* ******************************************************************* */

function etatJoueur(pseudo,deck){
  this.pseudo = pseudo;
  this.bouclier = 0;
  this.poudre = 25;
  this.main = [];
  this.deck = deck;
  this.effetsRetardement = [];
  this.cActives = [];
  this.cActivesNonRetournees = [];
  /* les point du j1 sont MAX_PDV - j2.pdv */
  this.pdv = NB_MAX_PDV;
}

function etatMatch(pseudo1,pseudo2,deck1,deck2) {
  this.tour = 1;
  this.joueur1 = new etatJoueur(pseudo1,deck1);
  this.joueur2 = new etatJoueur(pseudo2,deck2);
  this.nameRoom = "";
}

function Room(name, pseudo1, pseudo2){
  this.name = name;
  this.pseudo1 = pseudo1;
  this.pseudo2 = pseudo2;
}

function fournirSalons(){

  if (fifoJoueurs.length >= 2){

    initMatch(fifoJoueurs[0].pseudo,fifoJoueurs[1].pseudo);

   }
}

function melangeCarte(cartes){

  function echange(i,j){
    var tmp = cartes[i];
    cartes[i] = cartes[j];
    cartes[j] = tmp;
  }

  var j;
  /* deck de huit carte */

  for (var i = 0; i < 8; i++) {
    j = Math.floor(Math.random()*7);
    echange(i,j);
  }
  return cartes;
}

function tireCarteMain(etatJoueur){

  var carteSommetDeck;

  /* 4 cartes dans la main on tire donc (4-nb_carte_dans_main) parmis le deck */

  /* on calcule cb de carte on tire dans le deck */

  var nb_carte_a_tirer = NB_CARTE_MAIN_MAX - etatJoueur.main.length;


  for(var i=0;i<nb_carte_a_tirer;i++){
    /* on recupère une carte du sommet du deck */
    carteSommetDeck = etatJoueur.deck[0];

    /* on retire cette carte du deck de façon à ce qu'elle ne soit pas tiré deux fois */
    etatJoueur.deck.splice(0,1);

    /* puis on ajoute la carte tiré à la main du joueur */
    etatJoueur.main.push(carteSommetDeck);

  }

}

function initMatch(pseudo1, pseudo2){


  var queryCartesJ1 = "SELECT id_Carte, coutCarte, imageCarte FROM JoueurCarteDeck JOIN Deck USING (id_Deck) JOIN Carte USING (id_Carte) WHERE (Pseudo LIKE "+connection.escape(pseudo1)+" AND estActif = 1);";
  var queryCartesJ2 = "SELECT id_Carte, coutCarte, imageCarte FROM JoueurCarteDeck JOIN Deck USING (id_Deck) JOIN Carte USING (id_Carte) WHERE (Pseudo LIKE "+connection.escape(pseudo2)+" AND estActif = 1);";

  var deck1 = [];
  var deck2 = [];



  connection.query(queryCartesJ1, function(err, rows, fields){
    if (err) throw err;
    for(var i in rows){
      deck1.push(rows[i]);
    }
  });

  connection.query(queryCartesJ2, function(err, rows, fields){
    if (err) throw err;
    for(var i in rows){
      deck2.push(rows[i]);
    }

    var etatM = new etatMatch(pseudo1, pseudo2, deck1, deck2);

    var index = insereSalon(etatM);
    if(index > -1) var nameRoom = "room"+index;
    console.log("nameRoom = "+nameRoom);
    etatM.nameRoom = nameRoom;

    fifoJoueurs[0].socket.idRoom = index;
    fifoJoueurs[1].socket.idRoom = index;
    (fifoJoueurs[0].socket).join(nameRoom);
    (fifoJoueurs[1].socket).join(nameRoom);

    etatM.joueur1.deck = melangeCarte(etatM.joueur1.deck);
    etatM.joueur2.deck = melangeCarte(etatM.joueur2.deck);

    tireCarteMain(etatM.joueur1);
    tireCarteMain(etatM.joueur2);



    fifoJoueurs[0].socket.emit('matchStart',{'message' : 'match lancé vous etes dans la salle : '+nameRoom , 'etatJoueur' : etatM.joueur1, 'actifAdversaire' : etatM.joueur2.cActivesNonRetournees});
    fifoJoueurs[1].socket.emit('matchStart',{'message' : 'match lancé vous etes dans la salle : '+nameRoom , 'etatJoueur' : etatM.joueur2, 'actifAdversaire' : etatM.joueur1.cActivesNonRetournees});


    console.log(etatM);

    fifoJoueurs.shift();
    fifoJoueurs.shift();
    fournirSalons();
  });

}

var server = http.createServer(function(req,res){

  var url_p = url.parse(req.url,true,true);
  console.log(url_p.pathname);
  if(url_p.pathname == '/cli.html'){
    fs.readFile('./cli.html','utf-8',function(error,content){
      res.writeHead(200,{"Content-type":"text/html"});
      res.end(content);
    });
  }
  if(url_p.pathname == '/js/cli.js'){
    fs.readFile('./js/cli.js','utf-8',function(error,content){
      res.writeHead(200,{"Content-type":"text/html"});
      res.end(content);
    });
  }
  else {

    fs.readFile(url_p.pathname,'utf-8',function(error,content){
      res.writeHead(200,{"Content-type":"text/html"});
      res.end(content);
    });
  }

});

function infligeDegats(joueurEmetteur, joueurCible, degats){

  var residu;
  var bouclier = joueurCible.bouclier;

  residu = degats - bouclier;
  bouclier = bouclier - degats;
  if(residu < 0) residu = 0;
  if(bouclier < 0) bouclier = 0;

  joueurCible.pdv -= residu;
  joueurCible.bouclier = bouclier;
  joueurEmetteur.pdv += residu;

}

function finDeTour(etatJoueur){
  /* on fait gagner de la poudre */
  etatJoueur.poudre += NB_POUDRE_PAR_TOUR;
  /* on lui fait piocher des cartes */
  tireCarteMain(etatJoueur);

}

var io = require('socket.io').listen(server);

io.sockets.on('connection', function (socket){

  socket.on('chercherMatch',function(pseudo){
      fifoJoueurs.push({socket: socket,pseudo: pseudo});
      socket.pseudo = pseudo;
      console.log(pseudo+" cherche un match");
      fournirSalons();
  });

  socket.on('useCard', function(action){

    var idRoom = socket.idRoom;
    var etatM = listSalons[idRoom];

    var pseudo = action.pseudo;
    var id_carte = action.id_carte;

    var etatJoueurEmetteur = etatM.joueur1.pseudo == pseudo ? etatM.joueur1 : etatM.joueur2;
    var etatJoueurAdversaire = etatM.joueur1.pseudo == pseudo ? etatM.joueur2 : etatM.joueur1;

    var carteJoue;

    /* fonctions de contrôle */

    function poudreSuffisante(id_carte){
      for(var i=0;i<etatJoueurEmetteur.main.length;i++){
        if(etatJoueurEmetteur.main[i].id_Carte == id_carte && etatJoueurEmetteur.main[i].coutCarte <= etatJoueurEmetteur.poudre){
          return true;
        }
      }
      return false;
    }

    function possedeCarteDansMain(id_carte){
      for(var i=0;i<etatJoueurEmetteur.main.length;i++){
        if(etatJoueurEmetteur.main[i].id_Carte == id_carte){
          return true;
        }
      }
      return false;
    }

    function verificationTourJoueur(){
      /* soit tour joueur1 */
      if(pseudo == etatM.joueur1.pseudo){
        if(etatM.tour%2 == 0) return true;
        else return false;
      }
      /* soit tour joueur 2 */
      else {
        if(etatM.tour%2 == 1) return true;
        else return false;
      }
    }

    /**********************************************************************************************************************************/

    console.log("emetteur : "+etatJoueurEmetteur.pseudo);
    console.log("adversaire : "+etatJoueurAdversaire.pseudo);
    console.log(possedeCarteDansMain(id_carte));
    console.log(poudreSuffisante(id_carte));


    /* on verifie si il n'y a pas de tricherie */
    if(possedeCarteDansMain(id_carte) && poudreSuffisante(id_carte) && verificationTourJoueur()){



      /* *********************************** ajouter des effets de carte dans cette section ************************************** */

      console.log("carte utilisé par "+pseudo+" : "+id_carte);

      switch (+id_carte) {
        case CARD_BOMB_25:
          /* carte zero (BOMB 25)*/
          infligeDegats(etatJoueurEmetteur,etatJoueurAdversaire,25);

          break;

        case CARD_BOMB_50:

          infligeDegats(etatJoueurEmetteur,etatJoueurAdversaire,50);

          break;

        case CARD_BOUCLIER_45:

          etatJoueurEmetteur.bouclier += 45;
          break;

        case CARD_BOUCLIER_15:

          etatJoueurEmetteur.bouclier += 15;
          break;
      }


      for(var i=0;i<etatJoueurEmetteur.main.length;i++){
        if(etatJoueurEmetteur.main[i].id_Carte == id_carte){
          /* on retire la carte joué de la main du joueur */
          carteJoue = etatJoueurEmetteur.main[i];
          etatJoueurEmetteur.main.splice(i,1);
        }
      }

      /* il tire une nouvelle carte */
      tireCarteMain(etatJoueurEmetteur);

      /* puis on rajoute la carte joué dans le fond du deck */
      etatJoueurEmetteur.deck.push(carteJoue);
      etatJoueurEmetteur.poudre -= carteJoue.coutCarte;

      socket.emit('update',{'etatJoueur' : etatJoueurEmetteur, 'actifAdversaire' : etatJoueurAdversaire.cActivesNonRetournees, 'carteJoue' : carteJoue, 'bouclierAdversaire' : etatJoueurAdversaire.bouclier});
      socket.broadcast.emit('update',{'etatJoueur' : etatJoueurAdversaire, 'actifAdversaire' : etatJoueurEmetteur.cActivesNonRetournees, 'carteJoue' : carteJoue,  'bouclierAdversaire' : etatJoueurEmetteur.bouclier});

      etatM.tour++; /* a changer */
      console.log(etatM);

      /* ************************************************************************************************************************** */

    }
    else {
      /* tricherie ! */
      console.log("tricherie !");
    }

  });

  //Fonction très très longue :
});






initListSalon();
server.listen(8080);
console.log("server pret");
