package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
@Table(name = "alojamento")
public class Alojamento extends Entidade implements Serializable {

    private String classificacao;
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "idAlojamento")
    private List<Cliente> clientes;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idAlojamento")
    private List<Quarto> quartos;

    public Alojamento(String classificacao, String nome, String tipo, String enderecoFisico, String username, String password) {
        super(nome, tipo, enderecoFisico, username, password);
        this.classificacao = classificacao;
    }

    @Override
    public String toString() {
        return "\nTIPO: "+getTipo()+"\nNOME: "+getNome()+"\n"
                + "LOCALIZACAo: "+getEnderecoFisico()+"\nID: "+getIdEntidade()+"\n"
                + "CLASSIFICACAO: "+classificacao;
                
                
    }
    
    
    
    
}