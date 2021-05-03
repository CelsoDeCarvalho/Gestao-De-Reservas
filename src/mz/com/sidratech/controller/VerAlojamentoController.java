package mz.com.sidratech.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mz.com.sidratech.controller.file.LerEstadoLogin;
import mz.com.sidratech.model.bean.Entidade;
import mz.com.sidratech.model.bean.Quarto;
import mz.com.sidratech.repository.Repository;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class VerAlojamentoController implements Initializable {

    @FXML
    private Label nomeEnt;

    @FXML
    private TableView<Quarto> tabela;

    @FXML
    private TableColumn<Quarto, Integer> camas;

    @FXML
    private TableColumn<Quarto, String> descricao;

    @FXML
    private Label nomeEnt1;

    @FXML
    private Label local;

    @FXML
    private Label telefone;

    @FXML
    private Label email;

    @FXML
    private Label url;

    @FXML
    private Label tMulheres;

    @FXML
    private Label tHomens;
    @FXML
    private Label total;
    private static Entidade entidade;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeEnt.setText(entidade.getNome());
        nomeEnt1.setText(entidade.getNome());
        email.setText(entidade.getContacto().getEmail());
        telefone.setText("" + entidade.getContacto().getTelefone());
        local.setText(entidade.getEnderecoFisico());
        this.url.setText(entidade.getContacto().getUrl());

        Repository repository = new Repository();
        repository.getFuncionarios();
        repository.getQuartos();

        int subTotal = 0;
        int subTotalH = 0;
        int subTotalM = 0;

        for (int i = 0; i < Repository.funcionarios.size(); i++) {
            if (Repository.funcionarios.get(i).getIdEntidade().getIdEntidade()== entidade.getIdEntidade()) {
                subTotal++;
                if (Repository.funcionarios.get(i).getSexo() == 'M') {
                    subTotalM++;
                } else {
                    subTotalH++;
                }
            }
        }

        total.setText("" + subTotal);
        tHomens.setText("" + subTotalH);
        tMulheres.setText("" + subTotalM);

        ObservableList<Quarto> rooms = FXCollections.observableArrayList();


            for (int i = 0; i < Repository.quartos.size(); i++) {
                if (Repository.quartos.get(i).getIdAlojamento().getIdEntidade() == entidade.getIdEntidade()) {
                    rooms.add(Repository.quartos.get(i));
                }
            }

        camas.setCellValueFactory(new PropertyValueFactory<>("quantidadeCamas"));

        tabela.setItems(rooms);
    }

    public static void setEntidade(Entidade entidade) {
        VerAlojamentoController.entidade = entidade;
    }

}
