package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "restauracao")
public class Restauracao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_restauracao")
    private Integer idRestauracao;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "designacao")
    private String designacao;
    @Basic(optional = false)
    @Column(name = "endereco_fisico")
    private String enderecoFisico;
    @Basic(optional = false)
    @Column(name = "total_mesas")
    private int totalMesas;
    @Basic(optional = false)
    @Column(name = "total_cadeiras")
    private int totalCadeiras;
    @Basic(optional = false)
    @Column(name = "prato_do_dia")
    private String pratoDoDia;
    @OneToMany(mappedBy = "idRestauracao")
    private List<Cliente> clienteList;
    @OneToMany(mappedBy = "idRestauracao")
    private List<FuncionarioAdmin> funcionarioAdminList;
    @OneToMany(mappedBy = "idRestauracao")
    private List<Funcionario> funcionarioList;
    @OneToMany(mappedBy = "idRestauracao")
    private List<Relatorio> relatorioList;
    @OneToMany(mappedBy = "idRestauracao")
    private List<Contato> contatoList;
    @OneToMany(mappedBy = "idRestauracao")
    private List<FuncionarioUser> funcionarioUserList;
    
}
