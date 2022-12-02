package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal;

import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Conf;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfDAL{

    public ConfDAL(){
        try{
            String sql = "select count(1) from conf";
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            rs.next();
            if (rs.getInt(1) == 0) {
                Conf conf = new Conf();

                sql = "insert into conf (conf_valorhora, conf_valoradic, conf_carencia) values (?, ?, ?)";
                pstmt = Banco.getConn().prepareStatement(sql);
                pstmt.setDouble(1, conf.getValorHora());
                pstmt.setDouble(2, conf.getValorAdic());
                pstmt.setInt(3, conf.getCarencia());

                if (!Banco.getConexao().manipular(pstmt.toString())) {
                    System.out.println(Banco.getConexao().getMensagemErro());
                }
            }
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
    }

    public Conf Select(){
        try{
            String sql = "select conf_valorhora, conf_valoradic, conf_carencia from conf";
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            rs.next();
            return new Conf(rs.getDouble(1), rs.getDouble(2), rs.getInt(3));

        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }

        return null;
    }

    public boolean altera(Conf conf){
        try{
            String sql = "update conf set conf_valorhora=?, conf_valoradic=?, conf_carencia=?";
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setDouble(1, conf.getValorHora());
            pstmt.setDouble(2, conf.getValorAdic());
            pstmt.setInt(3, conf.getCarencia());

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
