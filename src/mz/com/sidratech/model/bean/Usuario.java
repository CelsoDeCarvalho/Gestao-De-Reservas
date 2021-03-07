package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "usuario")
public class Usuario extends Funcionario implements Serializable {


    private String username;
    private String password;
    @Basic(optional = false)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Relatorio> relatorios;

}
