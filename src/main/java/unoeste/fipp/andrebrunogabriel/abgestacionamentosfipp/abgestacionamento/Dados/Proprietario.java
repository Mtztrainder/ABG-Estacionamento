package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados;

public class Proprietario {
    private int Id, Numero;
    private String CPF, Email, Nome, CEP, Logradouro, Complemento, Bairro, Cidade, Estado;

    public void setId(int id) {
        Id = id;
    }

    public void setNumero(int numero) {
        Numero = numero;
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

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }

    public void setComplemento(String complemento) {
        Complemento = complemento;
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

    public int getNumero() {
        return Numero;
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

    public String getCEP() {
        return CEP;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public String getComplemento() {
        return Complemento;
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

    public Proprietario(int id, String CPF, String nome,
                        String email, String CEP, String estado, String cidade, String bairro,
                        String logradouro, int numero, String complemento) {
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
        setComplemento(complemento);
    }

    public Proprietario(String CPF, String nome,
                        String email, String CEP, String estado, String cidade, String bairro,
                        String logradouro, int numero, String complemento) {
        this(0, CPF, nome, email, CEP, estado, cidade, bairro, logradouro, numero, complemento);
    }

    public Proprietario(){
        this(0, "", "", "", "", "", "", "", "", 0, "");
    }
}
