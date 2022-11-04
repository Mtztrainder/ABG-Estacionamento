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
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Valores;

import java.net.URL;
import java.util.ResourceBundle;

public class TabelaValores implements Initializable {
    public TextField tfFiltro;
    public TableView <Valores>Tabela;

    public TableColumn <Valores, Integer>colCodigo;
    public TableColumn <Valores, Integer>colCarencia;
    public TableColumn <Valores, Double>colValorHora;
    public TableColumn <Valores, Double>colValorHoraExtra;


    public static Valores aux = new Valores();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<Valores, Integer>("Id"));
        colCarencia.setCellValueFactory(new PropertyValueFactory<Valores, Integer>("Carencia"));
        colValorHora.setCellValueFactory(new PropertyValueFactory<Valores, Double>("ValorHora"));
        colValorHoraExtra.setCellValueFactory(new PropertyValueFactory<Valores, Double>("ValorHoraExtra"));

        CarregarTabela();
    }

    public void onKeyTyped(KeyEvent keyEvent) {
    }

    public void onActionNovoValor(ActionEvent actionEvent) throws Exception{
        aux = new Valores();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadValores.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Nova Valores");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();
    }

    private void CarregarTabela(){
        Tabela.setItems(FXCollections.observableArrayList(Singleton.ListaValores));
    }



    public void onActionAlterar(ActionEvent actionEvent) throws Exception {

        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadValores.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Alterar Valores");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();

        CarregarTabela();
    }

    public void onActionApagar(ActionEvent actionEvent) {
        Singleton.ListaValores.remove(Tabela.getSelectionModel().getSelectedItem());
        CarregarTabela();
    }
}
