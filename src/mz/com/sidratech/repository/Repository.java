package mz.com.sidratech.repository;

import java.util.ArrayList;
import java.util.List;
import mz.com.sidratech.model.bean.Entidade;
import mz.com.sidratech.model.dao.DaoGenerico;

/**
 *
 * @author celso
 */
public class Repository {
    
    public static ArrayList<Entidade> entidades=null;
            
    public  void getEntidades(){
        DaoGenerico daoGenerico=new DaoGenerico();
        List<Object> objects= daoGenerico.readAll("Entidade");
        entidades=(ArrayList<Entidade>)(Object)objects;
    }
    
}
