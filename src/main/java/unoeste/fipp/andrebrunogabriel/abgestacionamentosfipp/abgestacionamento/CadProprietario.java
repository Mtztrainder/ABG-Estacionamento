package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Proprietario;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

public class CadProprietario implements Initializable {
    public TextField tfCodigo, tfNome, tfCPF, tfEmail, tfTelefone, tfCEP,
            tfLogradouro, tfNumero, tfComplemento, tfBairro, tfCidade, tfEstado;
    public Button btConfirmar, btCancelar;

    //método executado quando a janela é criada.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCodigo.setText("0");
        tfNome.setText(TabelaProprietario.aux.getNome());
        tfCPF.setText(TabelaProprietario.aux.getCPF());
        tfEmail.setText(TabelaProprietario.aux.getEmail());
        tfTelefone.setText(String.valueOf(TabelaProprietario.aux.getTelefone()));
        tfCEP.setText(TabelaProprietario.aux.getCEP());
        tfLogradouro.setText(TabelaProprietario.aux.getLogradouro());
        tfNumero.setText(String.valueOf(TabelaProprietario.aux.getNumero()));
        tfComplemento.setText(TabelaProprietario.aux.getComplemento());
        tfBairro.setText(TabelaProprietario.aux.getBairro());
        tfCidade.setText(TabelaProprietario.aux.getCidade());
        tfEstado.setText(TabelaProprietario.aux.getEstado());

        Platform.runLater(
                () -> {
                    tfNome.requestFocus();
                }
        );
    }


    public void onActionConfirmar(ActionEvent actionEvent) {

        Singleton.ListaProprietario.add(
                new Proprietario(Integer.parseInt(tfCodigo.getText()),
                        tfCPF.getText(),
                        tfNome.getText(),
                        tfEmail.getText(),
                        tfCEP.getText(),
                        tfEstado.getText(),
                        tfCidade.getText(),
                        tfBairro.getText(),
                        tfLogradouro.getText(),
                        Integer.parseInt(tfNumero.getText()),
                        tfComplemento.getText()
                        )
        );

        /*if(!tfDesc.getText().isEmpty() && cbMarca.getValue() != null){

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
        }*/


        tfNome.setText("");
        tfCPF.setText("");
        tfEmail.setText("");
        tfTelefone.setText("");
        tfCEP.setText("");
        tfLogradouro.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        tfBairro.setText("");
        tfCidade.setText("");
        tfEstado.setText("");
    }

    public void onActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onCEPChanged(InputMethodEvent inputMethodEvent) {
        if (!tfCEP.getText().isEmpty()){

        }
    }
}
