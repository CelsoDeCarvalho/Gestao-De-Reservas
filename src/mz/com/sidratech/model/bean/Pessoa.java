package mz.com.sidratech.model.bean;
/**
 *
 * @author celso
 */
public abstract class Pessoa {
    
    private String nome;
    private String apelido;
    private String bairro;
    private String sexo;

    public Pessoa(String nome, String apelido, String bairro, String sexo) {
        this.nome = nome;
        this.apelido = apelido;
        this.bairro = bairro;
        this.sexo = sexo;
    }
    
    public Pessoa(){
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    
    
    
    
    
}
