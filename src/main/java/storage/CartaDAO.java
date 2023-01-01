package storage;

import acquisto.Carta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartaDAO {
    /**
     *
     * @return
     */
    public ArrayList<Carta> getAllCarte(){
        try(Connection con=ConPool.getConnection()){
            ArrayList<Carta> carte=new ArrayList<Carta>();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM carta;");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Carta c=new Carta();
                c.setIdCarta(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setCategoria(rs.getString(3));
                c.setRarita(rs.getString(4));
                carte.add(c);
            }
            con.close();
            return carte;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Carta getCartaById(int id){
        try(Connection con= ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM carta WHERE idCarta=?");
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                Carta c=new Carta();
                c.setIdCarta(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setCategoria(rs.getString(3));
                c.setRarita(rs.getString(4));
                return c;
            }
            con.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(int idCarta,String nome,String categoria,String rarita){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("UPDATE Carta set nome=?,categoria=?,rarita=? where idCarta=?");
            ps.setString(1,nome);
            ps.setString(2,categoria);
            ps.setString(3,rarita);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doDelete(int idCarta){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("DELETE FROM Carta where idCarta=?");
            ps.setInt(1,idCarta);
            if(ps.executeUpdate()!=1 ){
                throw new RuntimeException("errore nella cancellazione");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Carta carta){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("INSERT into carta(nome,categoria,rarita) values (?,?,?)");
            ps.setString(1,carta.getNome());
            ps.setString(2, carta.getCategoria());
            ps.setString(3,carta.getRarita());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Errore nell'inserimento");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
