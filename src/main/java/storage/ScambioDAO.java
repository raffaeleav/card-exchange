package storage;

import acquisto.Carta;
import registrazione.Utente;
import scambio.Scambio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    // Permette di recuperare tutte le richieste scambio effettuate da un utente
    public ArrayList<Scambio> getAllScambiByIdMittente(int idUtenteMittente){
        try(Connection con = ConPool.getConnection()){

            //Preparo la query
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RichiestaDiScambio WHERE idUtenteMittente=?");
            ps.setInt(1, idUtenteMittente);

            // Inizializzo l'ArrayList che conterrà tutte le richieste di scambio
            ArrayList<Scambio> scambio = new ArrayList<>();


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
     * @param idUtenteDestinatario - ID dell'utente Destinatario
     * @return tutte le richieste di scambio ricevute
     */
    // Permette di recuperare tutte le richieste scambio ricevute da un utente
    public ArrayList<Scambio> getAllScambiByIdDestinatario(int idUtenteDestinatario){
        try(Connection con = ConPool.getConnection()){

            //Preparo la query
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RichiestaDiScambio WHERE idUtenteDestinatario=?");
            ps.setInt(1, idUtenteDestinatario);

            // Inizializzo l'ArrayList che conterrà tutte le richieste di scambio
            ArrayList<Scambio> scambio = new ArrayList<>();


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

    void doUpdate (int idScambio,Scambio richiestaDiScambio){
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
