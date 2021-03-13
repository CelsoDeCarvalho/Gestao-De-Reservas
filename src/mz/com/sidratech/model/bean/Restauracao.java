package mz.com.sidratech.model.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author celso
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "restauracao")
public class Restauracao extends Entidade implements Serializable {


    private int totalMesas;
    private int totalCadeiras;
    private String pratoDoDia;

    public Restauracao(int totalMesas, int totalCadeiras, String tipo, String nome, String enderecoFisico, String username, String password,String email) {
        super(nome, enderecoFisico, username, password,email,tipo);
        this.totalMesas = totalMesas;
        this.totalCadeiras = totalCadeiras;
    }
    
    

       @Override
    public String toString() {
        return "\nTIPO: "+getTipo()+"\nNOME: "+getNome()+"\n"
                + "LOCALIZACAo: "+getEnderecoFisico()+"\nID: "+getIdEntidade()+"\n"
                + "NUMERO DE CADEIRAS: "+totalCadeiras+"\nNUMERO MESAS: "+totalMesas+""
                + "\nEMAIL: "+getEmail();
                
                
    }
    
}