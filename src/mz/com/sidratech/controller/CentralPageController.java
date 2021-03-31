package mz.com.sidratech.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.controller.file.SalvarEstadoLogin;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Entidade;
import mz.com.sidratech.model.bean.EstadoLogin;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.octodb.OctoDBApplication;
import mz.com.sidratech.repository.Repository;
import mz.com.sidratech.services.Path;

public class CentralPageController implements Initializable {

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
    private  Label username;
    Button ver ;
    Button excluir;
    boolean logOutState;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listar();
        ver.setDisable(true);
        excluir.setDisable(true);
        mapearDados();
        haFuncionarios();
        search.setFocusTraversable(false);
    } 
    
    public ArrayList trocarArray(){
        ArrayList<Entidade> entidades=new ArrayList();
        for(int i=0;i<Repository.entidades.size();i++)
            if(Repository.entidades.get(i).getClass().equals(Alojamento.class)||Repository.entidades.get(i).getClass().equals(Restauracao.class))
                entidades.add(Repository.entidades.get(i));
        return entidades;
    }
    
    private void listar(){
        ObservableList<Entidade> list=FXCollections.observableArrayList(addAction(trocarArray()));
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("tipo"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("enderecoFisico"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("classificacao")); 
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("accoes"));
        table.setItems(list);
    }
    
    public List<Entidade> addAction(List<Entidade> entidades) {

        entidades.forEach((entidade) -> {
            FontAwesomeIcon fVer=new FontAwesomeIcon();
            FontAwesomeIcon fDel=new FontAwesomeIcon();
            fVer.setIconName("EYE");
            fDel.setIconName("TRASH");
            ButtonBar acao = new ButtonBar();
            ver=new Button();
            ver.setGraphic(fVer);
            ver.getStylesheets().add("/css/CentralPage.css");
            ver.setId("buttonSee");
            excluir=new Button();
            excluir.setGraphic(fDel);
            excluir.getStylesheets().add("/css/CentralPage.css");
            excluir.setId("buttonRemove");

            acao.getButtons().addAll(ver, excluir);
            entidade.setAccoes(acao);
            if(logOutState){
                excluir.setDisable(true);
                ver.setDisable(true);
            }
        });
            
            return entidades;
    }
    
    
    public void haFuncionarios(){
        if(Repository.funcionarios!=null)
        for(int i=0;i<Repository.funcionarios.size();i++){
            if(Repository.funcionarios.get(i).getIdEntidade().getIdEntidade()==LogInPageController.entidade.getIdEntidade()&&LerEstadoLogin.lerLogin().getIdUsuario()<1){
                    logOutState=true;
            }
        }
        logOutState=true;
    }
    
    
        @FXML
    void accaoHire(ActionEvent event) throws IOException {
            mostrarJanela(Path.PAGINA_REGFUNC,"",true);
    }
    
   private  void mostrarJanela(String caminho, String title, boolean resizable) throws IOException {
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
         mostrarJanela1(Path.PAGINA_INICIAL,"", event);
         
    }
    
    @FXML
    void exitAction(ActionEvent event) throws IOException {
        mostrarJanela1(Path.PAGINA_INICIAL,"", event);
    }
    
    private void mapearDados(){
        for(int i=0;i<Repository.contatos.size();i++){
            if(Repository.contatos.get(i).getIdEntidade().getIdEntidade()==LogInPageController.entidade.getIdEntidade()){
                phoneLbel.setText(""+Repository.contatos.get(i).getTelefone());
                emailLabel.setText(Repository.contatos.get(i).getEmail());
                urlLabel.setText(Repository.contatos.get(i).getUrl());
                nomeLabel.setText(LogInPageController.entidade.getNome());
            }
        }
        
        if(LerEstadoLogin.lerLogin().getIdUsuario()>0){
            for(int i=0;i<Repository.funcionarios.size();i++){
                if(LerEstadoLogin.lerLogin().getIdUsuario()==Repository.funcionarios.get(i).getIdFuncionario()){
                    username.setText(Repository.funcionarios.get(i).getUsername());
                    tipo.setText("("+Repository.funcionarios.get(i).getTipo()+")");
                    if(Repository.funcionarios.get(i).getTipo().equals("Administrador")){
                        logOut.setDisable(false);
                        logIn.setDisable(true);
                        hire.setDisable(false);
                        ver.setDisable(false);
                        excluir.setDisable(false);
                    }else
                    if(Repository.funcionarios.get(i).getTipo().equals("Usuario")){
                        logOut.setDisable(false);
                        logIn.setDisable(true);
                        hire.setDisable(true);
                        ver.setDisable(false);
                        excluir.setDisable(true);
                    }
                }
            }
        }
    }
    
    @FXML
    void loginAction(ActionEvent event) throws IOException {
        mostrarJanela(Path.PAGINA_LOGFUNC,"", false);
    }
    
        @FXML
    void logOutAction(ActionEvent event) {
            EstadoLogin estadoLogin=new EstadoLogin();
            estadoLogin=LerEstadoLogin.lerLogin();
            estadoLogin.setIdUsuario(0);
            SalvarEstadoLogin.guardarLogin(estadoLogin);
            threadLogout();
    }

    public void threadLogout(){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Thread.sleep(30);
                    Platform.runLater(() -> {
                        ver.setDisable(true);
                        excluir.setDisable(true);
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
}


