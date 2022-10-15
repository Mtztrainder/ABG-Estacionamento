package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

public class CadProprietario {
    public TextField tfCodigo,tfNome, tfCPF, tfEmail, tfTelefone, tfCEP,
            tfLogradouro, tfNumero, tfComplemento, tfBairro, tfCidade, tfEstado;
    public Button btConfirmar, btCancelar;


    public void onActionConfirmar(ActionEvent actionEvent) {
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
