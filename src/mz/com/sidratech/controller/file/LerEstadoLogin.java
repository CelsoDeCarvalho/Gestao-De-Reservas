package mz.com.sidratech.controller.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import mz.com.sidratech.model.bean.EstadoLogin;

/**
 *
 * @author celso
 */
public class LerEstadoLogin {
    
    static File file=new File("login.dat");
    
    
    /**
     * 
     * @return  O objeto lido no ficheiro, nesse caso, o objecto estado do login da entidade e usuario
     * retona 0 se a entidade ou usuario nao estiver logado
     * retona 1 se a entidade ou usuario estiverem logados
     */
    public static EstadoLogin lerLogin(){
        EstadoLogin login=new EstadoLogin();
        try{
            ObjectInputStream leitor=new ObjectInputStream(new FileInputStream(file));
            login=(EstadoLogin)leitor.readObject();
            leitor.close();
        }catch(IOException|ClassNotFoundException e){
            System.out.println("Impossivel acessar o directorio");
        }
        return login;
    }
    
    public  static boolean exist(){
        return file.exists();
    }
    
}
