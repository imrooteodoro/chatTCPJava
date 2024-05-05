from flask import Flask, render_template, request, redirect, url_for, session, jsonify
from flask_socketio import SocketIO, send
from datetime import datetime
import sys

app = Flask(__name__)
app.config['SECRET_KEY'] = 'your_secret_key'  # Substitua 'your_secret_key' por uma chave secreta real
app.config['SESSION_TYPE'] = 'filesystem'
app.config['SESSION_PERMANENT'] = True
socketio = SocketIO(app, cors_allowed_origins="*")

# Usuários Conectados
usuarios_conectados = []

@app.route('/')
def index():
    return render_template("login.html")

@app.route('/login', methods=['POST'])
def login():
    endereco_ip = request.form.get('endereco_ip')
    porta = request.form.get('porta')
    nome_usuario = request.form.get('nome_usuario')

    # Aqui você adicionaria sua lógica de validação real, como verificar contra um banco de dados
    if nome_usuario == 'admin' and porta == '5000':  # Exemplo de validação simples
        session['nome_usuario'] = nome_usuario
        session['porta'] = porta
        return jsonify({'status': 'success'})
    else:
        return jsonify({'status': 'error', 'message': 'Usuário ou senha inválidos'})

@socketio.on('message')
def handle_message(message):
    print("Mensagem recebida: " + message)
    if message != "User connected":
        current_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        message_with_time = f"{current_time} - {message}"
        send(message_with_time, broadcast=True)

@socketio.on('connect')
def handle_connect():
    usuarios_conectados.append(request.sid)
    print("\nConectados: " + str(usuarios_conectados) + "\n")
    if request.sid in usuarios_conectados:
        send('1234 conectou', broadcast=True)
        print('um cliente se conectou')

@socketio.on('disconnect')
def handle_disconnect():
    usuarios_conectados.remove(request.sid)
    print("\nConectados: " + str(usuarios_conectados) + "\n")
    if request.sid not in usuarios_conectados:
        send('1234 desconectou', broadcast=True)
        print("um cliente se desconectou")

if __name__ == "__main__":
    porta_server = int(sys.argv[1]) if len(sys.argv) > 1 else 5000
    socketio.run(app, host="localhost", port=porta_server, debug=False)