package mz.com.sidratech.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import mz.com.sidratech.connection.ConnectionFactory;
import mz.com.sidratech.services.CrudRules;
/**
 *
 * @author celso
 */
public class DaoGenerico implements CrudRules{
    
    //METODO PARA GRAVAR NO BANCO DE DADOS======================================
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
    
    //METODO PARA LER TODOS SO DADOS DO BANCO===================================
    @Override
    public List<Object> readAll(String tipoObjeto) {
        EntityManager entityManager= ConnectionFactory.getConnection();
        List<Object> objects=null;
        
        try{
            objects=entityManager.createQuery("FROM "+tipoObjeto).getResultList();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }finally{
            entityManager.close();
        }
        return objects;
    }

    //METODO PARA PROCURAR DADO POR ID==========================================
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

    //METODO PARA ACTUALIZAR DADOS NO BANCO=====================================
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

    //METODO PARA APAGAR DADOS DO BANCO=========================================
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
