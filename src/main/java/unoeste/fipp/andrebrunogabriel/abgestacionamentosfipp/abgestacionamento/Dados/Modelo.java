package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;

public class Modelo {
    private int Id;
    private String Descricao;
    private Marca Marca;

    public void setId(int id) {
        Id = id;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public void setMarca(Marca marca) {
        Marca = marca;
    }

    public int getId() {
        return Id;
    }

    public String getDescricao() {
        return Descricao;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    public Marca getMarca() {
        return Marca;
    }

    public Modelo(int Id, String Descricao, Marca Marca){
        setId(Id);
        setDescricao(Descricao);
        setMarca(Marca);
    }

    public Modelo(String Descricao, Marca Marca) {
        this(0, Descricao, Marca);
    }

    public Modelo() {
        this(0, "", new Marca());
    }
}
