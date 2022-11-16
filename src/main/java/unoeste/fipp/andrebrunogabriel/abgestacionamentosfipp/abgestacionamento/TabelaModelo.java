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
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.MarcaDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.ModeloDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Modelo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

public class TabelaModelo implements Initializable {
    public TextField tfFiltro;
    public TableView <Modelo>Tabela;
    public TableColumn <Modelo, Integer>colCodigo;
    public TableColumn <Modelo, Marca>colMarca;
    public TableColumn <Modelo, String>colDescricao;

    public static Modelo aux = new Modelo();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<Modelo, Integer>("Id"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<Modelo, String>("Descricao"));
        colMarca.setCellValueFactory(new PropertyValueFactory<Modelo, Marca>("Marca"));
        CarregarTabela();
    }

    public void onKeyTyped(KeyEvent keyEvent) {
        Tabela.setItems(FXCollections.observableArrayList(new ModeloDAL().Select(tfFiltro.getText())));
    }

    public void AbrirCadastro(String Titulo){

    }

    public void onActionNovoModelo(ActionEvent actionEvent) throws Exception{
        aux = new Modelo();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadModelo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Novo Modelo");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();
    }

    private void CarregarTabela(){
        Tabela.setItems(FXCollections.observableArrayList(new ModeloDAL().Select()));
    }



    public void onActionAlterar(ActionEvent actionEvent) throws Exception {

        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadModelo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Alterar Modelo");
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
        alert.setContentText("Deseja apagar o modelo " + aux.getDescricao());

        if (alert.showAndWait().get() == ButtonType.OK){

            if (!new ModeloDAL().dependentes(aux.getId()))
            {
                new ModeloDAL().deletar(aux.getId());
                CarregarTabela();
            }
            else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Erro");
                alert.setContentText("Não foi possível remover! O modelo "+aux.getDescricao()+" possui dependentes.");
                alert.showAndWait();
            }
        }
    }
}
