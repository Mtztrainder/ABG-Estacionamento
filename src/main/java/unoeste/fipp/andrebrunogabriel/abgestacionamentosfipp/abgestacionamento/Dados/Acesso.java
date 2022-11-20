package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;

import java.time.LocalDateTime;

public class Acesso {
    private int Id;
    private Veiculo Veiculo;
    private LocalDateTime HoraDeEntrada;
    private LocalDateTime HoraDeSaida;
    private double Valor;

    public Acesso() {
        this(0, new Veiculo(), null,null, 0);
    }

    public Acesso(int id, Veiculo veiculo, LocalDateTime horaDeEntrada, LocalDateTime horaDeSaida, double valor) {
        Id = id;
        Veiculo = veiculo;
        HoraDeEntrada = horaDeEntrada;
        HoraDeSaida = horaDeSaida;
        Valor = valor;
    }

    public Acesso(Veiculo veiculo, LocalDateTime horaDeEntrada, LocalDateTime horaDeSaida, double valor) {
        this(0, veiculo, horaDeEntrada, horaDeSaida, valor);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Veiculo getVeiculo() {
        return Veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        Veiculo = veiculo;
    }

    public LocalDateTime getHoraDeEntrada() {
        return HoraDeEntrada;
    }

    public void setHoraDeEntrada(LocalDateTime horaDeEntrada) {
        HoraDeEntrada = horaDeEntrada;
    }

    public LocalDateTime getHoraDeSaida() {
        return HoraDeSaida;
    }

    public void setHoraDeSaida(LocalDateTime horaDeSaida) {
        HoraDeSaida = horaDeSaida;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }
}
