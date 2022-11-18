package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal;

import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Conf;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConfDAL{

    public ConfDAL(){
        try{
            String sql = "select count(1) from conf";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);

            if (!Banco.getConexao().manipular(pstmt.toString())) {
                System.out.println(Banco.getConexao().getMensagemErro());
            }
            else{
                Conf conf = new Conf();

                sql = "insert into conf (conf_valorhora, conf_valoradic, conf_carencia) values (?, ?, ?)";
                Banco.Conectar();
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

    public boolean altera(Conf conf){
        try{
            String sql = "update conf set conf_valorhora=?, conf_valoradic=?, conf_carencia=?";
            Banco.Conectar();
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
