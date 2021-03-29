package mz.com.sidratech.model.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "funcionario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFuncionario;
    private String nome;
    private String apelido;
    private int telefone;
    private String email;
    private String tipo;
    private Character sexo;
    @JoinColumn(name = "idEntidade", referencedColumnName = "idEntidade")
    @ManyToOne
    private Entidade idEntidade;

    public Funcionario(String nome, String apelido, int telefone, String email, Character sexo,String tipo,Entidade idEntidade) {
        this.nome = nome;
        this.apelido = apelido;
        this.telefone = telefone;
        this.email = email;
        this.sexo = sexo;
        this.idEntidade = idEntidade;
        this.tipo=tipo;
    }
 
}