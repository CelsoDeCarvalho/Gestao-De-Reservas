package mz.com.sidratech.model.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import mz.com.sidratech.connection.ConnectionFactory;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Cliente;
import mz.com.sidratech.services.CrudRules;

/**
 *
 * @author celso
 */
public class DaoGenerico implements CrudRules {

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
        EntityManager entityManager = ConnectionFactory.getConnection();
        List<Object> objects = new ArrayList();

        try {
            objects = entityManager.createQuery("FROM " + tipoObjeto).getResultList();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            entityManager.close();
        }
        return objects;
    }

    //METODO PARA PROCURAR DADO POR ID==========================================
    @Override
    public Object readById(int id, Object object) {

        EntityManager entityManager = ConnectionFactory.getConnection();

        try {
            object = entityManager.find(object.getClass(), id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            entityManager.close();
        }

        return object;
    }

    //METODO PARA ACTUALIZAR DADOS NO BANCO=====================================
    @Override
    public void update(Object object) {

        EntityManager entityManager = ConnectionFactory.getConnection();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    //METODO PARA APAGAR DADOS DO BANCO=========================================
    @Override
    public void delete(int id, Object object) {

        EntityManager entityManager = ConnectionFactory.getConnection();

        try {
            object = entityManager.find(object.getClass(), id);
            entityManager.getTransaction().begin();
            entityManager.remove(object);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(ex);
        } finally {
            entityManager.close();
        }
    }

    public List<Cliente> searchGuest(String nome) {
        DaoGenerico daoGenerico=new DaoGenerico();
        EntityManager entityManager = ConnectionFactory.getConnection();
        List<Object[]> objects = new ArrayList();
        List<Cliente> clientes = new ArrayList();

        objects = entityManager.createNativeQuery("SELECT * FROM cliente WHERE nome LIKE '%" + nome + "%'").getResultList();
        Cliente cliente;
        Alojamento alojamento;
        Date date;
        
        for (int i = 0; i < objects.size(); i++) {
            alojamento = new Alojamento();
            cliente = new Cliente();
            cliente.setIdCliente((int) objects.get(i)[0]);
            date=(Date) objects.get(i)[1];
            cliente.setDataEntrada(date.toLocalDate());
            date=(Date) objects.get(i)[2];
            cliente.setDataSaida(date.toLocalDate());
            cliente.setNome((String) objects.get(i)[3]);
            cliente.setNumeroQuarto((int) objects.get(i)[4]);
            cliente.setValorPagar((double) objects.get(i)[5]);
            cliente.setIdAlojamento((Alojamento)daoGenerico.readById((int) objects.get(i)[6], alojamento));
            clientes.add(cliente);
        }
        return clientes;
    }

}
