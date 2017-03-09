/**
 * Created by vinspi on 06/03/17.
 */


$(document).ready(function () {
   $('.modal').modal({
       dismissible: true
   });

   $('#erreurMdp').hide();
});

function changeEmail() {
    $('#modalEmail').modal('open');
}

function validationChangeEmail() {
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


}

function changeMdp() {
    $('#modalMdp').modal('open');
}

function validationChangeMdp() {

    var mdp1 = $('#new-mdp').val();
    var mdp2 = $('#new-mdp-confirm').val();

    console.log("actual-mdp = "+$('#actual-mdp').val());

    console.log(mdp1+' : '+mdp2);

    if(mdp1 == mdp2) {
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

function changeIcone(){
    $('#modalIcone').modal('open');
}

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


function changeSkinCarte(){
    $('#modalSkinCarton').modal('open');
}

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


function changeSkinMap(){
    $('#modalSkinMap').modal('open');
}

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