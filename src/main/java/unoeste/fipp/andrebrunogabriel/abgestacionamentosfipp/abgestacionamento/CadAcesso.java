package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.AcessoDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.VeiculoDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Veiculo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes.MaskFieldUtil;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CadAcesso implements Initializable {
    public Label lbValor;
    public TextField tfCodigo, tfValor;
    public ComboBox <Veiculo> cbPlaca;
    public Button btConfirmar, btCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCodigo.setText(String.valueOf(TabelaAcesso.aux.getId()));
        cbPlaca.setItems(FXCollections.observableArrayList(new VeiculoDAL().SelectNaoEstacionados()));
        tfValor.setText(String.valueOf(TabelaAcesso.aux.getValor()).replace(".", ","));

        MaskFieldUtil.monetaryField(tfValor);

        if (!tfCodigo.getText().equals("0")){
            cbPlaca.setDisable(true);
        }
        else {
            tfValor.setVisible(false);
            lbValor.setVisible(false);
        }

        Platform.runLater(() ->{
            if (tfCodigo.getText().equals("0")) {
                cbPlaca.requestFocus();
            }
            else{
                tfValor.requestFocus();
            }
            cbPlaca.setValue(TabelaAcesso.aux.getVeiculo());
        });
    }


    public void onActionConfirmar(ActionEvent actionEvent) {
        if (tfCodigo.getText().equals("0")) {
            if (cbPlaca.getValue() != null) {
                if (!new AcessoDAL().RegistrarEntrada(cbPlaca.getValue(), LocalDateTime.now())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro: " + Banco.getConexao().getMensagemErro());
                    alert.showAndWait();
                }
                else{
                    onActionCancelar(actionEvent);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Placa deve ser informada!");
                alert.showAndWait();
            }
        }else{
            if (!tfValor.getText().isEmpty() && MaskFieldUtil.monetaryValueFromField(tfValor).doubleValue() >= 0) {
                if (!new AcessoDAL().RegistrarSaida(TabelaAcesso.aux.getId(), LocalDateTime.now(), MaskFieldUtil.monetaryValueFromField(tfValor).doubleValue())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro: " + Banco.getConexao().getMensagemErro());
                    alert.showAndWait();
                }
                else{
                    onActionCancelar(actionEvent);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Valor inválido!");
                alert.showAndWait();
            }
        }
    }

    public void onActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)){
            ((Node) keyEvent.getSource()).getScene().getWindow().hide();
        }
    }
}
