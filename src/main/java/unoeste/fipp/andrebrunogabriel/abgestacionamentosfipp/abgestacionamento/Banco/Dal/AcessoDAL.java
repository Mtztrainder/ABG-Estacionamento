package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal;

import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Acesso;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Veiculo;

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

    public boolean RegistrarSaida(Veiculo veiculo, LocalDateTime dh) {
        String sql ="update acesso " +
                    "set ac_horasaida = ?, " +
                        "ac_valor = ? " +
                    "where vei_cod = ? " +
                    "and ac_horasaida is null";
        try{
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, dh.toString());
            pstmt.setString(2, "");
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
}
