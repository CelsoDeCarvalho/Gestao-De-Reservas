package mz.com.sidratech.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import mz.com.sidratech.model.bean.Administrador;
import mz.com.sidratech.model.bean.Faxineiro;
import mz.com.sidratech.model.bean.Funcionario;
import mz.com.sidratech.model.bean.Usuario;
import mz.com.sidratech.model.dao.DaoGenerico;
import mz.com.sidratech.repository.Repository;

public class SigUpFuncionarioController implements Initializable {

    @FXML
    private Label typeLab;
    @FXML
    private Label nameLab;
    @FXML
    private Label apelidoLab;
    @FXML
    private Label phoneLab;
    @FXML
    private Label emailLab;
    @FXML
    private Label genreLab;
    @FXML
    private Label userLab;
    @FXML
    private Label passLab;
    @FXML
    private Button create;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<Character> genre;
    @FXML
    private TextField apelido;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascara mascara = new Mascara();
        mascara.apenasNumero(phone);
        type.setItems(FXCollections.observableArrayList("Administrador", "Usuario"));
        genre.setItems(FXCollections.observableArrayList('M', 'F'));
    }

    String tipo;
    char sexo = 'A';

    @FXML
    void genreAction(ActionEvent event) {
        sexo = genre.getSelectionModel().getSelectedItem();
    }

    @FXML
    void typeAction(ActionEvent event) {
        tipo = type.getSelectionModel().getSelectedItem();

        if (tipo.equals("Administrador") || tipo.equals("Usuario")) {
            name.setDisable(false);
            apelido.setDisable(false);
            user.setDisable(false);
            phone.setDisable(false);
            email.setDisable(false);
            genre.setDisable(false);
            password.setDisable(false);
        } else if (tipo.equals("Funcionario")) {
            name.setDisable(false);
            apelido.setDisable(false);
            user.setDisable(true);
            phone.setDisable(false);
            email.setDisable(false);
            genre.setDisable(false);
            password.setDisable(true);
        }
    }

    Repository repository = new Repository();

    @FXML
    void createAction(ActionEvent event) {
        if (tipo == null || tipo.isEmpty()) {
            thread(type, typeLab);
        } else if (name.getText().isEmpty()) {
            thread1(name, nameLab);
        } else if (apelido.getText().isEmpty()) {
            thread1(apelido, apelidoLab);
        } else if (phone.getText().isEmpty()) {
            thread1(phone, phoneLab);
        } else if (email.getText().isEmpty()) {
            thread1(email, emailLab);
        } else if (sexo == 'A') {
            thread(genre, genreLab);
        } else if (user.getText().isEmpty() && !user.isDisabled()) {
            thread1(user, userLab);
        } else if (password.getText().isEmpty() && !password.isDisabled()) {
            thread1(password, passLab);
        } else if (tipo.equals("Administrador")) {
            DaoGenerico generico = new DaoGenerico();
            Funcionario funcionario = new Administrador(name.getText(), apelido.getText(), user.getText(), password.getText(), Integer.parseInt(phone.getText()), email.getText(), tipo, sexo, LogInPageController.entidade);
            generico.create(funcionario);
            repository.getFuncionarios();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else if (tipo.equals("Usuario")) {
            DaoGenerico generico = new DaoGenerico();
            Funcionario funcionario = new Usuario(name.getText(), apelido.getText(), user.getText(), password.getText(), Integer.parseInt(phone.getText()), email.getText(), tipo, sexo, LogInPageController.entidade);
            generico.create(funcionario);
            repository.getFuncionarios();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else {
            DaoGenerico generico = new DaoGenerico();
            Funcionario funcionario = new Faxineiro(name.getText(), apelido.getText(), null, null, Integer.parseInt(phone.getText()), email.getText(), tipo, sexo, LogInPageController.entidade);
            generico.create(funcionario);
            repository.getFuncionarios();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    public void thread(ComboBox box, Label label) {
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
                    box.setStyle("-fx-border-color: transparent;");
                    label.setStyle("-fx-text-fill: #000000;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }

    public void thread1(TextField field, Label label) {
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
