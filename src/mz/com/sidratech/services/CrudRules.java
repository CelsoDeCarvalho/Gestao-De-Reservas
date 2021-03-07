package mz.com.sidratech.services;

import java.util.List;

/**
 *
 * @author celso
 */
public interface CrudRules {

    public abstract String create(Object object);

    public abstract List<Object> readAll();
    
    public abstract Object readById(int id);

    public abstract String update(Object object);

    public abstract String delete(Object object);
}
