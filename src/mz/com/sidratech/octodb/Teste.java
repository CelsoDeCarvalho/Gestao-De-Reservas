package mz.com.sidratech.octodb;

import java.util.ArrayList;
import java.util.List;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Central;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.model.dao.DaoGenerico;

/**
 *
 * @author celso
 */
public class Teste {
    
    public static void main(String[] args) {   
        
        //Alojamento alojamento=new Alojamento("3 estrelas","Hotel Resotel","Hotel","AV Ahmed Sekou Toure","andre faguntes","12345");
        Alojamento alojamento=new Alojamento();
        //Restauracao restauracao=new Restauracao(0, 0,"Las Vegas","Bar","Muahivire Expansao","ninas","celso12345");
        Central central=new Central("OctoDB","AV 3 De Fevereiro"
                + "","neeno", "neeno12345");
        
        DaoGenerico daoGenerico=new DaoGenerico();
        //daoGenerico.create(central);
        //daoGenerico.create(alojamento);
        List<Alojamento>alojamentos=new ArrayList<>();
        System.out.println(daoGenerico.readAll("Entidade")+"\n");
    }
}
