var i = 0;
var showChat = true;
var nbMessage = 0;
var socket = io.connect('http://192.168.43.7:3000');

var pseudo = $('#pseudo').html();

//$(document).ready(hideOrShowChat());


if(pseudo !== undefined){
  socket.emit('auth',pseudo);
  socket.on('updateusers', function(message){
    console.log(message);
    $('#utilisateurs').html("");
    var array = message.split(",");
    for(var i=0;i<array.length;i++){
      $('#utilisateurs').append("<li>"+array[i]+"</li>");
    }
  });

  socket.on('message', function(message){

    if(!showChat) Materialize.toast('new message !', 4000);
    if(i == 0) {
      $('#message-zone').append("<li id=1> "+message.user+"> "+message.msg+"</li>");
      nbMessage++;
      i++;
    }
    else{
      console.log(i);
      $('#'+i).after("<li id="+(i+1)+"> "+message.user+"> "+message.msg+"</li>");
      if(nbMessage != 10) nbMessage++;
      else {
        $('#'+(i-10)).remove();
      }
      i++;
    }

  });
}

function hideOrShowChat(){
  if(showChat) $('.chat').hide();
  else $('.chat').show();
  showChat = !showChat;
}

function sendMessage(){
  socket.emit('message',$('#message').val());
  $('#message').val("");
}
