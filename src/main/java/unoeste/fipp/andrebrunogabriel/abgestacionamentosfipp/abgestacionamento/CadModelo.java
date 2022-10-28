package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Modelo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

public class CadModelo implements Initializable {
    public TextField tfCodigo, tfDesc;
    public Button btConfirmar, btCancelar;
    public ComboBox <Marca>cbMarca;

    //método executado quando a janela é criada.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCodigo.setText("0");
        tfDesc.setText(TabelaMarca.aux.getDescricao());
        cbMarca.setItems(FXCollections.observableArrayList(Singleton.ListaMarcas));

        Platform.runLater(
                () -> {
                    tfDesc.requestFocus();
                }
        );
    }

    public void onActionConfirmar(ActionEvent actionEvent) {
        if(!tfDesc.getText().isEmpty() && cbMarca.getValue() != null){

            Singleton.ListaModelos.add(
                    new Modelo(Integer.parseInt(tfCodigo.getText()), tfDesc.getText(), cbMarca.getValue())
            );

            onActionCancelar(actionEvent);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Informação obrigatória");

            if (tfDesc.getText().isEmpty()) {
                alert.setContentText("A descrição não pode estar em branco.");
            }
            else
            {
                alert.setContentText("A marca não pode estar em branco.");
            }
            alert.showAndWait();
        }
    }

    public void onActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
