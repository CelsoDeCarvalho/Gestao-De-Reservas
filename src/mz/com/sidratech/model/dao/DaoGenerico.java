package mz.com.sidratech.model.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import mz.com.sidratech.connection.ConnectionFactory;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.model.bean.Administrador;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Cliente;
import mz.com.sidratech.model.bean.Entidade;
import mz.com.sidratech.model.bean.Faxineiro;
import mz.com.sidratech.model.bean.Funcionario;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.model.bean.Usuario;
import mz.com.sidratech.services.CrudRules;

/**
 *
 * @author celso
 */
public class DaoGenerico implements CrudRules {

    //METODO PARA GRAVAR NO BANCO DE DADOS======================================
    /**
     *
     * @param object recebe o ojecto a ser gravado no banco de dados e o
     * persiste
     */
    @Override
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
    /**
     *
     * @param tipoObjeto
     * @return Recebe uma string com o nome do tipo de objecto EX:
     * readAll("Aluno") faz a procura no banco de dados um objeto do tipo a
     * Aluno e retorna a lista correspondente
     */
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
    /**
     *
     * @param id recebe o id do objeto a ser procurado
     * @param object recebe o tipo de objeto a ser procurado
     * @return objecto procurado com o id e o tipo de objeto acima passados
     */
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
    /**
     *
     * @param object O objeto a ser actualizado
     */
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
    /**
     *
     * @param id rececebe o id do objeto a ser apagado
     * @param object o objeto a ser apagado
     */
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

    /**
     *
     * @param nome faz procura dinamica no banco do objeto cliente atraves do
     * atributo nome
     * @return retona a lista encontrada dos objetos que contem esse nome
     */
    public List<Cliente> searchGuest(String nome) {
        DaoGenerico daoGenerico = new DaoGenerico();
        EntityManager entityManager = ConnectionFactory.getConnection();
        List<Object[]> objects = new ArrayList();
        List<Cliente> clientes = new ArrayList();

        objects = entityManager.createNativeQuery("SELECT * FROM cliente WHERE (nome LIKE '%" + nome + "%')OR (dataSaida LIKE '%" + nome + "%') OR (dataEntrada LIKE '%" + nome + "%') OR (numeroQuarto LIKE '%" + nome + "%')").getResultList();
        Cliente cliente;
        Alojamento alojamento;
        Date date;

        for (int i = 0; i < objects.size(); i++) {
            alojamento = new Alojamento();
            cliente = new Cliente();
            cliente.setIdCliente((int) objects.get(i)[0]);
            date = (Date) objects.get(i)[1];
            cliente.setDataEntrada(date.toLocalDate());
            date = (Date) objects.get(i)[2];
            cliente.setDataSaida(date.toLocalDate());
            cliente.setNome((String) objects.get(i)[3]);
            cliente.setNumeroQuarto((int) objects.get(i)[4]);
            cliente.setValorPagar((double) objects.get(i)[5]);
            cliente.setIdAlojamento((Alojamento) daoGenerico.readById((int) objects.get(i)[6], alojamento));
            clientes.add(cliente);
        }
        return clientes;
    }

    /**
     *
     * @param nome faz procura dinamica no banco do objeto Employee atraves do
     * atributo nome
     * @return retona a lista encontrada dos objetos que contem esse nome
     */
    public List<Funcionario> searchEmployee(String nome) {
        DaoGenerico daoGenerico = new DaoGenerico();
        EntityManager entityManager = ConnectionFactory.getConnection();
        List<Object[]> objects = new ArrayList();
        List<Funcionario> funcionarios = new ArrayList();

        objects = entityManager.createNativeQuery("SELECT * FROM funcionario WHERE nome LIKE '%" + nome + "%'").getResultList();
        Funcionario funcionario = null;

        for (int i = 0; i < objects.size(); i++) {
            if ((int) objects.get(i)[9] == LerEstadoLogin.lerLogin().getIdEntidade()) {
                if (objects.get(i)[7].equals("Administrador")) {
                    funcionario = new Administrador();
                } else if (objects.get(i)[7].equals("Usuario")) {
                    funcionario = new Usuario();
                } else {
                    funcionario = new Faxineiro();
                }

                funcionario.setIdFuncionario((int) objects.get(i)[0]);
                funcionario.setNome((String) objects.get(i)[3]);
                funcionario.setApelido((String) objects.get(i)[1]);
                funcionario.setEmail((String) objects.get(i)[2]);
                funcionario.setPassword((String) objects.get(i)[4]);
                funcionario.setUsername((String) objects.get(i)[8]);
                funcionario.setTipo((String) objects.get(i)[7]);
                funcionario.setSexo((char) objects.get(i)[5]);
                funcionario.setTelefone((int) objects.get(i)[6]);

                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }

    /**
     *
     * @param nome faz procura dinamica no banco do objeto Entidade atraves do
     * atributo nome
     * @return retona a lista encontrada dos objetos que contem esse nome
     */
    public List<Entidade> searchEntity(String nome) {
        EntityManager entityManager = ConnectionFactory.getConnection();
        List<Object[]> objects = new ArrayList();
        List<Entidade> entidades = new ArrayList();

        objects = entityManager.createNativeQuery("SELECT * FROM entidade WHERE (nome LIKE '%" + nome + "%') OR (idEntidade LIKE '%" + nome + "%')OR (enderecoFisico LIKE '%" + nome + "%')OR (tipo LIKE '%" + nome + "%')OR (classificacao LIKE '%" + nome + "%')").getResultList();
        Entidade entidade = null;

        for (int i = 0; i < objects.size(); i++) {
            String tipo = (String) objects.get(i)[5];
            String classi=(String) objects.get(i)[1];

            if (tipo.equals("") || tipo.isEmpty() || tipo == null) {
            } else {
                if (classi==null||classi.equals("")|classi.isEmpty()) {
                    entidade = new Restauracao();

                    entidade.setIdEntidade((int) objects.get(i)[0]);
                    entidade.setClassificacao((String) objects.get(i)[1]);
                    entidade.setEnderecoFisico((String) objects.get(i)[2]);
                    entidade.setNome((String) objects.get(i)[3]);
                    entidade.setTipo((String) objects.get(i)[5]);
                    entidade.setPassword((String) objects.get(i)[4]);
                    entidade.setUsername((String) objects.get(i)[6]);
                    entidades.add(entidade);
                } else {
                    entidade = new Alojamento();
                    entidade.setIdEntidade((int) objects.get(i)[0]);
                    entidade.setClassificacao((String) objects.get(i)[1]);
                    entidade.setEnderecoFisico((String) objects.get(i)[2]);
                    entidade.setNome((String) objects.get(i)[3]);
                    entidade.setTipo((String) objects.get(i)[5]);
                    entidade.setPassword((String) objects.get(i)[4]);
                    entidade.setUsername((String) objects.get(i)[6]);
                    entidades.add(entidade);
                }

            }

        }
        return entidades;
    }

}
