package chat_tcp;


import java.io.DataInputStream;
import java.io.IOException;
import javax.swing.JTextArea;

class ThreadRecebe extends Thread{
JTextArea area;
DataInputStream entrada;

ThreadRecebe(JTextArea area, DataInputStream entrada){
    this.area=area;
    this.entrada=entrada;
}

public void run(){
    try{  
        while(true){
            area.setText(entrada.readUTF());
        }
    }catch(IOException e){
        System.out.println("erro ao ler mensagem no cliente "+e);
    }
}

}