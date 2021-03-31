package mz.com.sidratech.repository;

import java.util.ArrayList;
import java.util.List;
import mz.com.sidratech.model.bean.Contato;
import mz.com.sidratech.model.bean.Entidade;
import mz.com.sidratech.model.bean.Funcionario;
import mz.com.sidratech.model.dao.DaoGenerico;

/**
 *
 * @author celso
 */
public class Repository {
    
    public static ArrayList<Entidade> entidades=null;
    public static ArrayList<Funcionario> funcionarios=null;
    public static ArrayList<Contato> contatos=null;
    
    DaoGenerico daoGenerico;
    
    public  void getEntidades(){
        daoGenerico=new DaoGenerico();
        List<Object> objects= daoGenerico.readAll("Entidade");
        entidades=(ArrayList<Entidade>)(Object)objects;
    }
    
    public  void getFuncionarios(){
        daoGenerico=new DaoGenerico();
        List<Object> objects= daoGenerico.readAll("Funcionario");
        funcionarios=(ArrayList<Funcionario>)(Object)objects;
    }
    
    public void getContactos(){
        daoGenerico=new DaoGenerico();
        List<Object> objects= daoGenerico.readAll("Contato");
        contatos=(ArrayList<Contato>)(Object)objects;
    }
    
}
