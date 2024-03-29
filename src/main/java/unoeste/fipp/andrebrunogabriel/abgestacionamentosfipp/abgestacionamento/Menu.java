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
import javafx.stage.StageStyle;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;

import java.io.IOException;

public class Menu extends Application {
    public BorderPane Painel;
    public void moveScreen(Scene scene, Stage stage)
    {
        scene.setOnMousePressed(pressEvent -> {
            scene.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
        stage.setTitle("ABG - Estacionamentos");
        stage.setScene(scene);
        stage.setMaximized(true);

        Banco.Conectar();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void AlteraPainel(String XMLName) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource(XMLName));
        Painel.setCenter(fxmlLoader.load());
    }

    public void ExibePOPUP(String XMLName, String Titulo) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource(XMLName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        moveScreen(scene,stage);
        stage.setTitle(Titulo);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
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
        ExibePOPUP("CadValores.fxml", "Alterar Valores");
    }

    public void onActionRegistrar(ActionEvent actionEvent) throws Exception {
        AlteraPainel("TabelaAcesso.fxml");
    }

    public void onActionSair(ActionEvent actionEvent) {
        System.exit(0);
    }

}