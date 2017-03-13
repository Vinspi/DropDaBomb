/**
 * Created by deutsch on 10/03/17.
 */

$(document).ready(function() {
    $(".button-collapse").sideNav();
    $.ajax({
        url: "Admin",
        type: "POST",
        data: "idRequest=-1",
        success: function(data){
            console.log("reset");
        },
        error: function(data){
            console.log("erreur");
        }
    });
    getListEnsembles();
    getListLootPacks()
    getListPacks();

    $('.modal').modal();
    $('#modal-newEns').on('submit', function(e) {

        e.preventDefault();
        var nomEns = $('#nomEns').val();

        $.ajax({
            url: "Admin",
            type: 'POST',
            data: 'idRequest=3&nomEnsemble='+nomEns,
            dataType: 'json', // JSON
            success: function(data) {
                console.log(data);
                var result = data;//JSON.parse(data);
                console.log("succes ");
                document.getElementById("listEnsembles").innerHTML = "";
                var p = document.createElement('div');

                for(var i = 0; i < result.length; i++){
                    p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                        "       <div class=\"block\" id=\"E"+result[i].id+"\" onclick=\"setCurrentEnsemble("+result[i].id+")\"><p>Ensemble "+result[i].id+"</p></div>" +
                        "     </div>";
                    if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
                }
                document.getElementById("listEnsembles").appendChild(p);

                document.getElementById("actionEns").innerHTML = "<a href=\"#modal-newEns\" class=\"waves-effect waves-teal btn-flat modal-action modal-close modal-trigger\">New</a><a data-activates=\'slide-out\' class=\"waves-effect waves-teal btn-flat button-collapse\" onclick=\"addGetCarte()\">Add</a>";
                //('.modal').modal();
                $(".button-collapse").sideNav();
                $('#modal-newEns').modal('close');
                console.log("qq");
            }
        });
    });
    $('#modal-newLootPack').on('submit',function(e){
        e.preventDefault();
        var nomLootPack = $('#nomLootPack').val();

        $.ajax({
            url: "Admin",
            type: 'POST',
            data: 'idRequest=4&nomLootPack='+nomLootPack,
            dataType: 'json', // JSON
            success: function(data) {
                console.log(data);
                var result = JSON.parse(data);
                console.log("succes ");
                document.getElementById("listLootPack").innerHTML = "";
                var p = document.createElement('div');

                for(var i = 0; i < result.length; i++){
                    p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                        "       <div class=\"block\" id=\"LP"+result[i].id+"\" onclick=\"setCurrentLootPack("+result[i].id+")\"><p>LootPack "+result[i].id+"</p></div>" +
                        "     </div>";
                    if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
                }
                document.getElementById("listLootPack").appendChild(p);
                document.getElementById("actionLootPack").innerHTML = "<a href=\"#modal-newLootPack\" class=\"waves-effect waves-teal btn-flat modal-action modal-close modal-trigger\">New</a><a data-activates=\'slide-out\' class=\"waves-effect waves-teal btn-flat button-collapse\" onclick=\"addGetEnsemble()\">Add</a>";
                $(".button-collapse").sideNav();
                $('#modal-newLootPack').modal('close');
                console.log("qq");
            }
        });
    });
    $('#modal-newPack').on('submit',function(e){
        e.preventDefault();

    });

});

