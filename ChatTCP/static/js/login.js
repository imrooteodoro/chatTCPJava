
$(document).ready(function(){
    // Lidar com o evento submit do formulário
    $('#form_login_cliente').submit(function(e) {
        e.preventDefault(); // Evitar o comportamento padrão de recarregar a página
        
        ip = $('#endereco_ip').val();
        porta = $('#porta').val();
        nome_usuario = $('#nome_usuario').val();
        
        url = "http://" + ip + ":" + porta;
        
        var socket = io.connect(url);
        
        socket.on('connect', function(){
            socket.send("User connected!");
            console.log("User connected!");
            window.location.href = "/chat";
            
        });
    });
});