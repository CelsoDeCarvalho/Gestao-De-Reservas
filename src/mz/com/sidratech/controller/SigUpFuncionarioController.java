package mz.com.sidratech.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SigUpFuncionarioController implements Initializable {
    
    @FXML
    private ComboBox<String> funcTipo;
    @FXML
    private TextField nome;
    @FXML
    private TextField apelido;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<Character> genero;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private Button create;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        funcTipo.setItems(FXCollections.observableArrayList("Administrador", "Usuario","Funcionario"));
         genero.setItems(FXCollections.observableArrayList('M','F'));
    }  
    
    public void cadastrar(){
        
    }
    
}
