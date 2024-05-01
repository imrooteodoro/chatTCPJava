from tkinter import *
from tkinter.ttk import Label, Style
import subprocess
import platform

############################# FUNÇÕES E VALIDAÇÃO #############################

def iniciar_servidor():
    
    so = verificar_SO()
    global server_process
    porta = int(textBox_porta.get("1.0","end"))
    server_process = subprocess.Popen([str(so), "server.py", str(porta)])
    print("Servidor Iniciado")

def fechar_servidor():
    
    global server_process
    if server_process:
        server_process.kill()
        server_process = None
        exit()
    else:
        exit()

def verificar_SO():
    so_atual = platform.system()
    
    if so_atual == "Linux":
        return "python3"
    elif so_atual == "Windows":
        return "python"
    
############################## INTERFACE GRÁFICA ##############################

#### CRIAÇÃO DA JANELA ####
window = Tk()
window.title("Servidor")
window.geometry("600x400")
window.configure(background="#91c29b")


#### CRIAÇÃO DA LOGO ####
logo = PhotoImage(file="assets/servidor_chat.png", width=150, height=150)

label_logo = Label(window, image=logo, justify="center", background="#91c29b")
window.update()
label_logo.place(x=(window.winfo_width()/2 - 75),y=20,width=150, height=150)


#### CRIAÇÃO DA LABEL E TEXTBOX PARA CADASTRO DA ''PORTA'' ####
label_porta = Label(window, text="Porta:", font=("Arial","14","bold"), justify="center", background="#91c29b")
label_porta.place(x=(window.winfo_width()/2 - 90), y=window.winfo_height()/2 + 30, width=60, height=25)

textBox_porta = Text(window, font=("Arial","12"))
textBox_porta.place(x=(window.winfo_width()/2 - 25), y=window.winfo_height()/2 + 30,width= 120, height=25)


#### CRIAÇÃO DE UM BOTÃO PARA FAZER A ENTRADA DA PORTA OU CANCELAR ####
botao_iniciar = Button(window, text="Iniciar", font=("Velvica","16","bold"), justify="center", background="#31693c", foreground="#ededed", command=iniciar_servidor)
botao_iniciar.place(x=(window.winfo_width()/2 - botao_iniciar.winfo_width()) - 160, y=window.winfo_height()/2 + 130, width=140, height=30)

botao_cancelar = Button(window, text="Cancelar", font=("Velvica","16","bold"), justify="center", background="#d13f3f", foreground="#ededed", command=fechar_servidor)
botao_cancelar.place(x=(window.winfo_width()/2 - botao_cancelar.winfo_width()) + 40, y=window.winfo_height()/2 + 130, width=140, height=30)
window.mainloop()

