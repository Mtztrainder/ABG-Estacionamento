module unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento {
    requires javafx.controls;
    requires javafx.fxml;


    opens unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento to javafx.fxml;
    exports unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;
}