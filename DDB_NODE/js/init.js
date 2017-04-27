function setZoneSize(){
    if(ScreenW > ScreenH)
        ZoneW = ZoneW * ZoneRatio;    
    
    $("#zone").css("width", ZoneW + "px");
    $("#zone").css("height", ZoneH + "px");
}

function setZoneObjects(){ 
    for(var i=0; i < ZONE_OBJECTS.length; i++){
        var tmp = ZONE_OBJECTS[i];
        $(tmp.id).css("background-color", tmp.background);
    }
}