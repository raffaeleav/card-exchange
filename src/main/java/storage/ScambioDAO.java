package storage;

import registrazione.Utente;
import scambio.Scambio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScambioDAO {
    /**
     * @param idRichiestaScambio - ID della richiesta di scambio da eliminare
     */
    public void doDelete(int idRichiestaScambio) {
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps= con.prepareStatement("DELETE FROM RichiestaDiScambio WHERE idRichiestaScambio=?");
            ps.setInt(1,idRichiestaScambio);
            if(ps.executeUpdate()!=1){
                throw new RuntimeException("Errore durante la cancellazione dello scambio");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    /**
     * @param richiestaDiScambio - Lo scambio che verrà memorizzato all'interno del DB
     */
    public void doSave(Scambio richiestaDiScambio){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("INSERT into RichiestaDiScambio(idUtenteMittente,idUtenteDestinatario,idOfferta) VALUES (?,?,?,?,?);");
            ps.setInt(1,richiestaDiScambio.getIdUtenteMittente());
            ps.setInt(2,richiestaDiScambio.getIdUtenteDestinatario());
            ps.setInt(3,richiestaDiScambio.getIdOfferta());

            if(ps.executeUpdate()!=1){
                throw new RuntimeException("Errore durante l'inserimento dello scambio");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    /**
     * @param idOfferta - id dell'offerta messa in vendita
     * @return una lista con tutte le proposte di scambio ricevute per l'offerta
     */
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

    /**
     * @param idUtenteMittente - ID dell'utente che ha inviato le richieste di scambio
     * @return Tutte le richieste di scambio effettuate
     */
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

    /**
     * @param idUtenteDestinatario - ID dell'utente Destinatario
     * @return tutte le richieste di scambio ricevute
     */
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
