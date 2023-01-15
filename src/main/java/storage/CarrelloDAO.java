package storage;

import acquisto.Carrello;
import acquisto.Offerta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe permette le operazioni riguardanti gli oggetti Carrello
 * in relazione al DBMS MySQL
 * @author Salvatore
 */
public class CarrelloDAO {
    private static final String INSERT_CARRELLO_QUERY = "INSERT INTO Carrello (idutente,totale) VALUES (?, ?)";
    private static final String SELECT_CARRELLO_BY_ID_QUERY = "SELECT * FROM Carrello WHERE idCarrello = ?";
    private static final String SELECT_CARRELLO_BY_ID_UTENTE_QUERY = "SELECT * FROM Carrello WHERE idUtente = ?";
    private static final String SELECT_ALL_CARRELLI_QUERY = "SELECT * FROM Carrello";
    private static final String UPDATE_CARRELLO_QUERY ="UPDATE Carrello SET idCarrello=?, idUtente=? WHERE idCarrello=? ";
    private static final String DELETE_CARRELLO_QUERY = "DELETE FROM Carrello WHERE idCarrello = ?";
    private static final String INSERT_INTO_CARRELLOCONTIENEOFFERTA_QUERY = "INSERT INTO CarrelloContieneOfferta (idCarrello, idOfferta) VALUES (?, ?)";
    private static final String DELETE_FROM_CARRELLOCONTIENEOFFERTA_QUERY = "DELETE FROM CarrelloContieneOfferta WHERE idCarrello = ? and idOfferta = ?";
    private static final String DELETE_OFFERTE_FROM_CARRELLO_QUERY = "DELETE FROM CarrelloContieneOfferta WHERE idCarrello = ?";

    private static final String SELECT_TOTALE_BY_ID_CARRELLO_QUERY = "SELECT SUM(Offerta.prezzo) as prezzo FROM CarrelloContieneOfferta JOIN Offerta ON CarrelloContieneOfferta.idOfferta = Offerta.idOfferta WHERE CarrelloContieneOfferta.idCarrello = ?;";

    private static final String UPDATE_TOTALE_CARRELLO_QUERY = "UPDATE Carrello SET totale = ? WHERE idCarrello = ?";



