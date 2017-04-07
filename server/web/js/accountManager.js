//Fonctions JS associés à accountManager.jsp, donc à la gestion du compte (modifications d'email, de mot de passe etc).



$(document).ready(function () {
   $('.modal').modal({
       dismissible: true
   });
    $('#erreur-email').hide();
    $('#erreurMdp').hide();
});


//Ouvre le modal pour changer son email.
function changeEmail() {
    $('#modalEmail').modal('open');
}

//Envoie du formulaire de changement d'email (ne passe pas un submit, inutile de prévenir l'execution par défaut).
function validationChangeEmail() {

    var email = $('#new-email').val();

    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    $('#erreur-email').hide();

    if(!re.test(email)){
        $('#erreur-email').show();
        return;
    }

    $.ajax({

        url : '/AccountUpdater',

        type : 'POST',

        data : 'typeRequest=22&update='+$('#new-email').val(),

        succes: function (code_html,status) {
            console.log("succes "+status);
            alert("coucou");
        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }

    });

    $('.mail').html($('#new-email').val());
    $('#new-email').val("");
    $('#modalEmail').modal('close');


}


//Ouvre le modal de changement de mot de passe.
function changeMdp() {
    $('#modalMdp').modal('open');
}

//Envoie du formulaire de changement de mot de passe (ne passe pas un submit, inutile de prévenir l'execution par défaut).
function validationChangeMdp() {

    var mdp1 = $('#new-mdp').val();
    var mdp2 = $('#new-mdp-confirm').val();

    console.log("actual-mdp = "+$('#actual-mdp').val());

    console.log(mdp1+' : '+mdp2);

    if(mdp1 == mdp2 && mdp1.length >= 6) {
        $('#erreurMdp').hide();
        $.ajax({

            url: '/AccountUpdater',

            type: 'POST',

            data: 'typeRequest=21&update=' + $('#new-mdp').val()+"&password="+$('#actual-mdp').val(),

            succes: function (code_html, status) {
                console.log("succes " + status);
                alert("coucou");
            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }

        });
        $('#modalMdp').modal('close');

    } else {
        $('#erreurMdp').show();
    }
}

//Ouverture du modal de changement d'icone.
function changeIcone(){
    $('#modalIcone').modal('open');
}

//Envoie du formulaire de changement d'icone (ne passe pas un submit, inutile de prévenir l'execution par défaut).
function confirmationChangeIcone(id,nouvelleIcone) {

    console.log("id_icone = "+id);
    console.log(nouvelleIcone);

    var splitted = nouvelleIcone.split('/');

    console.log(splitted[splitted.length-1]);

    $.ajax({

        url: '/AccountUpdater',

        type: 'POST',

        data: {"typeRequest" : 20, "update" : id, "option" : splitted[splitted.length-1]},

        dataType: 'json',

        succes: function (code_html, status) {
            console.log("succes " + status);
            alert("coucou");
        },

        error: function (code_html, status) {
            console.log("request failed " + status);
        }

    });

    $('#icone-joueur-compte').attr('src',nouvelleIcone);
    $('#icone-joueur-nav').attr('src',nouvelleIcone);
    $('#modalIcone').modal('close');

}

//Ouverture du modal de changement de SkinCarton.
function changeSkinCarte(){
    $('#modalSkinCarton').modal('open');
}

//Envoie du formulaire de changement de SkinCarton (ne passe pas un submit, inutile de prévenir l'execution par défaut).
function confirmationChangeSkinCarte(id,nouvelleIcone) {



    var splitted = nouvelleIcone.split('/');



    $.ajax({

        url: '/AccountUpdater',

        type: 'POST',
        // 27 pour map
        data: 'typeRequest=23&update='+id+'&option='+splitted[splitted.length-1],

        succes: function (code_html, status) {
            console.log("succes " + status);
            alert("coucou");
        },

        error: function (code_html, status) {
            console.log("request failed " + status);
        }

    });

    $('#icone-carte-compte').attr('src',nouvelleIcone);

    $('#modalSkinCarton').modal('close');

}


//Ouverture du modal de changement de SkinMap.
function changeSkinMap(){
    $('#modalSkinMap').modal('open');
}

//Envoie du formulaire de changement de SkinMap (ne passe pas un submit, inutile de prévenir l'execution par défaut).
function confirmationChangeSkinMap(id,nouvelleIcone) {



    var splitted = nouvelleIcone.split('/');



    $.ajax({

        url: '/AccountUpdater',

        type: 'POST',

        data: {"typeRequest" : 27, "update" : id, "option" : splitted[splitted.length-1]},

        dataType: 'json',

        succes: function (code_html, status) {
            console.log("succes " + status);
            alert("coucou");
        },

        error: function (code_html, status) {
            console.log("request failed " + status);
        }

    });

    $('#icone-map-compte').attr('src',nouvelleIcone);

    $('#modalSkinMap').modal('close');

}