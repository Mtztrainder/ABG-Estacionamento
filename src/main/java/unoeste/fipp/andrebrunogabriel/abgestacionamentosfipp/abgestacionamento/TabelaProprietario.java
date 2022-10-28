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
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Modelo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Proprietario;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

public class TabelaProprietario implements Initializable {
    public TextField tfFiltro;
    public TableView <Proprietario>Tabela;
    public TableColumn <Proprietario, Integer>colCodigo;
    public TableColumn <Proprietario, String>colCPF;
    public TableColumn <Proprietario, String>colNome;

    public static Proprietario aux = new Proprietario();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<Proprietario, Integer>("Id"));
        colCPF.setCellValueFactory(new PropertyValueFactory<Proprietario, String>("CPF"));
        colNome.setCellValueFactory(new PropertyValueFactory<Proprietario, String>("Nome"));
        CarregarTabela();
    }

    public void onKeyTyped(KeyEvent keyEvent) {
    }

    public void AbrirCadastro(String Titulo){

    }

    public void onActionNovoModelo(ActionEvent actionEvent) throws Exception{
        aux = new Proprietario();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadProprietario.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Novo Proprietario");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();
    }

    private void CarregarTabela(){
        Tabela.setItems(FXCollections.observableArrayList(Singleton.ListaProprietario));
    }



    public void onActionAlterar(ActionEvent actionEvent) throws Exception {

        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadModelo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Alterar Marca");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);


        stage.showAndWait();

        CarregarTabela();
    }

    public void onActionApagar(ActionEvent actionEvent) {
        Singleton.ListaModelos.remove(Tabela.getSelectionModel().getSelectedItem());
        CarregarTabela();
    }
}
