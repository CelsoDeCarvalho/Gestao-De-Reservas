package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "alojamento")
public class Alojamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alojamento")
    private Integer idAlojamento;
    @Basic(optional = false)
    @Column(name = "designacao")
    private String designacao;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "classificacao")
    private String classificacao;
    @Basic(optional = false)
    @Column(name = "endereco_fisico")
    private String enderecoFisico;
    @Basic(optional = false)
    @Column(name = "total_trabalhadores")
    private int totalTrabalhadores;
    @OneToMany(mappedBy = "idAlojamento")
    private List<Cliente> clientes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlojamento")
    private List<Quarto> quartos;
    @OneToMany(mappedBy = "idAlojamento")
    private List<FuncionarioAdmin> admins;
    @OneToMany(mappedBy = "idAlojamento")
    private List<Funcionario> funcionarios;
    @OneToMany(mappedBy = "idAlojamento")
    private List<Relatorio> relatorios;
    @OneToMany(mappedBy = "idAlojamento")
    private List<Contato> contactos;
    @OneToMany(mappedBy = "idAlojamento")
    private List<FuncionarioUser> users;
}
