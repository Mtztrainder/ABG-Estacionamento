package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import org.json.JSONObject;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Proprietario;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;



public class CadProprietario implements Initializable {
    public TextField tfCodigo, tfNome, tfCPF, tfEmail, tfTelefone, tfCEP,
            tfLogradouro, tfNumero, tfComplemento, tfBairro, tfCidade, tfEstado;
    public Button btConfirmar, btCancelar;

    //Método Consumir Api Via CEP
    public static String consultaCep(String cep)
    {
        StringBuilder dados = new StringBuilder();
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
            URLConnection con = url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setAllowUserInteraction(false);
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = "";
            while (null != (s = br.readLine()))
                dados.append(s);
            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dados.toString();
    }

    //método executado quando a janela é criada.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCodigo.setText("0");
        tfNome.setText(TabelaProprietario.aux.getNome());
        tfCPF.setText(TabelaProprietario.aux.getCPF());
        tfEmail.setText(TabelaProprietario.aux.getEmail());
        tfTelefone.setText(String.valueOf(TabelaProprietario.aux.getTelefone()));
        tfCEP.setText(String.valueOf(TabelaProprietario.aux.getCEP()));
        tfLogradouro.setText(TabelaProprietario.aux.getLogradouro());
        tfNumero.setText(String.valueOf(TabelaProprietario.aux.getNumero()));
        tfComplemento.setText(TabelaProprietario.aux.getComplemento());
        tfBairro.setText(TabelaProprietario.aux.getBairro());
        tfCidade.setText(TabelaProprietario.aux.getCidade());
        tfEstado.setText(TabelaProprietario.aux.getEstado());
        tfCEP.focusedProperty().addListener(new ChangeListener<Boolean>() {             @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    System.out.println("Textfield on focus");
                } else {

                    String json_str = consultaCep(tfCEP.getText());
                    JSONObject my_obj = new JSONObject(json_str);
                    tfCidade.setText(my_obj.getString("localidade"));
                    tfBairro.setText(my_obj.getString("bairro"));
                    tfLogradouro.setText(my_obj.getString("logradouro"));
                    tfEstado.setText(my_obj.getString("uf"));
                }
            }
        });
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
                        Integer.parseInt(tfCEP.getText()),
                        tfEstado.getText(),
                        tfCidade.getText(),
                        tfBairro.getText(),
                        tfLogradouro.getText(),
                        tfNumero.getText(),
                        tfComplemento.getText(),
                        tfTelefone.getText()
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
