from flask import Flask, render_template
from flask_socketio import SocketIO, send
import sys

app =  Flask(__name__)
app.config['SECRET'] = "secret!123"
socketio = SocketIO(app, cors_allowed_origins="*")

@socketio.on('message')

def handle_message(message):
    print("Messagem recebida: " + message)
    if message != "User connected!":
        send(message, broadcast=True)

@app.route('/')

def index():
    return render_template("login.html")

if __name__ == "__main__":
    porta_server = int(sys.argv[1]) if len(sys.argv) > 1 else 5000
    socketio.run(app, host="localhost", port= porta_server, debug=True)