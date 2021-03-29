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
@Table(name = "faxineiro")
public class Faxineiro extends Funcionario implements Serializable {

    private String tarefa;

    public Faxineiro(String tarefa, String nome, String apelido, int telefone, String email, Character sexo,String tipo,Entidade idEntidade) {
        super(nome, apelido, telefone, email, sexo,tipo,idEntidade);
        this.tarefa = tarefa;
    }

    
}