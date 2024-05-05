
$(document).ready(function(){
    var socket = io();

    socket.on('connect', function(){
        socket.send("User connected!");
    });

    socket.on('user_connected', function(data){
        socket.send('User connected: ' + data.user_count);
        $('#message').val('');
    });

    socket.on('user_disconnected', function(data){
        socket.send('User disconnected: ' + data.user_count);
        $('#message').val('');
    });

    socket.on('message', function(data){
        $('#messages').append($('<p>').text(data));
    });

    $('#enviar_btn').on('click', function(){
        socket.send($('#user_name').val() + ': ' + $('#message').val());
        $('#message').val('');
    });
});

function atualizarHorario() {
    var data = new Date();
    var horas = data.getHours();
    var minutos = data.getMinutes();
    var segundos = data.getSeconds();

    horas = (horas < 10) ? "0" + horas : horas;
    minutos = (minutos < 10) ? "0" + minutos : minutos;
    segundos = (segundos < 10) ? "0" + segundos : segundos;

    document.getElementById("hora-local").textContent = horas + ":" + minutos + ":" + segundos;
}

setInterval(atualizarHorario, 1000);

window.onload = atualizarHorario;