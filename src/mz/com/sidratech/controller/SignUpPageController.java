package mz.com.sidratech.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Central;
import mz.com.sidratech.model.bean.Contato;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.model.dao.DaoGenerico;
import mz.com.sidratech.repository.Repository;
import mz.com.sidratech.services.Path;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class SignUpPageController implements Initializable {

    @FXML
    private BorderPane regPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab1;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField nameField;
    @FXML
    private TextField localField;
    @FXML
    private TextField userField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label localLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Label classificationLabel;
    @FXML
    private ComboBox<String> classificationField;
    @FXML
    private PasswordField passField;
    @FXML
    private TextField typeField;
    @FXML
    private Label typeLabel;
    @FXML
    private Tab tab2;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField urlField;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Button back;
    @FXML
    private Button next;

    String escolha;
    String classificacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascara mascara = new Mascara();
        mascara.apenasNumero(phoneField);
        comboBox.setItems(FXCollections.observableArrayList("Central", "Alojamento", "Restauracao"));
        classificationField.setItems(FXCollections.observableArrayList("Unica", "2 Estrelas"
                + "", "3 Estrelas", "4 Estrelas", "5 Estrelas"));
    }

    @FXML
    void comboAction(ActionEvent event) {
        escolha = comboBox.getSelectionModel().getSelectedItem();
        CentralCreatorController.senha = "";
        if (escolha.equals("Central")) {
            nameField.setDisable(false);
            passField.setDisable(false);
            userField.setDisable(false);
            localField.setDisable(false);
            phoneField.setDisable(false);
            emailField.setDisable(false);
            urlField.setDisable(false);
            classificationField.setDisable(true);
            typeField.setDisable(true);
        } else if (escolha.equals("Alojamento")) {
            nameField.setDisable(false);
            passField.setDisable(false);
            userField.setDisable(false);
            localField.setDisable(false);
            phoneField.setDisable(false);
            emailField.setDisable(false);
            urlField.setDisable(false);
            classificationField.setDisable(false);
            typeField.setDisable(false);
        } else if (escolha.equals("Restauracao")) {
            nameField.setDisable(false);
            passField.setDisable(false);
            userField.setDisable(false);
            localField.setDisable(false);
            phoneField.setDisable(false);
            emailField.setDisable(false);
            urlField.setDisable(false);
            typeField.setDisable(false);
            classificationField.setDisable(true);
        }
    }

    @FXML
    void backAction(ActionEvent event) {
        if (back.getText().equals("Back")) {
            tab2.setDisable(true);
            tab1.setDisable(false);
            next.setText("Next");
            back.setText("Cancel");
            tabPane.getSelectionModel().select(tab1);
        } else {
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    @FXML
    void classificationAction(ActionEvent event) {
        classificacao = classificationField.getSelectionModel().getSelectedItem();
    }

    DaoGenerico daoGenerico = new DaoGenerico();

    @FXML
    void nextAction(ActionEvent event) throws IOException {
        if (tab1.isSelected()) {

            if (nameField.getText().isEmpty()) {
                thread(nameField, nameLabel);
            } else if (localField.getText().isEmpty()) {
                thread(localField, localLabel);
            } else if (userField.getText().isEmpty()) {
                thread(userField, userLabel);
            } else if (passField.getText().isEmpty()) {
                thread(passField, passLabel);
            } else if (!classificationField.isDisabled() && classificacao == null) {
                thread1(classificationField, classificationLabel);
            } else if (!typeField.isDisabled() && typeField.getText().isEmpty()) {
                thread(typeField, typeLabel);
            } else {
                for (int i = 0; i < Repository.entidades.size(); i++) {
                    if (userField.getText().equals(Repository.entidades.get(i).getUsername())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("USERNAME " + userField.getText() + " JA EXISTE");
                        alert.show();
                        return;
                    }
                }

                tab1.setDisable(true);
                tab2.setDisable(false);
                tabPane.getSelectionModel().select(tab2);
                next.setText("Finish");
                back.setText("Back");
            }

        } else if (tab2.isSelected()) {

            if (phoneField.getText().isEmpty()) {
                thread(phoneField, phoneLabel);
            } else if (emailField.getText().isEmpty()) {
                thread(emailField, emailLabel);
            } else if (escolha.equals("Alojamento")) {

                for (int i = 0; i < Repository.contatos.size(); i++) {
                    if (emailField.getText().equals(Repository.contatos.get(i).getEmail())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("EMAIL " + emailField.getText() + " JA EXISTE");
                        alert.show();
                        return;
                    }
                }
                Alojamento alojamento = new Alojamento(classificacao, typeField.getText(), nameField.getText(), localField.getText(), userField.getText(), passField.getText());
                Contato contato = new Contato(Integer.parseInt(phoneField.getText()), urlField.getText(), emailField.getText(), alojamento);
                alojamento.setContacto(contato);
                daoGenerico.create(alojamento);
                ((Node) event.getSource()).getScene().getWindow().hide();
            } else if (escolha.equals("Restauracao")) {

                for (int i = 0; i < Repository.contatos.size(); i++) {
                    if (emailField.getText().equals(Repository.contatos.get(i).getEmail())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("EMAIL " + emailField.getText() + " JA EXISTE");
                        alert.show();
                        return;
                    }
                }
                Restauracao restauracao = new Restauracao(typeField.getText(), nameField.getText(), localField.getText(), userField.getText(), passField.getText(), classificacao);
                Contato contato = new Contato(Integer.parseInt(phoneField.getText()), urlField.getText(), emailField.getText(), restauracao);
                restauracao.setContacto(contato);
                daoGenerico.create(restauracao);
                ((Node) event.getSource()).getScene().getWindow().hide();
            } else {

                for (int i = 0; i < Repository.contatos.size(); i++) {
                    if (emailField.getText().equals(Repository.contatos.get(i).getEmail())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("EMAIL " + emailField.getText() + " JA EXISTE");
                        alert.show();
                        return;
                    }
                }
                mostrarJanela(Path.PAGINA_CENTRALCREATOR, "", false);
                if (CentralCreatorController.senha.equals("celso1999")) {
                    Central central = new Central(nameField.getText(), localField.getText(), userField.getText(), passField.getText(), typeField.getText(), classificacao);
                    Contato contato = new Contato(Integer.parseInt(phoneField.getText()), urlField.getText(), emailField.getText(), central);
                    central.setContacto(contato);
                    daoGenerico.create(central);
                    ((Node) event.getSource()).getScene().getWindow().hide();
                }
            }
        }
        Repository repository = new Repository();
        repository.getEntidades();
        repository.getContactos();
    }

    public void thread(TextField field, Label label) {
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
                    field.setStyle("-fx-border-color: #0061d2;");
                    label.setStyle("-fx-text-fill: #000000;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }

    public void thread1(ComboBox box, Label label) {
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
                    label.setStyle("-fx-text-fill: #000000;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }

    private void mostrarJanela(String caminho, String title, boolean resizable) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(regPane.getScene().getWindow());
        stage.setResizable(resizable);
        stage.show();
    }
}
