package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    private Date dataEntrada;
    private Date dataSaida;
    private int numeroQuarto;
    @JoinColumn(name = "idEntidade", referencedColumnName = "idEntidade")
    @ManyToOne
    private Entidade idEntidade;

    public Relatorio(String nome, double valorPagar, Date dataEntrada, Date dataSaida, int numeroQuarto,Entidade idEntidade) {
        this.nome = nome;
        this.valorPagar = valorPagar;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.numeroQuarto = numeroQuarto;
        this.idEntidade = idEntidade;
    }

    @Override
    public String toString() {
        return "Relatorio{" + "idRelatorio=" + idRelatorio + ", nome=" + nome + ", valorPagar=" + valorPagar + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + ", numeroQuarto=" + numeroQuarto + ", idEntidade=" + idEntidade + '}';
    }
  
    
}