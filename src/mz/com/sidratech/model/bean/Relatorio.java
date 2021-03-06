package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "relatorio")
public class Relatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_relatorio")
    private Integer idRelatorio;
    @Basic(optional = false)
    @Lob
    @Column(name = "mensagem")
    private String mensagem;
    @Basic(optional = false)
    @Column(name = "dataEmissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    @JoinColumn(name = "id_alojamento", referencedColumnName = "id_alojamento")
    @ManyToOne
    private Alojamento idAlojamento;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private FuncionarioUser idUser;
    @JoinColumn(name = "id_restauracao", referencedColumnName = "id_restauracao")
    @ManyToOne
    private Restauracao idRestauracao;    
}
