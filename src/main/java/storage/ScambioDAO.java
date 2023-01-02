package storage;

import scambio.Scambio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScambioDAO {


    // Permette di recuperare tutte le richieste di scambio per una determinata offerta
    public ArrayList<Scambio> getAllScambiByIdOfferta(int idOfferta){
        try(Connection con = ConPool.getConnection()){

            //Preparo la query
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RichiestaDiScambio WHERE idOfferta=?");
            ps.setString(1, String.valueOf(idOfferta));

            // Inizializzo l'ArrayList che conterrà tutte le richieste di scambio
            ArrayList<Scambio> scambio = new ArrayList<>();


            //Eseguo la query sul DB
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Scambio u = new Scambio();
                u.setIdRichiestaScambio(rs.getInt(1));
                u.setIdUtenteMittente(rs.getInt(2));
                u.setIdUtenteDestinatario(rs.getInt(3));
                u.setIdOfferta(rs.getInt(4));
                scambio.add(u);
            }
            con.close();
            return scambio;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Permette di recuperare tutte le richieste scambio effettuate da un utente
    public ArrayList<Scambio> getAllScambiByIdMittente(int idUtenteMittente){
        try(Connection con = ConPool.getConnection()){

            //Preparo la query
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RichiestaDiScambio WHERE idUtenteMittente=?");
            ps.setString(1, String.valueOf(idUtenteMittente));

            // Inizializzo l'ArrayList che conterrà tutte le richieste di scambio
            ArrayList<Scambio> scambio = new ArrayList<>();


            //Eseguo la query sul DB
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Scambio u = new Scambio();
                u.setIdRichiestaScambio(rs.getInt(1));
                u.setIdUtenteMittente(rs.getInt(2));
                u.setIdUtenteDestinatario(rs.getInt(3));
                u.setIdOfferta(rs.getInt(4));
                scambio.add(u);
            }
            con.close();
            return scambio;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Permette di recuperare tutte le richieste scambio ricevute da un utente
    public ArrayList<Scambio> getAllScambiByIdDestinatario(int idUtenteDestinatario){
        try(Connection con = ConPool.getConnection()){

            //Preparo la query
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RichiestaDiScambio WHERE idUtenteDestinatario=?");
            ps.setString(1, String.valueOf(idUtenteDestinatario));

            // Inizializzo l'ArrayList che conterrà tutte le richieste di scambio
            ArrayList<Scambio> scambio = new ArrayList<>();


            //Eseguo la query sul DB
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Scambio u = new Scambio();
                u.setIdRichiestaScambio(rs.getInt(1));
                u.setIdUtenteMittente(rs.getInt(2));
                u.setIdUtenteDestinatario(rs.getInt(3));
                u.setIdOfferta(rs.getInt(4));
                scambio.add(u);
            }
            con.close();
            return scambio;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
