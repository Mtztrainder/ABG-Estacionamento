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
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.ConfDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.ProprietarioDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.*;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes.MaskFieldUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class CadValores implements Initializable {
    public TextField tfCarencia, tfValorHora, tfValorHoraAdicional;

    public Button btConfirmar, btCancelar;

    private Conf conf;
    //método executado quando a janela é criada.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conf = new ConfDAL().Select();
        tfValorHora.setText(String.valueOf(conf.getValorHora()));
        tfCarencia.setText(String.valueOf(conf.getCarencia()));
        tfValorHoraAdicional.setText(String.valueOf(conf.getValorAdic()));

        Platform.runLater(
                () -> {
                    tfValorHora.requestFocus();

                    MaskFieldUtil.monetaryField(this.tfValorHora);
                    MaskFieldUtil.monetaryField(this.tfValorHoraAdicional);
                    MaskFieldUtil.onlyDigitsValue(this.tfCarencia);
                }
        );
    }

    public void onActionConfirmar(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (!tfValorHora.getText().isEmpty() &&
            !tfValorHoraAdicional.getText().isEmpty() &&
            !tfCarencia.getText().isEmpty()
        ){
            double ValorHora = MaskFieldUtil.monetaryValueFromField(tfValorHora).doubleValue(),
                   ValorAdic = MaskFieldUtil.monetaryValueFromField(tfValorHoraAdicional).doubleValue();
            int Carencia = Integer.parseInt(tfCarencia.getText());

            if (ValorHora >= 0 && ValorAdic >= 0 && Carencia >= 0){
                conf.setValorHora(ValorHora);
                conf.setValorAdic(ValorAdic);
                conf.setCarencia(Carencia);

                if(!new ConfDAL().altera(conf)) {

                    alert.setContentText("Erro ao gravar: " + Banco.getConexao().getMensagemErro());
                    alert.showAndWait();
                }else{
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Registro Atualizado com sucesso!");
                    alert.showAndWait();
                }
            }
            else{
                if (ValorHora < 0){
                    alert.setContentText("Valor Hora inválido!");
                }
                else{
                    if (ValorAdic < 0){
                        alert.setContentText("Valor Adicional inválido!");
                    }
                    else{
                        alert.setContentText("Carência inválida!");
                    }
                }

                alert.showAndWait();
            }
        }
        else
        {
            if (tfValorHora.getText().isEmpty()){
                alert.setContentText("Valor Hora inválido!");
            }
            else{
                if (tfValorHora.getText().isEmpty()){
                    alert.setContentText("Valor Adicional inválido!");
                }
                else{
                    alert.setContentText("Carência inválida!");
                }
            }

            alert.showAndWait();
        }
    }

    public void onActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
