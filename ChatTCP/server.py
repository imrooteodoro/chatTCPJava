from flask import Flask, render_template, request
from flask_socketio import SocketIO, send
from datetime import datetime
import sys

app = Flask(__name__)
app.config['SECRET'] = "secret!123"
socketio = SocketIO(app, cors_allowed_origins="*")

# Usuarios Conectados
usuarios_conectados = []
current_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")



@app.route('/')
def index():
    return render_template("index.html")

@socketio.on('message')
def handle_message(message):
    print("Messagem recebida: " + message)
    if message != "User connected!":
        message_with_time = f"{current_time} - {message}"
        send(message_with_time, broadcast=True)

@socketio.on('connect')
def handle_connect():
    usuarios_conectados.append(request.sid)
    print("\n Conectados: "+str(usuarios_conectados)+"\n")
    if request.sid in usuarios_conectados:
        send(f'{current_time} 1234 conectou', broadcast=True)
        print('um cliente se conectou')

@socketio.on('disconnect')
def handle_disconnect():
    usuarios_conectados.remove(request.sid)
    print("\n Conectados: "+str(usuarios_conectados)+"\n")
    if not request.sid in usuarios_conectados:
        send(f'{current_time} 1234 desconectou', broadcast=True)
        print("um cliente se desconectou")

if __name__ == "__main__":
    porta_server = int(sys.argv[1]) if len(sys.argv) > 1 else 5000
    socketio.run(app, host="localhost", port=porta_server, debug=False)
