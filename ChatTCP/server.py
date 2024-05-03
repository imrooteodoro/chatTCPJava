from flask import Flask, render_template, request
from flask_socketio import SocketIO, send
import sys

app =  Flask(__name__)
app.config['SECRET'] = "secret!123"
socketio = SocketIO(app, cors_allowed_origins="*")

# Usuarios Conectados
usuarios_conectados = set()


@app.route('/')

def index():
    return render_template("index.html")


@socketio.on('message')
def handle_message(message):
    print("Messagem recebida: " + message)
    if message != "User connected!":
        socketio.emit('message', message, broadcast=True)
#        send(message, broadcast=True)

@socketio.on('connect')
def handle_connect():
    print('um cliente se conectou')
    # Adiciona o ID do cliente aos usuários conectados
    usuarios_conectados.add(request.sid)
    # Envia uma mensagem de aviso para todos os clientes que um novo usuário se conectou
    socketio.emit('user_connected', {'user_count': len(usuarios_conectados)}, broadcast=True)

@socketio.on('disconnect')
def handle_disconnect():
    print("um cliente se desconectou")
    if request.sid in usuarios_conectados:
        print(usuarios_conectados)
        usuarios_conectados.remove(request.sid)
        socketio.emit('user_disconnect', {'user_count': len(usuarios_conectados)}, broadcast=True)


if __name__ == "__main__":
    porta_server = int(sys.argv[1]) if len(sys.argv) > 1 else 5000
    socketio.run(app, host="192.168.1.103", port= porta_server, debug=False)