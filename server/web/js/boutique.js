////Fonctions JS de la page boutique.jsp. Utilisées pour la génération de la page et pour les traitements (achat d'une Offre).

//Lorsque le document est prêt, charger la Boutique.
$(document).ready(function() {
    getShopView();
});


//Charge la vue de la boutique et l'affiche.
function getShopView(){

    $.ajax({

        url : 'Shop',
        type : 'POST',
        data : 'idRequest=0',


        success: function (data) {

            var result = data;


            //Affichage des Packs
            var p = document.createElement('div');
            for(var i = 0; i < result.listPackView.length; i++){


                p.innerHTML += "<div class=\"col s2 m2 l2\"><div class=\"card\">" +
                    "<div class=\"card-image waves-effect waves-block waves-light\">" +
                        "<img class=\"activator img_pack\" src=\"../img/utils/" + result.listPackView[i].image + "\">" +
                    "</div>" +
                    "<div class=\"card-content nom-pack\">" +
                        "<p class=\"activator\">"+result.listPackView[i].nom+"</p>" +
                    "</div>" +
                    "<div class=\"card-reveal\">" +
                        "<i class=\"card-title material-icons right\">close</i>" +
                        "<p>"+result.listPackView[i].description+"</p>" +
                        "<div class='btnAchat'><a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIG("+result.listPackView[i].id_Offre+")\">"+result.listPackView[i].monnaieIG+" IG</a>"+
                        "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIRL("+result.listPackView[i].id_Offre+")\">"+result.listPackView[i].monnaieIRL+" IRL</a></div>"+
                    "</div>" +
                    "</div></div>";

                if(i != 0 && i%6 == 5) p.innerHTML += "</div><div class=\"row\">";

            }
            p.innerHTML += "</div>";
            document.getElementById("pack").appendChild(p);


            //Affichage des Boosts
            var q = document.createElement("div");
            for(i = 0; i < result.listBoostView.length; i++){

                q.innerHTML += "<div class=\"col s2 m2 l2\"><div class=\"card\">" +
                    "<div class=\"card-image waves-effect waves-block waves-light\">" +
                    "<img class=\"activator img_pack\" src=\"../img/" + result.listBoostView[i].image + "\">" +
                    "</div>" +
                    "<div class=\"card-content nom-boost\">" +
                    "<p class=\"activator\">"+result.listBoostView[i].nom+"</p>" +
                    "</div>" +
                    "<div class=\"card-reveal\">" +
                    "<i class=\"card-title material-icons right\">close</i>" +
                    "<p>"+result.listBoostView[i].description+"</p>" +
                    "<div class='btnAchat'><a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIG("+result.listBoostView[i].id_Offre+")\">"+result.listBoostView[i].monnaieIG+" IG</a>"+
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIRL("+result.listBoostView[i].id_Offre+")\">"+result.listBoostView[i].monnaieIRL+" IRL</a></div>"+
                    "</div>" +
                    "</div></div>";

                if(i != 0 && i%6 == 5) q.innerHTML += "</div><div class=\"row\">";
            }
            q.innerHTML += "</div>";
            document.getElementById("boost").appendChild(q);






            //Affichage des SkinMaps
            var r = document.createElement("div");
            for(i = 0; i < result.listSkinMapView.length; i++){

                r.innerHTML += "<div class=\"col s2 m2 l2\"><div class=\"card\">" +
                    "<div class=\"card-image waves-effect waves-block waves-light\">" +
                    "<img class=\"activator img_map\" src=\"../img/SKIN_MAP/" + result.listSkinMapView[i].image + "\">" +
                    "</div>" +
                    "<div class=\"card-content nom-map\">" +
                    "<p class=\"activator\">"+result.listSkinMapView[i].nom+"</p>" +
                    "</div>" +
                    "<div class=\"card-reveal\">" +
                    "<i class=\"card-title material-icons right\">close</i>" +
                    "<p>"+result.listSkinMapView[i].description+"</p>" +
                    "<div class='btnAchat'><a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIG("+result.listSkinMapView[i].id_Offre+")\">"+result.listSkinMapView[i].monnaieIG+" IG</a>"+
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIRL("+result.listSkinMapView[i].id_Offre+")\">"+result.listSkinMapView[i].monnaieIRL+" IRL</a></div>"+
                    "</div>" +
                    "</div></div>";
                if(i != 0 && i%6 == 5) r.innerHTML += "</div><div class=\"row\">";

            }
            r.innerHTML += "</div>";
            document.getElementById("map").appendChild(r);


            //Affichage des SkinCartons
            var s = document.createElement("div");
            for(var i = 0; i < result.listSkinCartonView.length; i++){

                s.innerHTML += "<div class=\"col s2 m2 l2\"><div class=\"card\">" +
                    "<div class=\"card-image waves-effect waves-block waves-light\">" +
                    "<img class=\"activator img_carton\" src=\"../img/SKIN_CARTE/" + result.listSkinCartonView[i].image + "\">" +
                    "</div>" +
                    "<div class=\"card-content nom-carton\">" +
                    "<p class=\"activator\">"+result.listSkinCartonView[i].nom+"</p>" +
                    "</div>" +
                    "<div class=\"card-reveal\">" +
                    "<i class=\"card-title material-icons right\">close</i>" +
                    "<p>"+result.listSkinCartonView[i].description+"</p>" +
                    "<div class='btnAchat'><a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIG("+result.listSkinCartonView[i].id_Offre+")\">"+result.listSkinCartonView[i].monnaieIG+" IG</a>"+
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIRL("+result.listSkinCartonView[i].id_Offre+")\">"+result.listSkinCartonView[i].monnaieIRL+" IRL</a></div>"+
                    "</div>" +
                    "</div></div>";

                if(i != 0 && i%6 == 5) s.innerHTML += "</div><div class=\"row\">";
            }
            s.innerHTML += "</div>";
            document.getElementById("carton").appendChild(s);


            //Affichage des Icones
            var t = document.createElement('div');
            for(var i = 0; i < result.listIconeView.length; i++){

                t.innerHTML += "<div class=\"col s2 m2 l2\"><div class=\"card\">" +
                    "<div class=\"card-image waves-effect waves-block waves-light\">" +
                    "<img class=\"activator img_icone\" src=\"../img/ICONES/" + result.listIconeView[i].image + "\">" +
                    "</div>" +
                    "<div class=\"card-content nom-icone\">" +
                    "<p class=\"activator\">"+result.listIconeView[i].nom+"</p>" +
                    "</div>" +
                    "<div class=\"card-reveal\">" +
                    "<i class=\"card-title material-icons right\">close</i>" +
                    "<p>"+result.listIconeView[i].description+"</p>" +
                    "<div class='btnAchat'><a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIG("+result.listIconeView[i].id_Offre+")\">"+result.listIconeView[i].monnaieIG+" IG</a>"+
                    "<a class=\"waves-effect waves-light btn\" onclick=\"acheterOffreIRL("+result.listIconeView[i].id_Offre+")\">"+result.listIconeView[i].monnaieIRL+" IRL</a></div>"+
                    "</div>" +
                    "</div></div>";
                if(i != 0 && i%6 == 5) t.innerHTML += "</div><div class=\"row\">";

            }
            t.innerHTML += "</div>";
            document.getElementById("icone").appendChild(t);



        },

        error: function (code_html,status) {
            console.log("request failed "+status);
        }
    });





}

