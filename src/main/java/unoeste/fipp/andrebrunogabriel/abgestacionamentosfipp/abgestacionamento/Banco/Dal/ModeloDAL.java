package unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Dal;

import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Banco.Util.Banco;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Marca;
import unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Dados.Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeloDAL {
    public boolean inserir(Modelo modelo)
    {
        try{
            String sql = "insert into Modelo(mar_cod, mod_desc) values(?, ?)";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, modelo.getMarca().getId());
            pstmt.setString(2, modelo.getDescricao());
            return Banco.getConexao().manipular(pstmt.toString());
        }catch (SQLException sqlex)
        {
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return false;
    }
    public boolean alterar(Modelo modelo)
    {

        try{
            String sql = "update Modelo set mar_cod=?, mod_desc=? where mod_cod=?";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, modelo.getMarca().getId());
            pstmt.setString(2, modelo.getDescricao());
            pstmt.setInt(3, modelo.getId());
            return Banco.getConexao().manipular(pstmt.toString());
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return  false;
    }
    public boolean deletar(int id)
    {
        try{
            String sql = "delete from Modelo where mod_cod=?";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);
            return Banco.getConexao().manipular(pstmt.toString());
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return  false;
    }

    public List<Modelo> SelectAll()
    {
        List<Modelo> listaModelos= new ArrayList<Modelo>();
        String sql = "select * from modelo md inner join marca mc on mc.mar_cod = md.mar_cod order by mod_desc";
        Banco.Conectar();
        ResultSet rs = Banco.getConexao().consultar(sql);
        try {
            while(rs.next())
            {
                listaModelos.add(new Modelo(rs.getInt("mod_cod"), rs.getString("mod_desc"), new Marca(rs.getInt("mar_cod"), rs.getString("mar_desc"))));
            }
        }
        catch (Exception e){}
        return listaModelos;
    }

    public List<Modelo> SelectFilter(String filtro)
    {
        List<Modelo> listaModelos= new ArrayList<Modelo>();

        try{
            String sql = "select * from modelo md inner join marca mc on mc.mar_cod = md.mar_cod where lower(mod_desc) LIKE ? order by mod_desc";
            Banco.Conectar();
            PreparedStatement pstmt = Banco.getConn().prepareStatement(sql);
            pstmt.setString(1, filtro.toLowerCase()+"%");
            ResultSet rs = Banco.getConexao().consultar(pstmt.toString());
            while(rs.next())
            {
                listaModelos.add(new Modelo(rs.getInt("mod_cod"), rs.getString("mod_desc"), new Marca(rs.getInt("mar_cod"), rs.getString("mar_desc"))));
            }
        }catch(SQLException sqlex){
            System.out.println("Erro: "+ sqlex.getMessage());
        }
        return listaModelos;
    }
}
