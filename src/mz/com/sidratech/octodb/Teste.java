package mz.com.sidratech.octodb;

import mz.com.sidratech.model.dao.AlojamentoDAO;
import mz.com.sidratech.model.bean.Alojamento;
/**
 *
 * @author celso
 */
public class Teste {
    
    public static void main(String[] args) {
        
        AlojamentoDAO adao=new AlojamentoDAO();
        
        Alojamento alojamento=new Alojamento("Hotel Muhipiti","Hotel","4 estrelas", "AV Edurado Mondlane",20);
        
        adao.create(alojamento);
    }
    
}
