package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;

public class Conf {
    private double ValorHora, ValorAdic;
    private int Carencia;

    public void setValorHora(double valorHora) {
        ValorHora = valorHora;
    }

    public void setValorAdic(double valorAdic) {
        ValorAdic = valorAdic;
    }

    public void setCarencia(int carencia) {
        Carencia = carencia;
    }

    public double getValorHora() {
        return ValorHora;
    }

    public double getValorAdic() {
        return ValorAdic;
    }

    public int getCarencia() {
        return Carencia;
    }

    public Conf(double valorHora, double valorAdic, int carencia) {
        setValorHora(valorHora);
        setValorAdic(valorAdic);
        setCarencia(carencia);
    }
    public Conf() {
        this(0, 0, 30);
    }
}
