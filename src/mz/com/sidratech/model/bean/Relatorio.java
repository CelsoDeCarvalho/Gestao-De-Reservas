package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "relatorio")
public class Relatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRelatorio;
    @Lob
    private String mensagem;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    @JoinColumn(name = "idEntidade", referencedColumnName = "idEntidade")
    @ManyToOne
    private Entidade idEntidade;
    @JoinColumn(name = "idUser", referencedColumnName = "idFuncionario")
    @ManyToOne(optional = false)
    private Usuario idUser;   
}