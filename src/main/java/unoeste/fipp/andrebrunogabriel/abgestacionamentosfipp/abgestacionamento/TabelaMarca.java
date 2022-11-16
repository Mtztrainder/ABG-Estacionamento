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
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

public class TabelaMarca implements Initializable {
    public TextField tfFiltro;
    public TableView <Marca>Tabela;
    public TableColumn <Marca, Integer>colCodigo;
    public TableColumn <Marca, String>colDescricao;

    public static Marca aux = new Marca();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<Marca, Integer>("Id"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<Marca, String>("Descricao"));
        CarregarTabela();
    }

    public void onKeyTyped(KeyEvent keyEvent) {
        Tabela.setItems(FXCollections.observableArrayList(new MarcaDAL().Select(tfFiltro.getText())));
    }

    public void onActionNovaMarca(ActionEvent actionEvent) throws Exception{
        aux = new Marca();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadMarca.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Nova Marca");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();
    }

    private void CarregarTabela(){

        Tabela.setItems(FXCollections.observableArrayList(new MarcaDAL().Select()));
    }



    public void onActionAlterar(ActionEvent actionEvent) throws Exception {

        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadMarca.fxml"));
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
        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja apagar a marca " + aux.getDescricao());

        if (alert.showAndWait().get() == ButtonType.OK){

            if (!new MarcaDAL().dependentes(aux.getId()))
            {
                new MarcaDAL().deletar(aux.getId());
                CarregarTabela();
            }
            else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Erro");
                alert.setContentText("Não foi possível remover! A marca "+aux.getDescricao()+" possui dependentes.");
                alert.showAndWait();
            }
        }
    }
}
