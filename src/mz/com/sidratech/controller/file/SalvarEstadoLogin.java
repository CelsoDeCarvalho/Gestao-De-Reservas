package mz.com.sidratech.controller.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import mz.com.sidratech.model.bean.EstadoLogin;

/**
 *
 * @author celso
 */
public class SalvarEstadoLogin {
    
    /**
     * 
     * @param login 
     * recebe o estado do login de uma entidade ou usuario no ambito do seu login e gravado 
     * 1 para representar entidade ou usuario logado
     */
    public static void guardarLogin(EstadoLogin login){
        File arquivo=new File("login.dat");
        try{
            arquivo.delete();
            arquivo.createNewFile();
            ObjectOutputStream escritor=new ObjectOutputStream(new FileOutputStream(arquivo));
            escritor.writeObject(login);
            escritor.close();
            
        }catch(IOException e){
            System.err.println("IMPOSSIVEL GRAVAR FICHEIRO");
        }
        
    }
    
    public static void apagarFicheiro(){
        File arquivo=new File("login.dat");
        if(arquivo.exists())
            arquivo.delete();
    }
}
