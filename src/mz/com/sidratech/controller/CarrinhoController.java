package mz.com.sidratech.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import mz.com.sidratech.model.bean.Prato;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class CarrinhoController implements Initializable {


    @FXML
    private TableView<Prato> tabela;
     @FXML
    private TableColumn<Prato, String> columnName;

    @FXML
    private TableColumn<Prato, Double> columnPreco;

    @FXML
    private TableColumn<Prato, Button> columnProcessos;

    @FXML
    private Label total;

    @FXML
    private Button concluir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popularTabela();
        if(PratoController.pratos.isEmpty()||PratoController.pratos==null){
            concluir.setDisable(true);
        }else
            concluir.setDisable(false);
    }    
    
    void popularTabela(){
        ObservableList<Prato> prato = FXCollections.observableArrayList(addAction(PratoController.pratos));
        columnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        columnProcessos.setCellValueFactory(new PropertyValueFactory<>("button"));
        tabela.setItems(prato);
        total.setText(""+PratoController.total);
    }
    
    public List<Prato> addAction(List<Prato> pratos) {

        pratos.forEach((prato) -> {

            Button button = new Button();
            button.setPrefSize(70, 25);
            FontAwesomeIcon trash = new FontAwesomeIcon();
            trash.setIconName("TRASH");
            trash.setFill(Color.WHITE);

            button.getStylesheets().add("/css/CarrinhoStyle.css");
            button.setGraphic(trash);

            button.setId("delBtn");

            button.setOnAction((event) -> {
                tabela.getItems().clear();
                PratoController.pratos.remove(prato);
                PratoController.total=PratoController.total-prato.getPreco();
                total.setText(PratoController.total+"");
                popularTabela();
                if(PratoController.pratos.isEmpty()||PratoController.pratos==null)
                    concluir.setDisable(true);
            });
            
            prato.setButton(button);

        });

        return pratos;
    }
    
        
    @FXML
    void concluirAction(ActionEvent event) {
        PratoController.pratos.clear();
        tabela.getItems().clear();
        PratoController.total=0;
        popularTabela();

    }
    
}
