package mz.com.sidratech.model.bean;

import java.io.Serializable;
import java.time.LocalDate;
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
    private double valorPagar;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private int numeroQuarto;
    
    @JoinColumn(name = "idAlojamento", referencedColumnName = "idEntidade")
    @ManyToOne
    private Alojamento idAlojamento;

    public Cliente(String nome, double valorPagar, LocalDate dataEntrada, LocalDate dataSaida, int numeroQuarto, Alojamento idAlojamento) {
        this.nome = nome;
        this.valorPagar = valorPagar;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.numeroQuarto = numeroQuarto;
        this.idAlojamento = idAlojamento;
    }  

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nome=" + nome + ", valorPagar=" + valorPagar + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + ", numeroQuarto=" + numeroQuarto + ", idAlojamento=" + idAlojamento + '}';
    }
    
    
}