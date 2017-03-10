/*function etatMatch() {
  this.pseudo1 = "";
  this.pseudo2 = "";

  //OU
  //this.room = "";

  this.bBouclierJ1 = 0;
  this.bBouclierJ2 = 0;
  this.barreDeVie = 0;
  this.mainJ1 = []; // [{id : ,cout : },{...},...]
  this.mainJ2 = [];
  this.deckJ1 = [];
  this.deckJ2 = [];
  this.manaJ1 = [];
  this.manaJ2 = [];
  this.cActivesJ1 = [];
  this.cActivesJ2 = [];
  this.cActivesNonRetourneesJ1 = [];
  this.cActivesNonRetourneesJ2 = [];
}*/

var NB_MAX_SALON = 6;
var NB_MAX_PDV = 200;

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

  return connection.query(queryCartesJ2, function(err, rows, fields){
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
    io.sockets.in(nameRoom).emit('matchStart','match lancé vous etes dans la salle : '+nameRoom);

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
      console.log(pseudo+" cherche un match");
      fournirSalons();
  });

  socket.on('useCard', function(action){
    var idRoom = socket.idRoom;
    var etatM = listSalons[idRoom]
  });

  //Fonction très très longue :
});






initListSalon();
server.listen(8080);
console.log("server pret");
