module unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires com.jfoenix;
    requires org.json;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento to javafx.fxml;
    opens unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados to javafx.fxml;
    opens unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes to javafx.fxml;

    exports unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento;
    exports unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;
    exports unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes;

}