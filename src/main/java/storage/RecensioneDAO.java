package storage;

import recensione.Recensione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * La classe permette le operazioni riguardanti gli oggetti Recensione
 * in relazione al DBMS MySQL
 * @author Francesco Di Domenico
 */
public class RecensioneDAO {


    /**
     * Il metodo permette di ottenere tutti gli oggetti Recensione
     * memorizzati nel database
     * @return una lista di tutti gli oggetti recensioni all interno del database.
     * @author Francesco Di Domenico
     */
    public List<Recensione> doRetrieveAll(){
        try (Connection con= ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM Recensione");
            List<Recensione> recensioni=new ArrayList<>();
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


    /**
     * Il metodo permette di ottenere oggetti Recensione
     * memorizzati nel database
     * @return una lista degli oggetti recensioni all interno del database filtrati per id utente.
     *@author Francesco Di Domenico
     */
    public List<Recensione> getRecensioneByIdUtente(int idUtente){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM Recensione where idUtente=?");
            ps.setInt(1,idUtente);
            ResultSet rs=ps.executeQuery();
            List<Recensione> recensioni=new ArrayList<>();
            if(rs.next()){
                Recensione r=new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setIdUtente(rs.getInt(4));
                r.setIdOrdine(rs.getInt(5));
                recensioni.add(r);
            }
            con.close();
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo permette di modificare una recensione all'interno del database
     * @param idRecensione id della recensione
     * @param r oggetto recensione
     * @author Francesco Di Domenico
     */
        public void doUpdate(int idRecensione,Recensione r){
        try(Connection con=ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Recensione set valutazione=?,testo=? where idRecensione=?;");
            ps.setInt(1, r.getValutazione());
            ps.setString(2, r.getTesto());
            ps.setInt(3, idRecensione);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Metodo che permette di eliminare nel DB una recensione tramite id
     * @author Francesco Di Domenico
     */

    public void doDelete(int idRecensione){
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

    /**
     * Metodo che permette di trovare e restituire una recensione nel DB  tramite id
     * @param idRecensione id della recensione da ricercare nel database
     * @return oggetto Recensione
     * @author Francesco Di Domenico
     */

    public Recensione doRetrieveById(int idRecensione) {
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

    /**
     * Metodo che permette inserire una recensione nel DB
     * @param r nuovo oggetto recensione
     * @author Francesco Di Domenico
     */

    public void doSave(Recensione r){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("INSERT into Recensione(valutazione,testo,idUtente,idOrdine)VALUES(?,?,?,?);");
            ps.setInt(1,r.getValutazione());
            ps.setString(2,r.getTesto());
            ps.setInt(3,r.getIdUtente());
            ps.setInt(4,r.getIdOrdine());
            if(ps.executeUpdate()!=1){
                throw new RuntimeException("Errore durante l'inserimento della recensione");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
