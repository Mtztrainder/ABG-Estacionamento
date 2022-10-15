package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;

public class Marca {
    private int Id;
    private String Descricao;

    public void setId(int id) {
        Id = id;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public int getId() {
        return Id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public Marca(int Id, String Descricao){
        setId(Id);
        setDescricao(Descricao);
    }

    public Marca(String Descricao) {
        this(0, Descricao);
    }

    public Marca() {
        this(0, "");
    }
}
