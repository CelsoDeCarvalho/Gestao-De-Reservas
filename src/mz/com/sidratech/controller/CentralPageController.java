/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.sidratech.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mz.com.sidratech.model.bean.Alojamento;
import mz.com.sidratech.model.bean.Entidade;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.repository.Repository;

public class CentralPageController implements Initializable {

    @FXML
    private TableView<Entidade> table;
    @FXML
    private TableColumn<Entidade,String> tipoColumn;
    @FXML
    private TableColumn<Entidade,String> nomeColumn;
    @FXML
    private TableColumn<Entidade,String> localColumn;
    @FXML
    private TableColumn<Entidade,String> classificacaoColumn;
    @FXML
    private TableColumn<Entidade,Button> ver;
    @FXML
    private MenuBar menu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menu.setFocusTraversable(false);
        listar();
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
            acao.setMaxWidth(235);

            Button ver = new Button();
            ver.setGraphic(fVer);
            ver.getStylesheets().add("/css/CentralPage.css");
            ver.setId("buttonSee");

            Button excluir = new Button();
            excluir.setGraphic(fDel);
            excluir.getStylesheets().add("/css/CentralPage.css");
            excluir.setId("buttonRemove");

            acao.getButtons().addAll(ver, excluir);
            entidade.setAccoes(acao);
            
        });
            
            return entidades;
    }

}
