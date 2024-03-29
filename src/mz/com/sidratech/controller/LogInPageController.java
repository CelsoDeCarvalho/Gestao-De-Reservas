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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import mz.com.sidratech.controller.file.SalvarEstadoLogin;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Central;
import mz.com.sidratech.model.bean.Entidade;
import mz.com.sidratech.model.bean.EstadoLogin;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.octodb.OctoDBApplication;
import mz.com.sidratech.repository.Repository;
import mz.com.sidratech.services.Path;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class LogInPageController implements Initializable {

    @FXML
    private AnchorPane loginPane;

    @FXML
    private Label userLabel;

    @FXML
    private HBox loginPainelUser;

    @FXML
    private HBox loginPainelPass;

    @FXML
    private TextField username;

    @FXML
    private Label passLabel;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Label forgotPassword;

    @FXML
    private Label welcome;

    @FXML
    private Label signInTo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        username.setFocusTraversable(false);
        password.setFocusTraversable(false);

        if (PaginaPrincipalController.lingua.equals("EN")) {
            userLabel.setText("Username");
            passLabel.setText("Password");
            login.setText("Login");
            forgotPassword.setText("Forgot Password?");
            welcome.setText("Welcome!");
            signInTo.setText("Sign in to your account");
            username.setPromptText("entity username");
            password.setPromptText("Password");
        } else {
            userLabel.setText("Usuario");
            passLabel.setText("Senha");
            login.setText("Entrar");
            forgotPassword.setText("Esqueci a Senha?");
            welcome.setText("Bem Vindo!");
            signInTo.setText("Faça login em sua conta");
            username.setPromptText("Usuarioda entidade");
            password.setPromptText("Senha");
        }
    }

    private void mostrarJanela(String caminho, String title, ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        OctoDBApplication.getStage().close();
        Parent root = FXMLLoader.load(getClass().getResource(caminho));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle(title);
        stage.show();
    }

    static Entidade entidade;

    //LOGIN================================================ 
    @FXML
    void loginEnter(ActionEvent event) throws IOException {
        if (username.getText().isEmpty()) {
            thread(loginPainelUser, userLabel);
        } else if (password.getText().isEmpty()) {
            thread(loginPainelPass, passLabel);
        } else {
            for (int i = 0; i < Repository.entidades.size(); i++) {
                if (username.getText().equals(Repository.entidades.get(i).getUsername())
                        && password.getText().equals(Repository.entidades.get(i).getPassword())) {

                    EstadoLogin estadoLogin = new EstadoLogin();
                    estadoLogin.setIdEntidade(Repository.entidades.get(i).getIdEntidade());
                    SalvarEstadoLogin.guardarLogin(estadoLogin);

                    if (Repository.entidades.get(i).getClass().equals(Central.class)) {
                        entidade = new Central();
                        entidade = Repository.entidades.get(i);
                        mostrarJanela(Path.PAGINA_CENTRAL, "", event);
                        return;
                    } else if (Repository.entidades.get(i).getClass().equals(Alojamento.class)) {
                        entidade = new Alojamento();
                        entidade = Repository.entidades.get(i);
                        mostrarJanela(Path.PAGINA_ALOJAMENTO, "", event);
                        return;
                    } else {
                        entidade = new Restauracao();
                        entidade = Repository.entidades.get(i);
                        mostrarJanela(Path.PAGINA_RESTAURACAO, "", event);
                        return;
                    }
                }
            }
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("DADOS INVALIDOS");
            a.show();
        }

    }

    public void thread(HBox box, Label label) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                box.setStyle("-fx-border-color: red;");
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
                    box.setStyle("-fx-border-color: #0061d2;");
                    label.setStyle("-fx-text-fill: #ffffff;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }

}
