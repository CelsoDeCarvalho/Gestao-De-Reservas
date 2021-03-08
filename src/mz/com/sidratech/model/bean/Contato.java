package mz.com.sidratech.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "contato")
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContato;
    private int telefone;
    private String url;
    private String email;
    private int telemovel;
    @JoinColumn(name = "idEntidade", referencedColumnName = "idEntidade")
    @OneToOne
    private Entidade idEntidade;    

    public Contato(int telefone, String url, String email, int telemovel, Entidade idEntidade) {
        this.telefone = telefone;
        this.url = url;
        this.email = email;
        this.telemovel = telemovel;
        this.idEntidade = idEntidade;
    }

    @Override
    public String toString() {
        return "ID DO CONTATO: " + idContato + "\nTELEFONE: " + telefone + "\nURL" + url + "\n"
                + "EMAIL: =" + email + "\nTELEMOVEL: " + telemovel + "\nID ENTIDADE" + idEntidade;
    }
    
    
}