/**
 * Created by deutsch on 10/03/17.
 */

function setCurrentEnsemble(value){
    $.ajax({

        url : 'Admin',

        type : 'GET',

        data : 'idRequest=0&id_ensemble='+value.substring(8),

        succes: function (code_html,status) {
            console.log("succes "+status);
            alert("coucou");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}

function setCurrentLootPack(){
    $.ajax({

        url : 'Admin',

        type : 'GET',

        data : 'idRequest=1&id_LootPack='+value.substring(2),

        succes: function (code_html,status) {
            console.log("succes "+status);
            alert("coucou");

        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });
}

function setCurrentPack() {
    $.ajax({

        url : 'Admin',

        type : 'GET',

        data : 'idRequest=2&id_ensemble='+value.substring(1),

        succes: function (code_html,status) {
            console.log("succes "+status);
            alert("coucou");

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

function createEnsemble(name){
    $.ajax({

        url : 'Admin',

        type : 'POST',

        data : {"idRequest": 4,"nomEnsemble": name},

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