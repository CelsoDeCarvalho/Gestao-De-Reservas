package mz.com.sidratech.repository;

import java.util.ArrayList;
import java.util.List;
import mz.com.sidratech.model.bean.Cliente;
import mz.com.sidratech.model.bean.Contato;
import mz.com.sidratech.model.bean.Entidade;
import mz.com.sidratech.model.bean.Funcionario;
import mz.com.sidratech.model.bean.Quarto;
import mz.com.sidratech.model.bean.Relatorio;
import mz.com.sidratech.model.dao.DaoGenerico;

/**
 *
 * @author celso
 */
public class Repository {

    public static ArrayList<Entidade> entidades = null;
    public static ArrayList<Funcionario> funcionarios = null;
    public static ArrayList<Contato> contatos = null;
    public static ArrayList<Quarto> quartos = null;
    public static ArrayList<Cliente> clientes = null;
    public static ArrayList<Relatorio> relatorios = null;

    DaoGenerico daoGenerico;

    public void getEntidades() {
        daoGenerico = new DaoGenerico();
        List<Object> objects = daoGenerico.readAll("Entidade");
        entidades = (ArrayList<Entidade>) (Object) objects;
    }

    public void getFuncionarios() {
        daoGenerico = new DaoGenerico();
        List<Object> objects = daoGenerico.readAll("Funcionario");
        funcionarios = (ArrayList<Funcionario>) (Object) objects;
    }

    public void getContactos() {
        daoGenerico = new DaoGenerico();
        List<Object> objects = daoGenerico.readAll("Contato");
        contatos = (ArrayList<Contato>) (Object) objects;
    }

    public void getQuartos() {
        daoGenerico = new DaoGenerico();
        List<Object> objects = daoGenerico.readAll("Quarto");
        quartos = (ArrayList<Quarto>) (Object) objects;
    }

    public void getClientes() {
        daoGenerico = new DaoGenerico();
        List<Object> objects = daoGenerico.readAll("Cliente");
        clientes = (ArrayList<Cliente>) (Object) objects;
    }
    
        public void getRelatorios() {
        daoGenerico = new DaoGenerico();
        List<Object> objects = daoGenerico.readAll("Relatorio");
        relatorios = (ArrayList<Relatorio>) (Object) objects;
    }

}
