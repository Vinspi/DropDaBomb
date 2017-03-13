/* constantes */

var NB_MAX_PDV = 250;

/* ********** */


/* mise en place des handlers */

$('.card_clickable').on("click", utiliserCarte);

/*****************************/

var socket = io.connect('http://localhost:8080');

var Joueur;    //Récup le pseudo du guguss

var etatJoueur;
var actifAdversaire;



function utiliserCarte(){

  var id_Carte = $(this).attr('id');

  function verifPoudre(){
    for(var i=0 ; i<etatJoueur.main.length;i++){
      if(id_Carte == etatJoueur.main[i].id_Carte && etatJoueur.main[i].cout <= etatJoueur.poudre)
        return true;
    }
    return false;
  }



  if(verifPoudre()){
      console.log("Pas assez de poudre !");
  }
  else {
    console.log("vous jouez "+id_Carte+" !");
    socket.emit('useCard',{'id_carte' : id_Carte, 'pseudo' : Joueur});
  }

}

function chercherMatch(){
    Joueur = (document.getElementById('pseudo').value  !== null) ? document.getElementById('pseudo').value : 'TantPisPourToi';
    document.getElementById('attente').innerHTML = '<p>En attente !</p>';
    socket.emit('chercherMatch',Joueur);
}

function dessineMain(main){

  console.log(main[0].imageCarte);
  console.log(main[1].imageCarte);
  console.log(main[2].imageCarte);
  console.log(main[3].imageCarte);

  $('#zone_deck_cards_card1 img').attr('src','img/'+main[0].imageCarte);
  $('#zone_deck_cards_card1 img').attr('id',main[0].id_Carte);

  $('#zone_deck_cards_card2 img').attr('src','img/'+main[1].imageCarte);
  $('#zone_deck_cards_card2 img').attr('id',main[1].id_Carte);

  $('#zone_deck_cards_card3 img').attr('src','img/'+main[2].imageCarte);
  $('#zone_deck_cards_card3 img').attr('id',main[2].id_Carte);

  $('#zone_deck_cards_card4 img').attr('src','img/'+main[3].imageCarte);
  $('#zone_deck_cards_card4 img').attr('id',main[3].id_Carte);


}

function dessineBarreDeVie(pdv){
  /* 49% = NB_MAX_PDV pdv */
  /* pour dessiner X pdv -> Y% = 49*NB_MAX_PDV/X */

  var pourcent_barre_verte = 49*NB_MAX_PDV/pdv;
  var pourcent_barre_rouge = 98-pourcent_barre_verte;

  $('#zone_barre_barre_fond_left').css({'width' : pourcent_barre_verte+'%'});
  $('#zone_barre_barre_fond_right').css({'width' : pourcent_barre_rouge+'%'});

}

socket.on('matchStart', function (obj) {
  etatJoueur = obj.etatJoueur;
  actifAdversaire = obj.actifAdversaire;
  console.log(obj.actifAdversaire);
  console.log(obj.etatJoueur);
  console.log(obj.message);

  dessineMain(etatJoueur.main);
  dessineBarreDeVie(etatJoueur.pdv);
});

socket.on('update',function (obj) {
  etatJoueur = obj.etatJoueur;
  actifAdversaire = obj.actifAdversaire;


  dessineMain(etatJoueur.main);
  dessineBarreDeVie(etatJoueur.pdv);
});
