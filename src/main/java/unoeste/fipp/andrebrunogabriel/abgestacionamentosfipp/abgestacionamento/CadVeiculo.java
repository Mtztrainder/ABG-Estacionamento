package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.ModeloDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.ProprietarioDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.VeiculoDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.*;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes.MaskFieldUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class CadVeiculo implements Initializable {
    public TextField tfCodigo, tfPlaca;

    public ComboBox <Modelo>cbModelo;
    public ComboBox <Proprietario>cbProprietario;
    public ComboBox <String>cbCor;

    public Button btConfirmar, btCancelar;
    public Label lblClose, lblTitulo;

    //método executado quando a janela é criada.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCodigo.setText(String.valueOf(TabelaVeiculo.aux.getId()));
        tfPlaca.setText(TabelaVeiculo.aux.getPlaca());

        cbModelo.setItems(FXCollections.observableArrayList(new ModeloDAL().Select()));
        cbProprietario.setItems(FXCollections.observableArrayList(new ProprietarioDAL().Select()));
        cbCor.setItems(FXCollections.observableArrayList(Singleton.ListaCores));

        MaskFieldUtil.placaVeiculofield(tfPlaca);

        Platform.runLater(
                () -> {
                    if (!tfCodigo.getText().equals("0")) {
                        cbModelo.setValue(TabelaVeiculo.aux.getModelo());
                        cbProprietario.setValue(TabelaVeiculo.aux.getProprietario());
                        if (!TabelaVeiculo.aux.getCor().isEmpty())
                            cbCor.setValue(TabelaVeiculo.aux.getCor());
                    }

                    tfPlaca.requestFocus();
                }
        );

        if(TabelaVeiculo.aux.getId() != 0)
            lblTitulo.setText(lblTitulo.getText().replace("Cadastrar", "Alterar"));
    }

    private void AlertaObrigatoriedade(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Informação obrigatória");

        if (tfPlaca.getText().isEmpty()) {
            alert.setContentText("A Placa não pode estar em branco.");
        }
        else
        {
            if (!MaskFieldUtil.PlacaValida(tfPlaca))
                alert.setContentText("Placa inválida.");
            else
                if (cbModelo.getValue() == null)
                    alert.setContentText("O Modelo não pode estar em branco.");
                else
                    if (cbCor.getValue() == null)
                        alert.setContentText("A Cor não pode estar em branco.");
                    else
                        if (cbProprietario.getValue() == null)
                            alert.setContentText("O Proprietário não pode estar em branco.");

        }
        alert.showAndWait();
    }

    public void onActionConfirmar(ActionEvent actionEvent) {
        //if(!tfDesc.getText().isEmpty()){

        Veiculo v = new Veiculo(tfPlaca.getText(),
                                cbCor.getValue(),
                                cbModelo.getValue(),
                                cbProprietario.getValue()
        );

        if(tfCodigo.getText().equals("0"))
        {
            if(!tfPlaca.getText().isEmpty() &&
                    MaskFieldUtil.PlacaValida(tfPlaca) &&
                    cbCor.getValue() != null &&
                    cbProprietario.getValue() != null &&
                    cbModelo.getValue() != null)
            {
                if(!new VeiculoDAL().inserir(v))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro ao gravar: " + Banco.getConexao().getMensagemErro());
                    alert.showAndWait();
                }

                onActionCancelar(actionEvent);
            }
            else{
                AlertaObrigatoriedade();
            }
        }else{
            v.setId(Integer.parseInt(tfCodigo.getText()));

            if(!tfPlaca.getText().isEmpty() &&
                MaskFieldUtil.PlacaValida(tfPlaca)    &&
                cbCor.getValue() != null &&
                cbProprietario.getValue() != null &&
                cbModelo.getValue() != null)
            {
                if(!new VeiculoDAL().alterar(v))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro ao gravar: " + Banco.getConexao().getMensagemErro());
                    alert.showAndWait();
                }
                onActionCancelar(actionEvent);
            }
            else{
                AlertaObrigatoriedade();
            }
        }
    }

    public void onActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)){
            ((Node) keyEvent.getSource()).getScene().getWindow().hide();
        }
    }
    public void onActionX(MouseEvent mouseEvent) {
        ((Node) mouseEvent.getSource()).getScene().getWindow().hide();
    }
}
