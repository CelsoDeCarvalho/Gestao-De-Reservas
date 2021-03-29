package mz.com.sidratech.octodb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mz.com.sidratech.model.dao.DaoGenerico;
import mz.com.sidratech.repository.Repository;
import mz.com.sidratech.services.Path;

/**
 *
 * @author celso
 */
public class OctoDBApplication extends Application {
    
    private static Stage stage;
            
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Path.PAGINA_INICIAL));
        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setTitle("");
        stage.setScene(scene);
        stage.centerOnScreen();
        this.stage=stage;
        stage.show();
    }


    public static void main(String[] args) {
        
        DaoGenerico daoGenerico=new DaoGenerico();        
        Repository repository=new Repository();
        repository.getEntidades();
        launch(args);
    }
    
    public static Stage getStage(){
        return stage;
    }
    
}