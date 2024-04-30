from flask import Flask, render_template
from flask_socketio import SocketIO, send
import socket

app =  Flask(__name__)
app.config['SECRET'] = "secret!123"
socketio = SocketIO(app, cors_allowed_origins="*")


@socketio.on('message')

def handle_message(message):
    print("Received message: " + message)
    if message != "User connected!":
        send(message, broadcast=True)
        
@app.route('/')

def index():
    return render_template("index.html")

if __name__ == "__main__":
    host_ip = socket.gethostbyname(socket.gethostname())
    socketio.run(app, host=str(host_ip))