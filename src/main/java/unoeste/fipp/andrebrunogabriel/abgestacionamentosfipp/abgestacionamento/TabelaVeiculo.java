package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Modelo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Proprietario;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Veiculo;

import java.net.URL;
import java.util.ResourceBundle;

public class TabelaVeiculo implements Initializable {
    public TextField tfFiltro;
    public TableView <Veiculo>Tabela;
    public TableColumn <Veiculo, Integer>colCodigo;
    public TableColumn <Veiculo, String>colPlaca;
    public TableColumn <Veiculo, String>colCor;
    public TableColumn <Veiculo, Modelo>colModelo;
    public TableColumn <Veiculo, Proprietario>colProprietario;

    public static Veiculo aux = new Veiculo();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<Veiculo, Integer>("Id"));
        colPlaca.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("Placa"));
        colCor.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("Cor"));
        colModelo.setCellValueFactory(new PropertyValueFactory<Veiculo, Modelo>("Modelo"));
        colProprietario.setCellValueFactory(new PropertyValueFactory<Veiculo, Proprietario>("Proprietario"));

        CarregarTabela();
    }

    public void onKeyTyped(KeyEvent keyEvent) {
    }

    public void AbrirCadastro(String Titulo){

    }

    public void onActionNovoVeiculo(ActionEvent actionEvent) throws Exception{
        aux = new Veiculo();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadVeiculo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Novo Veículo");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();
    }

    private void CarregarTabela(){
        Tabela.setItems(FXCollections.observableArrayList(Singleton.ListaVeiculo));
    }



    public void onActionAlterar(ActionEvent actionEvent) throws Exception {

        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadVeiculo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Alterar Veículo");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);


        stage.showAndWait();

        CarregarTabela();
    }

    public void onActionApagar(ActionEvent actionEvent) {
        Singleton.ListaVeiculo.remove(Tabela.getSelectionModel().getSelectedItem());
        CarregarTabela();
    }
}
