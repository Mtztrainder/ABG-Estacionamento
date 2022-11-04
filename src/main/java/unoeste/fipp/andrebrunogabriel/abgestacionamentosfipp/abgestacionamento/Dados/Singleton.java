package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;

import java.util.ArrayList;

public class Singleton {
    public static ArrayList<Marca> ListaMarcas = new ArrayList<>();
    public static ArrayList<Modelo> ListaModelos = new ArrayList<>();
    public static ArrayList<Proprietario> ListaProprietario = new ArrayList<>();
    public static ArrayList<Veiculo> ListaVeiculo = new ArrayList<>();

    public static String[] ListaCores = new String[]{
            "Azul",
            "Vermelho",
            "Amarelo",
            "Verde",
            "Preto",
            "Cinza",
            "Branco"
    };

    private Singleton(){

    }
}
