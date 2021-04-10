package mz.com.sidratech.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.model.bean.Funcionario;
import mz.com.sidratech.repository.Repository;

/**
 * FXML Controller class
 * @author celso
 */
public class FuncionariosController implements Initializable {
    
    @FXML
    private TableView<Funcionario> tabela;
    @FXML
    private TableColumn<Funcionario, ImageView> column1;
    @FXML
    private TableColumn<Funcionario, String> column2;
    @FXML
    private TableColumn<Funcionario, String> column3;
    @FXML
    private TableColumn<Funcionario, Long> column4;
    @FXML
    private TableColumn<Funcionario, String> column5;
    @FXML
    private TableColumn<Funcionario, Character> column6;
    @FXML
    private TableColumn<Funcionario, String> column7;
    @FXML
    private TableColumn<Funcionario, ButtonBar> column8;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popularTabela();
    }    
    
    private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();
    
    void popularTabela(){
        column1.setCellValueFactory(new PropertyValueFactory<>("x"));
        column2.setCellValueFactory(new PropertyValueFactory<>("nome"));
        column3.setCellValueFactory(new PropertyValueFactory<>("apelido"));
        column4.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        column5.setCellValueFactory(new PropertyValueFactory<>("email"));
        column6.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        column7.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        column8.setCellValueFactory(new PropertyValueFactory<>("x"));
        
        for (int i = 0; i < Repository.funcionarios.size(); i++) {
            if (Repository.funcionarios.get(i).getIdEntidade().getIdEntidade() == LerEstadoLogin.lerLogin().getIdEntidade()) {
                funcionarios.add(Repository.funcionarios.get(i));
            }
        }
        tabela.setItems(funcionarios);
    }
    
}
