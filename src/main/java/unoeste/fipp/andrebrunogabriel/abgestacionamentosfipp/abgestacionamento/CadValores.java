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

        MaskFieldUtil.onlyDigitsValue(this.tfCarencia);

        Platform.runLater(
                () -> {
                    tfValorHora.requestFocus();
                }
        );
    }

    public void onActionConfirmar(ActionEvent actionEvent) {
        if (!tfValorHora.getText().isEmpty() &&
            !tfValorHoraAdicional.getText().isEmpty() &&
            !tfCarencia.getText().isEmpty()
        ){
            conf.setValorHora(Double.parseDouble(tfValorHora.getText()));
            conf.setValorAdic(Double.parseDouble(tfValorHoraAdicional.getText()));
            conf.setCarencia(Integer.parseInt(tfCarencia.getText()));

            if(!new ConfDAL().altera(conf))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro ao gravar: " + Banco.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        else
        {

        }
        onActionCancelar(actionEvent);
    }

    public void onActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
