package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.List;
import javafx.scene.control.ButtonBar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.com.sidratech.repository.Repository;

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
    private String classificacao;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idEntidade")
    private Contato contacto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidade")
    private List<Funcionario> funcionarios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidade")
    private List<Relatorio> relatorios;
    @Transient
    private ButtonBar accoes;

    public Entidade(String nome, String enderecoFisico, String username, String password,String tipo,String classificacao) {
        this.nome = nome;
        this.enderecoFisico = enderecoFisico;
        this.username = username;
        this.password = password;
        this.tipo=tipo;
        this.classificacao=classificacao;
    }

    public Entidade(ButtonBar accoes) {
        this.accoes = accoes;
    }
    
    public static List<Entidade> all() {
        return Repository.entidades;
    }
    
    
}
