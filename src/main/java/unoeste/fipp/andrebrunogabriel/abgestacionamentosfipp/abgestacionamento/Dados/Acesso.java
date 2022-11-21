package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        setId(id);
        setVeiculo(veiculo);
        setHoraDeEntrada(horaDeEntrada);
        setHoraDeSaida(horaDeSaida);
        setValor(valor);
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

    public String getHoraDeEntrada() {
        if (HoraDeEntrada != null)
            return HoraDeEntrada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        else
            return null;
    }

    public void setHoraDeEntrada(LocalDateTime horaDeEntrada) {
        HoraDeEntrada = horaDeEntrada;
    }

    public String getHoraDeSaida() {
        if (HoraDeSaida != null)
            return HoraDeSaida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return null;
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
