package storage;

import registrazione.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO {


    //Permette di recuperare tutti gli utenti registrati nel DB
    public ArrayList<Utente> getAllUtenti(){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente");
            ArrayList<Utente> utenti = new ArrayList<>();
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


    //Serve per controllo lato server: VERIFICA se esiste già un utente all interno del db con lo stesso username.
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

    // Permette di ricercare all'interno del DB un utente utilizzando l'email e pass
    public Utente getUtenteByEmailPassword(String email,String password){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente where email=? AND passwordhash=?");
            ps.setString(1,email);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();
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

    //Permette di ricercare un utente utilizzando l'ID
    public Utente getUtenteById(int id){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM Utente WHERE idUtente=?");
            ps.setInt(1,id);
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
}


