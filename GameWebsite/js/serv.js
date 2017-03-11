

/* definir toutes les cartes sous forme de constantes */

var CARD_BOMB_25 = 0;

/* fin des definitions de cartes */

var NB_MAX_SALON = 6;
var NB_MAX_PDV = 200;
var NB_CARTE_MAIN_MAX = 4;

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
  this.cActives = [];
  this.cActivesNonRetournees = [];
  /* les point du j1 sont MAX_PDV - j2.pdv */
  this.pdv = 100;
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


function tireCarteMain(etatJoueur){

  function difference(element){
    return etatJoueur.main.indexOf(element) == -1;
  }

  /* 4 cartes dans la main on tire donc (4-nb_carte_dans_main) parmis le deck */

  /* on calcule cb de carte on tire dans le deck */

  var nb_carte_a_tirer = NB_CARTE_MAIN_MAX - etatJoueur.main.length;

  var ensemble_deck = etatJoueur.deck.filter(difference);

  for(var i=0;i<nb_carte_a_tirer;i++){
    etatJoueur.main.push(ensemble_deck[Math.floor(Math.random()*NB_CARTE_MAIN_MAX)]);
    ensemble_deck = etatJoueur.deck.filter(difference);
  }

}

function initMatch(pseudo1, pseudo2){


  var queryCartesJ1 = "SELECT id_Carte, coutCarte FROM JoueurCarteDeck JOIN Deck USING (id_Deck) JOIN Carte USING (id_Carte) WHERE (Pseudo LIKE "+connection.escape(pseudo1)+" AND estActif = 1);";
  var queryCartesJ2 = "SELECT id_Carte, coutCarte FROM JoueurCarteDeck JOIN Deck USING (id_Deck) JOIN Carte USING (id_Carte) WHERE (Pseudo LIKE "+connection.escape(pseudo2)+" AND estActif = 1);";

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

});



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

    console.log("emetteur : "+etatJoueurEmetteur.pseudo);
    console.log("adversaire : "+etatJoueurAdversaire.pseudo);
    console.log(possedeCarteDansMain(id_carte));
    console.log(poudreSuffisante(id_carte));

    if(possedeCarteDansMain(id_carte) && poudreSuffisante(id_carte) && verificationTourJoueur()){

      /* on verifie si il n'y a pas de tricherie */

      /* *********************************** ajouter des effets de carte dans cette section ************************************** */

      console.log("carte utilisé par "+pseudo+" : "+id_carte);

      switch (id_carte) {
        case CARD_BOMB_25:
          /* carte zero (BOMB 25)*/

          etatJoueurEmetteur.pdv += 25;
          etatJoueurAdversaire.pdv -= 25;

          break;

        default:
            /* for the moment do nothing */
      }

      etatM.tour++;
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
