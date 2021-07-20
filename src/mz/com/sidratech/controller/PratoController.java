package mz.com.sidratech.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import mz.com.sidratech.model.bean.Prato;
import mz.com.sidratech.model.dao.DaoGenerico;
import mz.com.sidratech.services.Path;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class PratoController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nome;
    @FXML
    private Label categoria;
    @FXML
    private Label preco;
    @FXML
    private Button remove;
    @FXML
    private VBox vBox;
    @FXML
    private Button addCart;
    
    public static List<Prato>  pratos=new ArrayList<>();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    int id;

    public void setData(Prato prato) throws MalformedURLException {
        File file = new File(prato.getCaminhoFoto());
        Image image = new Image(file.toURI().toString());
        this.image.setImage(image);
        nome.setText(prato.getNome());
        categoria.setText(prato.getTipo());
        preco.setText("" + prato.getPreco());
        id = prato.getIdPrato();
    }

    @FXML
    void removeAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" Dialogo de Confirmacao");
        alert.setHeaderText("PRATO: " + nome.getText().toUpperCase());
        alert.setContentText("Pretente realmente eliminar este prato?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            DaoGenerico daoGenerico = new DaoGenerico();
            daoGenerico.delete((int) id, new Prato());

            try {
                //Load second scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.PAGINA_RESTAURACAO));
                Parent root = loader.load();

                //Get controller of scene2
                RestauracaoController controller = loader.getController();
                //Pass whatever data you want. You can have multiple method calls here
                controller.getPratosGrid().getChildren().clear();
                controller.getPratosGrid().setDisable(true);
                controller.update();

            } catch (IOException ex) {
                System.err.println(ex);
            }

        } else {
            alert.close();
        }
    }
    
    static double total=0;
        @FXML
    void addCartAction(ActionEvent event) {
            Prato prato=new Prato();
            prato.setNome(nome.getText());
            prato.setPreco(Double.parseDouble(preco.getText()));
            total=total+prato.getPreco();
            pratos.add(prato);
       }

}
