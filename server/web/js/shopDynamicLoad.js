/**
 * Created by deutsch on 12/02/17.
 */


d = document.getElementById(Offre);
var id = Offre.match(/\d+/)[0];
d.innerHTML = "<div id=\"offreFormAchat\" class=\"input-field col s12 m12 l12\">" +
    "                       <input readonly value=\""+id+"\" id=\"id_Offre\" name=\"id_Offre\" type=\"text\" class=\"validate\">" +
    "                       <label for=\"disabled\">Offre choisie</label>" +
    "                  </div>";