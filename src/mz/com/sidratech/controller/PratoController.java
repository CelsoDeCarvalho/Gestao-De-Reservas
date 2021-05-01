package mz.com.sidratech.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mz.com.sidratech.model.bean.Prato;

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


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setData(Prato prato) throws MalformedURLException {
        File file=new File(prato.getCaminhoFoto());
        Image image = new Image(file.toURI().toString());
        this.image.setImage(image);
        nome.setText(prato.getNome());
        categoria.setText(prato.getTipo());
        preco.setText("" + prato.getPreco());
    }
    
    @FXML
    void removeAction(ActionEvent event) {

    }
    

}
