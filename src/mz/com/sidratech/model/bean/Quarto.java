package mz.com.sidratech.model.bean;

import java.io.Serializable;
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
@Table(name = "quarto")
public class Quarto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuarto;
    private int numero;
    private int quantidadeCamas;
    private String descricao;
    private String ocupado;
    @JoinColumn(name = "idAlojamento", referencedColumnName = "idEntidade")
    @ManyToOne
    private Alojamento idAlojamento;

    public Quarto(int numero, int quantidadeCamas, Alojamento idAlojamento,String descricao) {
        this.numero = numero;
        this.quantidadeCamas = quantidadeCamas;
        this.idAlojamento = idAlojamento;
        this.descricao=descricao;
        ocupado="Disponivel";
    }
    
    
}