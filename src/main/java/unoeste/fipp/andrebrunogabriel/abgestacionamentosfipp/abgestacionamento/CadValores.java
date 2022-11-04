package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CadValores implements Initializable {
    public TextField tfCodigo, tfCarencia, tfValorHora, tfValorHoraExtra;

    public Button btConfirmar, btCancelar;

    //método executado quando a janela é criada.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCodigo.setText("0");
        tfValorHora.setText(String.valueOf(TabelaValores.aux.getValorHora()));
        tfCarencia.setText(String.valueOf(TabelaValores.aux.getCarencia()));
        tfValorHoraExtra.setText(String.valueOf(TabelaValores.aux.getValorHoraExtra()));

        Platform.runLater(
                () -> {
                    tfValorHora.requestFocus();
                }
        );
    }

    public void onActionConfirmar(ActionEvent actionEvent) {
        //if(!tfDesc.getText().isEmpty()){

            Singleton.ListaValores.add(
                    new Valores(Integer.parseInt(tfCodigo.getText()), Integer.parseInt(tfCarencia.getText()), Double.parseDouble(tfValorHora.getText()), Double.parseDouble(tfValorHoraExtra.getText()))
            );

            onActionCancelar(actionEvent);
        /*}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Informação obrigatória");
            alert.setContentText("A descrição não pode estar em branco.");

            alert.showAndWait();
        }*/
    }

    public void onActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
