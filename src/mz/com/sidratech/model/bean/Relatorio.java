package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.time.LocalDate;
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
    private String nome;
    private double valorPagar;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private int numeroQuarto;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    @JoinColumn(name = "idEntidade", referencedColumnName = "idEntidade")
    @ManyToOne
    private Entidade idEntidade;

    public Relatorio(String nome, double valorPagar, LocalDate dataEntrada, LocalDate dataSaida, int numeroQuarto, Date dataEmissao, Entidade idEntidade) {
        this.nome = nome;
        this.valorPagar = valorPagar;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.numeroQuarto = numeroQuarto;
        this.dataEmissao = dataEmissao;
        this.idEntidade = idEntidade;
    }
  
    
}