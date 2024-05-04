from flask import Flask, render_template, request
from flask_socketio import SocketIO, send
import sys

app =  Flask(__name__)
app.config['SECRET'] = "secret!123"
socketio = SocketIO(app, cors_allowed_origins="*")

# Usuarios Conectados
usuarios_conectados = []


@app.route('/')

def index():
    return render_template("index.html")

@socketio.on('message')
def handle_message(message):
    print("Messagem recebida: " + message)
    if message != "User connected!":
       # socketio.emit('message', message, broadcast=True)
        send(message,broadcast=True)

@socketio.on('connect')
def handle_connect():
    # Envia uma mensagem de aviso para todos os clientes que um novo usuário se conectou
    #for cliente in usuarios_conectados:
        #socketio.emit('user_connected', {'user_count': len(usuarios_conectados)}, room=cliente)
    usuarios_conectados.append(request.sid)
    print("\n Conectados: "+str(usuarios_conectados)+"\n")
    if request.sid in usuarios_conectados:
        send('1234 conectou', broadcast=True)
        print('um cliente se conectou')

@socketio.on('disconnect')
def handle_disconnect():
    usuarios_conectados.remove(request.sid)
    print("\n Conectados: "+str(usuarios_conectados)+"\n")
    if not request.sid in usuarios_conectados:
        send('1234 desconectou', broadcast=True)
        print("um cliente se desconectou")

if __name__ == "__main__":
    porta_server = int(sys.argv[1]) if len(sys.argv) > 1 else 5000
    socketio.run(app, host="192.168.1.103", port= porta_server, debug=False)