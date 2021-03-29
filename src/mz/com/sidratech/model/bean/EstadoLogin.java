package mz.com.sidratech.model.bean;

import java.io.Serializable;

/**
 *
 * @author celso
 */
public class EstadoLogin implements Serializable{
    
    private int idEntidade;
    private int idUsuario;
    
    public EstadoLogin(){
        idEntidade=0;
        idUsuario=0;
    }

    public int getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(int idEntidade) {
        this.idEntidade = idEntidade;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
