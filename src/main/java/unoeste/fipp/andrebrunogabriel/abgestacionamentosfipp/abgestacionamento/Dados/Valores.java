package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;

public class Valores {
    private int Id, Carencia;
    private double ValorHora, ValorHoraExtra;


    public void setId(int id) {
        Id = id;
    }

    public void setCarencia(int carencia) {
        Carencia = carencia;
    }

    public void setValorHora(double valorHora) {
        ValorHora = valorHora;
    }

    public void setValorHoraExtra(double valorHoraExtra) {
        ValorHoraExtra = valorHoraExtra;
    }

    public int getId() {
        return Id;
    }

    public int getCarencia() {
        return Carencia;
    }

    public double getValorHora() {
        return ValorHora;
    }

    public double getValorHoraExtra() {
        return ValorHoraExtra;
    }

    public Valores(int id, int carencia, double valorHora, double valorHoraExtra) {
        setId(id);
        setCarencia(carencia);
        setValorHora(valorHora);
        setValorHoraExtra(valorHoraExtra);
    }

    public Valores(int carencia, double valorHora, double valorHoraExtra) {
        this(0, carencia, valorHora, valorHoraExtra);
    }

    public Valores() {
        this(0, 0, 0, 0);
    }
}
