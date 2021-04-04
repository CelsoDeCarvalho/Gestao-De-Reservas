package mz.com.sidratech.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class CentralCreatorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public static String senha;

    @FXML
    private PasswordField text;

    @FXML
    private Label wrong;

    @FXML
    void doneAction(ActionEvent event) {
        if (text.getText().isEmpty()) {
            wrong.setText("campo vazio");
            thread(text, wrong);
        } else if (!text.getText().equals("celso1999")) {
            wrong.setText("senha incorreta");
            thread(text, wrong);
        } else {
            senha=text.getText();
            ((Node) event.getSource()).getScene().getWindow().hide();
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
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    field.setStyle("-fx-border-color: transparent;");
                    label.setStyle("-fx-text-fill: #ffffff;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }

}
