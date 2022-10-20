package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

public class CadMarca implements Initializable {
    public TextField tfCodigo, tfDesc;
    public Button btConfirmar, btCancelar;

    //método executado quando a janela é criada.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCodigo.setText("0");
        tfDesc.setText(TabelaMarca.aux.getDescricao());

        Platform.runLater(
                () -> {
                    tfDesc.requestFocus();
                }
        );
    }

    public void onActionConfirmar(ActionEvent actionEvent) {
        if(!tfDesc.getText().isEmpty()){

            Singleton.ListaMarcas.add(
                    new Marca(Integer.parseInt(tfCodigo.getText()), tfDesc.getText())
            );

            onActionCancelar(actionEvent);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Informação obrigatória");
            alert.setContentText("A descrição não pode estar em branco.");

            alert.showAndWait();
        }
    }

    public void onActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
