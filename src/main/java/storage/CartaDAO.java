package storage;

import acquisto.Carta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartaDAO {
    public List<Carta> doRetrieveAll(){//Metodo che permette di restituire tutte le carte presenti nel DB
        try(Connection con=ConPool.getConnection()){
            List<Carta> carte=new ArrayList<>();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM carta;");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Carta c=new Carta();
                c.setIdCarta(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setCategoria(rs.getString(3));
                c.setRarita(rs.getString(4));
                c.setImmagine(rs.getString(5));
                carte.add(c);
            }
            con.close();
            return carte;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Carta doRetrieveById(int idCarta){//Metodo che permette di trovare e restituire una carta presente nel DB tramite id
        try(Connection con= ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM carta WHERE idCarta=?");
            ps.setInt(1,idCarta);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                Carta c=new Carta();
                c.setIdCarta(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setCategoria(rs.getString(3));
                c.setRarita(rs.getString(4));
                c.setImmagine(rs.getString(5));

                return c;
            }
            con.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(int idCarta,Carta carta){//Metodo che permette di modificare una carta presente nel DB.
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("UPDATE Carta set nome=?, categoria=?, rarita=?, immagine=? where idCarta=?");
            ps.setString(1, carta.getNome());
            ps.setString(2, carta.getCategoria());
            ps.setString(3, carta.getRarita());
            ps.setInt(4,idCarta);
            ps.setString(5, carta.getImmagine());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int idCarta){//Metodo che permette di eliminare nel DB una carta tramite id
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

    public void doSave(Carta carta){//Metodo che permette di inserire una nuova carta nel DB
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("INSERT into carta(nome,categoria,rarita,immagine) values (?,?,?,?)");
            ps.setString(1, carta.getNome());
            ps.setString(2, carta.getCategoria());
            ps.setString(3, carta.getRarita());
            ps.setString(4, carta.getImmagine());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Errore nell'inserimento");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
