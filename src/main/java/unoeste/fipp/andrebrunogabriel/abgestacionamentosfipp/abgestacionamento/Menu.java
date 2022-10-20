package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ABG Estacionamentos");
        stage.setScene(scene);
        //stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void onActionMarca(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("TabelaMarca.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Consulta de Marcas");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        //stage.setResizable(false);
        stage.showAndWait();
    }

    public void onActionModelo(ActionEvent actionEvent) {

    }

    public void onActionProprietario(ActionEvent actionEvent) {

    }

    public void onActionVeiculo(ActionEvent actionEvent) {

    }

    public void onActionSair(ActionEvent actionEvent) {

    }
}