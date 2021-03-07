package mz.com.sidratech.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author celso
 */
public class ConnectionFactory{
    
    private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("MyUnit");
    
    public static EntityManager getConnection(){
        return emf.createEntityManager();
    }
    
}