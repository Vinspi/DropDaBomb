
var socket;
var step = 1;

$(document).ready(function(){

    socket = io.connect('http://localhost:8080');

    $("#btn-create").click(function(e){create_account();});

    socket.on("CREATE_ACCOUNT_FAIL_ALREADYEXIST", function(obj){
        Materialize.toast("Un compte avec ce pseudo/email existe déjà", 4000);
        
    });
    socket.on("CREATE_ACCOUNT_SUCCESS", function(obj){
        next_step();
    });

});


function create_account(){


    var pseudo      = $("#pseudo").val();
    var mdp         = $("#password").val();
    var mdp_confirm = $("#password_confirm").val();
    var email       = $("#email").val();

    if(pseudo == "" || pseudo == " "){                              
        Materialize.toast('Pseudo invalide', 6000);         return;    }

    if(mdp == "" || mdp == " "){                                    
        Materialize.toast('Mot de passe invalide', 6000);         return;    }

    if(mdp_confirm != mdp){                                         
        Materialize.toast('Les mots de passes de correspondent pas', 6000);         return;    }

    if(email == "" || email == " " || email.indexOf('@') < 1){      
        Materialize.toast('Email invalide', 6000);         return;    }
    

    var account = {
        'pseudo': pseudo,
        'mdp': mdp,
        'mdp_confirm': mdp_confirm,
        'email': email
    }

    console.log(account);

    socket.emit("CREATE_ACCOUNT_SEND", account);

}


function next_step(){

    $("#tab-step-"+step).removeClass("tab-active");
    $("#tab-step-"+step).addClass("disabled");
    $("#tab-step-"+step).tooltip("remove");

    step++;
    $("#tab-step-"+step).removeClass("disabled");
    $("#tab-step-"+step).addClass("tab-active");
    $("#tab-step-"+step+"-click").trigger("click");
    $("#tab-step-"+step).trigger("mouseenter");

   

    // console.log("toto");

    // $("#step"+step).tooltip('remove');
    // $("#step"+step+"_text").removeClass("point-actif");

    // step++;

    // $("#step"+step).trigger("mouseenter");
    // $("#step"+step+"_text").addClass("point-actif");


    // if(step == 2){
    //     $("#zone_content_form").hide();
    //     $("#zone_content_configure_account").show();
    // }
    // else if(step == 3){
    //     $("#zone_content_form").hide();
    //     $("#zone_content_configure_account").hide();
    //     $("#zone_content_starter").show();
    // }


}





