package storage;

import recensione.Recensione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecensioneDAO {
    public ArrayList<Recensione> doRetrieveAll(){//Metodo che permette di restituire tutte le recensioni presenti nel DB
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM Recensione");
            ArrayList<Recensione> recensioni=new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Recensione r=new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));

                recensioni.add(r);
            }
            con.close();
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(int idRecensione,int valutazione,String testo){
        try(Connection con=ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Recensione set valutazione=?,testo=? where idRecensione=?;");
            ps.setInt(1, valutazione);
            ps.setString(2, testo);
            ps.setInt(3, idRecensione);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int idRecensione){//Metodo che permette di eliminare nel DB una carta tramite id
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("DELETE FROM Recensione where idRecensione=?");
            ps.setInt(1,idRecensione);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Errore durante la cancellazione della recensione");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Recensione getRecensioneById(int idRecensione) { //Metodo che permette di trovare e restituire una recensione nel DB  tramite id
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT*FROM Recensione WHERE idRecensione=?");
            ps.setInt(1, idRecensione);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Recensione r = new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                return r;
            }
            con.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Recensione r){ //Metodo che permette inserire una recensione nel DB
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("INSERT into Recensione(valutazione,testo)VALUES(?,?);");
            ps.setInt(1,r.getValutazione());
            ps.setString(2,r.getTesto());
            if(ps.executeUpdate()!=1){
                throw new RuntimeException("Errore durante l'inserimento della recensione");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    }
