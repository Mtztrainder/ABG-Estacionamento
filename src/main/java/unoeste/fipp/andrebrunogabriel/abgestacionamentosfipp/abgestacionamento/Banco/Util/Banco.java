package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util;

import java.sql.Connection;


public class Banco {
    private static Connection conn = null;
    private static  Conexao conexao = null;

    private Banco(){

    }
    public static boolean Conectar(){
       conexao = new Conexao();
       return conexao.conectar("jdbc:postgresql://localhost/", "parkingdb", "postgres", "postgres123");

    }

    public static Connection getConn() {
        conn = conexao.getConexao();
        return conn;
    }
    public static Conexao getConexao(){
        return conexao;
    }
}
