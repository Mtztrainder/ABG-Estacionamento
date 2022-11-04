package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;

public class Veiculo {
    private int Id;
    private String Placa, Cor;
    private Modelo Modelo;
    private Proprietario Proprietario;


    public void setId(int id) {
        Id = id;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public void setCor(String cor) {
        Cor = cor;
    }

    public void setModelo(Modelo modelo) {
        Modelo = modelo;
    }

    public void setProprietario(Proprietario proprietario) {
        Proprietario = proprietario;
    }

    public int getId() {
        return Id;
    }

    public String getPlaca() {
        return Placa;
    }

    public String getCor() {
        return Cor;
    }

    public Modelo getModelo() {
        return Modelo;
    }

    public Proprietario getProprietario() {
        return Proprietario;
    }

    @Override
    public String toString() {
        return getPlaca();
    }

    public Veiculo(int Id, String Placa, String Cor, Modelo Modelo, Proprietario Proprietario){
        setId(Id);
        setPlaca(Placa);
        setCor(Cor);
        setModelo(Modelo);
        setProprietario(Proprietario);
    }

    public Veiculo(String Placa, String Cor, Modelo Modelo, Proprietario Proprietario) {
        this(0, Placa, Cor, Modelo, Proprietario);
    }

    public Veiculo() {
        this(0, "", "", null, null);
    }
}