    /**
     * Il metodo permette di memorizzare un oggetto Carrello
     * nel database
     * @param carrello il carrello da memorizzare nel database
     * */
    public void doSave(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_CARRELLO_QUERY);
            statement.setInt(1, carrello.getIdUtente());
            statement.setDouble(2, carrello.getTotale());
            if(statement.executeUpdate()!=1){
                throw new RuntimeException("Errore durante l'inserimento del carrello");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Il metodo permette di ottenere un oggetto Carrello con l'id
     * specificato
     * @param idCarrello id dell' oggetto Carrello che si vuole
     *                      reperire dal database
     * @return un oggetto Carrello il cui id coincide con quello specificato
     *                      come parametro
     */
    public Carrello doRetrieveById(int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_CARRELLO_BY_ID_QUERY);
            statement.setInt(1, idCarrello);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Carrello carrello =new Carrello();
                carrello.setIdCarrello(resultSet.getInt(1));
                carrello.setIdUtente(resultSet.getInt(2));
                carrello.setTotale(resultSet.getDouble(3));
                return carrello;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    /**
     * Il metodo permette di ottenere un oggetto Carrello con l'id dell'Utente
     * specificato
     * @param idUtente id dell' oggetto Utente che si vuole
     *                      reperire dal database
     * @return un oggetto Carrello il cui idUtente coincide con quello specificato
     *                      come parametro
     */
    public Carrello getCarrelloByIdUtente(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_CARRELLO_BY_ID_UTENTE_QUERY);
            statement.setInt(1, idUtente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                    Carrello carrello = new Carrello();
                    carrello.setIdCarrello(resultSet.getInt(1));
                    carrello.setIdUtente(resultSet.getInt(2));
                    carrello.setTotale(resultSet.getInt(3));
                    return carrello;
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
            return null;
        }


    /**
     * Il metodo permette di ottenere tutti gli oggetti Carrello
     * memorizzati nel database
     * @return Una lista di oggetti Carrello che contiene tutte
     *                      le istanze di oggetti Carrello nel database
     */
    public List<Carrello> doRetrieveAll() {
        List<Carrello> carrelli = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_ALL_CARRELLI_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idCarrello = resultSet.getInt("idCarrello");
                int idUtente = resultSet.getInt("idUtente");
                double totale = resultSet.getDouble("totale");
                carrelli.add(new Carrello(idCarrello, idUtente,totale));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carrelli;
    }

    /**
     * Il metodo permette di modificare un oggetto Carrello
     * memorizzato nel database
     * @param idCarrello id dell' oggetto Carrello che si vuole
     * @param carrello oggetto che contiene i campi da modificare
     * */
    public void doUpdate(int idCarrello,Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(UPDATE_CARRELLO_QUERY);
            statement.setInt(1, carrello.getIdUtente());
            statement.setInt(2, idCarrello);
            statement.setDouble(3, carrello.getTotale());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Il metodo permette di aggiungere un oggetto Offerta al Carrello salvando nella table CarrelloContieneOfferta
     * memorizzato nel database
     * @param offerta oggetto che contiene i campi dell'offerta da aggiungere alla table
     * @param idCarrello id dell'oggetto Carrello al quale aggiungere l'offerta nella table CarrelloContieneOfferta
     * */
    public void aggiungiOfferta(Offerta offerta, int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_INTO_CARRELLOCONTIENEOFFERTA_QUERY);
            statement.setInt(1, idCarrello);
            statement.setInt(2, offerta.getIdOfferta());
            statement.executeUpdate();
            double totale = calcolaTotale(idCarrello);
            statement = con.prepareStatement(UPDATE_TOTALE_CARRELLO_QUERY);
            statement.setDouble(1,totale);
            statement.setInt(2, idCarrello);
            statement.executeUpdate();
            Carrello carrello = new Carrello();
            carrello.setIdCarrello(idCarrello);
            carrello.setTotale(totale);
            doUpdate(idCarrello,carrello);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Il metodo permette di rimuovere un oggetto Offerta dal Carrello e dalla table CarrelloContieneOfferta
     * memorizzato nel database
     * @param offerta oggetto che contiene i campi dell'offerta da rimuovere dalla table
     * @param idCarrello id dell'oggetto carrello al quale rimuovere l'offerta nella table CarrelloContieneOfferta
     * */
    public void rimuoviOfferta(Offerta offerta, int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(DELETE_FROM_CARRELLOCONTIENEOFFERTA_QUERY);
            statement.setInt(1, idCarrello);
            statement.setInt(2, offerta.getIdOfferta());
            statement.executeUpdate();
            double totale = calcolaTotale(idCarrello);
            statement = con.prepareStatement(UPDATE_TOTALE_CARRELLO_QUERY);
            statement.setDouble(1,totale);
            statement.setInt(2, idCarrello);
            statement.executeUpdate();
            Carrello carrello = new Carrello();
            carrello.setIdCarrello(idCarrello);
            carrello.setTotale(totale);
            doUpdate(idCarrello,carrello);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Il metodo permette di calcolare l'attributo totale del Carrello dell' id corrispondente
     * sommando il prezzo delle offerte associate nella table CarrelloContieneOfferte
     * @param idCarrello id dell'oggetto Carrello al quale si vuole calcolare il totale
     * */

    public double calcolaTotale(int idCarrello) {
        double totale = 0.0;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_TOTALE_BY_ID_CARRELLO_QUERY);
            statement.setInt(1, idCarrello);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                totale += resultSet.getDouble("prezzo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totale;
    }

    /**
     * Il metodo permette di svuotare un oggetto Carrello da tutte le sue offerte  dalla table CarrelloContieneOfferta
     * memorizzato nel database
     * @param idCarrello id dell'oggetto Carrello da svuotare
     * */
    public void svuotaCarrello(int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(DELETE_OFFERTE_FROM_CARRELLO_QUERY);
            statement.setInt(1, idCarrello);
            statement.executeUpdate();
            statement = con.prepareStatement(UPDATE_TOTALE_CARRELLO_QUERY);
            statement.setDouble(1, 0.0);
            statement.setInt(2, idCarrello);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Il metodo permette di eliminare un oggetto Carrello
     * memorizzato nel database
     * @param idCarrello id dell' oggetto Carrello che si vuole
     *                      eliminare dal database
     * */
    public void doDelete(int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(DELETE_CARRELLO_QUERY);
            statement.setInt(1, idCarrello);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}