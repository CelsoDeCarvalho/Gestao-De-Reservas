package mz.com.sidratech.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static mz.com.sidratech.controller.LogInPageController.entidade;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Central;
import mz.com.sidratech.model.bean.EstadoLogin;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.octodb.OctoDBApplication;
import mz.com.sidratech.repository.Repository;
import mz.com.sidratech.services.Path;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class PaginaPrincipalController implements Initializable {

    public static String lingua="EN";
    
    @FXML
    private BorderPane pane;

    @FXML
    private Button homeBtn;

    @FXML
    private Button licenseBtn;

    @FXML
    private Button aboutMeBtn;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signupBtn;

    @FXML
    private Button contactBtn;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private Button aboutUsBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBox.setItems(FXCollections.observableArrayList("EN", "PT"));
        choiceBox.getSelectionModel().selectFirst();

        choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("PT")) {
                    homeBtn.setText("Casa.");
                    licenseBtn.setText("Licenca.");
                    aboutMeBtn.setText("Sobre Me.");
                    loginBtn.setText("Entrar");
                    signupBtn.setText("Cadastrar");
                    contactBtn.setText("Fale Conosco");
                    aboutUsBtn.setText("Mais Sobre Nos");
                    label1.setText("Agência Digital");
                    label2.setText("com Excelente");
                    label3.setText("Servico.");
                    label4.setText("Dê Vida e Automatização ao seu Negócio");
                    label5.setText("com os Software da sidratech.");
                    label6.setText("SIDRATECH inovando a sua vida.");
                } else {
                    homeBtn.setText("Home.");
                    licenseBtn.setText("Licence.");
                    aboutMeBtn.setText("About Me.");
                    loginBtn.setText("Log In");
                    signupBtn.setText("Sign Up");
                    contactBtn.setText("Contact Us");
                    aboutUsBtn.setText("More About Us");
                    label1.setText("Digital Agency");
                    label2.setText("With Excellent");
                    label3.setText("Service.");
                    label4.setText("Give life and automation to your");
                    label5.setText("business  with sidratech software.");
                    label6.setText("SIDRATECH inovating your life.");
                }
                lingua=newValue;

            }

        });
    }

    private   void mostrarJanela(String caminho, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(pane.getScene().getWindow());
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        EstadoLogin estadoLogin=new EstadoLogin();
        estadoLogin=LerEstadoLogin.lerLogin();
        if(LerEstadoLogin.exist() && estadoLogin.getIdEntidade()>0)
            for(int i=0;i<Repository.entidades.size();i++){
                if(Repository.entidades.get(i).getIdEntidade()==LerEstadoLogin.lerLogin().getIdEntidade()){
                    if(Repository.entidades.get(i).getClass()==Central.class){
                        entidade=new Central();
                         entidade=Repository.entidades.get(i);
                        mostrarJanela1(Path.PAGINA_CENTRAL,"",event);
                    }else
                        if(Repository.entidades.get(i).getClass()==Alojamento.class){
                            entidade=new Alojamento();
                            entidade=Repository.entidades.get(i);
                            mostrarJanela1(Path.PAGINA_ALOJAMENTO,"",event);
                        }else{
                            entidade=new Restauracao();
                            entidade=Repository.entidades.get(i);
                            mostrarJanela1(Path.PAGINA_RESTAURACAO,"",event);
                        }
                }
            }
        else
        mostrarJanela(Path.PAGINA_LOGIN, "LOGIN");
    }

    @FXML
    void signupAction(ActionEvent event) throws IOException {
        mostrarJanela(Path.PAGINA_SIGNUP,"CADASTRO");
    }
    
    @FXML
    void aboutUsBtnAction(ActionEvent event) {

    }

    @FXML
    void contactUsBtnAction(ActionEvent event) {

    }

    @FXML
    void liceceBtnAction(ActionEvent event) throws IOException {
        mostrarJanela(Path.PAGINA_LICENCE,"GNU LICENCE");
    }
    
    @FXML
    void meBtnAction(ActionEvent event) throws IOException {
        mostrarJanela(Path.PAGINA_CONTACTME,"");
    }

    @FXML
    void sidratechLabeAction(MouseEvent event) {      
        
    }

        private   void mostrarJanela1(String caminho, String title, ActionEvent event) throws IOException {
            ((Node) event.getSource()).getScene().getWindow().hide();
            OctoDBApplication.getStage().close();
            Parent root = FXMLLoader.load(getClass().getResource(caminho));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle(title);
            stage.show();
    }
        
}
