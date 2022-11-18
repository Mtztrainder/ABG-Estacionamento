package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu extends Application {
    public BorderPane Painel;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ABG - Estacionamentos");
        stage.setScene(scene);
        stage.setMaximized(true);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void AlteraPainel(String XMLName) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource(XMLName));
        Painel.setCenter(fxmlLoader.load());
    }

    public void onActionMarca(ActionEvent actionEvent) throws Exception {
        AlteraPainel("TabelaMarca.fxml");
    }

    public void onActionModelo(ActionEvent actionEvent) throws Exception {
        AlteraPainel("TabelaModelo.fxml");
    }

    public void onActionProprietario(ActionEvent actionEvent) throws Exception {
        AlteraPainel("TabelaProprietario.fxml");
    }

    public void onActionVeiculo(ActionEvent actionEvent) throws Exception {
        AlteraPainel("TabelaVeiculo.fxml");
    }

    public void onActionValores(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("CadValores.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Alterar Valores");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void onActionSair(ActionEvent actionEvent) {
        System.exit(0);
    }
}