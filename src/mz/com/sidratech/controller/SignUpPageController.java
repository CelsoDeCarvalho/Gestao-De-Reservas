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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Central;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.model.dao.DaoGenerico;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class SignUpPageController implements Initializable {
    
    @FXML
    private BorderPane regPane;
    @FXML
    private Tab tab1;
    @FXML
    private AnchorPane dataPane;
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
    private ComboBox<String> classificationField;
    @FXML
    private PasswordField passField;
    @FXML
    private Tab tab2;
    @FXML
    private AnchorPane contactsPane;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField urlField;
    @FXML
    private Button back;
    @FXML
    private Button next;  
    @FXML
    private TabPane tabPane;
    @FXML
    private TextField typeField;
    
    String escolha;
    String classificacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox.setItems(FXCollections.observableArrayList("Central","Alojamento","Restauracao"));
        classificationField.setItems(FXCollections.observableArrayList("Unica","2 Estrelas"
                + "","3 Estrelas","4 Estrelas","5 Estrelas"));
    }    
    
    @FXML
    void comboAction(ActionEvent event) {       
        escolha=comboBox.getSelectionModel().getSelectedItem();
        
        if(escolha.equals("Central")){
            nameField.setDisable(false);
            passField.setDisable(false);
            userField.setDisable(false);
            localField.setDisable(false);
            phoneField.setDisable(false);
            emailField.setDisable(false);
            urlField.setDisable(false);
            classificationField.setDisable(true);
            typeField.setDisable(true);
        }else
            if(escolha.equals("Alojamento")){
                nameField.setDisable(false);
                passField.setDisable(false);
                userField.setDisable(false);
                localField.setDisable(false);
                phoneField.setDisable(false);
                emailField.setDisable(false);
                urlField.setDisable(false);
                classificationField.setDisable(false);
                typeField.setDisable(false);
            }else
                if(escolha.equals("Restauracao")){
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
    void backAction(ActionEvent event){
        if(back.getText().equals("Back")){
            tab2.setDisable(true);
            tab1.setDisable(false);
            next.setText("Next");
            back.setText("Cancel");
            tabPane.getSelectionModel().select(tab1);
        }else
            ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    @FXML
    void classificationAction(ActionEvent event) {
        classificacao=classificationField.getSelectionModel().getSelectedItem();
    }
    
    DaoGenerico daoGenerico=new DaoGenerico();

    @FXML
    void nextAction(ActionEvent event) {
        if(tab1.isSelected()){
            
            if(nameField.getText().isEmpty())
                thread(nameField, nameLabel);
            else
                if(localField.getText().isEmpty())
                    thread(localField,nameLabel);
            else
                    if(userField.getText().isEmpty())
                        thread(userField,nameLabel);
            else
                        if(passField.getText().isEmpty())
                            thread(passField,nameLabel);
            else
            if(escolha.equals("Alojamento")){
                if(classificationField.getSelectionModel().isEmpty())
                    thread1(classificationField,nameLabel);
            }else
                if(escolha.equals("Alojamento")||escolha.equals("Restauracao")){
                    if(typeField.getText().isEmpty())
                        thread(typeField,nameLabel);
            }else{
                tab1.setDisable(true);
                tab2.setDisable(false);
                tabPane.getSelectionModel().select(tab2);
                next.setText("Finish");
                back.setText("Back");
            }

        }else
            if(tab2.isSelected()){
                                
                if(phoneField.getText().isEmpty())
                    thread(phoneField, nameLabel);
                else
                if(emailField.getText().isEmpty())
                    thread(emailField,nameLabel);
                else
                    if(escolha.equals("Alojamento")){
                        Alojamento alojamento=new Alojamento(classificacao,typeField.getText(),nameField.getText(),localField.getText(),userField.getText(),passField.getText());
                        daoGenerico.create(alojamento);
                        ((Node) event.getSource()).getScene().getWindow().hide();
                    }else
                    if(escolha.equals("Restauracao")){
                        Restauracao restauracao=new Restauracao(typeField.getText(),nameField.getText(),localField.getText(),userField.getText(),passField.getText());
                        daoGenerico.create(restauracao);
                        ((Node) event.getSource()).getScene().getWindow().hide();
                    }else{
                        Central central=new Central(nameField.getText(),localField.getText(),userField.getText(),passField.getText(),typeField.getText());
                        daoGenerico.create(central);
                        ((Node) event.getSource()).getScene().getWindow().hide();
                    }
            }
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
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    field.setStyle("-fx-border-color: #0061d2;");
                    label.setStyle("-fx-text-fill: #E4EFF1;");
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
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    box.setStyle("-fx-border-color: #0061d2;");
                    label.setStyle("-fx-text-fill: #E4EFF1;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }
    
    
}
