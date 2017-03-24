/* constantes */

var NB_MAX_PDV = 150;
var CARD_DESENVOUTEMENT = 8;

/* ********** */


/* mise en place des handlers */

$('.card_clickable').on("click", utiliserCarte);



/*****************************/

var socket = io.connect('http://192.168.43.7:8080');
//var socket = io.connect('http://localhost:8080');


var Joueur;    //Récup le pseudo du guguss

var etatJoueur;
var actifAdversaire;
var carteJoue;
var FLAG_FIN_PARTIE = false;
var vainqueur;

// $(document).ready(function(){
//   card_desenvoutement();
//
// });

function handleSelectCardEffect(value){

  socket.emit('useCard',{'id_carte' : CARD_DESENVOUTEMENT, 'pseudo' : Joueur, 'id_carte_destroyed' : value});

}

function card_desenvoutement(){
  var width = $('#zone').width();
  var height = $(document).height();

  console.log('width '+width+' height '+height);



  $('#zone_select_card_effect').css({'opacity': 0.95, 'width': width+"px", 'height': (height*0.2)+"px",'top': height/2-((height*0.3)/2)});
  $('#zone_select_card_effect').addClass('pop_select_card_effect');
  $('#zone_barre').css({'opacity': 0.3});
  $('#zone_jeu').css({'opacity': 0.3});
  $('#zone_deck').css({'opacity': 0.3});



}

function utiliserCarte(){

  var id_Carte = $(this).attr('id');



  function verifPoudre(){
    for(var i=0 ; i<etatJoueur.main.length;i++){
      if(id_Carte == etatJoueur.main[i].id_Carte && etatJoueur.main[i].cout <= etatJoueur.poudre)
        return true;
    }
    return false;
  }


  if(id_Carte >= 0){
    if(verifPoudre()){
        console.log("Pas assez de poudre !");
    }
    else {
      if(id_Carte == CARD_DESENVOUTEMENT){
        card_desenvoutement();
      }
      else {
        console.log(Joueur+"joue jouez "+id_Carte+" !");
        socket.emit('useCard',{'id_carte' : id_Carte, 'pseudo' : Joueur});
      }

    }
  }
}

function chercherMatch(){
    Joueur = (document.getElementById('pseudo').value  !== null) ? document.getElementById('pseudo').value : 'TantPisPourToi';
    document.getElementById('attente').innerHTML = '<p>En attente !</p>';
    socket.emit('chercherMatch',Joueur);
}



function dessineMain(main){

  if(main[0] !== undefined){
    $('#zone_deck_cards_card1 img').attr('src','img/'+main[0].imageCarte);
    $('#zone_deck_cards_card1 img').attr('id',main[0].id_Carte);
  }
  else {
    $('#zone_deck_cards_card1 img').attr('src','img/CARD_DEFAULT_VERSO.png');
    $('#zone_deck_cards_card1 img').attr('id',-1);
  }

  if(main[1] !== undefined){
    $('#zone_deck_cards_card2 img').attr('src','img/'+main[1].imageCarte);
    $('#zone_deck_cards_card2 img').attr('id',main[1].id_Carte);
  }
  else {
    $('#zone_deck_cards_card2 img').attr('src','img/CARD_DEFAULT_VERSO.png');
    $('#zone_deck_cards_card2 img').attr('id',-1);
  }

  if(main[2] !== undefined){
    $('#zone_deck_cards_card3 img').attr('src','img/'+main[2].imageCarte);
    $('#zone_deck_cards_card3 img').attr('id',main[2].id_Carte);
  }
  else {
    $('#zone_deck_cards_card3 img').attr('src','img/CARD_DEFAULT_VERSO.png');
    $('#zone_deck_cards_card3 img').attr('id',-1);
  }

  if(main[3] !== undefined){
    $('#zone_deck_cards_card4 img').attr('src','img/'+main[3].imageCarte);
    $('#zone_deck_cards_card4 img').attr('id',main[3].id_Carte);
  }
  else {
    $('#zone_deck_cards_card4 img').attr('src','img/CARD_DEFAULT_VERSO.png');
    $('#zone_deck_cards_card4 img').attr('id',-1);
  }


}

function dessineBarreDeVie(etatJoueur,bouclierAdversaire){


  var pourcent_barre_verte = 98*etatJoueur.pdv/(NB_MAX_PDV*2+etatJoueur.bouclier+bouclierAdversaire);
  var pourcent_bouclier_joueur = 98*etatJoueur.bouclier/(NB_MAX_PDV*2+etatJoueur.bouclier+bouclierAdversaire);
  var pourcent_bouclier_adversaire = 98*bouclierAdversaire/(NB_MAX_PDV*2+etatJoueur.bouclier+bouclierAdversaire);
  var pourcent_barre_rouge = 98-(pourcent_barre_verte+pourcent_bouclier_joueur+pourcent_bouclier_adversaire);



  $('#zone_barre_barre_fond_left').css({'width' : pourcent_barre_verte+'%'});
  $('#zone_barre_barre_fond_bouclier_left').css({'width' : pourcent_bouclier_joueur+'%'});
  $('#zone_barre_barre_fond_bouclier_right').css({'width' : pourcent_bouclier_adversaire+'%'});
  $('#zone_barre_barre_fond_right').css({'width' : pourcent_barre_rouge+'%'});

  console.log("vie : "+etatJoueur.pdv);
  console.log("vie : "+etatJoueur.bouclier);


  $('#zone_barre_vie_left').html('<span class=\"white-text\">'+(etatJoueur.pdv+etatJoueur.bouclier)+'</span>(<span class = \"green-text\">'+etatJoueur.pdv+'</span> + <span class=\"purple-text\">'+etatJoueur.bouclier+'</span>)');

}

