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
    public TableColumn <Proprietario, String>colCidade;
    public TableColumn <Proprietario, String>colEstado;

    public static Proprietario aux = new Proprietario();


    public void moveScreen(Scene scene, Stage stage)
    {
        scene.setOnMousePressed(pressEvent -> {
            scene.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<Proprietario, Integer>("Id"));
        colCPF.setCellValueFactory(new PropertyValueFactory<Proprietario, String>("CPF"));
        colNome.setCellValueFactory(new PropertyValueFactory<Proprietario, String>("Nome"));
        colCidade.setCellValueFactory(new PropertyValueFactory<Proprietario, String>("Cidade"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Proprietario, String>("Estado"));


        CarregarTabela();
    }

    public void onKeyTyped(KeyEvent keyEvent) {
        CarregarTabela();
        Tabela.setItems(FXCollections.observableArrayList(new ProprietarioDAL().Select(tfFiltro.getText())));
    }

    public void onActionNovoProprietario(ActionEvent actionEvent) throws Exception{
        aux = new Proprietario();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadProprietario.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        moveScreen(scene, stage);
        stage.setTitle("Novo Proprietario");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();
    }

    private void CarregarTabela(){
        if (!tfFiltro.getText().isEmpty()){
            Tabela.setItems(FXCollections.observableArrayList(new ProprietarioDAL().Select(tfFiltro.getText())));
        }
        else {
            Tabela.setItems(FXCollections.observableArrayList(new ProprietarioDAL().Select()));
        }
    }


    public void onActionAlterar(ActionEvent actionEvent) throws Exception {

        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadProprietario.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Alterar Proprietario");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);


        stage.showAndWait();

        CarregarTabela();
    }

    public void onActionApagar(ActionEvent actionEvent) {
        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja apagar o proprietário " + aux.getNome());

        if (alert.showAndWait().get() == ButtonType.OK){

            if (!new ProprietarioDAL().dependentes(aux.getId()))
            {
                new ProprietarioDAL().deletar(aux.getId());
                CarregarTabela();
            }
            else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Erro");
                alert.setContentText("Não foi possível remover! O proprietário "+aux.getNome()+" possui dependentes.");
                alert.showAndWait();
            }
        }
    }
}
