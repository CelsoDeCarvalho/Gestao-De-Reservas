package mz.com.sidratech.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class PaginaPrincipalController implements Initializable {

    @FXML
    private BorderPane pane;

    @FXML
    private Button signupBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private ChoiceBox choiceBox;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBox.setItems(FXCollections.observableArrayList("EN", "PT"));
        choiceBox.getSelectionModel().selectFirst();

    }

}
