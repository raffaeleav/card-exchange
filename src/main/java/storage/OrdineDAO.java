package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import storage.ConPool;
import acquisto.Ordine;
import acquisto.Offerta;

public class OrdineDAO {
    private static final String INSERT_ORDINE_QUERY = "INSERT INTO Ordine(data, indirizzo, idUtente, totale) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ORDINE_BY_ID_QUERY = "SELECT * FROM Ordine WHERE idOrdine = ?";
    private static final String SELECT_ORDINI_BY_ID_UTENTE_QUERY = "SELECT * FROM Ordine WHERE idUtente = ?";
    private static final String UPDATE_ORDINE_QUERY = "UPDATE Ordine SET data = ?, indirizzo = ?, idUtente = ?, totale = ? WHERE idOrdine = ?";
    private static final String DELETE_ORDINE_QUERY = "DELETE FROM Ordine WHERE idOrdine = ?";
    private static final String UPDATE_ORDINECONTIENEOFFERTA_QUERY= "INSERT INTO OrdineContieneOfferta (idOrdine, idOfferta) VALUES (?, ?)";

    public void doSave(Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_ORDINE_QUERY);
            statement.setDate(1, (java.sql.Date) ordine.getData());
            statement.setString(2, ordine.getIndirizzo());
            statement.setInt(3, ordine.getIdUtente());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ordine doRetrieveById(int idOrdine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_ORDINE_BY_ID_QUERY);
            statement.setInt(1, idOrdine);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idUtente = resultSet.getInt("idUtente");
                Date data = resultSet.getDate("data");
                String indirizzo = resultSet.getString("indirizzo");
                double totale = resultSet.getDouble("totale");
                OffertaDAO offertaDAO= new OffertaDAO();
                // Recupera le offerte dell'ordine utilizzando il metodo getOfferteByIdOrdine del DAO OffertaDAO
                List<Offerta> offerte = offertaDAO.getOfferteByIdOrdine(idOrdine);
                return new Ordine(idOrdine, data, indirizzo, idUtente,offerte, totale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Ordine> doRetrieveByIdUtente(int idUtente) {
        List<Ordine> ordini = new ArrayList<>();
        try (Connection conn = ConPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ORDINI_BY_ID_UTENTE_QUERY)) {
            stmt.setInt(1, idUtente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idOrdine = rs.getInt("idOrdine");
                Date data = rs.getDate("data");
                String indirizzo = rs.getString("indirizzo");
                // Recupera le offerte dell'ordine utilizzando il metodo getOfferteByIdOrdine del DAO OffertaDAO
                OffertaDAO offertaDAO= new OffertaDAO();
                List<Offerta> offerte = offertaDAO.getOfferteByIdOrdine(idOrdine);
                double totale = rs.getDouble("totale");
                Ordine ordine = new Ordine(idOrdine, data, indirizzo, idUtente, offerte, totale);
                ordini.add(ordine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordini;
    }


    public void doUpdate(Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(UPDATE_ORDINE_QUERY);
            statement.setDate(1, (java.sql.Date) ordine.getData());
            statement.setString(2, ordine.getIndirizzo());
            statement.setInt(3, ordine.getIdUtente());
            statement.setInt(4, ordine.getIdOrdine());
            statement.setDouble(5, ordine.getTotale());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOfferteToOrdine(int idOrdine, List<Integer> idOfferte){
        // Apertura della connessione
        try (Connection conn = ConPool.getConnection()) {
            // Creazione del PreparedStatement
            PreparedStatement stmt = conn.prepareStatement(UPDATE_ORDINECONTIENEOFFERTA_QUERY);
            // Imposta i parametri della query
            stmt.setInt(1, idOrdine);

            // Esegue la query per ogni offerta presente nella lista
            for (Integer idOfferta : idOfferte) {
                stmt.setInt(2, idOfferta);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void doDelete(int idOrdine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(DELETE_ORDINE_QUERY);
            statement.setInt(1, idOrdine);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}