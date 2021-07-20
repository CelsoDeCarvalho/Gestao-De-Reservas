package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name = "restauracao")
public class Restauracao extends Entidade implements Serializable {

    private int totalMesas;
    private int totalCadeiras;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRestauracao")
    private List<Prato> pratos;

    public Restauracao(String tipo, String nome, String enderecoFisico, String username, String password, String classificacao) {
        super(nome, enderecoFisico, username, password, tipo, classificacao);
        this.totalMesas = totalMesas;
        this.totalCadeiras = totalCadeiras;
    }

    @Override
    public String toString() {
        return "\nTIPO: " + getTipo() + "\nNOME: " + getNome() + "\n"
                + "LOCALIZACAo: " + getEnderecoFisico() + "\nID: " + getIdEntidade() + "\n"
                + "NUMERO DE CADEIRAS: " + totalCadeiras + "\nNUMERO MESAS: " + totalMesas;

    }

}
