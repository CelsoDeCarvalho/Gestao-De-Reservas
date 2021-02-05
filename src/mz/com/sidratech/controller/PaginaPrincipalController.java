/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.sidratech.controller;

import gestao.reservas.GestaoDeReservas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author celso
 */
public class PaginaPrincipalController implements Initializable {
    
    @FXML
    AnchorPane painelSuperior;
    @FXML
    private HBox boxSuperior;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button entrar;
    @FXML
    private Button recuperarSenha;
    @FXML
    private AnchorPane painelEsquerdo;
    @FXML
    private HBox userHBox;
    @FXML
    private HBox passHBox;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accaoSair(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accaoMaximizar(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        if(stage.isFullScreen())
            stage.setFullScreen(false);
        else
            stage.setFullScreen(true);
    }

    @FXML
    private void minimizar(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
}
