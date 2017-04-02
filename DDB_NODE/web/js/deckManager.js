
var deck_actif = 1;
var carte_deck = -1;
var carte_inventaire = -1;

//ajout css
$(document).ready(function(){
  var height = $('#deck1').height();
  $('#decks').css({'height': height+'px'});

  $('.carte-inventaire').on('click', selectionne_carte_inventaire);
  $('.carte-deck').on('click', selectionne_carte_deck);

  $('.carte-deck').on('webkitAnimationEnd oanimationend msAnimationEnd animationend',
      function(e) {
      $('.carte-deck').removeClass('carte-deck');
  });


});

function switcher(deck){
  carte_deck = -1;
  carte_inventaire = -1;
  if(deck == 2){
    $('#deck2').removeClass('deck2-out');
    $('#deck1').removeClass('deck1-in');
    $('#deck1').addClass('deck1-out');
    $('#deck2').addClass('deck2-in');
    $('#inventaire2').show();
    $('#inventaire1').hide();
    deck_actif = 2;
  }
  if(deck == 1){
    $('#deck1').removeClass('deck1-out');
    $('#deck2').removeClass('deck2-in');
    $('#deck2').addClass('deck2-out');
    $('#deck1').addClass('deck1-in');
    $('#inventaire1').show();
    $('#inventaire2').hide();
    deck_actif = 1;
  }
}

function selectionne_carte_deck(value) {
  carte_deck = $(this).attr('id');

  if(carte_inventaire != -1){
    /* on echange les des carte */
    $('#'+carte_deck).addClass('carte-deck');

    carte_deck = -1;
    carte_inventaire = -1;
  }
}

function selectionne_carte_inventaire(){
  carte_inventaire = $(this).attr('id');
  if(carte_deck != -1){
    /* on echange les carte */
    carte_deck = -1;
    carte_inventaire = -1;
  }
}
