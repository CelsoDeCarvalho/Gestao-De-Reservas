package mz.com.sidratech.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import mz.com.sidratech.connection.ConnectionFactory;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.services.CrudRules;
/**
 *
 * @author celso
 */
public class DaoGenerico implements CrudRules{

    public void create(Object object) {
        EntityManager entityManager = ConnectionFactory.getConnection();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alojamento> readAll() {
        EntityManager entityManager= ConnectionFactory.getConnection();
        List<Alojamento> alojamentos=null;
        
        try{
            alojamentos=entityManager.createQuery("from Alojamento").getResultList();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }finally{
            entityManager.close();
        }
        return alojamentos;
    }

    @Override
    public Object readById(int id,Object object) {
               
        EntityManager entityManager= ConnectionFactory.getConnection();
        
       try{
           object=entityManager.find(object.getClass(),id);
       }catch(Exception ex){
           throw new RuntimeException(ex);
       }finally{
           entityManager.close();
       }
       
       return object;
    }

     @Override
    public  void update(Object object) {
        
        EntityManager entityManager= ConnectionFactory.getConnection();
        
        try{
           entityManager.getTransaction().begin();
           entityManager.merge(object);
           
           entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
        }finally{
            entityManager.close();
        }
    }

    @Override
    public void delete(int id,Object object) {
                       
        EntityManager entityManager= ConnectionFactory.getConnection();
        
       try{
           object=entityManager.find(object.getClass(),id);
           entityManager.getTransaction().begin();
           entityManager.remove(object);
           entityManager.getTransaction().commit();
       }catch(Exception ex){
           entityManager.getTransaction().rollback();
           throw new RuntimeException(ex);
       }finally{
           entityManager.close();
       }
    }

}
