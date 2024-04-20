package chat_tcp;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

class Servidor extends Thread{
    ServerSocket server;
    Socket socket;
    DataInputStream entrada;
    DataOutputStream saida;
    int porta;
    
    ArrayList<Cliente> listaClientes;

    Servidor(int porta){
        this.porta=porta;
        listaClientes = new ArrayList<Cliente>();
    }
    
    
    public void run(){
        while(true)
        try {
            socket = server.accept();
            saida = new DataOutputStream(socket.getOutputStream());
            entrada = new DataInputStream(socket.getInputStream());
            
            listaClientes.add(new Cliente(entrada,saida, socket));
            
            new Thread(){
                public void run(){
                    while(true)
                    try {
                        System.out.println("Aguardando mensagem... ");
                        String mensagem = receberMensagem();
                        System.out.println("Mensagem recebida pelo servidor...");
                        enviarMensagemTodos(mensagem);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,ex );   
                    }
                }
            }.start();
          
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null,e );   
        }        
    }
   
    void iniciar(){
        try {
            server = new ServerSocket(porta);
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null,e );        
        }
    }
    
    void desconectar() throws IOException{
        socket.close();
    }
    
    void enviarMensagemTodos(String mensagem) throws IOException{
        for(Cliente c: listaClientes){
            c.saida.writeUTF(mensagem);
        }  
    }
    
    String receberMensagem() throws IOException{
        String mensagem = entrada.readUTF();
        return mensagem;
    }
    
}
    
