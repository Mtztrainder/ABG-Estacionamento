package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal;

import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Modelo;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Proprietario;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VeiculoDAL implements IDAL<Veiculo>{

    public boolean inserir(Veiculo veiculo)
    {
        try{
            String sql = "insert into Veiculo(vei_placa, mod_cod, vei_cor, prop_cod) values(?, ?, ?, ?)";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);

            pstmt.setString(1, veiculo.getPlaca());
            pstmt.setInt(2, veiculo.getModelo().getId());
            pstmt.setString(3, veiculo.getCor());
            pstmt.setInt(4, veiculo.getProprietario().getId());

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
    public boolean alterar(Veiculo veiculo)
    {

        try{
            String sql = "update Veiculo set vei_placa=?, mod_cod=?, vei_cor=?, prop_cod=? where vei_cod=?";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);

            pstmt.setString(1, veiculo.getPlaca());
            pstmt.setInt(2, veiculo.getModelo().getId());
            pstmt.setString(3, veiculo.getCor());
            pstmt.setInt(4, veiculo.getProprietario().getId());
            pstmt.setInt(5, veiculo.getId());

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

    public boolean dependentes(int id){
        /*try {
            String sql = "select count(1) from Modelo where mar_cod=?";
            Banco.Conectar();

            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            rs.next();
            return rs.getInt(1) > 0;
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }*/

        return false;
    }

    @Override
    public Veiculo Select(int id) {
       Veiculo v = null;

        try{
            String sql = "SELECT V.VEI_COD, "+
                    "	V.VEI_PLACA, "+
                    "	V.VEI_COR, "+
                    "	V.MOD_COD, "+
                    "	MO.MOD_DESC, "+
                    "   MA.MAR_COD, "+
                    "	MA.MAR_DESC, "+
                    "	V.PROP_COD, "+
                    "	PR.PROP_CPF, "+
                    "	PR.PROP_NOME, "+
                    "	PR.PROP_RUA, "+
                    "	PR.PROP_NUMERO, "+
                    "	PR.PROP_CEP, "+
                    "	PR.PROP_BAIRRO, "+
                    "	PR.PROP_CIDADE, "+
                    "	PR.PROP_UF, "+
                    "	PR.PROP_EMAIL, "+
                    "	PR.PROP_FONE "+
                    "FROM VEICULO V "+
                    "INNER JOIN MODELO MO ON MO.MOD_COD = V.MOD_COD "+
                    "INNER JOIN MARCA MA ON MA.MAR_COD = MO.MAR_COD "+
                    "INNER JOIN PROPRIETARIO PR ON PR.PROP_COD = V.PROP_COD "+
                    "WHERE vei_cod = ? "+
                    "ORDER BY V.VEI_PLACA";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            rs.next();

            v = new Veiculo(rs.getInt("vei_cod"),
                        rs.getString("vei_placa"),
                        rs.getString("vei_cor"),
                        new Modelo(rs.getInt("mod_cod"),
                                rs.getString("mod_desc"),
                                new Marca(rs.getInt("mar_cod"),
                                        rs.getString("mar_desc"))
                        ),
                        new Proprietario(rs.getInt("prop_cod"),
                                rs.getString("prop_cpf"),
                                rs.getString("prop_nome"),
                                rs.getString("prop_email"),
                                rs.getInt("prop_cep"),
                                rs.getString("prop_uf"),
                                rs.getString("prop_cidade"),
                                rs.getString("prop_bairro"),
                                rs.getString("prop_rua"),
                                rs.getString("prop_numero"),
                                rs.getString("prop_fone")
                        )
                );

        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return v;
    }

    @Override
    public List<Veiculo> Select() {
        List<Veiculo> listaVeiculo= new ArrayList<Veiculo>();
        String sql = "SELECT V.VEI_COD, "+
                "	V.VEI_PLACA, "+
                "	V.VEI_COR, "+
                "	V.MOD_COD, "+
                "	MO.MOD_DESC, "+
                "   MA.MAR_COD, "+
                "	MA.MAR_DESC, "+
                "	V.PROP_COD, "+
                "	PR.PROP_CPF, "+
                "	PR.PROP_NOME, "+
                "	PR.PROP_RUA, "+
                "	PR.PROP_NUMERO, "+
                "	PR.PROP_CEP, "+
                "	PR.PROP_BAIRRO, "+
                "	PR.PROP_CIDADE, "+
                "	PR.PROP_UF, "+
                "	PR.PROP_EMAIL, "+
                "	PR.PROP_FONE "+
                "FROM VEICULO V "+
                "INNER JOIN MODELO MO ON MO.MOD_COD = V.MOD_COD "+
                "INNER JOIN MARCA MA ON MA.MAR_COD = MO.MAR_COD "+
                "INNER JOIN PROPRIETARIO PR ON PR.PROP_COD = V.PROP_COD "+
                "ORDER BY V.VEI_PLACA";
        Banco.Conectar();
        ResultSet rs = Banco.getConexao().consultar(sql);
        try {
            while(rs.next())
            {
                listaVeiculo.add(new Veiculo(rs.getInt("vei_cod"),
                        rs.getString("vei_placa"),
                        rs.getString("vei_cor"),
                        new Modelo(rs.getInt("mod_cod"),
                                rs.getString("mod_desc"),
                                new Marca(rs.getInt("mar_cod"),
                                        rs.getString("mar_desc"))
                        ),
                        new Proprietario(rs.getInt("prop_cod"),
                                rs.getString("prop_cpf"),
                                rs.getString("prop_nome"),
                                rs.getString("prop_email"),
                                rs.getInt("prop_cep"),
                                rs.getString("prop_uf"),
                                rs.getString("prop_cidade"),
                                rs.getString("prop_bairro"),
                                rs.getString("prop_rua"),
                                rs.getString("prop_numero"),
                                rs.getString("prop_fone")
                        )
                ));
            }
        }
        catch (Exception e){}
        return listaVeiculo;
    }

    @Override
    public List<Veiculo> Select(String filtro) {
        List<Veiculo> listaVeiculo= new ArrayList<Veiculo>();

        try{
            String sql = "SELECT V.VEI_COD, "+
                    "	V.VEI_PLACA, "+
                    "	V.VEI_COR, "+
                    "	V.MOD_COD, "+
                    "	MO.MOD_DESC, "+
                    "   MA.MAR_COD, "+
                    "	MA.MAR_DESC, "+
                    "	V.PROP_COD, "+
                    "	PR.PROP_CPF, "+
                    "	PR.PROP_NOME, "+
                    "	PR.PROP_RUA, "+
                    "	PR.PROP_NUMERO, "+
                    "	PR.PROP_CEP, "+
                    "	PR.PROP_BAIRRO, "+
                    "	PR.PROP_CIDADE, "+
                    "	PR.PROP_UF, "+
                    "	PR.PROP_EMAIL, "+
                    "	PR.PROP_FONE "+
                    "FROM VEICULO V "+
                    "INNER JOIN MODELO MO ON MO.MOD_COD = V.MOD_COD "+
                    "INNER JOIN MARCA MA ON MA.MAR_COD = MO.MAR_COD "+
                    "INNER JOIN PROPRIETARIO PR ON PR.PROP_COD = V.PROP_COD "+
                    "WHERE lower(vei_placa) LIKE ? "+
                    "ORDER BY V.VEI_PLACA";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, filtro.toLowerCase()+"%");
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            while(rs.next()) {
                listaVeiculo.add(new Veiculo(rs.getInt("vei_cod"),
                        rs.getString("vei_placa"),
                        rs.getString("vei_cor"),
                        new Modelo(rs.getInt("mod_cod"),
                                rs.getString("mod_desc"),
                                new Marca(rs.getInt("mar_cod"),
                                        rs.getString("mar_desc"))
                        ),
                        new Proprietario(rs.getInt("prop_cod"),
                                rs.getString("prop_cpf"),
                                rs.getString("prop_nome"),
                                rs.getString("prop_email"),
                                rs.getInt("prop_cep"),
                                rs.getString("prop_uf"),
                                rs.getString("prop_cidade"),
                                rs.getString("prop_bairro"),
                                rs.getString("prop_rua"),
                                rs.getString("prop_numero"),
                                rs.getString("prop_fone")
                        )
                ));
            }
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return listaVeiculo;
    }


    public List<Veiculo> SelectNaoEstacionados() {
        List<Veiculo> listaVeiculo= new ArrayList<Veiculo>();

        try{
            String sql = "SELECT V.VEI_COD, "+
                    "	V.VEI_PLACA, "+
                    "	V.VEI_COR, "+
                    "	V.MOD_COD, "+
                    "	MO.MOD_DESC, "+
                    "   MA.MAR_COD, "+
                    "	MA.MAR_DESC, "+
                    "	V.PROP_COD, "+
                    "	PR.PROP_CPF, "+
                    "	PR.PROP_NOME, "+
                    "	PR.PROP_RUA, "+
                    "	PR.PROP_NUMERO, "+
                    "	PR.PROP_CEP, "+
                    "	PR.PROP_BAIRRO, "+
                    "	PR.PROP_CIDADE, "+
                    "	PR.PROP_UF, "+
                    "	PR.PROP_EMAIL, "+
                    "	PR.PROP_FONE "+
                    "FROM VEICULO V "+
                    "INNER JOIN MODELO MO ON MO.MOD_COD = V.MOD_COD "+
                    "INNER JOIN MARCA MA ON MA.MAR_COD = MO.MAR_COD "+
                    "INNER JOIN PROPRIETARIO PR ON PR.PROP_COD = V.PROP_COD "+
                    "WHERE (SELECT COUNT(1) FROM ACESSO AC " +
                    "       WHERE AC.VEI_COD = V.VEI_COD " +
                    "       AND AC.AC_HORASAIDA IS NULL) = 0 "+
                    "ORDER BY V.VEI_PLACA";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            while(rs.next()) {
                listaVeiculo.add(new Veiculo(rs.getInt("vei_cod"),
                        rs.getString("vei_placa"),
                        rs.getString("vei_cor"),
                        new Modelo(rs.getInt("mod_cod"),
                                rs.getString("mod_desc"),
                                new Marca(rs.getInt("mar_cod"),
                                        rs.getString("mar_desc"))
                        ),
                        new Proprietario(rs.getInt("prop_cod"),
                                rs.getString("prop_cpf"),
                                rs.getString("prop_nome"),
                                rs.getString("prop_email"),
                                rs.getInt("prop_cep"),
                                rs.getString("prop_uf"),
                                rs.getString("prop_cidade"),
                                rs.getString("prop_bairro"),
                                rs.getString("prop_rua"),
                                rs.getString("prop_numero"),
                                rs.getString("prop_fone")
                        )
                ));
            }
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return listaVeiculo;
    }

    public boolean deletar(int id)
    {
        try{
            String sql = "delete from Veiculo where vei_cod=?";
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
}
