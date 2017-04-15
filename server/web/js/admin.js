//Fonctions JS de la page admin.jsp. Utilisées pour la génération de la page et pour les traitements (ajout dans un Ensemble, suppression d'un LootPack etc).


//Quand la page est prête :
// -> charger les ensembles/lootpacks/packs et les afficher,
// -> préparer les modaux d'ajout de nouveaux ensembles/lootpacks/packs.
$(document).ready(function() {
    $(".button-collapse").sideNav();
    $.ajax({
        url: "AdminManager",
        type: "POST",
        data: "idRequest=-1",
        success: function(data){
            console.log("Rafraichissement de la page");
        },
        error: function(data){
            console.log("erreur");
        }
    });
    getListEnsembles();
    getListLootPacks();
    getListPacks();

    $('.modal').modal();
    $('#modal-newEns').on('submit', function(e) {

        e.preventDefault();
        if($('#nommEns').val().length != 0) {
            var nomEns = $('#nomEns').val();

            $.ajax({
                url: "AdminManager",
                type: 'POST',
                data: 'idRequest=3&nomEnsemble=' + nomEns,
                dataType: 'json',

                success: function (data) {

                    var result = data;
                    document.getElementById("listEnsembles").innerHTML = "";
                    var p = document.createElement('div');

                    for (var i = 0; i < result.length; i++) {
                        p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                            "       <div class=\"block\" id=\"E" + result[i].id + "\" onclick=\"setCurrentEnsemble(" + result[i].id + ")\"><p>Ensemble " + result[i].id + "</p></div>" +
                            "     </div>";
                        if (i != 0 && i % 6 == 5) p.innerHTML += "</div><div class=\"row\">";
                    }
                    document.getElementById("listEnsembles").appendChild(p);

                    $(".button-collapse").sideNav();
                    $('#modal-newEns').modal('close');
                }
            });
        }
        else {
            Materialize.toast("Input nomEnsemble vide",4000);
        }
    });
    $('#modal-newLootPack').on('submit',function(e){
        e.preventDefault();
        if($('#nomLootPack').val().length != 0){
            var nomLootPack = $('#nomLootPack').val();

            $.ajax({
                url: "AdminManager",
                type: 'POST',
                data: 'idRequest=4&nomLootPack='+nomLootPack,
                dataType: 'json',

                success: function(data) {

                    var result = data;
                    document.getElementById("listLootPack").innerHTML = "";
                    var p = document.createElement('div');

                    for(var i = 0; i < result.length; i++){
                        p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                            "       <div class=\"block\" id=\"LP"+result[i].id+"\" onclick=\"setCurrentLootPack("+result[i].id+")\"><p>LootPack "+result[i].id+"</p></div>" +
                            "     </div>";
                        if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
                    }
                    document.getElementById("listLootPack").appendChild(p);
                    $(".button-collapse").sideNav();
                    $('#modal-newLootPack').modal('close');
                }
            });
        }
        else {
            Materialize.toast("Input Nom LootPack vide ",4000);
        }
    });
    $('#modal-newPack').on('submit',function(e){
        e.preventDefault();
        if($("#nomPack").val().length != 0 && $("#prixIG").val().length != 0 && $("#prixIRL").val().length != 0) {
            var nomPack = $('#nomPack').val();
            var description;
            if($("#descriptionPack").val().length != 0) description = $("#descriptionPack").val();
            else description = "";

            var image;
            if($("#imagePack").val().length != 0) image = $("#imagePack").val();
            else image = "";

            var prixIG = $("#prixIG").val();
            var prixIRL = $("#prixIRL").val();

            $.ajax({
                url: "AdminManager",
                type: 'POST',
                data: {"idRequest": 5, "nomPack": nomPack, "description" : description, "image": image, "prixIG": prixIG, "prixIRL": prixIRL},
                dataType: 'json',

                success: function (data) {

                    var result = data;
                    document.getElementById("listPack").innerHTML = "";
                    var p = document.createElement('div');

                    for(var i = 0; i < result.length; i++){
                        if(result[i].misEnVente == 1) {
                            p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                                "       <div class=\"block\" id=\"P"+result[i].id+"\" onclick=\"setCurrentPack("+result[i].id+")\"><p>Pack "+result[i].id+"</p></div>" +
                                "     </div>";
                        }
                        else
                        {
                            p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                                "       <div class=\"pev\" id=\"P"+result[i].id+"\" onclick=\"setCurrentPack("+result[i].id+")\"><p>Pack "+result[i].id+"</p></div>" +
                                "     </div>";
                        }
                        if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
                    }
                    document.getElementById("listPack").appendChild(p);

                    $(".button-collapse").sideNav();
                    $('#modal-newPack').modal('close');
                    Materialize.toast("Pack  créé",4000);
                }
            });
        }
        else {
            Materialize.toast("Input nomPack vide",4000);
        }
    });


});


