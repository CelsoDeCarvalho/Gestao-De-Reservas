package mz.com.sidratech.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.controller.file.SalvarEstadoLogin;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Cliente;
import mz.com.sidratech.model.bean.EstadoLogin;
import mz.com.sidratech.model.bean.Quarto;
import mz.com.sidratech.model.dao.DaoGenerico;
import mz.com.sidratech.octodb.OctoDBApplication;
import mz.com.sidratech.repository.Repository;
import mz.com.sidratech.services.Help;
import mz.com.sidratech.services.Path;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class AlojamentoController implements Initializable {

    @FXML
    private Label enterLabel;
    @FXML
    private Label leftLabel;
    @FXML
    private TableView<Quarto> tabelaQuartos;
    @FXML
    private BorderPane pane;
    @FXML
    private Label username;
    @FXML
    private Label tipo;
    @FXML
    private Button login;
    @FXML
    private Button logout;
    @FXML
    private Button button;
    @FXML
    private Button hire;
    @FXML
    private Label nameLabel;
    @FXML
    private Label roomLabel;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label url;
    @FXML
    private ComboBox<Integer> combo;
    @FXML
    private Button saveBtn;
    @FXML
    private TableView<Cliente> tabelaClientes;
    @FXML
    private TextField name;
    @FXML
    private DatePicker enterDa;
    @FXML
    private DatePicker leftDa;
    @FXML
    private Label roomNuLab;
    @FXML
    private TextField total;
    @FXML
    private TextField roomNuField;
    @FXML
    private Label bedNuLab;
    @FXML
    private TextField bedNuField;
    @FXML
    private Button add;
    private BarChart<String, Integer> barChart;
    @FXML
    private Label totalLabel;
    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox2;
    @FXML
    private Label totalGues;
    @FXML
    private TextField fieldPesquisar;
    @FXML
    private Label disponivelLabel;
    @FXML
    private Label ocupadoLabel;

    @FXML
    private TableColumn<Cliente, String> columnName;

    @FXML
    private TableColumn<Cliente, Integer> columnRoom;

    @FXML
    private TableColumn<Cliente, Double> columnPrice;

    @FXML
    private TableColumn<Cliente, LocalDate> columnEnter;

    @FXML
    private TableColumn<Cliente, LocalDate> columnLeft;
    @FXML
    private CategoryAxis xAxis;
    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        total.setText("0");
        Mascara mascara = new Mascara();
        mascara.apenasNumero(bedNuField);
        tableGuestPopulating();
        tableRoomPopulating();
        roomDetail();
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);
        haFuncionarios();
        mapearDados();
    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        mostrarJanela(Path.PAGINA_LOGFUNCALOJA, "", false);
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        EstadoLogin estadoLogin = new EstadoLogin();
        estadoLogin = LerEstadoLogin.lerLogin();
        estadoLogin.setIdUsuario(0);
        SalvarEstadoLogin.guardarLogin(estadoLogin);
        threadLogout();
    }

    @FXML
    private void exitAction(ActionEvent event) throws IOException {
        mostrarJanela1(Path.PAGINA_INICIAL, "", event);
    }

    @FXML
    private void hireAction(ActionEvent event) throws IOException {
        mostrarJanela(Path.PAGINA_REGFUNCALOJA, "", true);
    }

    @FXML
    private void allUser(ActionEvent event) {
    }

    @FXML
    private void sessionAction(ActionEvent event) throws IOException {
        SalvarEstadoLogin.apagarFicheiro();
        mostrarJanela1(Path.PAGINA_INICIAL, "", event);
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

    private void mostrarJanela(String caminho, String title, boolean resizable) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(pane.getScene().getWindow());
        stage.setResizable(resizable);
        stage.show();
    }

    private void mostrarJanela1(String caminho, String title, ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        OctoDBApplication.getStage().show();
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
                        logout.setDisable(true);
                        hire.setDisable(true);
                        login.setDisable(false);
                        vbox1.setDisable(true);
                        vbox2.setDisable(true);
                    });
                }
            }
        };
        new Thread(task).start();
    }

    private void mapearDados() {
        Repository repository = new Repository();
        repository.getContactos();
        for (int i = 0; i < Repository.contatos.size(); i++) {
            if (Repository.contatos.get(i).getIdEntidade().getIdEntidade() == LerEstadoLogin.lerLogin().getIdEntidade()) {
                phone.setText("" + Repository.contatos.get(i).getTelefone());
                email.setText(Repository.contatos.get(i).getEmail());
                url.setText(Repository.contatos.get(i).getUrl());
                if (LoginFuncionarioControllerAloja.funcionario != null) {
                    username.setText(LoginFuncionarioControllerAloja.funcionario.getUsername());
                }
            }
        }

        if (LerEstadoLogin.lerLogin().getIdUsuario() > 0) {
            vbox1.setDisable(false);
            vbox2.setDisable(false);
            for (int i = 0; i < Repository.funcionarios.size(); i++) {
                if (LerEstadoLogin.lerLogin().getIdUsuario() == Repository.funcionarios.get(i).getIdFuncionario()) {
                    username.setText(Repository.funcionarios.get(i).getUsername());
                    tipo.setText("(" + Repository.funcionarios.get(i).getTipo() + ")");
                    if (Repository.funcionarios.get(i).getTipo().equals("Administrador")) {
                        logout.setDisable(false);
                        login.setDisable(true);
                        hire.setDisable(false);
                    } else if (Repository.funcionarios.get(i).getTipo().equals("Usuario")) {
                        logout.setDisable(false);
                        login.setDisable(true);
                        hire.setDisable(true);
                    }
                }
            }
        } else {
            vbox1.setDisable(true);
            vbox2.setDisable(true);
        }
    }

    private ObservableList<Integer> rooms = FXCollections.observableArrayList();

    @FXML
    void addRoom(ActionEvent event) {
        if (roomNuField.getText().isEmpty()) {
            thread(roomNuField, roomNuLab);
        } else if (bedNuField.getText().isEmpty()) {
            thread(bedNuField, bedNuLab);
        } else {
            combo.getItems().clear();
            tabelaQuartos.getItems().clear();
            Quarto quarto = new Quarto(Integer.parseInt(roomNuField.getText()), Integer.parseInt(bedNuField.getText()), (Alojamento) LogInPageController.entidade);
            roomNuField.setText("");
            bedNuField.setText("");
            DaoGenerico daoGenerico = new DaoGenerico();
            daoGenerico.create(quarto);
            tableRoomPopulating();

            roomDetail();
        }
    }

    void roomDetail() {
        Repository repository = new Repository();
        repository.getQuartos();
        for (int i = 0; i < Repository.quartos.size(); i++) {
            if (Repository.quartos.get(i).getIdAlojamento().getIdEntidade() == LerEstadoLogin.lerLogin().getIdEntidade()) {
                if (Repository.quartos.get(i).getOcupado().equals("Disponivel")) {
                    rooms.add(Repository.quartos.get(i).getNumero());
                }
            }
        }
        combo.setItems(rooms);
    }

    @FXML
    void refreshAction(ActionEvent event) {
        int nuQuarto = (int) (1000 + Math.random() * (10000 - 1000 + 1));

        for (int i = 0; i < Repository.quartos.size(); i++) {
            if (Repository.quartos.get(i).getIdAlojamento().getIdEntidade() == LerEstadoLogin.lerLogin().getIdEntidade()) {
                if (Repository.quartos.get(i).getNumero() == nuQuarto) {
                    nuQuarto = (int) (1000 + Math.random() * (10000 - 1000 + 1));
                }
            }
        }

        roomNuField.setText("" + nuQuarto);
    }

    @FXML
    void refreshPressed(MouseEvent event) {
        roomNuField.setText("");
    }

    private ObservableList<Quarto> room = FXCollections.observableArrayList();

    /**
     *
     * @param quartos
     */
    public void tableRoomPopulating() {
        int total = 0, ocupados = 0, disponiveis = 0;
        Repository repository = new Repository();
        repository.getQuartos();
        tabelaQuartos.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("numero"));
        tabelaQuartos.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("quantidadeCamas"));
        tabelaQuartos.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ocupado"));
        for (int i = 0; i < Repository.quartos.size(); i++) {
            if (Repository.quartos.get(i).getIdAlojamento().getIdEntidade() == LerEstadoLogin.lerLogin().getIdEntidade()) {
                room.add(Repository.quartos.get(i));
                total++;
                if (Repository.quartos.get(i).getOcupado().equals("Ocupado")) {
                    ocupados++;
                } else {
                    disponiveis++;
                }
            }
        }
        tabelaQuartos.setItems(room);
        totalLabel.setText("" + total);
        ocupadoLabel.setText("" + ocupados);
        disponivelLabel.setText("" + disponiveis);
    }

    @FXML
    void saveAction(ActionEvent event) {
        Repository repository = new Repository();

        if (name.getText().isEmpty()) {
            thread(name, nameLabel);
        } else if (combo.getSelectionModel().getSelectedItem() == null) {
            thread1(combo, roomLabel);
        } else if (enterDa.getValue() == null) {
            thread(enterDa.getEditor(), enterLabel);
        } else if (leftDa.getValue() == null) {
            thread(leftDa.getEditor(), leftLabel);
        } else {
            tabelaClientes.getItems().clear();
            Cliente cliente = new Cliente(name.getText(), Integer.parseInt(total.getText()), enterDa.getValue(), leftDa.getValue(), (int) combo.getSelectionModel().getSelectedItem(), (Alojamento) LogInPageController.entidade);
            DaoGenerico daoGenerico = new DaoGenerico();
            name.setText("");
            combo.getSelectionModel().clearSelection();
            enterDa.getEditor().setText("");
            leftDa.getEditor().setText("");
            total.setText("0");
            daoGenerico.create(cliente);
            for (int i = 0; i < Repository.quartos.size(); i++) {
                if (Repository.quartos.get(i).getNumero() == numero) {
                    combo.getItems().clear();
                    tabelaQuartos.getItems().clear();
                    Quarto quarto = new Quarto();
                    quarto = Repository.quartos.get(i);
                    quarto.setOcupado("Ocupado");
                    daoGenerico.update(quarto);
                    tableRoomPopulating();
                }
            }
            roomDetail();
            repository.getClientes();
            tableGuestPopulating();
        }
    }

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    public void tableGuestPopulating() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnRoom.setCellValueFactory(new PropertyValueFactory<>("numeroQuarto"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("valorPagar"));
        columnEnter.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        columnLeft.setCellValueFactory(new PropertyValueFactory<>("dataSaida"));
        for (int i = 0; i < Repository.clientes.size(); i++) {
            if (Repository.clientes.get(i).getIdAlojamento().getIdEntidade() == LerEstadoLogin.lerLogin().getIdEntidade()) {
                clientes.add(Repository.clientes.get(i));
            }
        }
        tabelaClientes.setItems(clientes);
        totalGues.setText("" + clientes.size());
    }

    int numero;

    @FXML
    void comboAction(ActionEvent event) {
        if (combo.getSelectionModel().getSelectedItem()!=null) {
            numero = (int) combo.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    void leftDateActio(ActionEvent event) throws ParseException {

        if (enterDa.getEditor().getText().isEmpty()) {
            thread(enterDa.getEditor(), enterLabel);
            leftDa.getEditor().setText("");
        } else if (leftDa.getValue().isBefore(enterDa.getValue()) || leftDa.getValue().isEqual(enterDa.getValue())) {
            leftDa.getEditor().setText("");
            thread(leftDa.getEditor(), leftLabel);
        } else {
            long totalDias = DAYS.between(enterDa.getValue(), leftDa.getValue());
            total.setText("" + 3500 * totalDias);
        }

    }

    @FXML
    void enterAction(ActionEvent event) {
        try {
            Date date = new Date(System.currentTimeMillis());
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (enterDa.getValue().isBefore(localDate)) {
                enterDa.getEditor().setText("");
                thread(enterDa.getEditor(), enterLabel);
            }
        } catch (NullPointerException e) {

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

    private ObservableList<Cliente> clientesFiltrados = FXCollections.observableArrayList();

    @FXML
    void accaoPesquisar(ActionEvent event) {
        if (fieldPesquisar.getText().isEmpty()) {
            tabelaClientes.getItems().clear();
            tableGuestPopulating();
        } else {
            tabelaClientes.getItems().clear();
            DaoGenerico daoGenerico = new DaoGenerico();
            List<Cliente> clientes = new ArrayList();
            clientes = daoGenerico.searchGuest(fieldPesquisar.getText());

            for (int i = 0; i < clientes.size(); i++) {
                clientesFiltrados.add(clientes.get(i));
            }

            totalGues.setText("" + clientes.size());
            tabelaClientes.setItems(clientesFiltrados);

        }
    }
}
