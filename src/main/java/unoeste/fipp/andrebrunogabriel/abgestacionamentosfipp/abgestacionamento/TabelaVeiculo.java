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
import javafx.stage.StageStyle;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.ModeloDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.ProprietarioDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.VeiculoDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Modelo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Proprietario;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Veiculo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes.MaskFieldUtil;

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
        CarregarTabela();
    }

    public void moveScreen(Scene scene, Stage stage)
    {
        scene.setOnMousePressed(pressEvent -> {
            scene.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    public void onActionNovoVeiculo(ActionEvent actionEvent) throws Exception{
        aux = new Veiculo();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadVeiculo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
        Stage stage = new Stage();

        moveScreen(scene,stage);

        stage.setTitle("Novo Veículo");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();
    }

    private void CarregarTabela(){
        if (!tfFiltro.getText().isEmpty()){
            Tabela.setItems(FXCollections.observableArrayList(new VeiculoDAL().Select(tfFiltro.getText())));
        }
        else {
            Tabela.setItems(FXCollections.observableArrayList(new VeiculoDAL().Select()));
        }
    }


    public void onActionAlterar(ActionEvent actionEvent) throws Exception {

        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadVeiculo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());

        Stage stage = new Stage();

        moveScreen(scene,stage);

        stage.setTitle("Alterar Veículo");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();
    }

    public void onActionApagar(ActionEvent actionEvent) {
        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
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
        }
    }
}
