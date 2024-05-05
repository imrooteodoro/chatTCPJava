from flask import Flask, render_template, request
from flask_socketio import SocketIO, send
from datetime import datetime
import sys, socket
import subprocess

app = Flask(__name__)
app.config['SECRET'] = "secret!123"
socketio = SocketIO(app, cors_allowed_origins="*")

# Usuarios Conectados
usuarios_conectados = {}

def pegar_ip():
    out = subprocess.Popen(['hostname', '-I'], stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    stdout,_ = out.communicate()
    stdout = stdout.decode().strip()
    return stdout


@app.route('/')
def login():
    return render_template("login.html")

@app.route('/chat')
def index():
    return render_template ('index.html')

@socketio.on('message')
def handle_message(message):
    print("Messagem recebida: " + message)
    current_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    if message != "User connected!":
        message_with_time = f'{current_time} - {message}'
        send(message_with_time, broadcast=True)

@socketio.on('connect')
def handle_connect():
    current_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    user_id = request.sid
    usuarios_conectados[user_id] = current_time
    print("\n Conectados: "+str(usuarios_conectados)+"\n")
    if user_id in usuarios_conectados:
        send(f'{current_time} {user_id} conectou', broadcast=True)
        print('um cliente se conectou')

@socketio.on('disconnect')
def handle_disconnect():
    current_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    user_id = request.sid
    if user_id in usuarios_conectados:
        del usuarios_conectados[user_id]
        print("\n Conectados: "+str(usuarios_conectados)+"\n")
        send(f'{current_time} {user_id} desconectou', broadcast=True)
        print("um cliente se desconectou")

if __name__ == "__main__":
    porta_server = int(sys.argv[1]) if len(sys.argv) > 1 else 5000
    socketio.run(app, host=pegar_ip(), port=porta_server, debug=True)
