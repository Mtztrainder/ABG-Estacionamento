package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import org.json.JSONObject;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.ModeloDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.ProprietarioDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Proprietario;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes.MaskFieldUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;



public class CadProprietario implements Initializable {
    public TextField tfCodigo, tfNome, tfCPF, tfEmail, tfTelefone, tfCEP,
            tfLogradouro, tfNumero, tfBairro, tfCidade, tfEstado;
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
        tfCodigo.setText(String.valueOf(TabelaProprietario.aux.getId()));
        tfNome.setText(TabelaProprietario.aux.getNome());
        tfCPF.setText(TabelaProprietario.aux.getCPF());

        MaskFieldUtil.cpfField(this.tfCPF);

        tfEmail.setText(TabelaProprietario.aux.getEmail());
        tfTelefone.setText(String.valueOf(TabelaProprietario.aux.getTelefone()));
        tfCEP.setText(String.valueOf(TabelaProprietario.aux.getCEP()));

        MaskFieldUtil.cepField(this.tfCEP);

        tfLogradouro.setText(TabelaProprietario.aux.getLogradouro());
        tfNumero.setText(String.valueOf(TabelaProprietario.aux.getNumero()));
        tfBairro.setText(TabelaProprietario.aux.getBairro());
        tfCidade.setText(TabelaProprietario.aux.getCidade());
        tfEstado.setText(TabelaProprietario.aux.getEstado());
        tfCEP.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue && tfCEP.getText().replace("-", "").length() == 8) {
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
        Proprietario p = new Proprietario(tfCPF.getText(),
                        tfNome.getText(),
                        tfEmail.getText(),
                        Integer.parseInt(tfCEP.getText().replace("-", "")),
                        tfEstado.getText(),
                        tfCidade.getText(),
                        tfBairro.getText(),
                        tfLogradouro.getText(),
                        tfNumero.getText(),
                        tfTelefone.getText()
                        );


        if(tfCodigo.getText().equals("0"))
        {
            if(!new ProprietarioDAL().inserir(p))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro ao gravar: " + Banco.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }else{
            p.setId(Integer.parseInt(tfCodigo.getText()));
            if(!new ProprietarioDAL().alterar(p))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro ao gravar: " + Banco.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }


        tfNome.setText("");
        tfCPF.setText("");
        tfEmail.setText("");
        tfTelefone.setText("");
        tfCEP.setText("");
        tfLogradouro.setText("");
        tfNumero.setText("");
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
