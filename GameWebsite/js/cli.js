var socket = io.connect('http://localhost:8080');
var Joueur = (document.getElementById('pseudo').value  !== null) ? document.getElementById('pseudo').value : 'TantPisPourToi' ;    //Récup le pseudo du guguss


function utiliserCarte(etatMatch){

  /*var Joueur;
  if(joueur == etatMatch.joueur1.pseudo) Joueur = etatMatch.joueur1;
  else Joueur = etatMatch.joueur2;*/

  //Récupérer la carte = id_Carte;
  if (Joueur.main.includes(id_Carte)){
    if(Joueur.main[id_Carte].cout >= Joueur.poudre){
        console.log("Pas assez de poudre !");
    }
    else {
      socket.emit('useCard', id_Carte);
    }
  }
  else {
      console.log("Carte pas dans la main");
  }
}

function chercherMatch(){
    document.getElementById('attente').innerHTML = '<p>En attente !</p>';
    socket.emit('chercherMatch',Joueur);
}

socket.on('matchStart', function (message) {
  alert(message);
});