function dessineCarteCentre(carteJoue){

  $('#zone_jeu_card_middle_card').attr('src','img/'+carteJoue.imageCarte);
  $('#zone_jeu_card_middle_card').addClass('pop_card_center');

  $('.pop_card_center').one('webkitAnimationEnd oanimationend msAnimationEnd animationend',
  function(e) {
      console.log("coucou l'anim est finie");
      $('#zone_jeu_card_middle_card').removeClass('pop_card_center');
  });
}

function dessineCartesActives(etatJoueur,actifAdversaire){

  var id, src, duree;
  var id_adv, src_adv, duree_adv;
  var zone, zone_adv;
  var selecteur, selecteurAdversaire;
  var overlay, overlay_adv;
  var rm_class = true;

  for(var i=0;i<5;i++){
    rm_class = true;
    selecteur = "#zone_jeu_cards_player_card"+(i+1)+" img";
    overlay_adv = "#zone_jeu_cards_adversaire_card"+(i+1)+" .jeu_cards_overlay";
    zone = "#zone_jeu_cards_player_card"+(i+1);

    overlay = "#zone_jeu_cards_player_card"+(i+1)+" .jeu_cards_overlay";
    selecteurAdversaire = "#zone_jeu_cards_adversaire_card"+(i+1)+" img";
    selecteurZoneSelect = "#zone_select_card_effect_card"+(i+1);
    zone_adv = "#zone_jeu_cards_adversaire_card"+(i+1);


    if(actifAdversaire[i] === undefined){
      src_adv = "img/CARD_DEFAULT_VERSO.png";
      id_adv = -1;
      $(selecteurZoneSelect).removeClass("card_clickable");
      duree_adv = 0;
    }
    else{
      src_adv = "img/"+actifAdversaire[i].imageCarte;
      id_adv = actifAdversaire[i].id_carte;
      duree_adv = actifAdversaire[i].duree;
      $(selecteurZoneSelect).addClass("card_clickable");
      $(selecteurZoneSelect).on('click',handleSelectCardEffect());
      if(duree_adv == 0) rm_class = false;
    }


    if(etatJoueur.carteActiveNonRetourne[i] === undefined){
      src = "img/CARD_DEFAULT_VERSO.png";
      id = -1;
      duree = 0;
    }
    else{
      src = "img/"+etatJoueur.carteActiveNonRetourne[i].imageCarte;
      id = etatJoueur.carteActiveNonRetourne[i].id_carte;
      duree = etatJoueur.carteActiveNonRetourne[i].duree;

    }


    $(selecteur).attr('src',src);
    $(selecteur).attr('id',id);
    $(overlay).text(duree);



    $(selecteurAdversaire).attr('src',src_adv);
    $(selecteurAdversaire).attr('id',id_adv);
    $(selecteurZoneSelect).attr('src',src_adv);
    $(overlay_adv).text(duree_adv);

    $(zone_adv).addClass("boom");
    if(rm_class) $(zone_adv).removeClass("boom");

  }
}

function annoncerVictoire(){
  console.log("victoire");
  $('#modal_annonce_victoire').modal('open');
}

function annoncerDefaite(){
  console.log("defaite");
  $('#modal_annonce_defaite').modal('open');
}

socket.on('matchStart', function (obj) {
  etatJoueur = obj.etatJoueur;
  actifAdversaire = obj.actifAdversaire;
  console.log(obj.actifAdversaire);
  console.log(obj.etatJoueur);
  console.log(obj.message);

  dessineMain(etatJoueur.main);
  dessineBarreDeVie(etatJoueur,0);
});

socket.on('FIN_DU_GAME', function(obj){
  if(obj.vainqueur == Joueur){
    annoncerVictoire();
  }
  else {
    annoncerDefaite();
  }
});

socket.on('update',function (obj) {

  etatJoueur = obj.etatJoueur;
  actifAdversaire = obj.actifAdversaire;
  carteJoue = obj.carteJoue;

  dessineMain(etatJoueur.main);
  dessineBarreDeVie(etatJoueur,obj.bouclierAdversaire);
  dessineCartesActives(etatJoueur,actifAdversaire);

  console.log("update !");
  dessineCarteCentre(carteJoue);
  $('#zone_deck_infos_bottom_overlay').text(obj.etatJoueur.poudre);
});


socket.on("CLOCK_UPDATE", function(obj){
   $("#zone_barre_timer_time").text("00:" + obj);
   //$("#zone_barre_timer_idjoueur").text();
});


socket.on("FIN_TOUR", function(obj){
   $("#zone_barre_timer_idjoueur").text(obj.joueurTour);
   $('#zone_deck_infos_bottom_overlay').text(obj.etatJoueur.poudre);
   dessineMain(obj.etatJoueur.main);
   dessineCartesActives(obj.etatJoueur,obj.actifAdversaire);
   //$("#zone_barre_timer_idjoueur").text();
});
