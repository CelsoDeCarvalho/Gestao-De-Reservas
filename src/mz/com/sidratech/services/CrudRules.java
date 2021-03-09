package mz.com.sidratech.services;

import java.util.List;
/**
 *
 * @author celso
 */
public interface CrudRules {

    public abstract void create(Object object);

    public abstract List<Object> readAll(String TipoObjeto);
    
    public abstract Object readById(int id,Object object);

    public abstract void update(Object object);

    public abstract void delete(int id,Object object);
}
