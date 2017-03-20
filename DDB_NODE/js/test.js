$(document).ready(function(){
    console.log("Ready");

    $("#zone_content_configure_account").hide();
    $("#zone_content_starter").hide();

     $("#step1").trigger("mouseenter");


     setTimeout(function(){ next_step();}, 2000);
     setTimeout(function(){ next_step();}, 4000);
});