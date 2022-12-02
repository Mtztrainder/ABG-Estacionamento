package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.MarcaDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadMarca implements Initializable {
    public TextField tfCodigo, tfDesc;
    public Button btConfirmar, btCancelar;
    public Label lblClose;
    public Label lblTitulo;


    //método executado quando a janela é criada.
    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        tfCodigo.setText(String.valueOf(TabelaMarca.aux.getId()));
        tfDesc.setText(TabelaMarca.aux.getDescricao());

        Platform.runLater(
                () -> {
                    tfDesc.requestFocus();
                }
        );
        if(TabelaMarca.aux.getId() != 0)
            lblTitulo.setText(lblTitulo.getText().replace("Cadastrar", "Alterar"));

    }

    public void onActionConfirmar(ActionEvent actionEvent) {
        Marca mc = new Marca(tfDesc.getText());
        if(tfCodigo.getText().equals("0")){
            if(!tfDesc.getText().isEmpty())
            {
                if(!new MarcaDAL().inserir(mc))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro ao gravar: " + Banco.getConexao().getMensagemErro());
                    alert.showAndWait();
                }
                onActionCancelar(actionEvent);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Informação obrigatória");
                alert.setContentText("A descrição não pode estar em branco.");
                alert.showAndWait();
            }
        }
        else {
            mc.setId(Integer.parseInt(tfCodigo.getText()));
            if(!tfDesc.getText().isEmpty())
            {
                if(!new MarcaDAL().alterar(mc))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Erro");
                    alert.setContentText("Erro ao gravar: " + Banco.getConexao().getMensagemErro());
                    alert.showAndWait();
                }
                onActionCancelar(actionEvent);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Informação obrigatória");
                alert.setContentText("A descrição não pode estar em branco.");
                alert.showAndWait();
            }
        }

    }

    public void onActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onActionX(MouseEvent mouseEvent) {
        ((Node) mouseEvent.getSource()).getScene().getWindow().hide();
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)){
            ((Node) keyEvent.getSource()).getScene().getWindow().hide();
        }
    }
}
