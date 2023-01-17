package storage;

import scambio.Scambio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Data Access Object per la gestione delle richieste di scambio nel database.
 * @author Michele Menzione
 * @version 1.0
 */

public class ScambioDAO {

    /**
     * Rimuove una richiesta di scambio dal database.
     * @param idRichiestaScambio ID della richiesta di scambio da eliminare
     */
    public void doDelete(int idRichiestaScambio) {
        try(Connection con= ConPool.getConnection()){
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
     * Memorizza una richiesta di scambio nel database.
     * @param richiestaDiScambio La richiesta di scambio da memorizzare
     */
    public void doSave(Scambio richiestaDiScambio){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("INSERT into RichiestaDiScambio(idUtenteMittente,idUtenteDestinatario,idOffertaMittente,idOffertaDestinatario, conguaglio)" +
                    " VALUES (?,?,?,?,?);");
            ps.setInt(1,richiestaDiScambio.getIdUtenteMittente());
            ps.setInt(2,richiestaDiScambio.getIdUtenteDestinatario());
            ps.setInt(3,richiestaDiScambio.getIdOffertaMittente());
            ps.setInt(4,richiestaDiScambio.getIdOffertaDestinatario());
            ps.setDouble(5,richiestaDiScambio.getConguaglio());



            if(ps.executeUpdate()!=1){
                throw new RuntimeException("Errore durante l'inserimento dello scambio");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    /**
     * Recupera tutte le richieste di scambio per una determinata offerta
     * @param idOfferta ID dell'offerta per cui recuperare le richieste di scambio
     * @return Una lista di richieste di scambio per l'offerta specificata
     */
    public List<Scambio> getAllScambiByIdOfferta(int idOfferta){
        try(Connection con = ConPool.getConnection()){

            //Preparo la query
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RichiestaDiScambio WHERE idOfferta=?");
            ps.setString(1, String.valueOf(idOfferta));

            // Inizializzo l'ArrayList che conterrà tutte le richieste di scambio
            List<Scambio> scambio = new ArrayList<>();


            //Eseguo la query sul DB
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Scambio u = new Scambio();
                u.setIdRichiestaScambio(rs.getInt(1));
                u.setIdUtenteMittente(rs.getInt(2));
                u.setIdUtenteDestinatario(rs.getInt(3));
                u.setIdOffertaMittente(rs.getInt(4));
                u.setIdUtenteDestinatario(rs.getInt(5));
                u.setConguaglio(rs.getDouble(6));
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
    public List<Scambio> getAllScambiByIdMittente(int idUtenteMittente){
        try(Connection con = ConPool.getConnection()){

            //Preparo la query
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RichiestaDiScambio WHERE idUtenteMittente=?");
            ps.setInt(1, idUtenteMittente);

            // Inizializzo l'ArrayList che conterrà tutte le richieste di scambio
            List<Scambio> scambio = new ArrayList<>();


            //Eseguo la query sul DB
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Scambio u = new Scambio();
                u.setIdRichiestaScambio(rs.getInt(1));
                u.setIdUtenteMittente(rs.getInt(2));
                u.setIdUtenteDestinatario(rs.getInt(3));
                u.setIdOffertaMittente(rs.getInt(4));
                u.setIdUtenteDestinatario(rs.getInt(5));
                u.setConguaglio(rs.getDouble(6));
                scambio.add(u);
            }
            con.close();
            return scambio;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recupera tutte le richieste di scambio inviate a un determinato utente destinatario.
     * @param idUtenteDestinatario ID dell'utente destinatario per cui recuperare le richieste di scambio
     * @return Una lista di richieste di scambio inviate all'utente destinatario specificato
     */
    public List<Scambio> getAllScambiByIdDestinatario(int idUtenteDestinatario){
        try(Connection con = ConPool.getConnection()){

            //Preparo la query
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RichiestaDiScambio WHERE idUtenteDestinatario=?");
            ps.setInt(1, idUtenteDestinatario);

            // Inizializzo l'ArrayList che conterrà tutte le richieste di scambio
            List<Scambio> scambio = new ArrayList<>();


            //Eseguo la query sul DB
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Scambio u = new Scambio();
                u.setIdRichiestaScambio(rs.getInt(1));
                u.setIdUtenteMittente(rs.getInt(2));
                u.setIdUtenteDestinatario(rs.getInt(3));
                u.setIdOffertaMittente(rs.getInt(4));
                u.setIdUtenteDestinatario(rs.getInt(5));
                u.setConguaglio(rs.getDouble(6));
                scambio.add(u);
            }
            con.close();
            return scambio;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recupera tutte le richieste di scambio presenti nel database
     * @return Una lista di tutte le richieste di scambio presenti nel database
     */
    public List<Scambio> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()){
            List<Scambio> scambioList = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM carta;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Scambio u = new Scambio();
                u.setIdRichiestaScambio(rs.getInt(1));
                u.setIdUtenteMittente(rs.getInt(2));
                u.setIdUtenteDestinatario(rs.getInt(3));
                u.setIdOffertaMittente(rs.getInt(4));
                u.setIdUtenteDestinatario(rs.getInt(5));
                u.setConguaglio(rs.getDouble(6));
                scambioList.add(u);
            }
            con.close();
            return scambioList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *
     * Il metodo doRetrieveById recupera una richiesta di scambio specifica dal database utilizzando l'ID della richiesta di scambio.
     * @param IdRichiestaScambio - l'ID della richiesta di scambio da recuperare
     * @return l'oggetto Scambio corrispondente alla richiesta di scambio recuperata dal database
     *
     */
    public Scambio doRetrieveById(int IdRichiestaScambio){
        try(Connection con = ConPool.getConnection()){

            //Preparo la query
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RichiestaDiScambio WHERE IdRichiestaScambio=?");
            ps.setInt(1, IdRichiestaScambio);


            Scambio u = new Scambio();
            //Eseguo la query sul DB
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                u.setIdRichiestaScambio(rs.getInt(1));
                u.setIdUtenteMittente(rs.getInt(2));
                u.setIdUtenteDestinatario(rs.getInt(3));
                u.setIdOffertaMittente(rs.getInt(4));
                u.setIdUtenteDestinatario(rs.getInt(5));
                u.setConguaglio(rs.getDouble(6));
            }
            con.close();
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     *
     * Il metodo doUpdate consente di aggiornare i valori di una richiesta di scambio esistente nel database.
     * @param idScambio - l'ID della richiesta di scambio da aggiornare
     * @param richiestaDiScambio - l'oggetto Scambio con i nuovi valori da inserire nel database
     *
     */
    public void  doUpdate (int idScambio,Scambio richiestaDiScambio){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("UPDATE richiestaDiScambio set IdUtenteMittente=?,IdUtenteDestinatario=?,IdOffertaMittente=?, " +
                    "IdOffertaDestinatario=?, conguaglio=? where idRichiestaDiScambio=?");
            ps.setInt(1,richiestaDiScambio.getIdUtenteMittente());
            ps.setInt(2,richiestaDiScambio.getIdUtenteDestinatario());
            ps.setInt(3,richiestaDiScambio.getIdOffertaMittente());
            ps.setInt(4,richiestaDiScambio.getIdOffertaDestinatario());
            ps.setDouble(5,richiestaDiScambio.getConguaglio());

            ps.setInt(6,idScambio);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
