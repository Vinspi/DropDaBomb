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

var fifoJoueurs = [];
var listSalons = [];

var http = require('http');
var url = require('url');
var fs = require('fs');


var mysql = require('mysql');
var connection = mysql.createConnection({
  host : '109.7.220.208',
  user : 'Vinspi',
  password : 'vinspi13',
  database : 'DropDaBomb',
});



function etatJoueur(pseudo,deck){
  this.pseudo = pseudo;
  this.bouclier = 0;
  this.poudre = 25;
  this.main = [];
  this.deck = [];
  this.cActives = [];
  this.cActivesNonRetournees = [];
}

function etatMatch(pseudo1,pseudo2,deck1,deck2) {
  this.joueur1 = new etatJoueur(pseudo1,deck1);
  this.joueur2 = new etatJoueur(pseudo2,deck2);
  this.barreDeVie  = 0;
  //this.room = "";
}

function Room(name, pseudo1, pseudo2){
  this.name = name;
  this.pseudo1 = pseudo1;
  this.pseudo2 = pseudo2;
}

function fournirSalons(){
  var index = listSalons.length
  while (fifoJoueurs.length > 2){
    var nameRoom = 'room'+index;
    var room = new Room(nameRoom, fifoJoueurs[0].pseudo, fifoJoueurs[1].pseudo);
    listSalons.push(room);
    index++;
    (fifoJoueurs[0].socket).join(nameRoom);
    (fifoJoueurs[1].socket).join(nameRoom);
    fifoJoueurs.shift(); fifoJoueur.shift();
    console.log('Salon '+room.name+' créé avec '+room.pseudo1+' et '+room.pseudo2+'.');
    startMatch(room);
   }
}

function initMatch(room){

  connection.connect();
  var queryCartesJ1 = "SELECT id_Carte,coutCarte FROM JoueurCarteDeck JOIN Deck USING (id_Deck) JOIN Carte USING (id_Carte) WHERE (Pseudo LIKE \'"+connection.escape(room.pseudo1)+"\' AND actifDeck=1)";
  var queryCartesJ2 = "SELECT id_Carte,coutCarte FROM JoueurCarteDeck JOIN Deck USING (id_Deck) JOIN Carte USING (id_Carte) WHERE (Pseudo LIKE \'"+connection.escape(room.pseudo2)+"\' AND actifDeck=1)";

  var deck1 = [];
  connection.query(queryCartesJ1, function(err, rows, fields){
    if (err) throw err;
    console.log('rows = '+rows);
    for(var i in rows){
      console.log('rows['+i+'] = '+rows[i]);
      deck1.push(rows[i]);
    }
  });
  connection.query(queryCartesJ2, function(err, rows, fields){
    if (err) throw err;
    console.log('rows = '+rows);
    for(var i in rows){
      console.log('rows['+i+'] = '+rows[i]);
      deck2.push(rows[i]);
    }
  });


  connection.end();
  var etatMatch = new etatMatch(room.pseudo1, room.pseudo2, deck1, deck2);
  alert('Match lancé !');
  console.log(etatMatch);
}

var server = http.createServer(function(req,res){

  var url_p = url.parse(req.url,true,true);

  if(url_p.pathname == '/cli.html'){
    fs.readFile('./cli.html','utf-8',function(error,content){
      res.writeHead(200,{"Content-type":"text/html"});
      res.end(content);
    });
  }
  else {
    res.writeHead(404,{"Content-type":"text/html"})
    res.write("<p><h1>ERREUR 404</h1>Page inexistante</p>");
    res.end();
  }
});

var io = require('socket.io').listen(server);

io.sockets.on('connection', function (socket){

  socket.on('chercherMatch',function(pseudo){
      fifoJoueurs.push({socket: socket,pseudo: pseudo});
      fournirSalons();
  });


  //Fonction très très longue :
});


server.listen(8080);
