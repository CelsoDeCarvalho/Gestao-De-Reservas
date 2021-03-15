package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "entidade")
public abstract class Entidade implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idEntidade;
    private String nome;
    private String tipo;
    private String enderecoFisico;
    private String username;
    private String password;
    @OneToOne(mappedBy = "idEntidade")
    private Contato contacto;
    @OneToMany(mappedBy = "idEntidade")
    private List<Funcionario> funcionarios;

    public Entidade(String nome, String enderecoFisico, String username, String password,String tipo) {
        this.nome = nome;
        this.enderecoFisico = enderecoFisico;
        this.username = username;
        this.password = password;
        this.tipo=tipo;
    }
    
}
