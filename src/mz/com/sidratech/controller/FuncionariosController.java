package mz.com.sidratech.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.model.bean.Funcionario;
import mz.com.sidratech.model.dao.DaoGenerico;

/**
 * FXML Controller class
 *
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
    @FXML
    private TextField fieldPesquisar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popularTabela();
    }

    void popularTabela() {

        ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(addAction(Funcionario.all()));

        column1.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        column2.setCellValueFactory(new PropertyValueFactory<>("nome"));
        column3.setCellValueFactory(new PropertyValueFactory<>("apelido"));
        column4.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        column5.setCellValueFactory(new PropertyValueFactory<>("email"));
        column6.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        column7.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        column8.setCellValueFactory(new PropertyValueFactory<>("button"));

        tabela.setItems(funcionarios);
    }

    @FXML
    void searchAction(KeyEvent event) {

        tabela.getItems().clear();
        if (fieldPesquisar.getText().isEmpty() || fieldPesquisar.getText().equals("")) {
            popularTabela();
        } else {
            DaoGenerico daoGenerico = new DaoGenerico();
            ObservableList<Funcionario> funcionariosFiltrados = FXCollections.observableArrayList(addAction(daoGenerico.searchEmployee(fieldPesquisar.getText())));
            tabela.setItems(funcionariosFiltrados);
        }
    }

    public List<Funcionario> addAction(List<Funcionario> funcionarios) {

        funcionarios.forEach((funcionario) -> {
            File file = new File("src/mz/com/sidratech/image/doctor.png");
            File file1 = new File("src/mz/com/sidratech/image/girl.png");
            Image image = new Image(file.toURI().toString());
            Image image1 = new Image(file1.toURI().toString());

            Button button = new Button();
            button.setPrefSize(70, 25);
            FontAwesomeIcon trash = new FontAwesomeIcon();
            trash.setIconName("TRASH");
            trash.setFill(Color.WHITE);

            button.getStylesheets().add("/css/FuncionariosStyle.css");
            button.setGraphic(trash);

            button.setId("delBtn");

            button.setOnAction((event) -> {

                if (funcionario.getIdFuncionario() == LerEstadoLogin.lerLogin().getIdUsuario()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" Dialogo de informacao");
                    alert.setHeaderText("FUNCIONARIO: " + funcionario.getNome().toUpperCase());
                    alert.setContentText("Impossivel remover este funcionario, pois esta logado");
                    alert.setResizable(true);
                    alert.show();
                } else {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(" Dialogo de Confirmacao");
                    alert.setHeaderText("FUNCIONARIO: " + funcionario.getNome().toUpperCase());
                    alert.setContentText("Pretente realmente eliminar este funcionario?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        tabela.getItems().clear();
                        DaoGenerico daoGenerico = new DaoGenerico();
                        daoGenerico.delete(funcionario.getIdFuncionario(), funcionario);
                        popularTabela();
                    } else {
                        alert.close();
                    }
                }
            });

            funcionario.setButton(button);
            if (funcionario.getSexo() == 'M') {
                funcionario.setImageView(new ImageView(image));
            } else {
                funcionario.setImageView(new ImageView(image1));
            }

        });

        return funcionarios;

    }
}