//Demande confirmation d'achat, puis envoie une requête à la servlet ShopAchat pour effectuer l'achat de l'Offre id_offre avec de la monnaieIG.
function acheterOffreIG(id_offre) {

    var verif = prompt("Voulez-vous acheter l'offre " + id_offre + " avec de la monnaieIG ?");
    if (verif == "Oui" || verif == "oui" || verif == "yes" || verif == "Yes" || verif == "OUI" || verif == "YES" || verif == "o" || verif == "O" || verif == "y" || verif == "Y") {
        $.ajax({

            url: 'ShopAchat',
            type: 'POST',
            data: {"idRequest": 1, "id_Offre": id_offre, "money": "monnaieIG"},
            dataType: 'json',

            success: function (data) {

                if(data.id == 5) {  //5 == Achat réussi.
                    Materialize.toast('Offre ' + id_offre + ' acheté !', 4000);

                    $( "#pseudo" ).load(" #pseudo");
                }
                else Materialize.toast('Erreur : veuillez ré-essayer (vous n\'avez pas été débité', 4000);
            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }
        });
    }
    else {
        Materialize.toast('Vous ne vouliez pas acheter l\'offre '+id_offre, 4000);
    }
}


//Demande confirmation d'achat, puis envoie une requête à la servlet ShopAchat pour effectuer l'achat de l'Offre id_offre avec de la monnaieIRL.
function acheterOffreIRL(id_offre) {

    var verif = prompt("Voulez-vous acheter l'offre " + id_offre + " avec de la monnaieIRL ?");
    if (verif == "Oui" || verif == "oui" || verif == "yes" || verif == "Yes" || verif == "OUI" || verif == "YES" || verif == "o" || verif == "O" || verif == "y" || verif == "Y") {
        $.ajax({

            url: 'ShopAchat',
            type: 'POST',
            data: {"idRequest": 2, "id_Offre": id_offre, "money": "monnaieIRL"},
            dataType: 'json',

            success: function (data) {
                if(data.id == 5) {  //5 == Achat réussi.
                    Materialize.toast('Offre ' + id_offre + ' acheté !', 4000);
                }
                else Materialize.toast('Erreur : veuillez ré-essayer (vous n\'avez pas été débité', 4000);
            },

            error: function (code_html, status) {
                console.log("request failed " + status);
            }
        });
    }
    else {
        Materialize.toast('Vous ne vouliez pas acheter l\'offre '+id_offre, 4000);
    }
}