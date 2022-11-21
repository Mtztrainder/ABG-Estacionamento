package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.AcessoDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.VeiculoDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Veiculo;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CadAcesso implements Initializable {
    public TextField tfCodigo;
    public ComboBox <Veiculo> cbPlaca;
    public Button btConfirmar, btCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCodigo.setText(String.valueOf(TabelaAcesso.aux.getId()));
        cbPlaca.setItems(FXCollections.observableArrayList(new VeiculoDAL().SelectNaoEstacionados()));

        Platform.runLater(() ->{
            cbPlaca.requestFocus();
        });
    }


    public void onActionConfirmar(ActionEvent actionEvent) {
        if (cbPlaca.getValue() != null)
        {
            if (!new AcessoDAL().RegistrarEntrada(cbPlaca.getValue(), LocalDateTime.now())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro: "+ Banco.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Placa deve ser informada!");
            alert.showAndWait();
        }
    }

    public void onActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

}
