module unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento to javafx.fxml;
    opens unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados to javafx.fxml;
    exports unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;
    exports unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;
}