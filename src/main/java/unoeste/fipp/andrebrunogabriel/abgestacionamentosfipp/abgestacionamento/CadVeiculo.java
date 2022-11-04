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
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CadVeiculo implements Initializable {
    public TextField tfCodigo, tfPlaca;

    public ComboBox <Modelo>cbModelo;
    public ComboBox <Proprietario>cbProprietario;
    public ComboBox <String>cbCor;

    public Button btConfirmar, btCancelar;

    //método executado quando a janela é criada.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCodigo.setText("0");
        tfPlaca.setText(TabelaVeiculo.aux.getPlaca());
        cbModelo.setItems(FXCollections.observableArrayList(Singleton.ListaModelos));
        cbProprietario.setItems(FXCollections.observableArrayList(Singleton.ListaProprietario));
        cbCor.setItems(FXCollections.observableArrayList(Singleton.ListaCores));

        Platform.runLater(
                () -> {
                    tfPlaca.requestFocus();
                }
        );
    }

    public void onActionConfirmar(ActionEvent actionEvent) {
        //if(!tfDesc.getText().isEmpty()){

            Singleton.ListaVeiculo.add(
                    new Veiculo(Integer.parseInt(tfCodigo.getText()), tfPlaca.getText(), cbCor.getValue(),  cbModelo.getValue(), cbProprietario.getValue())
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
