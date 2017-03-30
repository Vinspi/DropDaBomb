/**
 * Created by deutsch on 23/03/17.
 */

$(document).ready(function() {

    getShopView();

    console.log("get lancé");



});



function getShopView(){

    $.ajax({

        url : 'Shop',

        type : 'POST',
        data : 'idRequest=0',


        success: function (data) {
            console.log(data);
            var result = data;//JSON.parse(data);

            console.log('ajax');
            //Pack

            var p = document.createElement('div');

            for(var i = 0; i < result.listPackView.length; i++){


                p.innerHTML += " <div class=\"card\">" +
                "<div class=\"card-image waves-effect waves-block waves-light\">" +
                    "<img class=\"activator img_pack\" src=\"../img/PACKS/" + result.listPackView[i].image + "\">" +
                "</div>" +
                "<div class=\"card-content\">" +
                    "<p class=\"activator\">"+result.listPackView[i].nom+"</p>" +
                "</div>" +
                "<div class=\"card-reveal\">" +
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIG("+result.listPackView[i].id_Offre+")\">IG</a>"+
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIRL("+result.listPackView[i].id_Offre+")\">IRL</a>"+
                "</div>" +
                "</div>";

                /*p.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"p"+result.listPackView[i].id_Offre+"\" onclick=\"acheterOffre("+result.listPackView[i].id_Offre+")\"><img class=\"img_pack\" src=\'../img/PACKS/" + result.listPackView[i].image + "\' ><p>"+result.listPackView[i].nom+"</p></div>" +
                    "     </div>";*/
            }
            console.log("fin liste");
            document.getElementById("pack").appendChild(p);


            //Boost

            var q = document.createElement("div");

            q.innerHTML = "";
            for(var i = 0; i < result.listBoostView.length; i++){

                q.innerHTML += " <div class=\"card\">" +
                    "<div class=\"card-image waves-effect waves-block waves-light\">" +
                    "<img class=\"activator img_pack\" src=\"../img/BOOSTS/" + result.listBoostView[i].image + "\">" +
                    "</div>" +
                    "<div class=\"card-content\">" +
                    "<p class=\"activator\">"+result.listBoostView[i].nom+"</p>" +
                    "</div>" +
                    "<div class=\"card-reveal\">" +
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIG("+result.listBoostView[i].id_Offre+")\">IG</a>"+
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIRL("+result.listBoostView[i].id_Offre+")\">IRL</a>"+
                    "</div>" +
                    "</div>";

/*                q.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"b"+result.listBoostView[i].id_Offre+"\" onclick=\"acheterOffre("+result.listBoostView[i].id_Offre+")\"><img class=\"img_boost\" src=\'../img/BOOSTS/" + result.listBoostView[i].image + "\' ><p>"+result.listBoostView[i].nom+"</p></div>" +
                    "     </div>";
*/                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";
            }
            document.getElementById("boost").appendChild(q);
            console.log("cc");






            //Map
            var r = document.createElement("div");

            for(var i = 0; i < result.listSkinMapView.length; i++){
                r.innerHTML += " <div class=\"card\">" +
                    "<div class=\"card-image waves-effect waves-block waves-light\">" +
                    "<img class=\"activator img_map\" src=\"../img/SKIN_MAP/" + result.listSkinMapView[i].image + "\">" +
                    "</div>" +
                    "<div class=\"card-content\">" +
                    "<p class=\"activator\">"+result.listSkinMapView[i].nom+"</p>" +
                    "</div>" +
                    "<div class=\"card-reveal\">" +
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIG("+result.listSkinMapView[i].id_Offre+")\">IG</a>"+
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIRL("+result.listSkinMapView[i].id_Offre+")\">IRL</a>"+
                    "</div>" +
                    "</div>";
                /*r.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"m"+result.listSkinMapView[i].id_Offre+"\" onclick=\"acheterOffre("+result.listSkinMapView[i].id_Offre+")\"><img class=\"img_map\" src=\'../img/SKIN_MAP/" + result.listSkinMapView[i].image + "\' ><p>"+result.listSkinMapView[i].nom+"</p></div>" +
                    "     </div>";*/
            }
            document.getElementById("map").appendChild(r);


            //Carton
            var s = document.createElement("div");
            for(var i = 0; i < result.listSkinCartonView.length; i++){
                s.innerHTML += " <div class=\"card\">" +
                    "<div class=\"card-image waves-effect waves-block waves-light\">" +
                    "<img class=\"activator img_carton\" src=\"../img/SKIN_CARTE/" + result.listSkinCartonView[i].image + "\">" +
                    "</div>" +
                    "<div class=\"card-content\">" +
                    "<p class=\"activator\">"+result.listSkinCartonView[i].nom+"</p>" +
                    "</div>" +
                    "<div class=\"card-reveal\">" +
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIG("+result.listSkinCartonView[i].id_Offre+")\">IG</a>"+
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIRL("+result.listSkinCartonView[i].id_Offre+")\">IRL</a>"+
                    "</div>" +
                    "</div>";

                /*
                s.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"c"+result.listSkinCartonView[i].id_Offre+"\" onclick=\"acheterOffre("+result.listSkinCartonView[i].id_Offre+")\"><img class=\"img_carton\" src=\'../img/SKIN_CARTE/" + result.listSkinCartonView[i].image + "\' ><p>"+result.listSkinCartonView[i].nom+"</p></div>" +
                    "     </div>";*/
            }
            document.getElementById("carton").appendChild(s);


            //Icone
            var t = document.createElement('div');
            for(var i = 0; i < result.listIconeView.length; i++){
                t.innerHTML += " <div class=\"card\">" +
                    "<div class=\"card-image waves-effect waves-block waves-light\">" +
                    "<img class=\"activator img_icone\" src=\"../img/ICONES/" + result.listIconeView[i].image + "\">" +
                    "</div>" +
                    "<div class=\"card-content\">" +
                    "<p class=\"activator\">"+result.listIconeView[i].nom+"</p>" +
                    "</div>" +
                    "<div class=\"card-reveal\">" +
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIG("+result.listIconeView[i].id_Offre+")\">IG</a>"+
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIRL("+result.listIconeView[i].id_Offre+")\">IRL</a>"+
                    "</div>" +
                    "</div>";

                /*t.innerHTML += "<div class=\"col s2 m2 l2\">" +
                    "       <div class=\"block\" id=\"i"+result.listIconeView[i].id_Offre+"\" onclick=\"acheterOffre("+result.listIconeView[i].id_Offre+")\"><img class=\"img_carton\" src=\'../img/ICONES/" + result.listIconeView[i].image + "\' ><p>"+result.listIconeView[i].nom+"</p></div>" +
                    "     </div>";*/
            }
            document.getElementById("icone").appendChild(t);
            console.log("qq");


        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });





}


function acheterOffreIG(id_offre) {

    var verif = prompt("Voulez-vous acheter l'offre " + id_offre + " avec de la monnaieIG ?");
    if (verif == "Oui" || verif == "oui" || verif == "yes" || verif == "Yes" || verif == "OUI" || verif == "YES" || verif == "o" || verif == "O" || verif == "y" || verif == "Y") {
        $.ajax({

            url: 'ShopAchat',
            type: 'POST',

            data: {"idRequest": 1, "id_Offre": id_offre, "money": "monnaieIG"},

            dataType: 'json',

            success: function (data) {

                console.log(data);
                Materialize.toast('Offre '+id_offre+' acheté !', 4000) // 4000 is the duration of the toast

            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }
        });
    }
    else {
        Materialize.toast('Vous ne vouliez pas acheter l\'offre '+id_offre, 4000) // 4000 is the duration of the toast
    }
}


function acheterOffreIRL(id_offre) {

    var verif = prompt("Voulez-vous acheter l'offre " + id_offre + " avec de la monnaieIRL ?");
    if (verif == "Oui" || verif == "oui" || verif == "yes" || verif == "Yes" || verif == "OUI" || verif == "YES" || verif == "o" || verif == "O" || verif == "y" || verif == "Y") {
        $.ajax({

            url: 'ShopAchat',
            type: 'POST',

            data: {"idRequest": 2, "id_Offre": id_offre, "money": "monnaieIRL"},

            dataType: 'json',

            success: function (data) {
                console.log(data);

                Materialize.toast('Offre '+id_offre+' acheté !', 4000) // 4000 is the duration of the toast

            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }
        });
    }
    else {
        Materialize.toast('Vous ne vouliez pas acheter l\'offre '+id_offre, 4000) // 4000 is the duration of the toast
    }
}