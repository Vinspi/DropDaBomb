

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
var NB_MAX_PDV = 150;
var NB_CARTE_MAIN_MAX = 4;
var NB_POUDRE_PAR_TOUR = 5;
var NB_MAX_TIMER = 15;
var NB_MAX_CARTE_ACTIVE = 5;

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


/* ****** CLOCK DU SERVEUR (POUR UPDATE LES TIMERS DES PARTIES) **** */


var CLOCK = setInterval(update_timer, 1000);

function update_timer(){
    for(var i=0; i<NB_MAX_SALON; i++){
        if(listSalons[i] != "LIBRE"){
            if(listSalons[i].timer <= 1){
                listSalons[i].timer = NB_MAX_TIMER;
                finDeTour(listSalons[i]);
            }
            else{
                listSalons[i].timer--;
            }
            io.sockets.to(listSalons[i].nameRoom).emit("CLOCK_UPDATE", listSalons[i].timer);
        }
    }
}



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
  this.carteActiveNonRetourne = [];
  /* les point du j1 sont MAX_PDV - j2.pdv */
  this.pdv = NB_MAX_PDV;
}

function etatMatch(pseudo1,pseudo2,deck1,deck2) {
  this.tour = 1;
  this.joueur1 = new etatJoueur(pseudo1,deck1);
  this.joueur2 = new etatJoueur(pseudo2,deck2);
  this.nameRoom = "";
  this.timer = NB_MAX_TIMER;
}

