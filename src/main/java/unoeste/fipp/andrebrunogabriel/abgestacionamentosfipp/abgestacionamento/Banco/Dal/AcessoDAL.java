package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal;

import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AcessoDAL{
    public boolean RegistrarEntrada(Veiculo veiculo, LocalDateTime dh) {
        String sql ="insert into acesso(vei_cod, ac_horaentrada) values (?, ?)";
        try{
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, veiculo.getId());
            pstmt.setString(2, dh.toString());

            if (!Banco.getConexao().manipular(pstmt.toString())) {
                System.out.println(Banco.getConexao().getMensagemErro());
                return false;
            }

            return true;
        }catch (SQLException sqlex) {
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return false;
    }

    public boolean RegistrarSaida(Veiculo veiculo, LocalDateTime dh, double valor) {
        String sql ="update acesso " +
                    "set ac_horasaida = ?, " +
                        "ac_valor = ? " +
                    "where vei_cod = ? " +
                    "and ac_horasaida is null";
        try{
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, dh.toString());
            pstmt.setDouble(2, valor);
            pstmt.setInt(3, veiculo.getId());

            if (!Banco.getConexao().manipular(pstmt.toString())) {
                System.out.println(Banco.getConexao().getMensagemErro());
                return false;
            }

            return true;
        }catch (SQLException sqlex) {
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return false;
    }



    public Acesso Select(int id) {
        Acesso ac = null;

        try{
            String sql = "select * from acesso ac inner join veiculo vec on ac.vei_cod = vec.vei_cod where ac_cod = ? ";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            rs.next();

            ac = new Acesso(rs.getInt("ac_cod"),
                    new VeiculoDAL().Select(rs.getInt("vei_cod")),
                     rs.getTimestamp("ac_horaentrada").toLocalDateTime(),
                    rs.getTimestamp("ac_horasaida").toLocalDateTime(),
                     rs.getDouble("ac_valor"));

        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return ac;
    }


    public List<Acesso> Select() {
        List<Acesso> listaAcesso = new ArrayList<Acesso>();
        String sql = "SELECT * FROM ACESSO AC INNER JOIN VEICULO VEC" +
                "ON AC.VEI_COD = VEC.VEI_COD" +
                "INNER JOIN MODELO MO ON MO.MOD_COD = VEC.MOD_COD "+
                "INNER JOIN MARCA MA ON MA.MAR_COD = MO.MAR_COD "+
                "INNER JOIN PROPRIETARIO PR ON PR.PROP_COD = VEC.PROP_COD ";


        Banco.Conectar();
        ResultSet rs = Banco.getConexao().consultar(sql);
        try {
            while(rs.next())
            {
                listaAcesso.add(new Acesso(rs.getInt("ac_cod"),
                        new Veiculo(rs.getInt("vei_cod"),
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
                                ),


                                rs.getTimestamp("ac_horaentrada").toLocalDateTime(),
                                rs.getTimestamp("ac_horasaida").toLocalDateTime(),
                                rs.getDouble("ac_valor")));
            }
        }
        catch (Exception e){}
        return listaAcesso;
    }


    public List<Acesso> Select(String filtro) {
        List<Acesso> listaAcesso = new ArrayList<Acesso>();

        try {
            String sql = "SELECT * FROM ACESSO AC INNER JOIN VEICULO VEC" +
                    "ON AC.VEI_COD = VEC.VEI_COD" +
                    "INNER JOIN MODELO MO ON MO.MOD_COD = VEC.MOD_COD "+
                    "INNER JOIN MARCA MA ON MA.MAR_COD = MO.MAR_COD "+
                    "INNER JOIN PROPRIETARIO PR ON PR.PROP_COD = VEC.PROP_COD WHERE LOWER(VEC.VEI_PLACA) LIKE ?";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, filtro.toLowerCase() + "%");
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            while (rs.next()) {
                listaAcesso.add(new Acesso(rs.getInt("ac_cod"),
                        new Veiculo(rs.getInt("vei_cod"),
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
                        ),
                        rs.getTimestamp("ac_horaentrada").toLocalDateTime(),
                        rs.getTimestamp("ac_horasaida").toLocalDateTime(),
                        rs.getDouble("ac_valor")));
            }
        } catch (SQLException sqlex) {
            System.out.println("Erro: " + sqlex.getMessage());
        }
        return listaAcesso;
    }
}
