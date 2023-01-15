package storage;

import registrazione.Utente;
import storage.controller.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
    //Metodo che permette di trovare e restituire dal DB tutti gli utenti presenti
    public List<Utente> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente");
            List<Utente> utenti = new ArrayList<>();
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Utente u = new Utente();
                u.setIdUtente(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setCognome(rs.getString(5));
                u.setEmail(rs.getString(6));

                utenti.add(u);
            }
            con.close();
            return utenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param email - stringa dell'email
     * @return
     */
    //Serve per controllo lato server: VERIFICA se esiste già un utente all interno del db con stessa email.
    public boolean getUtenteByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente WHERE email=? ");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo che permette di trovare un utente nel DB tramite email e password
    public Utente getUtenteByEmailPassword(String email,String password){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM Utente where email=? AND passwordhash=?");
            ps.setString(1,email);
            ps.setString(2,password);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Utente u = new Utente();
                u.setIdUtente(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setCognome(rs.getString(5));
                u.setEmail(rs.getString(6));
                return u;
            }
            con.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //Metodo che permette di trovare un utente nel DB tramite id  e lo restituisce
    public Utente doRetrieveById(int idUtente){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM Utente WHERE idUtente=?");
            ps.setInt(1,idUtente);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Utente u = new Utente();
                u.setIdUtente(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setCognome(rs.getString(5));
                u.setEmail(rs.getString(6));
                return u;
            }
            con.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo che permette di eliminare un utente dal DB tramite id
    public void doDelete(int idUtente) {
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps= con.prepareStatement("DELETE FROM Utente WHERE idUtente=?");
            ps.setInt(1,idUtente);
            if(ps.executeUpdate()!=1){
                throw new RuntimeException("Errore durante la cancellazione utente");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    /**
     * @param user - L'utente che verrà memorizzato all'interno del DB
     */
    //Metodo che permette di inserire un utente nel DB (registrazione)
    public void doSave(Utente user){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("INSERT into Utente(username,passwordhash,nome,cognome,email) VALUES (?,?,?,?,?);");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getNome());
            ps.setString(4,user.getCognome());
            ps.setString(5,user.getEmail());

            if(ps.executeUpdate()!=1){
                throw new RuntimeException("Errore durante l'inserimento dell' utente");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    //Metodo che permette di modificare i dati di un utente
    public void doUpdate(int idUtente,Utente utente){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("UPDATE Utente set username=?,passwordhash=?,nome=?,cognome=?,email=? where idUtente=?;");
            ps.setString(1,utente.getUsername());
            ps.setString(2,utente.getPassword());
            ps.setString(3,utente.getNome());
            ps.setString(4,utente.getCognome());
            ps.setString(5,utente.getEmail());
            ps.setInt(6,idUtente);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @param username - Oggetto da ricercare all'interno del DB
     * @return - true se esiste un oggetto uguale nel DB
     * @return - false se non esiste un oggetto uguale nel DB
     */
    //Metodo che permette di sapere se un utente è presente nel DB tramite username
    public boolean getUtenteByUsername(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente WHERE username=? ");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


