package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.AcessoDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal.VeiculoDAL;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Acesso;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Modelo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Proprietario;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Veiculo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes.MaskFieldUtil;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TabelaAcesso implements Initializable {
    public TextField tfFiltro;
    public TableView <Acesso>Tabela;
    public TableColumn <Acesso, Integer>colCodigo;
    public TableColumn <Acesso, Veiculo>colPlaca;
    public TableColumn <Acesso, LocalDateTime>colEntrada;
    public TableColumn <Acesso, LocalDateTime>colSaida;
    public TableColumn <Acesso, Double>colValor;
    public Button btExibicao;
    public DatePicker dpData;

    private int VisualizaTodos;
    public static Acesso aux = new Acesso();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<Acesso, Integer>("Id"));
        colPlaca.setCellValueFactory(new PropertyValueFactory<Acesso, Veiculo>("Veiculo"));
        colEntrada.setCellValueFactory(new PropertyValueFactory<Acesso, LocalDateTime>("HoraDeEntrada"));
        colSaida.setCellValueFactory(new PropertyValueFactory<Acesso, LocalDateTime>("HoraDeSaida"));
        colValor.setCellValueFactory(new PropertyValueFactory<Acesso, Double>("Valor"));

        MaskFieldUtil.placaVeiculofield(tfFiltro);

        VisualizaTodos = 0;
        btExibicao.setText("Exibir Todos");
        dpData.setValue(LocalDate.now());
        dpData.setVisible(false);

        CarregarTabela();
    }

    public void onKeyTyped(KeyEvent keyEvent) {
        CarregarTabela();
    }

    public void onChangeData(){
        CarregarTabela();
    }

    public void onActionNovaEntrada(ActionEvent actionEvent) throws IOException {
        aux = new Acesso();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadAcesso.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Registrar Entrada");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();
    }

    private void CarregarTabela(){
        LocalDate data = dpData.getValue();
        if (data == null)
            data = LocalDate.now();

        if (!tfFiltro.getText().isEmpty()){
            if (VisualizaTodos == 1)
                Tabela.setItems(FXCollections.observableArrayList(new AcessoDAL().Select("where date_trunc('day', COALESCE(AC_HORASAIDA, CURRENT_TIMESTAMP)) = '"+data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"' " +
                                                                                               "and lower(vei_placa) LIKE ('"+tfFiltro.getText().toLowerCase()+"%') ")));
            else
                Tabela.setItems(FXCollections.observableArrayList(new AcessoDAL().Select("where ac_horasaida is null and lower(vei_placa) LIKE ('"+tfFiltro.getText().toLowerCase()+"%')")));
        }
        else {
            if (VisualizaTodos == 1)
                Tabela.setItems(FXCollections.observableArrayList(new AcessoDAL().Select("where date_trunc('day', COALESCE(AC_HORASAIDA, CURRENT_TIMESTAMP)) = '"+data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"' ")));
            else
                Tabela.setItems(FXCollections.observableArrayList(new AcessoDAL().Select("where ac_horasaida is null")));
        }
    }



    public void onActionAlterar(ActionEvent actionEvent) throws Exception {

        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadAcesso.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Registrar Saída");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);
        stage.showAndWait();

        CarregarTabela();
    }

    public void onActionApagar(ActionEvent actionEvent) {
        if (Tabela.getSelectionModel().getSelectedIndex() > -1)
            aux = Tabela.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja realmente apagar o registro?");
        if (alert.showAndWait().get() == ButtonType.OK){
            new AcessoDAL().deletar(aux.getId());
            CarregarTabela();
        }
    }

    public void onActionExibir(ActionEvent actionEvent) {
        if (VisualizaTodos == 1) {
            VisualizaTodos = 0;
            btExibicao.setText("Exibir Todos");
            dpData.setVisible(false);
        }
        else {
            VisualizaTodos = 1;
            btExibicao.setText("Exibir Apenas Estacionados");
            dpData.setValue(LocalDate.now());
            dpData.setVisible(true);
        }

        CarregarTabela();
    }
}
