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

function dessineMain(main){
  console.log(main[0].imageCarte);
  console.log(main[1].imageCarte);
  console.log(main[2].imageCarte);
  console.log(main[3].imageCarte);
  $('#zone_deck_cards_card1 img').attr('src','img/'+main[0].imageCarte);

  $('#zone_deck_cards_card2 img').attr('src','img/'+main[1].imageCarte);
  $('#zone_deck_cards_card3 img').attr('src','img/'+main[2].imageCarte);
  $('#zone_deck_cards_card4 img').attr('src','img/'+main[3].imageCarte);

}

socket.on('matchStart', function (obj) {
  etatJoueur = obj.etatJoueur;
  actifAdversaire = obj.actifAdversaire;
  console.log(obj.actifAdversaire);
  console.log(obj.etatJoueur);
  console.log(obj.message);
  
  dessineMain(etatJoueur.main);
});
