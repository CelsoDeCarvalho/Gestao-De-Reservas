package mz.com.sidratech.model.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author celso
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario_admin")
public class FuncionarioAdmin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_admin")
    private Integer idAdmin;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "apelido")
    private String apelido;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "id_alojamento", referencedColumnName = "id_alojamento")
    @ManyToOne
    private Alojamento idAlojamento;
    @JoinColumn(name = "id_central", referencedColumnName = "id_central")
    @ManyToOne
    private Central idCentral;
    @JoinColumn(name = "id_restauracao", referencedColumnName = "id_restauracao")
    @ManyToOne
    private Restauracao idRestauracao;
}
