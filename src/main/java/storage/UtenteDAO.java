package storage;

import registrazione.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO {
    public ArrayList<Utente> getAllUtenti(){//Metodo che permette di trovare e restituire dal DB tutti gli utenti presenti
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM Utente");
            ArrayList<Utente> utenti=new ArrayList<>();
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Utente u=new Utente();
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

    public Utente getUtenteByEmail(String email){ ////Metodo che serve per controllo lato server: vede se esiste già un utente all interno del db con stessa email.
        try (Connection con= ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM Utente WHERE email=? ");
            ps.setString(1,email);
            ResultSet rs= ps.executeQuery();

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Utente getUtenteByEmailPassword(String email,String password){//Metodo che permette di trovare un utente nel DB tramite email e password
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

    public Utente getUtenteById(int idUtente){//Metodo che permette di trovare un utente nel DB tramite id  e lo restituisce
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

    public void doDelete(int idUtente) {//Metodo che permette di eliminare un utente dal DB tramite id
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

    public void doSave(Utente u){//Metodo che permette di inserire un utente nel DB (registrazione)
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("INSERT into Utente(username,passwordhash,nome,cognome,email) VALUES (?,?,?,?,?);");
            ps.setString(1,u.getUsername());
            ps.setString(2,u.getPassword());
            ps.setString(3,u.getNome());
            ps.setString(4,u.getCognome());
            ps.setString(5,u.getEmail());

            if(ps.executeUpdate()!=1){
                throw new RuntimeException("Errore durante l'inserimento dell' utente");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(int idUtente,String username,String passwordhash,String nome,String cognome,String email){ //Metodo che permette di modificare i dati di un utente
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("UPDATE Utente set username=?,passwordhash=?,nome=?,cognome=?,email=? where idUtente=?;");
            ps.setString(1,username);
            ps.setString(2,passwordhash);
            ps.setString(3,nome);
            ps.setString(4,cognome);
            ps.setString(5,email);
            ps.setInt(6,idUtente);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


