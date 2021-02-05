package gestao.reservas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.Caminhos;

/**
 *
 * @author celso
 */
public class GestaoDeReservas extends Application {
    
            
    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource(Caminhos.PAGINA_INICIAL));
        Scene scene = new Scene(root);
        stage.setFullScreen(true);
        stage.setTitle("Pagina Principal");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
