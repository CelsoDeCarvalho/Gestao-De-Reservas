package mz.com.sidratech.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.controller.file.SalvarEstadoLogin;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Central;
import mz.com.sidratech.model.bean.Entidade;
import mz.com.sidratech.model.bean.EstadoLogin;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.model.dao.DaoGenerico;
import mz.com.sidratech.octodb.OctoDBApplication;
import mz.com.sidratech.repository.Repository;
import mz.com.sidratech.services.Path;

public class CentralPageController implements Initializable {

    @FXML
    private RadioButton findGuest;
    @FXML
    private RadioButton findEnt;
    @FXML
    private Label tipo;
    @FXML
    private Label nomeLabel;
    @FXML
    private BorderPane centralPane;
    @FXML
    private Button logIn;
    @FXML
    private Button logOut;
    @FXML
    private Button hire;
    @FXML
    private TextField search;
    @FXML
    private TableView<Entidade> table;
    @FXML
    private Label phoneLbel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label urlLabel;
    @FXML
    private Label username;
    Button ver;
    Button excluir;
    boolean logOutState;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        haFuncionarios();
        listar();
        mapearDados();
        search.setFocusTraversable(false);
        findEnt.setSelected(true);
    }

    public ArrayList trocarArray() {
        Repository repository = new Repository();
        repository.getEntidades();
        ArrayList<Entidade> entidades = new ArrayList();
        for (int i = 0; i < Repository.entidades.size(); i++) {
            if (Repository.entidades.get(i).getClass().equals(Alojamento.class) || Repository.entidades.get(i).getClass().equals(Restauracao.class)) {
                entidades.add(Repository.entidades.get(i));
            }
        }
        return entidades;
    }

    private void listar() {
        ObservableList<Entidade> list = FXCollections.observableArrayList(addAction(trocarArray()));
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("tipo"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("enderecoFisico"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("accoes"));
        table.setItems(list);
    }

    public List<Entidade> addAction(List<Entidade> entidades) {

        entidades.forEach((entidade) -> {
            FontAwesomeIcon fVer = new FontAwesomeIcon();
            FontAwesomeIcon fDel = new FontAwesomeIcon();
            fVer.setIconName("EYE");
            fDel.setIconName("TRASH");
            fVer.setFill(Color.WHITE);
            fDel.setFill(Color.WHITE);
            ButtonBar acao = new ButtonBar();
            ver = new Button();
            ver.setGraphic(fVer);
            ver.getStylesheets().add("/css/CentralPage.css");
            ver.setId("buttonSee");
            excluir = new Button();
            excluir.setGraphic(fDel);
            excluir.getStylesheets().add("/css/CentralPage.css");
            excluir.setId("buttonRemove");

            excluir.setOnAction((event) -> {
                String tipo = null;

                for (int i = 0; i < Repository.funcionarios.size(); i++) {
                    if (Repository.funcionarios.get(i).getIdFuncionario() == LerEstadoLogin.lerLogin().getIdUsuario()) {
                        tipo = Repository.funcionarios.get(i).getTipo();
                    }
                }

                if (LerEstadoLogin.lerLogin().getIdUsuario() < 1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Faça o login");
                    alert.show();
                } else if (tipo.equals("Usuario")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Um Usuario nao pode executar essa funcao");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(" Dialogo de Confirmacao");
                    alert.setHeaderText("ENTIDADE: " + entidade.getNome().toUpperCase());
                    alert.setContentText("Pretente realmente eliminar esta entidade?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        table.getItems().clear();
                        DaoGenerico daoGenerico = new DaoGenerico();
                        daoGenerico.delete(entidade.getIdEntidade(), entidade);
                        listar();
                    } else {
                        alert.close();
                    }
                }
            });

            ver.setOnAction((event) -> {
                if (entidade.getClass() == Alojamento.class) {
                    VerAlojamentoController.setEntidade(entidade);
                    try {
                        mostrarJanela(Path.PAGINA_VERALOJA, "", true);
                    } catch (IOException ex) {
                    }
                } else 
                if(entidade.getClass() == Restauracao.class){
                    VerRestauracaoController.setEntidade(entidade);
                    try {
                        mostrarJanela(Path.PAGINA_VERRES, "", true);
                    } catch (IOException ex) {
                    }
                }
            });

            acao.getButtons().addAll(ver, excluir);
            entidade.setAccoes(acao);
        });

        return entidades;
    }

    public void haFuncionarios() {
        if (Repository.funcionarios != null) {
            for (int i = 0; i < Repository.funcionarios.size(); i++) {
                if (Repository.funcionarios.get(i).getIdEntidade().getIdEntidade() == LogInPageController.entidade.getIdEntidade()) {
                    hire.setDisable(true);
                    return;
                }
            }
            hire.setDisable(false);
        } else {
            hire.setDisable(false);
        }
    }

    @FXML
    void accaoHire(ActionEvent event) throws IOException {
        mostrarJanela(Path.PAGINA_REGFUNC, "", true);
    }

    private void mostrarJanela1(String caminho, String title, boolean resizable) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(centralPane.getScene().getWindow());
        stage.setResizable(resizable);
        stage.show();
    }

    private void mostrarJanela(String caminho, String title, boolean resizable) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(centralPane.getScene().getWindow());
        stage.setResizable(resizable);
        stage.show();
    }

    private void mostrarJanela1(String caminho, String title, ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        OctoDBApplication.getStage().show();
    }

    @FXML
    void closeSessionAction(ActionEvent event) throws IOException {
        SalvarEstadoLogin.apagarFicheiro();
        mostrarJanela1(Path.PAGINA_INICIAL, "", event);

    }

    @FXML
    void exitAction(ActionEvent event) throws IOException {
        mostrarJanela1(Path.PAGINA_INICIAL, "", event);
    }

    private void mapearDados() {
        Central c = new Central();
        Repository repository = new Repository();
        repository.getContactos();

        DaoGenerico daoGenerico = new DaoGenerico();

        c = (Central) daoGenerico.readById(LerEstadoLogin.lerLogin().getIdEntidade(), c);

        nomeLabel.setText(c.getNome());

        for (int i = 0; i < Repository.contatos.size(); i++) {
            if (Repository.contatos.get(i).getIdEntidade().getIdEntidade() == LerEstadoLogin.lerLogin().getIdEntidade()) {
                phoneLbel.setText("" + Repository.contatos.get(i).getTelefone());
                emailLabel.setText(Repository.contatos.get(i).getEmail());
                urlLabel.setText(Repository.contatos.get(i).getUrl());
            }
        }

        if (LerEstadoLogin.lerLogin().getIdUsuario() > 0) {
            for (int i = 0; i < Repository.funcionarios.size(); i++) {
                if (LerEstadoLogin.lerLogin().getIdUsuario() == Repository.funcionarios.get(i).getIdFuncionario()) {
                    username.setText(Repository.funcionarios.get(i).getUsername());
                    tipo.setText("(" + Repository.funcionarios.get(i).getTipo() + ")");
                    if (Repository.funcionarios.get(i).getTipo().equals("Administrador")) {
                        logOut.setDisable(false);
                        logIn.setDisable(true);
                        hire.setDisable(false);
                    } else if (Repository.funcionarios.get(i).getTipo().equals("Usuario")) {
                        logOut.setDisable(false);
                        logIn.setDisable(true);
                        hire.setDisable(true);
                    }
                }
            }
        }
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        mostrarJanela1(Path.PAGINA_LOGFUNC, "", false);
    }

    @FXML
    void logOutAction(ActionEvent event) {
        EstadoLogin estadoLogin = new EstadoLogin();
        estadoLogin = LerEstadoLogin.lerLogin();
        estadoLogin.setIdUsuario(0);
        SalvarEstadoLogin.guardarLogin(estadoLogin);
        threadLogout();
    }

    public void threadLogout() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Thread.sleep(30);
                    Platform.runLater(() -> {
                        username.setText("Username");
                        tipo.setText("");
                        logOut.setDisable(true);
                        hire.setDisable(true);
                        logIn.setDisable(false);
                    });
                }
            }
        };
        new Thread(task).start();
    }

    @FXML
    void upAction(ActionEvent event) {

    }

    @FXML
    void allUserAction(ActionEvent event) throws IOException {
        mostrarJanela(Path.PAGINA_FUNCPAGE, "", false);
    }

    @FXML
    void searchAction(KeyEvent event) {

        ObservableList<Entidade> entidadesFiltradas = FXCollections.observableArrayList();
        table.getItems().clear();

        if (search.getText().isEmpty() || search.getText().equals("")) {
            listar();
        } else {
            DaoGenerico daoGenerico = new DaoGenerico();
            List<Entidade> entidades = new ArrayList();
            if (findEnt.isSelected()) {
                entidades = daoGenerico.searchEntity(search.getText());

                for (int i = 0; i < entidades.size(); i++) {
                        entidadesFiltradas.add(addAction(entidades).get(i));
                }

                table.setItems(entidadesFiltradas);
            } else {
                //PESQUISA POR CLIENTE
                listar();
            }
        }
    }
}
