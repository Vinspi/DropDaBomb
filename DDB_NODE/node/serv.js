
// var starter_HP = [0,1,2,3,4,5,6,7];
// var starter_MTBBWY = [0,1,2,3,4,5,6,7];
// var starter_YSNP = [0,1,2,3,4,5,6,7];

// var fifoJoueurs = [];
// var listSalons = [];

// var http = require('http');
// var url = require('url');
// var mysql = require('mysql');
// var c = require('./const');

// var connection = mysql.createConnection({
//   host : '217.182.69.175',
//   user : 'Vinspi',
//   password : 'vinspi13',
//   database : 'DropDaBomb',
// });

// connection.connect();

// var server = http.createServer();
// var io = require('socket.io').listen(server);

// server.listen(8080);
// console.log("server pret");

// io.sockets.on("connection", function(socket){
//     console.log("nouvelle connexion");
//     socket.on("CREATE_ACCOUNT_CHECK", function(obj){check_account(obj, socket);});
//     socket.on("CREATE_ACCOUNT_SEND", function(obj){create_account(obj, socket);});
//     socket.on("CONNECT_ACCOUNT_CHECK", function(obj){connect_account(obj, socket)});
// });

// function connect_account(obj, sock){
//     console.log("Demande de connexion : " + JSON.stringify(obj));

//      var req = "SELECT Pseudo, mdpCompte FROM CompteJoueur WHERE (Pseudo LIKE "+connection.escape(obj.pseudo)+" AND " +
//                 "mdpCompte LIKE " +connection.escape(obj.password) +");";

//         connection.query(req, function(err, rows, fields){
//             if (err) throw err;
//             if(rows.length != 0){
//                 console.log("success");
//                 sock.emit("CONNECT_ACCOUNT_CHECK_SUCCESS", 0);
//             }
//             else{
//                 console.log("fail");
//                 sock.emit("CONNECT_ACCOUNT_CHECK_FAIL", 0);
//             }
//         });
// }

// function check_account(obj, sock){
//         console.log(obj.mdp.length + " " + c.MIN_SIZE_PASSWORD);

//         if(obj.mdp.length < c.MIN_SIZE_PASSWORD){
//             return c.ERR_MDP;
//         }

//         var r = c.CREATE_ACCOUNT_FAILED_PSEUDO;

//         var req = "SELECT Pseudo, mailCompte FROM CompteJoueur WHERE (Pseudo LIKE "+connection.escape(obj.pseudo)+" OR " +
//                 "mailCompte LIKE " +connection.escape(obj.email) +");";

//         console.log(req);

//         connection.query(req, function(err, rows, fields){
//             if (err) throw err;
//             if(rows.length != 0){
//                 sock.emit("CREATE_ACCOUNT_CHECK_FAIL_ALREADYEXIST", 0);
//             }
//             else{
//                 sock.emit("CREATE_ACCOUNT_CHECK_SUCCESS", 0);
//             }
//         });
// }

// function create_account(obj, sock){

//             console.log("Jai recu final create : " + JSON.stringify(obj));
        
//                 connection.query("INSERT INTO CompteJoueur (Pseudo, mailCompte, mdpCompte, nomGuilde, id_Division) " +
//                              "VALUES ("+connection.escape(obj.pseudo)+","+connection.escape(obj.email)+","+connection.escape(obj.mdp)+",NULL, NULL);");
//                 connection.query("INSERT INTO Deck(id_Deck,estActif) VALUES ("+connection.escape(obj.pseudo+'0')+",0);");
//                 connection.query("INSERT INTO Deck(id_Deck,estActif) VALUES ("+connection.escape(obj.pseudo+'1')+",1);");
//                 connection.query("INSERT INTO Deck(id_Deck,estActif) VALUES ("+connection.escape(obj.pseudo+'2')+",0);");   


//                 var starter;

//                 if(obj.starter == "HP"){
//                     starter = starter_HP;
//                 }
//                 else if(obj.starter == "MTBBWY"){
//                     starter = starter_MTBBWY;
//                 }
//                 else if(obj.starter == "YSNP"){
//                     starter = starter_YSNP;
//                 }
//                 else{
//                     return;
//                 }

//                 console.log("Je crée starter : " + starter);

//                 for(var i=0; i<8; i++){
//                     connection.query("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, "+connection.escape(obj.pseudo+'0')+","+connection.escape(obj.pseudo)+","+ starter[i] +");");
//                     connection.query("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, "+connection.escape(obj.pseudo+'1')+","+connection.escape(obj.pseudo)+","+ starter[i] +");");
//                     connection.query("INSERT INTO JoueurCarteDeck(qteCarteDeck, id_Deck, Pseudo, id_Carte) VALUES (1, "+connection.escape(obj.pseudo+'2')+","+connection.escape(obj.pseudo)+","+ starter[i] +");");
//                }   

//                 connection.query("INSERT INTO posséderIconeJoueur(Pseudo,id_IconeJoueur) VALUES ("+connection.escape(obj.pseudo)+",0)");

//                 connection.query("INSERT INTO posséderSkinMap(Pseudo,id_SkinMap) VALUES ("+connection.escape(obj.pseudo)+",0)");

//                 connection.query("INSERT INTO posséderSkinCartonCarte(Pseudo,id_SkinCartonCarte) VALUES ("+connection.escape(obj.pseudo)+",0)");

//                 sock.emit("CREATE_ACCOUNT_SUCCESS", 0);
// }


// var express      = require('express')
// var cookieParser = require('cookie-parser')
 
// var app = express()
// app.use(cookieParser())

// app.get('/Account', function(req, res) {
//   req.cookies = cookieParser.JSONCookie("Salut : salut");
//   res.send("You're Awesome.");
// });
// app.get('/Boutique', function(req, res) {
//   res.send('What a radical visit! \n : ' + cookieParser.JSONCookie(req.cookies));
// });
// app.get('/AccountCreate', function(req, res) {
//   res.send('Are you a surfer?');
// });
// //If you host the application on Modulus the PORT environment variable will be defined,
// //otherwise I’m simply using 8080.
// app.listen(process.env.PORT || 8080);


var express = require('express');
var bodyParser = require('body-parser'); // for reading POSTed form data into `req.body`
var expressSession = require('express-session');
var cookieParser = require('cookie-parser'); // the session is stored in a cookie, so we use this to parse it
var path = require('path');

var app = express();

// must use cookieParser before expressSession
app.use(cookieParser());

app.use(expressSession({secret:'somesecrettokenhere'}));

app.use(bodyParser());  

app.use(express.static(path.resolve('../')));

app.set('view engine', 'jade');
app.set('views', '../views');

app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies

app.get('/', function (req, res) {
  res.render('index', { title: 'Hey', message: 'Hello hey!'});
});


app.get('/Account', function (req, res) {
     res.render('AccountA', req.session.account);
});

app.post('/Account', function (req, res) {
    console.log(req.param("signin_pseudo"));
 req.session.account = {pseudo : req.param("signin_pseudo")};
 req.session.save( (err) => {} );
 console.log(req.session.account);
 res.render('AccountA', req.session.account);
});


app.listen(8080);