package mz.com.sidratech.model.bean;

import javafx.scene.control.Button;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author celso
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrato;
    private String nome;
    private String caminhoFoto;
    private double preco;
    private String tipo;
    @JoinColumn(name = "idRestauracao", referencedColumnName = "idEntidade")
    @ManyToOne
    private Restauracao idRestauracao;
    @Transient
    private Button button;

    public Prato(String nome, String caminhoFoto, double preco, String tipo) {
        this.nome = nome;
        this.caminhoFoto = caminhoFoto;
        this.preco = preco;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Prato{" + "idPrato=" + idPrato + ", nome=" + nome + ", caminhoFoto=" + caminhoFoto + ", preco=" + preco + ", tipo=" + tipo + ", idRestauracao=" + idRestauracao + '}';
    }
    
}
