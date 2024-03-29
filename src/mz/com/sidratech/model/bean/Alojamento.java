package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY ,mappedBy = "idAlojamento")
    private List<Cliente> clientes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "idAlojamento")
    private List<Quarto> quartos;

    public Alojamento(String classificacao,String tipo, String nome, String enderecoFisico, String username, String password) {
        super(nome, enderecoFisico, username, password,tipo,classificacao);

    }    
    
}