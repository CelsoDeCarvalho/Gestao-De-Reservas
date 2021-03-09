package mz.com.sidratech.model.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "central")
public class Central extends Entidade implements Serializable {

    public Central(String nome, String enderecoFisico, String username, String password) {
        super(nome, enderecoFisico, username, password);
    }

    @Override
    public String toString() {
        return "NOME: " + getNome() + "\nENDERECO FISICO: " + getEnderecoFisico() + "\n"
                + "USERNAME: " + getUsername() + "\nPASSWORD: " + getPassword() + "\n"
                + "ID: " + getIdEntidade();
    }

}
