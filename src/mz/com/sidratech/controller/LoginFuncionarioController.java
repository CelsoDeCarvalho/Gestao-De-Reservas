package mz.com.sidratech.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.controller.file.SalvarEstadoLogin;
import mz.com.sidratech.model.bean.EstadoLogin;
import mz.com.sidratech.repository.Repository;
import mz.com.sidratech.services.Path;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class LoginFuncionarioController implements Initializable {
    
    
    @FXML
    private BorderPane pane;
    @FXML
    private Label userLab;
    @FXML
    private TextField user;
    @FXML
    private Label passLab;
    @FXML
    private PasswordField pass;
    
    @FXML
    void loginAction(ActionEvent event) throws IOException {
        if(user.getText().isEmpty())
            thread(user, userLab);
        else
            if(pass.getText().isEmpty())
                thread(pass, passLab);
        else
                for(int i=0;i<Repository.funcionarios.size();i++){
                    if(Repository.funcionarios.get(i).getIdEntidade().getIdEntidade()==LerEstadoLogin.lerLogin().getIdEntidade()){
                        if(user.getText().equals(Repository.funcionarios.get(i).getUsername())&&pass.getText().equals(Repository.funcionarios.get(i).getPassword())){
                                EstadoLogin estadoLogin=new EstadoLogin();
                                estadoLogin=LerEstadoLogin.lerLogin();
                                estadoLogin.setIdUsuario(Repository.funcionarios.get(i).getIdFuncionario());
                                SalvarEstadoLogin.guardarLogin(estadoLogin);
                                ((Node) event.getSource()).getScene().getWindow().hide(); 
                                
                        }
                    }
                }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public  void thread(TextField field, Label label) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                field.setStyle("-fx-border-color: red;");
                label.setStyle("-fx-text-fill: red;");
                FadeTransition transition = new FadeTransition(Duration.millis(2000), label);
                transition.setFromValue(0.0);
                transition.setToValue(1.0);
                transition.play();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    field.setStyle("-fx-border-color: transparent;");
                    label.setStyle("-fx-text-fill: #000000;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }
}
