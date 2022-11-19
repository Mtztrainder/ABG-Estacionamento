package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.AcessoDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.VeiculoDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Acesso;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Modelo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Proprietario;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Veiculo;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class TabelaAcesso implements Initializable {
    public TextField tfFiltro;
    public TableView <Acesso>Tabela;
    public TableColumn <Acesso, Integer>colCodigo;
    public TableColumn <Acesso, Veiculo>colPlaca;
    public TableColumn <Acesso, Veiculo>colCor;
    public TableColumn <Acesso, Veiculo>colModelo;
    public TableColumn <Acesso, Veiculo>colProprietario;
    public TableColumn <Acesso, LocalDateTime>colEntrada;
    public TableColumn <Acesso, LocalDateTime>colSaida;
    public TableColumn <Acesso, Double>colValor;



    public static Acesso aux = new Acesso();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<Acesso, Integer>("Id"));
        colEntrada.setCellValueFactory(new PropertyValueFactory<Acesso, LocalDateTime>("Placa"));
        colSaida.setCellValueFactory(new PropertyValueFactory<Acesso, LocalDateTime>("Placa"));
        colValor.setCellValueFactory(new PropertyValueFactory<Acesso, Double>("Placa"));

        /*colPlaca.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("Placa"));
        colCor.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("Cor"));
        colModelo.setCellValueFactory(new PropertyValueFactory<Veiculo, Modelo>("Modelo"));
        colProprietario.setCellValueFactory(new PropertyValueFactory<Veiculo, Proprietario>("Proprietario"));
*/
        CarregarTabela();
    }

    public void onKeyTyped(KeyEvent keyEvent) {
        //Tabela.setItems(FXCollections.observableArrayList(new AcessoDAL().Select(tfFiltro.getText())));
    }

    public void onActionNovaEntrada(ActionEvent actionEvent) {
        /*aux = new Acesso();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadVeiculo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Novo Veículo");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();*/
    }

    private void CarregarTabela(){
        //Tabela.setItems(FXCollections.observableArrayList(new VeiculoDAL().Select()));
    }



    public void onActionAlterar(ActionEvent actionEvent) throws Exception {
/*
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

        CarregarTabela();*/
    }

    public void onActionApagar(ActionEvent actionEvent) {
        /*if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();



        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja apagar o veículo " + aux.getPlaca());

        if (alert.showAndWait().get() == ButtonType.OK){

            if (!new VeiculoDAL().dependentes(aux.getId()))
            {
                new VeiculoDAL().deletar(aux.getId());
                CarregarTabela();
            }
            else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Erro");
                alert.setContentText("Não foi possível remover! O veículo "+aux.getPlaca()+" possui dependentes.");
                alert.showAndWait();
            }
        }*/
    }
}
