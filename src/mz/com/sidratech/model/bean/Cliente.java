package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    private String nome;
    private String apelido;
    private double valorPago;
    private Date dataEntrada;
    private Date dataSaida;
    @JoinColumn(name = "idAlojamento", referencedColumnName = "idEntidade")
    @ManyToOne
    private Alojamento idAlojamento;
}