function effet(id_carte,duree,image_carte){
  this.id_carte = id_carte;
  this.duree = duree; // mettre a -1 pour un effet permanent
  this.image_carte = image_carte;
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



    fifoJoueurs[0].socket.emit('matchStart',{'message' : 'match lancé vous etes dans la salle : '+nameRoom , 'etatJoueur' : etatM.joueur1, 'actifAdversaire' : etatM.joueur2.carteActiveNonRetourne});
    fifoJoueurs[1].socket.emit('matchStart',{'message' : 'match lancé vous etes dans la salle : '+nameRoom , 'etatJoueur' : etatM.joueur2, 'actifAdversaire' : etatM.joueur1.carteActiveNonRetourne});



    etatM.socketJ1 = fifoJoueurs[0].socket;
    etatM.socketJ2 = fifoJoueurs[1].socket;


    console.log(etatM);

    fifoJoueurs.shift();
    fifoJoueurs.shift();

    finDeTour(etatM);

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

  console.log(joueurEmetteur.pseudo+' a infligé '+degats+' à '+joueurCible.pseudo);
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

function ajouteEffet(etatJoueur, effet){
  if(etatJoueur.carteActiveNonRetourne.length < NB_MAX_CARTE_ACTIVE){
    etatJoueur.carteActiveNonRetourne.push(effet);
    console.log('Ajoute effet : '+effet.id_carte+' pour '+effet.duree);
    return true; //insertion réussie;
  }
  return false; // insertion fail
}

function checkEffets(etatJoueur, etatJoueurAdversaire,etatMatch){
  for(var i=0 ; i<NB_MAX_CARTE_ACTIVE ; i++){
    if(etatJoueur.carteActiveNonRetourne[i] === undefined) return; // fin de la liste.
    if(etatJoueur.carteActiveNonRetourne[i].duree == 0){ // timeout de l'effet.

      console.log(etatJoueur.carteActiveNonRetourne[i].id_carte +' durée -- : '+etatJoueur.carteActiveNonRetourne[i].duree+' -> effet appliqué');
      appliqueEffets(etatJoueur,etatJoueurAdversaire,etatJoueur.carteActiveNonRetourne[i]);
      (etatJoueur.carteActiveNonRetourne).splice(i,1);
      if (etatMatch.joueur1.pseudo == etatJoueur.pseudo) {
        etatMatch.socketJ1.emit('update',{'etatJoueur' : etatJoueur, 'actifAdversaire' : etatJoueurAdversaire.carteActiveNonRetourne, 'carteJoue' : etatJoueur.carteActiveNonRetourne[i], 'bouclierAdversaire' : etatJoueurAdversaire.bouclier});
        etatMatch.socketJ2.emit('update',{'etatJoueur' : etatJoueurAdversaire, 'actifAdversaire' : etatJoueur.carteActiveNonRetourne, 'carteJoue' : etatJoueur.carteActiveNonRetourne[i],  'bouclierAdversaire' : etatJoueur.bouclier});
      }
      else {
        etatMatch.socketJ2.emit('update',{'etatJoueur' : etatJoueur, 'actifAdversaire' : etatJoueurAdversaire.carteActiveNonRetourne, 'carteJoue' : etatJoueur.carteActiveNonRetourne[i], 'bouclierAdversaire' : etatJoueurAdversaire.bouclier});
        etatMatch.socketJ1.emit('update',{'etatJoueur' : etatJoueurAdversaire, 'actifAdversaire' : etatJoueur.carteActiveNonRetourne, 'carteJoue' : etatJoueur.carteActiveNonRetourne[i],  'bouclierAdversaire' : etatJoueur.bouclier});

      }

    }
    else{
      etatJoueur.carteActiveNonRetourne[i].duree--; // sinon on décrémente la duree.
      console.log(etatJoueur.carteActiveNonRetourne[i].id_carte +' durée -- : '+etatJoueur.carteActiveNonRetourne[i].duree);

    }
  }
}

function appliqueEffets(etatJoueurEmetteur,etatJoueurAdversaire,effet){
  switch (+effet.id_carte) {

    case CARD_BOMB_50_2TOUR:
      infligeDegats(etatJoueurEmetteur,etatJoueurAdversaire,50);
  //    etatJoueurEmetteur.pdv += 50;
  //    etatJoueurAdversaire.pdv -+ 50;
      break;

    case CARD_BOMB_100_2TOUR:
      infligeDegats(etatJoueurEmetteur,etatJoueurAdversaire,100);
//      etatJoueurEmetteur.pdv += 100;
//      etatJoueurAdversaire.pdv -+ 100;
      break;

    default:

  }
}

function finDeTour(etatMatch){

  /* c'est la fin du tour du joueur 1 */
  if(etatMatch.tour%2 == 0){
    /* on fait gagner de la poudre */
    etatMatch.joueur1.poudre += NB_POUDRE_PAR_TOUR;
    /* on lui fait piocher des cartes */
    tireCarteMain(etatMatch.joueur1);
    checkEffets(etatMatch.joueur1,etatMatch.joueur2,etatMatch);
    etatMatch.socketJ1.emit('FIN_TOUR', {'joueurTour' : etatMatch.joueur2.pseudo, 'etatJoueur' : etatMatch.joueur1, 'actifAdversaire' : etatMatch.joueur2.carteActiveNonRetourne});
    etatMatch.socketJ2.emit('FIN_TOUR', {'joueurTour' : etatMatch.joueur2.pseudo, 'etatJoueur' : etatMatch.joueur2, 'actifAdversaire' : etatMatch.joueur1.carteActiveNonRetourne});
  }
  else {
    /* on fait gagner de la poudre */    /* on fait gagner de la poudre */

    etatMatch.joueur2.poudre += NB_POUDRE_PAR_TOUR;
    /* on lui fait piocher des cartes */
    tireCarteMain(etatMatch.joueur2);
    checkEffets(etatMatch.joueur2,etatMatch.joueur1,etatMatch);
    etatMatch.socketJ1.emit('FIN_TOUR', {'joueurTour' : etatMatch.joueur1.pseudo, 'etatJoueur' : etatMatch.joueur1, 'actifAdversaire' : etatMatch.joueur2.carteActiveNonRetourne});
    etatMatch.socketJ2.emit('FIN_TOUR', {'joueurTour' : etatMatch.joueur1.pseudo, 'etatJoueur' : etatMatch.joueur2, 'actifAdversaire' : etatMatch.joueur1.carteActiveNonRetourne});

  }
  etatMatch.tour++;




  console.log("fin de tour !");

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
    var retirerCarte = true;

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
    console.log(verificationTourJoueur());


    /* on verifie si il n'y a pas de tricherie */
    //retirerCarte = possedeCarteDansMain(id_carte) && poudreSuffisante(id_carte) && verificationTourJoueur();
    if(possedeCarteDansMain(id_carte) && poudreSuffisante(id_carte) && verificationTourJoueur()){
    //if(retirerCarte){

      /*for(var i=0;i<etatJoueurEmetteur.main.length;i++){
          if(etatJoueurEmetteur.main[i].id_Carte == id_carte){
            // on retire la carte joué de la main du joueur //
            carteJoue = etatJoueurEmetteur.main[i];
            etatJoueurEmetteur.main.splice(i,1);
          }
      }

      // il tire une nouvelle carte
      // tireCarteMain(etatJoueurEmetteur); remplace par fonction fin de tour

      // puis on rajoute la carte joué dans le fond du deck
      etatJoueurEmetteur.deck.push(carteJoue);
      etatJoueurEmetteur.poudre -= carteJoue.coutCarte;
      */
      /* *********************************** ajouter des effets de carte dans cette section ************************************** */

      for(var i=0;i<etatJoueurEmetteur.main.length;i++){
        if(etatJoueurEmetteur.main[i].id_Carte == id_carte){
          carteJoue = etatJoueurEmetteur.main[i];
        }
      }
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

        case CARD_BOMB_50_2TOUR:

          retirerCarte = ajouteEffet(etatJoueurEmetteur,new effet(CARD_BOMB_50_2TOUR,2,carteJoue.imageCarte));
          break;
        case CARD_BOMB_100_2TOUR:
          retirerCarte = ajouteEffet(etatJoueurEmetteur,new effet(CARD_BOMB_100_2TOUR,2,carteJoue.imageCarte));
          break;

        case CARD_MELANGE:
          for(var k = 0; k < etatJoueurEmetteur.main.length; k++){
            etatJoueurEmetteur.deck.push(etatJoueurEmetteur.main[i]);
            etatJoueurEmetteur.main.slice(i,1);
          }
          etatJoueurEmetteur.deck = melangeCarte(etatJoueurEmetteur.deck);
          tireCarteMain(etatJoueurEmetteur);
          retirerCarte = false;
          etatJoueurEmetteur.poudre -= carteJoue.coutCarte;
          break;
      }




      if(retirerCarte){
        for(var i=0;i<etatJoueurEmetteur.main.length;i++){
          if(etatJoueurEmetteur.main[i].id_Carte == id_carte){
            // on retire la carte joué de la main du joueur //
            carteJoue = etatJoueurEmetteur.main[i];
            etatJoueurEmetteur.main.splice(i,1);
          }
        }
      }
      // il tire une nouvelle carte
      // tireCarteMain(etatJoueurEmetteur); remplace par fonction fin de tour

      // puis on rajoute la carte joué dans le fond du deck
      if(retirerCarte){
        etatJoueurEmetteur.deck.push(carteJoue);
        etatJoueurEmetteur.poudre -= carteJoue.coutCarte;
      }

      socket.emit('update',{'etatJoueur' : etatJoueurEmetteur, 'actifAdversaire' : etatJoueurAdversaire.carteActiveNonRetourne, 'carteJoue' : carteJoue, 'bouclierAdversaire' : etatJoueurAdversaire.bouclier});
      socket.broadcast.emit('update',{'etatJoueur' : etatJoueurAdversaire, 'actifAdversaire' : etatJoueurEmetteur.carteActiveNonRetourne, 'carteJoue' : carteJoue,  'bouclierAdversaire' : etatJoueurEmetteur.bouclier});

      //etatM.tour++; /* a changer */
      //console.log(etatM);

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