function setCurrentEnsemble(value){

    $.ajax({

        url : 'Admin',

        type : 'POST',
        data : 'idRequest=0&id_ensemble='+value,

        dataType: 'json',

        success: function (data) {
            console.log(data);
            var result = data;//JSON.parse(data);
            console.log("succes ");
            document.getElementById("currentEnsemble").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.cartes.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\"><div id=\"cE"+result.cartes[i].id+"\" class=\"card-image grow card_img_crd\" onclick=\"removeCarte("+result.cartes[i].id+")\">" +
                    "       <img class=\"card_img_crd\" src=\"../img/CARDS/" + result.cartes[i].img + "\">" +
                    "     </div></div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("currentEnsemble").appendChild(p);
            document.getElementById("actionEns").innerHTML = "<a href=\"#modal-newEns\" class=\"waves-effect waves-teal btn-flat modal-action modal-close modal-trigger\">New</a><a data-activates=\'slide-out\' class=\"waves-effect waves-teal btn-flat button-collapse\" onclick=\"addGetCarte()\">Add</a>";
            $(".button-collapse").sideNav();
            if(result.nom != null) document.getElementById("idCurrentEnsemble").innerHTML = "Ensemble courant : " + result.nom + ", "+result.id;
            else document.getElementById("idCurrentEnsemble").innerHTML = "Ensemble courant : "+result.id;


            console.log("qq");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });

}
function setCurrentLootPack(value){
    $.ajax({

        url : 'Admin',

        type : 'POST',
        data : 'idRequest=1&id_lootpack='+value,
        dataType: 'json',

        complete: function (data) {
            console.log(data);
            var result = JSON.parse(data.responseText);
            console.log("succes ");
            document.getElementById("currentLootPack").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.ensembles.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"LP"+result.ensembles[i].id+"\"><p>Ensemble "+result.ensembles[i].id+"</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("currentLootPack").appendChild(p);
            document.getElementById("actionLootPack").innerHTML = "<a href=\"#modal-newLootPack\" class=\"waves-effect waves-teal btn-flat modal-action modal-close modal-trigger\">New</a><a data-activates=\'slide-out\' class=\"waves-effect waves-teal btn-flat button-collapse\" onclick=\"addGetCarte()\">Add</a>";
            $(".button-collapse").sideNav();
            if(result.nom != null) document.getElementById("idCurrentLootPack").innerHTML = "LootPack courant : " + result.nom + ", "+result.id;
            else document.getElementById("idCurrentLootPack").innerHTML = "LootPack courant : "+result.id;
            console.log("qq");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
function setCurrentPack(value) {
    $.ajax({

        url : 'Admin',

        type : 'POST',
        cache: false,
        data : 'idRequest=2&id_pack='+value,


        complete: function (data) {
            console.log(data);
            var result = JSON.parse(data);
            console.log("succes ");
            document.getElementById("currentPack").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.lootPacks.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"P"+result.lootPacks[i].id+"\" onclick=\"setCurrentLootPack($(this).attr('id'))\"><p>LootPack "+result.lootPacks[i].id+"</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("currentPack").appendChild(p);
            console.log("qq");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}


function getListEnsembles(){
    $.ajax({

        url : 'Admin',

        type : 'POST',
        cache: false,
        data : 'idRequest=42',


        success: function (data) {
            console.log(data);
            var result = JSON.parse(data);
            console.log("succes ");
            document.getElementById("listEnsembles").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"E"+result[i].id+"\" onclick=\"setCurrentEnsemble("+result[i].id+")\"><p>Ensemble "+result[i].id+"</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("listEnsembles").appendChild(p);

            console.log("qq");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
function getListLootPacks(){
    $.ajax({

        url : 'Admin',

        type : 'POST',
        data : 'idRequest=43',


        success: function (data) {
            console.log(data);
            var result = JSON.parse(data);
            console.log("succes ");
            document.getElementById("listLootPack").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"LP"+result[i].id+"\" onclick=\"setCurrentLootPack("+result[i].id+")\"><p>LootPack "+result[i].id+"</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("listLootPack").appendChild(p);

            console.log("qq");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
function getListPacks(){
    $.ajax({

        url : 'Admin',

        type : 'POST',
        data : 'idRequest=44',


        success: function (data) {
            console.log(data);
            var result = JSON.parse(data);
            console.log("succes ");
            document.getElementById("listPack").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"P"+result[i].id+"\" onclick=\"setCurrentPack("+result[i].id+")\"><p>Pack "+result[i].id+"</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("listPack").appendChild(p);

            console.log("qq");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}


function switchMiseEnVente(value) {
    $.ajax({

        url : 'Admin',

        type : 'POST',

        data : {"idRequest": 3,"id_Pack": value.substring(1)},

        dataType : 'json',

        succes: function (code_html,status) {
            console.log("succes "+status);
            alert("coucou");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}


function addGetCarte(){
    $.ajax({

        url : 'Admin',


        type : 'POST',

        data : 'idRequest=10',

        success: function (data) {
            console.log(data);
            var result = JSON.parse(data);
            console.log("succes ");
            document.getElementById("slide-out").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.length; i++){
                p.innerHTML += "<li onclick=\"addCarteToCurrentEnsemble("+result[i].id+")\"><img id=\'c"+result[i].id+"\' class=\"card_img_crd\" src=\'../img/CARDS/" + result[i].img + "\' ></li>";
            }
            document.getElementById("slide-out").appendChild(p);
            //$('.button-collapse').sideNav('show');
            console.log("qq");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });

}
function addCarteToCurrentEnsemble(id_carte) {
    $.ajax({

        url : 'Admin',
        type : 'POST',

        data : {"idRequest": 11,"id_carte": id_carte},

        dataType : 'json',

        complete: function (data) {
            console.log(data);
            var result = JSON.parse(data.responseText);
            console.log("succes ");
            document.getElementById("currentEnsemble").innerHTML = "";
            var p = document.createElement('div');

            p.innerHTML += "<div class=\"row\">";
            for(var i = 0; i < result.cartes.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\"><div id=\"cE"+result.cartes[i].id+"\" class=\"card-image grow card_img_crd\" onclick=\"removeCarte("+result.cartes[i].id+")\">" +
                    "       <img class=\"card_img_crd\" src=\"../img/CARDS/" + result.cartes[i].img + "\">" +
                    "     </div></div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            p.innerHTML += "</div>";
            document.getElementById("currentEnsemble").appendChild(p);
            Materialize.toast('Carte '+id_carte+' ajoutée !', 4000) // 4000 is the duration of the toast
            $('.button-collapse').sideNav('hide');

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
function removeCarte(id_carte){

    var verif = prompt("Voulez-vous supprimer la carte "+id_carte+" de l'ensemble courant ?");
    if (verif == "Oui" || verif == "oui" || verif == "yes" || verif == "Yes" || verif == "OUI" || verif == "YES" || verif == "o" || verif == "O" || verif == "y" || verif == "Y") {


        $.ajax({

            url: 'Admin',
            cache: false,
            type: 'POST',

            data: {"idRequest": 12, "id_carte": id_carte},

            dataType: 'json',

            complete: function (data) {
                console.log(data);
                var result = JSON.parse(data.responseText);
                console.log("succes ");
                document.getElementById("currentEnsemble").innerHTML = "";
                var p = document.createElement('div');

                p.innerHTML += "<div class=\"row\">";
                for (var i = 0; i < result.cartes.length; i++) {
                    p.innerHTML += "<div class=\"col s2 m2 l2\"><div id=\"cE" + result.cartes[i].id + "\" class=\"card-image grow card_img_crd\" onclick=\"removeCarte("+result.cartes[i].id+")\">" +
                        "       <img class=\"card_img_crd\" src=\"../img/CARDS/" + result.cartes[i].img + "\">" +
                        "     </div></div>";
                    if (i != 0 && i % 6 == 5) p.innerHTML += "</div><div class=\"row\">";
                }
                p.innerHTML += "</div>";
                document.getElementById("currentEnsemble").appendChild(p);
                Materialize.toast('Carte '+id_carte+' supprimée !', 4000) // 4000 is the duration of the toast

            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }
        });
    }
    else {
        Materialize.toast('Vous ne vouliez pas supprimer la carte '+id_carte, 4000) // 4000 is the duration of the toast
    }

}

function addGetEnsemble(){
    $.ajax({

        url : 'Admin',


        type : 'POST',

        data : 'idRequest=13',

        success: function (data) {
            console.log(data);
            var result = JSON.parse(data);
            console.log("succes ");
            document.getElementById("slide-out").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.length; i++){
                p.innerHTML += "<li ><div class=\"block\" id=\"E"+result[i].id+"\" onclick=\"addEnsembleToCurrentLootPack("+result[i].id+")\"><p>Ensemble "+result[i].id+"</p></div></li>";
            }
            document.getElementById("slide-out").appendChild(p);
            //$('.button-collapse').sideNav('show');
            console.log("qq");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}

function addEnsembleToCurrentLootPack(value){
    $.ajax({

        url : 'Admin',
        type : 'POST',

        data : {"idRequest": 14,"id_Ensemble": value},

        dataType : 'json',

        complete: function (data) {
            console.log(data);
            var result = JSON.parse(data.responseText);
            console.log("succes ");
            document.getElementById("currentLootPack").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.ensembles.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"eLP"+result.ensembles[i].id+"\"><p>Ensemble "+result.ensembles[i].id+"</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("currentLootPack").appendChild(p);

            Materialize.toast('Ensemble '+value+' ajouté !', 4000) // 4000 is the duration of the toast
            $('.button-collapse').sideNav('hide');

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });

}