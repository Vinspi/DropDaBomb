$(document).ready(function(){
    console.log("Document pret");
    
    setZoneSize();
    
    console.log("ScreenW: " + ScreenW);
    console.log("ScreenH: " + ScreenH);
    
    console.log("ZoneW: " + ZoneW);
    console.log("ZoneH: " + ZoneH);
    
    setZoneObjects();
        
    $('#modal_connexion').modal({
      dismissible: false, // Modal can be dismissed by clicking outside of the modal
      opacity: .5, // Opacity of modal background
      inDuration: 300, // Transition in duration
      outDuration: 200, // Transition out duration
      startingTop: '4%', // Starting top style attribute
      endingTop: '10%', // Ending top style attribute
    });
    
    $('#modal_connexion').modal('open');
    
    
    $("#connexion_btn").click(function(e){
                
        var auth = { 'pseudo': $("#connexion_pseudo").val(), 'mdp': $("#connexion_mdp").val()};
        console.log(auth);
        socket.emit("AUTH_CLI", auth); 
    });
    
    socket.on("AUTH_CLI_OK", function(obj){
            var $toastContent = $('<span color="red">OK CONNECTE</span>');
            Materialize.toast($toastContent, 6000);
            $('#modal_connexion').modal('close');
    } );
    
    
    socket.on("AUTH_CLI_FAIL", function(obj){
            var $toastContent = $('<span color="red">Erreur, mauvais pseudo et/ou mot de passe !</span>');
            Materialize.toast($toastContent, 6000);
            $("#connexion_pseudo").val("");
            $("#connexion_mdp").val("");

    } );
    
    
    
    
    
    
  });






