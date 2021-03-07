package mz.com.sidratech.services;

import java.util.List;

/**
 *
 * @author celso
 */
public interface CrudRules {

    abstract String create(Object object);

    abstract List<Object> readAll();
    
    abstract Object readById(int id);

    abstract String update(Object object);

    abstract String delete(Object object);
}
