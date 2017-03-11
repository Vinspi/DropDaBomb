var socket = io.connect('http://localhost:8080');
var Joueur = (document.getElementById('pseudo').value  !== null) ? document.getElementById('pseudo').value : 'TantPisPourToi' ;    //Récup le pseudo du guguss

var etatJoueur;
var actifAdversaire;

function utiliserCarte(id_Carte){


  if(etatJoueur.main[id_Carte].cout >= Joueur.poudre){
      console.log("Pas assez de poudre !");
  }
  else {
    socket.emit('useCard',{'id_carte' : id_Carte, 'pseudo' : Joueur});
  }

}

function chercherMatch(){
    document.getElementById('attente').innerHTML = '<p>En attente !</p>';
    socket.emit('chercherMatch',Joueur);
}

socket.on('matchStart', function (obj) {
  etatJoueur = obj.etatJoueur;
  actifAdversaire = obj.actifAdversaire;
  console.log(obj.actifAdversaire);
  console.log(obj.etatJoueur);
  console.log(obj.message);
  for (var i = 0; i < 4; i++) {
    console.log("id_carte : "+etatJoueur.main[i].id_Carte);
  }
});
