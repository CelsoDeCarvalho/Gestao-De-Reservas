package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.repository.Repository;
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
    private String username;
    private String password;
    private int telefone;
    private String email;
    private String tipo;
    private Character sexo;
    @JoinColumn(name = "idEntidade", referencedColumnName = "idEntidade")
    @ManyToOne
    private Entidade idEntidade;
    @Transient
    private ImageView imageView;
    @Transient
    private Button button;

    public Funcionario(String nome, String apelido, String username, String password, int telefone, String email, String tipo, Character sexo, Entidade idEntidade) {
        this.nome = nome;
        this.apelido = apelido;
        this.username = username;
        this.password = password;
        this.telefone = telefone;
        this.email = email;
        this.tipo = tipo;
        this.sexo = sexo;
        this.idEntidade = idEntidade;
    }

    public static  List<Funcionario> all(){
        Repository repository=new Repository();
        repository.getFuncionarios();
        
        List<Funcionario> funcionarios=new ArrayList();
        
        for(int i=0;i<Repository.funcionarios.size();i++){
            if(Repository.funcionarios.get(i).idEntidade.getIdEntidade()==LerEstadoLogin.lerLogin().getIdEntidade())
                funcionarios.add(Repository.funcionarios.get(i));
        }
        
        return funcionarios;
    }

 
}