package storage;
import storage.ConPool;
import acquisto.Carrello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO {
    private static final String INSERT_CARRELLO_QUERY = "INSERT INTO Carrello (idCarrello, idUtente, totale) VALUES (?, ?, ?)";
    private static final String SELECT_CARRELLO_BY_ID_QUERY = "SELECT * FROM Carrello WHERE idCarrello = ?";
    private static final String SELECT_ALL_CARRELLI_QUERY = "SELECT * FROM Carrello";
    private static final String DELETE_CARRELLO_QUERY = "DELETE FROM Carrello WHERE idCarrello = ?";

    // Inserisce un nuovo carrello nel database
    public void addCarrello(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(INSERT_CARRELLO_QUERY);
            stmt.setInt(1, carrello.getIdCarrello());
            stmt.setInt(2, carrello.getIdUtente());
            stmt.setInt(3, carrello.getTotale());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Restituisce il carrello con l'ID specificato, null se non esiste
    public Carrello getCarrelloById(int idCarrello) {
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

    // Restituisce tutti i carrelli presenti nel database
    public List<Carrello> getAllCarrelli() {
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

    // Elimina il carrello con l'ID specificato dal database
    public void deleteCarrello(int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(DELETE_CARRELLO_QUERY);
            statement.setInt(1, idCarrello);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}