package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;

public class Proprietario {
    private int Id, CEP;
    private String CPF, Email, Nome, Numero, Logradouro,  Bairro, Cidade, Estado, Telefone;

    public void setId(int id) {
        Id = id;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }
    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }



    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public int getId() {
        return Id;
    }

    public String getNumero() {
        return Numero;
    }

    public String getTelefone() {
        return Telefone;
    }
    public String getCPF() {
        return CPF;
    }

    public String getEmail() {
        return Email;
    }

    public String getNome() {
        return Nome;
    }

    public int getCEP() {
        return CEP;
    }

    public String getLogradouro() {
        return Logradouro;
    }



    public String getBairro() {
        return Bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public String getEstado() {
        return Estado;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public Proprietario(int id, String CPF, String nome,
                        String email, int CEP, String estado, String cidade, String bairro,
                        String logradouro, String numero, String telefone) {
        setId(id);
        setCPF(CPF);
        setNome(nome);

        setNumero(numero);
        setEmail(email);

        setCEP(CEP);
        setEstado(estado);
        setCidade(cidade);

        setBairro(bairro);
        setLogradouro(logradouro);
        setNumero(numero);
        setTelefone(telefone);
    }

    public Proprietario(String CPF, String nome,
                        String email, int CEP, String estado, String cidade, String bairro,
                        String logradouro, String numero,  String telefone) {
        this(0, CPF, nome, email, CEP, estado, cidade, bairro, logradouro, numero,  telefone);
    }

    public Proprietario(){
        this(0, "", "", "", 0, "", "", "", "", "", "");
    }
}
