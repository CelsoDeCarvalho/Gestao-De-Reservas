package mz.com.sidratech.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.controller.file.SalvarEstadoLogin;
import mz.com.sidratech.model.bean.EstadoLogin;
import mz.com.sidratech.model.bean.Prato;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.model.dao.DaoGenerico;
import mz.com.sidratech.octodb.OctoDBApplication;
import mz.com.sidratech.repository.Repository;
import mz.com.sidratech.services.Path;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class RestauracaoController implements Initializable {

    
    @FXML
    private Label username;
    @FXML
    private MenuButton menuBar;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField type;
    @FXML
    private BorderPane pane;
    @FXML
    private Button addBtn;
    @FXML
    private GridPane pratosGrid;
    @FXML
    private MenuItem login;
    @FXML
    private MenuItem logout;
    @FXML
    private MenuItem exit;
    @FXML
    private MenuItem close;
    private List<Prato> pratos;
    @FXML
    private ImageView image;
    @FXML
    private Label nameEnt;
    @FXML
    private TextField mesas;
    @FXML
    private TextField cadeiras;
    @FXML
    private FontAwesomeIcon carrinho;
    List<Prato> novoPrato = new ArrayList<>();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapearDados();
        Mascara mascara = new Mascara();
        mascara.apenasNumero(price);
        for (int i = 0; i < Repository.pratos.size(); i++) {
            if (Repository.pratos.get(i).getIdRestauracao().getIdEntidade() == LerEstadoLogin.lerLogin().getIdEntidade()) {
                novoPrato = Repository.pratos;
            }
        }
        update();
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
        stage.initOwner(pane.getScene().getWindow());
        stage.setResizable(resizable);
        stage.show();
    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        menuBar.getScene().getWindow().hide();
        mostrarJanela1(Path.PAGINA_LOGFUNCREST, "", false);
    }

    @FXML
    private void logoutAction(ActionEvent event) {
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
                        username.setText("USERNAME");
                        logout.setDisable(true);
                        login.setDisable(false);
                        pratosGrid.setDisable((true));
                    });
                }
            }
        };
        new Thread(task).start();
    }

    @FXML
    private void exitAction(ActionEvent event) throws IOException {
        mostrarJanela1(Path.PAGINA_INICIAL);
    }

    @FXML
    private void closeAction(ActionEvent event) throws IOException {
        SalvarEstadoLogin.apagarFicheiro();
        mostrarJanela1(Path.PAGINA_INICIAL);
    }

    private void mostrarJanela1(String caminho) throws IOException {
        menuBar.getScene().getWindow().hide();
        OctoDBApplication.getStage().show();
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

    String caminho;

    File file;

    @FXML
    void escolherFoto(MouseEvent event) {
        Stage stage = new Stage();

        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new ExtensionFilter("imagens", "*.jpg", "*.png"));
        file = chooser.showOpenDialog(stage);
        if (file != null) {
            caminho = file.getAbsolutePath();
            Image image = new Image(file.toURI().toString());
            this.image.setImage(image);
        } else {
            file = new File("src/mz/com/sidratech/image/istockphoto-1222357475-612x612.jpg");
            caminho = file.getAbsolutePath();
            Image image = new Image(file.toURI().toString());
            this.image.setImage(image);
        }

    }

    @FXML
    void addAction(ActionEvent event) throws IOException {

        Prato prato = new Prato();
        if (name.getText().isEmpty()) {
            thread(name);
        } else if (price.getText().isEmpty()) {
            thread(price);
        } else if (type.getText().isEmpty()) {
            thread(type);
        } else {

            if (file == null) {
                file = new File("src/mz/com/sidratech/image/istockphoto-1222357475-612x612.jpg");
                caminho = file.getAbsolutePath();
            } else {
                caminho = file.getAbsolutePath();
            }
            prato.setCaminhoFoto(caminho);
            prato.setNome(name.getText());
            prato.setPreco(Double.parseDouble(price.getText()));
            prato.setTipo(type.getText());
            Restauracao restauracao = new Restauracao();
            prato.setIdRestauracao((Restauracao) new DaoGenerico().readById(LerEstadoLogin.lerLogin().getIdEntidade(), restauracao));
            novoPrato.add(prato);
            update();
            DaoGenerico daoGenerico = new DaoGenerico();
            daoGenerico.create(prato);

            file = new File("src/mz/com/sidratech/image/istockphoto-1222357475-612x612.jpg");
            caminho = file.getAbsolutePath();
            Image image = new Image(file.toURI().toString());
            this.image.setImage(image);
            name.setText("");
            price.setText("");
            type.setText("");
        }
    }

    public void update() {
        Repository repository = new Repository();
        repository.getPratos();
        pratos = new ArrayList<>(novoPrato);

        int columns = 0;
        int row = 1;

        try {
            for (int i = 0; i < pratos.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(Path.PAGINA_PRATOS));
                VBox vBox = loader.load();
                PratoController pratoController = loader.getController();
                pratoController.setData(pratos.get(i));

                if (columns == 3) {
                    columns = 0;
                    ++row;
                }

                pratosGrid.add(vBox, columns++, row);
                GridPane.setMargin(vBox, new Insets(5));

            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void thread(TextField field) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                field.setStyle("-fx-border-color: red;");
                FadeTransition transition = new FadeTransition(Duration.millis(2000));
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
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }

    private void mapearDados() {
        Restauracao r = new Restauracao();
        Repository repository = new Repository();
        repository.getContactos();

        DaoGenerico daoGenerico = new DaoGenerico();

        r = (Restauracao) daoGenerico.readById((int) LerEstadoLogin.lerLogin().getIdEntidade(), r);
        nameEnt.setText(r.getNome());

        for (int i = 0; i < Repository.contatos.size(); i++) {
            if (Repository.contatos.get(i).getIdEntidade().getIdEntidade() == LerEstadoLogin.lerLogin().getIdEntidade()) {
            }
        }

        if (LerEstadoLogin.lerLogin().getIdUsuario() > 0) {
            
            for (int i = 0; i < Repository.funcionarios.size(); i++) {
                if (LerEstadoLogin.lerLogin().getIdUsuario() == Repository.funcionarios.get(i).getIdFuncionario()) {
                    username.setText(Repository.funcionarios.get(i).getUsername());
                    if (Repository.funcionarios.get(i).getTipo().equals("Administrador")) {
                        logout.setDisable(false);
                        login.setDisable(true);
                    } else if (Repository.funcionarios.get(i).getTipo().equals("Usuario")) {
                        logout.setDisable(false);
                        login.setDisable(true);
                    }
                }
            }
        }else{
            pratosGrid.setDisable(true);
            
        }
    }

    @FXML
    void employeeAction(ActionEvent event) throws IOException {
        mostrarJanela(Path.PAGINA_FUNCPAGE, "", false);
    }

    @FXML
    void hireAction(ActionEvent event) throws IOException {
        mostrarJanela(Path.PAGINA_REGFUNCREST, "", true);
    }

    @FXML
    void reportActio(ActionEvent event) {

    }

    @FXML
    void addLugarAction(ActionEvent event) {
        if (mesas.getText().isEmpty()) {
            thread(mesas);
        } else if (cadeiras.getText().isEmpty()) {
            thread(cadeiras);
        } else {
            Restauracao restauracao = new Restauracao();
            restauracao = (Restauracao) new DaoGenerico().readById(LerEstadoLogin.lerLogin().getIdEntidade(), restauracao);
            restauracao.setTotalMesas(restauracao.getTotalMesas() + Integer.parseInt(mesas.getText()));
            restauracao.setTotalCadeiras(restauracao.getTotalCadeiras() + Integer.parseInt(cadeiras.getText()));
            new DaoGenerico().update(restauracao);
            mesas.setText("");
            cadeiras.setText("");
        }
    }

    public Label getNameEnt() {
        return nameEnt;
    }

    public void setNameEnt(Label nameEnt) {
        this.nameEnt = nameEnt;
    }

    public GridPane getPratosGrid() {
        return pratosGrid;
    }

    public void setPratosGrid(GridPane pratosGrid) {
        this.pratosGrid = pratosGrid;
    }
    
        @FXML
    void verCarrinho(MouseEvent event) throws IOException {
            mostrarJanela(Path.PAGINA_CARRINHO, "", false);
    }

}
