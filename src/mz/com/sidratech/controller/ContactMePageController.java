package mz.com.sidratech.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class ContactMePageController implements Initializable {

    String htLink = "http://";

    @FXML
    private WebView web;
    
    WebEngine engine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = web.getEngine();
        engine.setJavaScriptEnabled(true);
        engine.load(htLink+"www.youtube.com/channel/UCCJxgXrV3x_lc4Gw5ogynew");
    }
    
        @FXML
    void faceBtn(ActionEvent event) {
        engine.load("https://www.facebook.com/");
    }

    @FXML
    void githubBtn(ActionEvent event) {
        engine.load("http://github.com/CelsoDeCarvalho/Gestao-De-Reservas");
    }

    @FXML
    void youtubeBtn(ActionEvent event) {
        engine.load("http://www.youtube.com/channel/UCCJxgXrV3x_lc4Gw5ogynew");
    }
}
