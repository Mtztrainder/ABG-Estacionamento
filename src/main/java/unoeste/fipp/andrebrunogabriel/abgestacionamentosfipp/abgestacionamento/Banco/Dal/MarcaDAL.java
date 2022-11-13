package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal;

import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAL {

    public boolean inserir(Marca marca)
    {
        try{
            String sql = "insert into Marca(mar_desc) values(?)";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, marca.getDescricao());
            return Banco.getConexao().manipular(pstmt.toString());
        }catch (SQLException sqlex)
        {
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return false;
    }
    public boolean alterar(Marca marca)
    {

        try{
            String sql = "update Marca set mar_desc=? where mar_cod=?";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, marca.getDescricao());
            pstmt.setInt(2, marca.getId());
            return Banco.getConexao().manipular(pstmt.toString());
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return  false;
    }

    public boolean dependentes(int id){
        try {
            String sql = "select count(1) from Modelo where mar_cod=?";
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

    public boolean deletar(int id)
    {
        try{
            String sql = "delete from Marca where mar_cod=?";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);
            return Banco.getConexao().manipular(pstmt.toString());
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return  false;
    }

    public List<Marca> SelectAll()
    {
        List<Marca> listaMarcas= new ArrayList<Marca>();
        String sql = "select * from marca order by mar_desc";
        Banco.Conectar();
        ResultSet rs = Banco.getConexao().consultar(sql);
        try {
            while(rs.next())
            {
                listaMarcas.add(new Marca(rs.getInt("mar_cod"), rs.getString("mar_desc")));
            }
        }
        catch (Exception e){}
        return listaMarcas;
    }

    public List<Marca> SelectFilter(String filtro)
    {
        List<Marca> listaMarcas= new ArrayList<Marca>();

        try{
            String sql = "select * from marca where lower(mar_desc) LIKE ? order by mar_desc";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, filtro.toLowerCase()+"%");
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            while(rs.next())
            {
                listaMarcas.add(new Marca(rs.getInt("mar_cod"), rs.getString("mar_desc")));
            }
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return listaMarcas;
    }

}
