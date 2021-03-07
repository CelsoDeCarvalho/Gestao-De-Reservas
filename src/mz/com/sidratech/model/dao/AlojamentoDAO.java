package mz.com.sidratech.model.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import mz.com.sidratech.connection.ConnectionFactory;
import mz.com.sidratech.model.bean.Alojamento;
/**
 *
 * @author celso
 */
public class AlojamentoDAO implements Serializable {
    
    public  String create(Alojamento alojamento) {
        
        EntityManager entityManager= ConnectionFactory.getConnection();
        
        try{
           entityManager.getTransaction().begin();
           
           entityManager.persist(alojamento);
           
           entityManager.getTransaction().commit();
           return "adicionado com sucesso";
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
           throw new RuntimeException("Erro ao Salvar Alojamento",ex);
        }finally{
            entityManager.close();
        }
    }
    
}
