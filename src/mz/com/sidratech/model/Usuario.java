package mz.com.sidratech.model;

/**
 *
 * @author celso
 */
public class Usuario extends Pessoa{
    
    private int id;
    private String username;
    private String password;

    public Usuario(int id, String username, String password, String nome, String apelido, String bairro, String sexo) {
        super(nome, apelido, bairro, sexo);
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    public Usuario(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