//Fonctions de changement d'Ensemble/LootPack/Pack courant. L'E/L/P cliqué devient courant.
function setCurrentEnsemble(value){

    $.ajax({

        url : 'AdminManager',
        type : 'POST',
        data : 'idRequest=0&id_ensemble='+value,
        dataType: 'json',

        success: function (data) {

            var result = data;
            document.getElementById("currentEnsemble").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.cartes.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\"><div id=\"cE"+result.cartes[i].id+"\" class=\"card-image grow card_img_crd\" onclick=\"removeCarte("+result.cartes[i].id+")\">" +
                    "       <img class=\"card_img_crd\" src=\"../img/CARDS/" + result.cartes[i].img + "\">" +
                    "     </div></div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("currentEnsemble").appendChild(p);
            document.getElementById("actionEns").innerHTML = "<a href=\"#modal-newEns\" class=\"btn-flat modal-action modal-close modal-trigger\">New</a><a data-activates=\'slide-out\' class=\"btn-flat button-collapse\" onclick=\"addGetCarte()\">Add</a>";
            $(".button-collapse").sideNav();

            if(result.nom != null) document.getElementById("idCurrentEnsemble").innerHTML = "Ensemble courant : nom = " + result.nom + ", id = "+result.id;
            else document.getElementById("idCurrentEnsemble").innerHTML = "Ensemble courant : id = "+result.id;



        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });

}
function setCurrentLootPack(value){
    $.ajax({

        url : 'AdminManager',
        type : 'POST',
        data : 'idRequest=1&id_lootpack='+value,
        dataType: 'json',

        success: function (data) {

            var result = data;
            document.getElementById("currentLootPack").innerHTML = "";
            document.getElementById("gestion-LootPack").innerHTML = "";
            var p = document.createElement('div');
            var b = document.createElement('div');
            b.innerHTML = "<ul>";

            for(var i = 0; i < result.ensembles.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"eLP"+result.ensembles[i].id+"\" onclick=\"setCurrentEnsemble("+result.ensembles[i].id+")\"><p>Ensemble "+result.ensembles[i].id+"</br>"+result.ensembles[i].dropRate+"%</p></div></div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
                b.innerHTML += "<li><div class=\"block\" id=\"eLP"+result.ensembles[i].id+"\" onclick=\"removeEnsemble("+result.ensembles[i].id+")\"><p></br>Ensemble "+result.ensembles[i].id+"</br>"+result.ensembles[i].dropRate+"%</p></div></div>"+
                    "<div class=\"input-field\"><input id=\"newDropRate"+result.ensembles[i].id+"\" type=\"text\" class=\"validate\"> <label for=\"newDropRate\">Changer le DropRate ?</label>"+
                    "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"modifyDropRate("+result.ensembles[i].id+")\">DropRate</a>"+
                    "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"removeEnsemble("+result.ensembles[i].id+")\">Remove</a>"+
                    "</div></li>";
            }
            b.innerHTML += "</ul>";
            document.getElementById("currentLootPack").appendChild(p);
            document.getElementById("gestion-LootPack").appendChild(b);
            document.getElementById("actionLootPack").innerHTML = "<a href=\"#modal-newLootPack\" class=\"btn-flat modal-action modal-close modal-trigger\">New</a>"+
                "<a data-activates=\'slide-out\' class=\"btn-flat button-collapse\" onclick=\"addGetEnsemble()\">Add</a>"+
                "<a href=\"#modal-gestionLootPack\" class=\"btn-flat modal-action modal-close modal-trigger\">Modifier</a>";


            $(".button-collapse").sideNav();
            if(result.nom != null) document.getElementById("idCurrentLootPack").innerHTML = "LootPack courant : nom = " + result.nom + ", id = "+result.id;
            else document.getElementById("idCurrentLootPack").innerHTML = "LootPack courant : id = "+result.id;

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
function setCurrentPack(value) {
    $.ajax({

        url : 'AdminManager',
        type : 'POST',
        data : 'idRequest=2&id_pack='+value,
        dataType: 'json',
        success: function (data) {

            var result = data;
            document.getElementById("currentPack").innerHTML = "";
            document.getElementById("gestion-Pack").innerHTML = "";

            var p = document.createElement('div');
            var b = document.createElement('div');
            b.innerHTML += "<ul>";

            for(var i = 0; i < result.lootPacks.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"lpP"+result.lootPacks[i].id+"\" onclick=\"setCurrentLootPack("+result.lootPacks[i].id+")\">LootPack "+result.lootPacks[i].id+"<br>"+result.lootPacks[i].qte+" cartes</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
                b.innerHTML += "<li class=\'row\'><div class=\"block \" id=\"lpP"+result.lootPacks[i].id+"\" onclick=\"removeLootPack("+result.lootPacks[i].id+")\"><p>LootPack "+result.lootPacks[i].id+"</br>"+result.lootPacks[i].qte+" cartes</p></div></div>"+
                    "<div class=\"input-field\"><input id=\"newQte"+result.lootPacks[i].id+"\" type=\"text\" class=\"validate\"> <label for=\"newQte\">Changer la quantité ?</label>"+
                    "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"modifyQte("+result.lootPacks[i].id+")\">Qte</a>"+
                    "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"removeLootPack("+result.lootPacks[i].id+")\">Remove</a>"+
                    "</div></li>";
            }
            b.innerHTML += "</ul>";
            document.getElementById("currentPack").appendChild(p);
            document.getElementById("gestion-Pack").appendChild(b);
            document.getElementById("actionPack").innerHTML = "<a href=\"#modal-newPack\" class=\"btn-flat modal-action modal-close modal-trigger\">New</a>"+
                "<a data-activates=\'slide-out\' class=\"btn-flat button-collapse\" onclick=\"addGetLootPack()\">Add</a>"+
                "<a href=\"#modal-gestionPack\" class=\"btn-flat modal-action modal-close modal-trigger\">Modifier</a>"+
                "<a onclick=\"switchMiseEnVente()\" class=\"btn-flat modal-action modal-close modal-trigger\">Switch</a>";


            if(result.nom != null) document.getElementById("idCurrentPack").innerHTML = "Pack courant : nom = " + result.nom + ", id = "+result.id;
            else document.getElementById("idCurrentPack").innerHTML = "Pack courant : id = "+result.id;
            $(".button-collapse").sideNav();



        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}

//Charge les listes d'Ensembles/LootPacks/Packs et les affiche.
function getListEnsembles(){
    $.ajax({

        url : 'AdminManager',
        type : 'POST',
        data : 'idRequest=42',

        success: function (data) {

            var result = JSON.parse(data);
            document.getElementById("listEnsembles").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.length; i++){
                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"E"+result[i].id+"\" onclick=\"setCurrentEnsemble("+result[i].id+")\"><p>Ensemble "+result[i].id+"</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("listEnsembles").appendChild(p);

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
function getListLootPacks(){
    $.ajax({

        url : 'AdminManager',

        type : 'POST',
        data : 'idRequest=43',


        success: function (data) {
            var result = JSON.parse(data);
            document.getElementById("listLootPack").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.length; i++){

                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"LP"+result[i].id+"\" onclick=\"setCurrentLootPack("+result[i].id+")\"><p>LootPack "+result[i].id+"</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";

            }
            document.getElementById("listLootPack").appendChild(p);

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
function getListPacks(){
    $.ajax({

        url : 'AdminManager',

        type : 'POST',
        data : 'idRequest=44',


        success: function (data) {
            var result = JSON.parse(data);
            document.getElementById("listPack").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.length; i++){

                if(result[i].misEnVente == 1) {
                    p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                        "       <div class=\"block\" id=\"P"+result[i].id+"\" onclick=\"setCurrentPack("+result[i].id+")\"><p>Pack "+result[i].id+"</p></div>" +
                        "     </div>";
                }
                else
                {
                    p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                        "       <div class=\"pev\" id=\"P"+result[i].id+"\" onclick=\"setCurrentPack("+result[i].id+")\"><p>Pack "+result[i].id+"</p></div>" +
                        "     </div>";
                }
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("listPack").appendChild(p);
        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}


//Charge les cartes ajoutables à l'Ensemble courant, et les place dans le slide-out. Le slide-out s'ouvre en parallèle.
function addGetCarte(){

    $.ajax({

        url : 'AdminManager',
        type : 'POST',
        data : 'idRequest=10',

        success: function (data) {

            var result = JSON.parse(data);
            document.getElementById("slide-out").innerHTML = "";
            var p = document.createElement('div');

            for(var i = 0; i < result.length; i++){
                p.innerHTML += "<li onclick=\"addCarteToCurrentEnsemble("+result[i].id+")\"><img id=\'c"+result[i].id+"\' class=\"card_img_crd_b\" src=\'../img/CARDS/" + result[i].img + "\' ></li>";
            }
            document.getElementById("slide-out").appendChild(p);


        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });

}
//Ajoute une carte choisie dans le slide-out à l'Ensemble courant. Ferme le slide-out.
function addCarteToCurrentEnsemble(id_carte) {
    $.ajax({

        url : 'AdminManager',
        type : 'POST',
        data : {"idRequest": 11,"id_carte": id_carte},
        dataType : 'json',

        success: function (data) {

            var result = JSON.parse(data.responseText);
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
            Materialize.toast('Carte '+id_carte+' ajoutée !', 4000);
            $('.button-collapse').sideNav('hide');

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
//Enlève une carte de l'Ensemble courant.
function removeCarte(id_carte){

    var verif = prompt("Voulez-vous supprimer la carte "+id_carte+" de l'ensemble courant ?");
    if (verif == "Oui" || verif == "oui" || verif == "yes" || verif == "Yes" || verif == "OUI" || verif == "YES" || verif == "o" || verif == "O" || verif == "y" || verif == "Y") {


        $.ajax({

            url: 'AdminManager',
            type: 'POST',
            data: {"idRequest": 12, "id_carte": id_carte},
            dataType: 'json',

            success: function (data) {

                var result = JSON.parse(data.responseText);
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
                Materialize.toast('Carte '+id_carte+' supprimée !', 4000);

            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }
        });
    }
    else {
        Materialize.toast('Vous ne vouliez pas supprimer la carte '+id_carte, 4000)
    }

}




//Charge les Ensembles ajoutables au LootPack courant, et les place dans le slide-out. Le slide-out s'ouvre en parallèle.
function addGetEnsemble(){
    $.ajax({

        url : 'AdminManager',
        type : 'POST',
        data : 'idRequest=13',

        success: function (data) {

            var result = JSON.parse(data);
            document.getElementById("slide-out").innerHTML = "";
            var p = document.createElement('div');

            p.innerHTML ="<div class=\"row ipa\"><div class=\"input-field col s12 m12 l12\"><input id=\"dropEns\" type=\"text\" class=\"validate\"><label for=\"dropEns\" class='truncate'>DropRate de l'ensemble</label></div></div>";

            for(var i = 0; i < result.length; i++){
                p.innerHTML += "<li class='slb' ><div class=\"block\" id=\"E"+result[i].id+"\" onclick=\"addEnsembleToCurrentLootPack("+result[i].id+")\"><p>Ensemble "+result[i].id+"</p></div></li>";
            }
            document.getElementById("slide-out").appendChild(p);


        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
//Ajoute l'Ensemble au LootPack courant, lui associant un dropRate (rentré dans l'input du slide-out, par défaut 0 si l'utilisateur n'a rien mis).
function addEnsembleToCurrentLootPack(value){
    var drop;
    if (document.getElementById("dropEns").value !== "") drop = document.getElementById("dropEns").value;
    else drop = 0.0;

    $.ajax({

        url : 'AdminManager',
        type : 'POST',
        data : {"idRequest": 14,"id_Ensemble": value, "dropRate": drop},
        dataType : 'json',

        success: function (data) {

            var result = data;
            document.getElementById("currentLootPack").innerHTML = "";
            document.getElementById("gestion-LootPack").innerHTML = "";
            var p = document.createElement('div');
            var b = document.createElement('div');
            b.innerHTML = "<ul>";

            for(var i = 0; i < result.ensembles.length; i++){

                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"eLP"+result.ensembles[i].id+"\" onclick=\"setCurrentEnsemble("+result.ensembles[i].id+")\"><p>Ensemble "+result.ensembles[i].id+"</br>"+result.ensembles[i].dropRate+"%</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";

                b.innerHTML += "<li class=\'row\'><div class=\"block \" id=\"eLP"+result.ensembles[i].id+"\" onclick=\"removeEnsemble("+result.ensembles[i].id+")\"><p>Ensemble "+result.ensembles[i].id+"</br>"+result.ensembles[i].dropRate+"%</p></div></div>"+
                    "<div class=\"input-field\"><input id=\"newDropRate"+result.ensembles[i].id+"\" type=\"text\" class=\"validate\"> <label for=\"newDropRate\">Changer le DropRate ?</label>"+
                    "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"modifyDropRate("+result.ensembles[i].id+")\">DropRate</a>"+
                    "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"removeEnsemble("+result.ensembles[i].id+")\">Remove</a>"+
                    "</div></li>";
            }

            document.getElementById("currentLootPack").appendChild(p);
            document.getElementById("gestion-LootPack").appendChild(b);

            Materialize.toast('Ensemble '+value+' ajouté !', 4000);
            $('.button-collapse').sideNav('hide');

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });

}
//Enlève un Ensemble du LootPack courant.
function removeEnsemble(id_ensemble){
    $("modal-gestionLootPack").modal('close');
    var verif = prompt("Voulez-vous supprimer l'ensemble "+id_ensemble+" de l'ensemble courant ?");
    if (verif == "Oui" || verif == "oui" || verif == "yes" || verif == "Yes" || verif == "OUI" || verif == "YES" || verif == "o" || verif == "O" || verif == "y" || verif == "Y") {


        $.ajax({

            url: 'AdminManager',
            type: 'POST',

            data: {"idRequest": 15, "id_Ensemble": id_ensemble},

            dataType: 'json',

            success: function (data) {

                var result = data;
                document.getElementById("currentLootPack").innerHTML = "";
                document.getElementById("gestion-LootPack").innerHTML = "";
                var p = document.createElement('div');
                var b = document.createElement('div');
                b.innerHTML = "<ul>";

                for(var i = 0; i < result.ensembles.length; i++){

                    p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                        "       <div class=\"block\" id=\"eLP"+result.ensembles[i].id+"\" onclick=\"setCurrentEnsemble("+result.ensembles[i].id+")\"><p>Ensemble "+result.ensembles[i].id+"</br>"+result.ensembles[i].dropRate+"%</p></div></div>";
                    if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";

                    b.innerHTML += "<li class=\'row\'><div class=\"block \" id=\"eLP"+result.ensembles[i].id+"\" onclick=\"removeEnsemble("+result.ensembles[i].id+")\"><p>Ensemble "+result.ensembles[i].id+"</br>"+result.ensembles[i].dropRate+"%</p></div></div>"+
                        "<div class=\"input-field\"><input id=\"newDropRate"+result.ensembles[i].id+"\" type=\"text\" class=\"validate\"> <label for=\"newDropRate\">Changer le DropRate ?</label>"+
                        "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"modifyDropRate("+result.ensembles[i].id+")\">DropRate</a>"+
                        "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"removeEnsemble("+result.ensembles[i].id+")\">Remove</a>"+
                        "</div></li>";
                }
                b.innerHTML += "</ul>";
                document.getElementById("currentLootPack").appendChild(p);
                document.getElementById("gestion-LootPack").appendChild(b);
                Materialize.toast('Ensemble '+id_ensemble+' retiré !', 4000);

            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }
        });
    }
    else {
        Materialize.toast('Vous ne vouliez pas supprimer l\'ensemble du LootPack '+id_ensemble, 4000);
    }




}
//Modifie le dropRate d'un Ensemble du LootPack courant, en fonction de la valeur de l'input associé. Si input vide, retourne une erreur.
function modifyDropRate(id_ensemble) {

    if ($('#newDropRate' + id_ensemble).val().length != 0) {
        var drop = $('#newDropRate' + id_ensemble).val();

        $.ajax({

            url: 'AdminManager',
            type: 'POST',
            data: {"idRequest": 16, "id_Ensemble": id_ensemble, "dropRate": drop},
            dataType: 'json',

            success: function (data) {

                var result = data;
                document.getElementById("currentLootPack").innerHTML = "";
                document.getElementById("gestion-LootPack").innerHTML = "";
                var p = document.createElement('div');
                var b = document.createElement('div');
                b.innerHTML = "<ul>";

                for (var i = 0; i < result.ensembles.length; i++) {
                    p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                        "       <div class=\"block\" id=\"eLP" + result.ensembles[i].id + "\" onclick=\"setCurrentEnsemble(" + result.ensembles[i].id + ")\"><p>Ensemble " + result.ensembles[i].id + "</br>" + result.ensembles[i].dropRate + "%</p></div></div>";
                    if (i != 0 && i % 6 == 5) p.innerHTML += "</div><div class=\"row\">";
                    b.innerHTML += "<li><div class=\"block\" id=\"eLP" + result.ensembles[i].id + "\" ><p>Ensemble " + result.ensembles[i].id + "</br>" + result.ensembles[i].dropRate + "%</p></div></div>" +
                        "<div class=\"input-field\"><input id=\"newDropRate"+result.ensembles[i].id+"\" type=\"text\" class=\"validate\"> <label for=\"newDropRate\">Changer le DropRate ?</label>" +
                        "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"modifyDropRate(" + result.ensembles[i].id + ")\">DropRate</a>" +
                        "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"removeEnsemble(" + result.ensembles[i].id + ")\">Remove</a>" +
                        "</div></li>";
                }
                b.innerHTML += "</ul>";
                document.getElementById("currentLootPack").appendChild(p);
                document.getElementById("gestion-LootPack").appendChild(b);
                Materialize.toast('DropRate de l\'Ensemble ' + id_ensemble + ' modifié !', 4000);

            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }
        });
    }
    else {
        Materialize.toast('Input du DropRate vide', 4000);
    }
}


//Charge les LootPacks ajoutables au Pack courant, et les place dans le slide-out. Le slide-out s'ouvre en parallèle.
function addGetLootPack(){
    $.ajax({

        url : 'AdminManager',
        type : 'POST',
        data : 'idRequest=17',

        success: function (data) {

            var result = JSON.parse(data);
            document.getElementById("slide-out").innerHTML = "";
            var p = document.createElement('div');

            p.innerHTML ="<div class=\"row ipa\"><div class=\"input-field col s12 m12 l12\"><input id=\"qteLP\" type=\"text\" class=\"validate\"><label for=\"qtePack\">Quantité de cartes à piocher</label></div></div>";

            for(var i = 0; i < result.length; i++){
                p.innerHTML += "<li class='slb'><div class=\"block\" id=\"lp"+result[i].id+"\" onclick=\"addLootPackToCurrentPack("+result[i].id+")\"><p>LootPack "+result[i].id+"</p></div></li>";
            }

            document.getElementById("slide-out").appendChild(p);

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
//Ajoute un LootPack au Pack courant, lui associant un nombre de tirage qte (rentré dans l'input du slide-out, par défaut 0 si l'utilisateur n'a rien mis).
function addLootPackToCurrentPack(value){
    var qte;
    if (document.getElementById("qteLP").value !== "") qte = document.getElementById("qteLP").value;
    else qte = 0;

    $.ajax({

        url : 'AdminManager',
        type : 'POST',
        data : {"idRequest": 18,"id_LootPack": value, "qte": qte},
        dataType : 'json',

        success: function (data) {

            var result = data;
            document.getElementById("currentPack").innerHTML = "";
            document.getElementById("gestion-Pack").innerHTML = "";
            var p = document.createElement('div');
            var b = document.createElement('div');
            b.innerHTML = "<ul>";

            for(var i = 0; i < result.lootPacks.length; i++){

                p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"lpp"+result.lootPacks[i].id+"\" onclick=\"setCurrentLootPack("+result.lootPacks[i].id+")\">LootPack "+result.lootPacks[i].id+"<br>"+result.lootPacks[i].qte+" cartes</p></div>" +
                    "     </div>";
                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";

                b.innerHTML += "<li class=\'row\'><div class=\"block \" id=\"lpP"+result.lootPacks[i].id+"\" onclick=\"removeLootPack("+result.lootPacks[i].id+")\"><p>LootPack "+result.lootPacks[i].id+"</br>"+result.lootPacks[i].qte+" cartes</p></div></div>"+
                    "<div class=\"input-field\"><input id=\"newQte"+result.lootPacks[i].id+"\" type=\"text\" class=\"validate\"> <label for=\"newQte\">Changer la quantité ?</label>"+
                    "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"modifyQte("+result.lootPacks[i].id+")\">Qte</a>"+
                    "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"removeLootPack("+result.lootPacks[i].id+")\">remove</a>"+
                    "</div></li>";
            }
            b.innerHTML += "</ul>";
            document.getElementById("currentPack").appendChild(p);
            document.getElementById("gestion-Pack").appendChild(b);

            Materialize.toast('LootPack '+value+' ajouté !', 4000);
            $('.button-collapse').sideNav('hide');

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}
//Enlève un LootPack du Pack courant.
function removeLootPack(value){

    $("modal-gestionPack").modal('close');
    var verif = prompt("Voulez-vous supprimer le LootPack "+value+" du pack courant ?");
    if (verif == "Oui" || verif == "oui" || verif == "yes" || verif == "Yes" || verif == "OUI" || verif == "YES" || verif == "o" || verif == "O" || verif == "y" || verif == "Y") {


        $.ajax({

            url: 'AdminManager',
            type: 'POST',
            data: {"idRequest": 19, "id_LootPack": value},
            dataType: 'json',

            success: function (data) {

                var result = data;
                document.getElementById("currentPack").innerHTML = "";
                document.getElementById("gestion-Pack").innerHTML = "";
                var p = document.createElement('div');
                var b = document.createElement('div');
                b.innerHTML = "<ul>";

                for(var i = 0; i < result.lootPacks.length; i++){

                    p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                        "       <div class=\"block\" id=\"lpp"+result.lootPacks[i].id+"\" onclick=\"setCurrentLootPack("+result.lootPacks[i].id+")\">LootPack "+result.lootPacks[i].id+"<br>"+result.lootPacks[i].qte+" cartes</p></div>" +
                        "     </div>";
                    if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";

                    b.innerHTML += "<li class=\'row\'><div class=\"block \" id=\"lpP"+result.lootPacks[i].id+"\" onclick=\"removeLootPack("+result.lootPacks[i].id+")\"><p>LootPack "+result.lootPacks[i].id+"</br>"+result.lootPacks[i].qte+" cartes</p></div></div>"+
                        "<div class=\"input-field\"><input id=\"newQte"+result.lootPacks[i].id+"\" type=\"text\" class=\"validate\"> <label for=\"newQte\">Changer la quantité ?</label>"+
                        "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"modifyQte("+result.lootPacks[i].id+")\">Qte</a>"+
                        "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"removeLootPack("+result.lootPacks[i].id+")\">Remove</a>"+
                        "</div></li>";
                }
                b.innerHTML += "</ul>";

                document.getElementById("currentPack").appendChild(p);
                document.getElementById("gestion-Pack").appendChild(b);
                Materialize.toast('LootPack '+value+' retiré !', 4000);

            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }
        });
    }
    else {
        Materialize.toast('Vous ne vouliez pas supprimer le lootPack '+value+' du Pack', 4000);
    }
}
//Modifie l'attribut Qte pour un LootPack donné, dans le Pack courant, en fonction de la valeur de l'input associé. Si input vide, retourne une erreur.
function modifyQte(value){

    if($('#newQte'+value).val().length != 0) {
        var qte = $('#newQte' + value).val();

        $.ajax({

            url: 'AdminManager',
            type: 'POST',
            data: {"idRequest": 20, "id_LootPack": value, "qte": qte},
            dataType: 'json',

            success: function (data) {

                var result = data;
                document.getElementById("currentPack").innerHTML = "";
                document.getElementById("gestion-Pack").innerHTML = "";
                var p = document.createElement('div');
                var b = document.createElement('div');
                b.innerHTML = "<ul>";

                for (var i = 0; i < result.lootPacks.length; i++) {

                    p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                        "       <div class=\"block\" id=\"lpp"+result.lootPacks[i].id+"\" onclick=\"setCurrentLootPack("+result.lootPacks[i].id+")\">LootPack "+result.lootPacks[i].id+"<br>"+result.lootPacks[i].qte+" cartes</p></div>" +
                        "     </div>";
                    if (i != 0 && i % 6 == 5) p.innerHTML += "</div><div class=\"row\">";

                    b.innerHTML += "<li class=\'row\'><div class=\"block \" id=\"lpP" + result.lootPacks[i].id + "\" onclick=\"removeLootPack(" + result.lootPacks[i].id + ")\"><p>LootPack " + result.lootPacks[i].id + "</br>" + result.lootPacks[i].qte + " cartes</p></div></div>" +
                        "<div class=\"input-field\"><input id=\"newQte" + result.lootPacks[i].id + "\" type=\"text\" class=\"validate\"> <label for=\"newQte\">Changer la quantité ?</label>" +
                        "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"modifyQte(" + result.lootPacks[i].id + ")\">Qte</a>" +
                        "<a class=\"waves-effect waves-light btn modal-close\" onclick=\"removeLootPack(" + result.lootPacks[i].id + ")\">Remove</a>" +
                        "</div></li>";
                }
                b.innerHTML += "</ul>";
                document.getElementById("currentPack").appendChild(p);
                document.getElementById("gestion-Pack").appendChild(b);
                Materialize.toast('Quantité du LootPack ' + value + ' modifié !', 4000);

            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }

        });
    }
    else {
        Materialize.toast('Input du DropRate vide', 4000);
    }

}
//Redéfinit la mise en vente ou non d'un Pack et de son Offre.
function switchMiseEnVente(){
    $.ajax({

        url: 'AdminManager',
        type: 'POST',
        data: {"idRequest": 21},
        dataType: 'json',

        success: function (data) {

            var result = data;

            if(result.misEnVente == 1){
                $("#P"+result.id).attr('class', 'block');
                Materialize.toast(result.id+' mis en vente', 4000);
            }
            else {
                $("#P"+result.id).attr('class','pev');
                Materialize.toast(result.id+' retiré de la vente',4000);
            }
        },

        error: function (code_html, status) {
            console.log("request failed " + status);
        }


    });


}
