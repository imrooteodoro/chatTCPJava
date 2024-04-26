package chat_tcp;


import java.io.DataInputStream;
import java.io.IOException;
import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.List;

class ThreadRecebe extends Thread{
    JTextArea area;
    DataInputStream entrada;

    ThreadRecebe(JTextArea area, DataInputStream entrada){
        this.area=area;
        this.entrada=entrada;
    }

    public void run(){
        List<String> lista_mensagens = new ArrayList<>();
        
        try{
            while(true){
                lista_mensagens.add((entrada.readUTF()).toString());
                
                StringBuilder mensagem_completa = new StringBuilder();
                
                lista_mensagens.forEach((msg) -> {
                    mensagem_completa.append(msg+"\n\n");
                });
                
                area.setText(mensagem_completa.toString());
            }
        }catch(IOException e){
            System.out.println("erro ao ler mensagem no cliente "+e);
        }
    }
    
    /*public Mensagens mensagens_recebidas(String mensagem){
        List<Mensagens> mensagens = new ArrayList<Mensagens>();
        mensagens.add(mensagem,null);
        return mensagens;
    }
*/
}