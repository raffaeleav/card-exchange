package storage;

import acquisto.Carrello;
import acquisto.Offerta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO {
    private static final String INSERT_CARRELLO_QUERY = "INSERT INTO Carrello (idCarrello, idUtente, totale) VALUES (?, ?, ?)";
    private static final String SELECT_CARRELLO_BY_ID_QUERY = "SELECT * FROM Carrello WHERE idCarrello = ?";
    private static final String SELECT_CARRELLO_BY_ID_UTENTE_QUERY = "SELECT * FROM Carrello WHERE idUtente = ?";
    private static final String SELECT_ALL_CARRELLI_QUERY = "SELECT * FROM Carrello";
    private static final String UPDATE_CARRELLO_QUERY ="UPDATE Carrello SET id Carrello=?, idUtente=?, totale=? WHERE idCarrello=? ";
    private static final String DELETE_CARRELLO_QUERY = "DELETE FROM Carrello WHERE idCarrello = ?";
    private static final String INSERT_INTO_CARRELLOCONTIENEOFFERTA_QUERY = "INSERT INTO CarrelloContieneOfferta (idCarrello, idOfferta) VALUES (?, ?)";
    private static final String DELETE_FROM_CARRELLOCONTIENEOFFERTA_QUERY = "DELETE FROM CarrelloContieneOfferta WHERE idCarrello = ? and idOfferta = ?";
    private static final String DELETE_OFFERTE_FROM_CARRELLO_QUERY = "DELETE FROM CarrelloContieneOfferta WHERE idCarrello = ?";

    private static final String SELECT_TOTALE_BY_ID_CARRELLO_QUERY = "SELECT SUM(Offerta.prezzo) as prezzo FROM CarrelloContieneOfferta " +
            "JOIN Offerta ON CarrelloContieneOfferta.idOfferta = Offerta.idOfferta" +
            "WHERE CarrelloContieneOfferta.idCarrello = ?;";

    private static final String UPDATE_TOTALE_CARRELLO_QUERY = "UPDATE Carrello SET totale = ? WHERE idCarrello = ?";



    // Inserisce un nuovo carrello nel database
    public void doSave(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_CARRELLO_QUERY);
            statement.setInt(1, carrello.getIdCarrello());
            statement.setInt(2, carrello.getIdUtente());
            statement.setDouble(3, carrello.getTotale());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Restituisce il carrello con l'ID specificato, null se non esiste
    public Carrello doRetrieveById(int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_CARRELLO_BY_ID_QUERY);
            statement.setInt(1, idCarrello);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idUtente = resultSet.getInt("idUtente");
                return new Carrello(idCarrello, idUtente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
    Restituisce il carrello associato all'ID dell'utente specificato, null se non esiste
    Questo metodo recupera il carrello dell'utente dal database utilizzando l'id dell'utente come parametro
    e utilizzando la stringa di query SELECT_CARRELLO_BY_ID_UTENTE_QUERY
    Una volta recuperato il carrello, viene chiamato il metodo getOfferteByIdUtente del DAO OffertaDAO
    per recuperare le offerte presenti nel carrello dell'utente
    infine viene restituito un oggetto Carrello con l'id del carrello e l'id dell'utente specificati
    Se il carrello non viene trovato, viene restituito null.
     */
    public Carrello getCarrelloByIdUtente(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_CARRELLO_BY_ID_UTENTE_QUERY);
            statement.setInt(1, idUtente);
            ResultSet resultSet = statement.executeQuery();
            OffertaDAO offertaDAO = new OffertaDAO();
            if (resultSet.next()) {
                int idCarrello = resultSet.getInt("idCarrello");
                // Recupera le offerte presenti nel carrello dal database
                // utilizzando il metodo getOfferteByIdUtente del DAO OffertaDAO
                List < Offerta > offerte = offertaDAO.getOfferteByIdUtente(idUtente);
                // Crea un oggetto Carrello con l'ID del carrello e l'ID dell'utente
                return new Carrello(idCarrello, idUtente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Restituisce tutti i carrelli presenti nel database
    public List<Carrello> doRetrieveAll() {
        List<Carrello> carrelli = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_ALL_CARRELLI_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idCarrello = resultSet.getInt("idCarrello");
                int idUtente = resultSet.getInt("idUtente");
                carrelli.add(new Carrello(idCarrello, idUtente));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carrelli;
    }

    // Aggiorna il carrello nel database
    public void doUpdate(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(UPDATE_CARRELLO_QUERY);
            statement.setInt(1, carrello.getIdUtente());
            statement.setDouble(2, carrello.getTotale());
            statement.setInt(3, carrello.getIdCarrello());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void aggiungiOfferta(Offerta offerta, int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_INTO_CARRELLOCONTIENEOFFERTA_QUERY);
            statement.setInt(1, idCarrello);
            statement.setInt(2, offerta.getIdOfferta());
            statement.executeUpdate();
            Carrello carrello = new Carrello();
            carrello.setIdCarrello(idCarrello);
            carrello.setTotale(calcolaTotale(idCarrello));
            //doUpdate(carrello);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rimuoviOfferta(Offerta offerta, int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(DELETE_FROM_CARRELLOCONTIENEOFFERTA_QUERY);
            statement.setInt(1, idCarrello);
            statement.setInt(2, offerta.getIdOfferta());
            statement.executeUpdate();
            Carrello carrello = new Carrello();
            carrello.setIdCarrello(idCarrello);
            carrello.setTotale(calcolaTotale(idCarrello));
            //doUpdate(carrello);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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



    // Elimina il carrello con l'ID specificato dal database
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