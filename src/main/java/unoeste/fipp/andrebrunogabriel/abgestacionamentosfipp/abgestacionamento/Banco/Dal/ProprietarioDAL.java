package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal;

import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Modelo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Proprietario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAL implements IDAL<Proprietario>{
    public boolean inserir(Proprietario proprietario)
    {
        try{
            String sql = "insert into proprietario(prop_cpf, prop_nome, prop_rua,prop_numero, prop_cep, prop_bairro, prop_cidade, prop_uf, prop_email, prop_fone) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, proprietario.getCPF());
            pstmt.setString(2, proprietario.getNome());
            pstmt.setString(3, proprietario.getLogradouro());
            pstmt.setString(4, proprietario.getNumero());
            pstmt.setInt(5,proprietario.getCEP());
            pstmt.setString(6, proprietario.getBairro());
            pstmt.setString(7,proprietario.getCidade());
            pstmt.setString(8, proprietario.getEstado());
            pstmt.setString(9, proprietario.getEmail());
            pstmt.setString(10, proprietario.getTelefone());

            if (!Banco.getConexao().manipular(pstmt.toString())) {
                System.out.println(Banco.getConexao().getMensagemErro());
                return false;
            }

            return true;
        }catch (SQLException sqlex)
        {
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return false;
    }
    public boolean alterar(Proprietario proprietario)
    {

        try{
            String sql = "update proprietario set prop_cpf=?, prop_nome=?,prop_rua=?,prop_numero=?, prop_cep=?,prop_bairro=?, prop_cidade=?, prop_uf=?, prop_email=?, prop_fone=? where prop_cod=?";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, proprietario.getCPF());
            pstmt.setString(2, proprietario.getNome());
            pstmt.setString(3, proprietario.getLogradouro());
            pstmt.setString(4, proprietario.getNumero());
            pstmt.setInt(5,proprietario.getCEP());
            pstmt.setString(6, proprietario.getBairro());
            pstmt.setString(7,proprietario.getCidade());
            pstmt.setString(8, proprietario.getEstado());
            pstmt.setString(9, proprietario.getEmail());
            pstmt.setString(10, proprietario.getTelefone());
            pstmt.setInt(11, proprietario.getId());

            if (!Banco.getConexao().manipular(pstmt.toString())) {
                System.out.println(Banco.getConexao().getMensagemErro());
                return false;
            }

            return true;
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return  false;
    }
    public boolean deletar(int id)
    {
        try{
            String sql = "delete from proprietario where prop_cod=?";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);

            if (!Banco.getConexao().manipular(pstmt.toString())) {
                System.out.println(Banco.getConexao().getMensagemErro());
                return false;
            }

            return true;
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return  false;
    }

    @Override
    public boolean dependentes(int id) {
        try {
            String sql = "select count(1) from Veiculo where prop_cod=?";
            Banco.Conectar();

            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            rs.next();
            return rs.getInt(1) > 0;
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }

        return false;
    }

    @Override
    public Proprietario Select(int id) {
        Proprietario P = null;

        try{
            String sql = "select * from proprietario where prop_cod = ?";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            rs.next();
            P = new Proprietario(rs.getInt("prop_cod"), rs.getString("prop_cpf"), rs.getString("prop_nome"), rs.getString("prop_email"), rs.getInt("prop_cep"), rs.getString("prop_uf"), rs.getString("prop_cidade"), rs.getString("prop_bairro"), rs.getString("prop_rua"), rs.getString("prop_numero"), rs.getString("prop_fone"));
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return P;
    }

    @Override
    public List<Proprietario> Select() {
        List<Proprietario> listaProprietarios= new ArrayList<Proprietario>();
        String sql = "select * from proprietario";
        Banco.Conectar();
        ResultSet rs = Banco.getConexao().consultar(sql);
        try {
            while(rs.next())
            {
                listaProprietarios.add(new Proprietario(rs.getInt("prop_cod"), rs.getString("prop_cpf"), rs.getString("prop_nome"), rs.getString("prop_email"), rs.getInt("prop_cep"), rs.getString("prop_uf"), rs.getString("prop_cidade"), rs.getString("prop_bairro"), rs.getString("prop_rua"), rs.getString("prop_numero"), rs.getString("prop_fone")));
            }
        }
        catch (Exception e){}
        return listaProprietarios;
    }

    @Override
    public List<Proprietario> Select(String filtro) {
        List<Proprietario> listaProprietarios = new ArrayList<Proprietario>();

        try{
            String sql = "select * from proprietario where prop_nome LIKE ? order by prop_nome";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, filtro+"%");
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            while(rs.next())
            {
                listaProprietarios.add(new Proprietario(rs.getInt("prop_cod"), rs.getString("prop_cpf"), rs.getString("prop_nome"), rs.getString("prop_email"), rs.getInt("prop_cep"), rs.getString("prop_uf"), rs.getString("prop_cidade"), rs.getString("prop_bairro"), rs.getString("prop_rua"), rs.getString("prop_numero"), rs.getString("prop_fone")));
            }
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return listaProprietarios;
    }
}
