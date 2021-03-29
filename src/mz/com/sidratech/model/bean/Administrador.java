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
@Table(name = "administrador")
public class Administrador extends Funcionario implements Serializable {


    private String username;
    private String password;

    public Administrador(String username, String password, String nome, String apelido, int telefone, String email, Character sexo,String tipo,Entidade idEntidade) {
        super(nome, apelido, telefone, email, sexo,tipo,idEntidade);
        this.username = username;
        this.password = password;
    }

    
}

