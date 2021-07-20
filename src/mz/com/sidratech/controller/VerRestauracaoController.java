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
import mz.com.sidratech.model.bean.Entidade;
import mz.com.sidratech.model.bean.Prato;
import mz.com.sidratech.model.bean.Restauracao;
import mz.com.sidratech.model.dao.DaoGenerico;
import mz.com.sidratech.repository.Repository;

/**
 * FXML Controller class
 *
 * @author celso
 */
public class VerRestauracaoController implements Initializable {

    @FXML
    private Label nomeEnt;
    @FXML
    private TableView<Prato> tabela;
    @FXML
    private TableColumn<Prato, String> refeicao;
    @FXML
    private TableColumn<Prato, Double> preco;
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
    @FXML
    private Label totalMesas;
    @FXML
    private Label totalCadeiras;
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
        repository.getPratos();

        int subTotal = 0;
        int subTotalH = 0;
        int subTotalM = 0;
        
        Restauracao restauracao=new Restauracao();
        restauracao= (Restauracao) new DaoGenerico().readById(entidade.getIdEntidade(), restauracao);
        
        totalMesas.setText(""+restauracao.getTotalMesas());
        totalCadeiras.setText(""+restauracao.getTotalCadeiras());

        for (int i = 0; i < Repository.funcionarios.size(); i++) {
            if (Repository.funcionarios.get(i).getIdEntidade().getIdEntidade() == entidade.getIdEntidade()) {
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

        ObservableList<Prato> pratos = FXCollections.observableArrayList();

        for (int i = 0; i < Repository.pratos.size(); i++) {
            if (Repository.pratos.get(i).getIdRestauracao().getIdEntidade() == entidade.getIdEntidade()) {
                    pratos.add(Repository.pratos.get(i));
            }
        }
        
        refeicao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        preco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        tabela.setItems(pratos);

    }

    public static void setEntidade(Entidade entidade) {
        VerRestauracaoController.entidade = entidade;
    }
}
