package chat_tcp;


import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

class Cliente{
    
    Usuario usuario;
    DataInputStream entrada;
    DataOutputStream saida;
    Socket socket;
    
 
    Cliente(DataInputStream entrada, DataOutputStream saida, Socket socket){
        this.socket=socket;
        this.saida = saida;
        this.entrada = entrada;
    }
    
    Cliente(Usuario usuario){
        this.usuario=usuario;
    }
        
    void conectar() throws IOException{
        socket = new Socket(InetAddress.getByName(usuario.ip), usuario.porta);
        saida = new DataOutputStream(socket.getOutputStream());
        entrada = new DataInputStream(socket.getInputStream());
    }

    void enviarMensagem(String mensagem){
       try{
           saida.writeUTF(mensagem);
           System.out.println("Cliente enviou mensagem ");
       }
       catch(IOException e){
           JOptionPane.showMessageDialog(null, e);
       }   
    }
    
    String receberMensagem() throws IOException{               
        String mensagem=entrada.readUTF();
        return mensagem;
    }
    
    void desconectar() throws IOException{
        socket.close();
    }
}