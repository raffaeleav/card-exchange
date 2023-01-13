package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import storage.ConPool;
import acquisto.Ordine;
import acquisto.Offerta;

/**
 * La classe permette le operazioni riguardanti gli oggetti Ordine
 * in relazione al DBMS MySQL
 * @author Salvatore Sautariello
 */
public class OrdineDAO {
    private static final String INSERT_ORDINE_QUERY = "INSERT INTO Ordine(data, indirizzo, idUtente, totale) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ORDINE_BY_ID_QUERY = "SELECT * FROM Ordine WHERE idOrdine = ?";

    private static final String SELECT_ALL_ORDINI = " SELECT * FROM Ordine";
    private static final String SELECT_ORDINI_BY_ID_UTENTE_QUERY = "SELECT * FROM Ordine WHERE idUtente = ?";
    private static final String UPDATE_ORDINE_QUERY = "UPDATE Ordine SET data = ?, indirizzo = ?, idUtente = ?, totale = ? WHERE idOrdine = ?";
    private static final String DELETE_ORDINE_QUERY = "DELETE FROM Ordine WHERE idOrdine = ?";
    private static final String UPDATE_ORDINECONTIENEOFFERTA_QUERY= "INSERT INTO OrdineContieneOfferta (idOrdine, idOfferta) VALUES (?, ?)";

    /**
     * Il metodo permette di memorizzare un oggetto Ordine
     * nel database
     * @param ordine l'ordine da memorizzare nel database
     * */
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

    /**
     * Il metodo permette di ottenere un oggetto Ordine con l'id
     * specificato
     * @param idOrdine id dell' oggetto Ordine che si vuole
     *                      reperire dal database
     * @return un oggetto Ordine il cui id coincide con quello specificato
     *                      come parametro
     */
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

                return new Ordine(idOrdine, data, indirizzo, idUtente, totale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Il metodo permette di ottenere una lista di oggetti Offerta con l'idUtente
     * specificato
     * @param idUtente idUtente della lista di oggetto Offerta che si vuole
     *                      reperire dal database
     * @return Una lista di oggetti Offerta che contiene le istanze di
     *                      oggetti Offerta con l'idUtente specificato nel database
     */
    public List<Ordine> getOrdiniByIdUtente(int idUtente) {
        List<Ordine> ordini = new ArrayList<>();
        try (Connection conn = ConPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ORDINI_BY_ID_UTENTE_QUERY)) {
            stmt.setInt(1, idUtente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idOrdine = rs.getInt("idOrdine");
                Date data = rs.getDate("data");
                String indirizzo = rs.getString("indirizzo");

                double totale = rs.getDouble("totale");
                Ordine ordine = new Ordine(idOrdine, data, indirizzo, idUtente, totale);
                ordini.add(ordine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordini;
    }

    /**
     * Il metodo permette di modificare un oggetto Ordine
     * memorizzato nel database
     * @param idOrdine id dell' oggetto Ordine che si vuole modificare
     * @param ordine oggetto che contiene i campi da modificare
     * */
    public void doUpdate(int idOrdine, Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(UPDATE_ORDINE_QUERY);
            statement.setDate(1, (java.sql.Date) ordine.getData());
            statement.setString(2, ordine.getIndirizzo());
            statement.setInt(3, ordine.getIdUtente());
            statement.setInt(4, idOrdine);
            statement.setDouble(5, ordine.getTotale());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Il metodo permette di aggiornare la tabella OrdineContieneOfferte
     * @param idOrdine id dell' oggetto Ordine cui si vuole aggiungere l'offerta
     * @param idOfferte id delle offerte da aggiungere
     * */
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

    /**
     * Il metodo permette di ottenere tutti gli oggetti Ordine
     * memorizzati nel database
     * @return Una lista di oggetti Ordine che contiene tutte
     *                      le istanze di oggetti Ordine nel database
     */
    public List < Ordine > doRetrieveAll() {
        List < Ordine > ordini = new ArrayList < > ();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_ALL_ORDINI);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idOrdine = resultSet.getInt("idOrdine");
                Date data = resultSet.getDate("data");
                String indirizzo = resultSet.getString("indirizzo");
                int idUtente = resultSet.getInt("idUtente");
                double totale = resultSet.getDouble("totale");

                Ordine ordine = new Ordine(idOrdine, data, indirizzo, idUtente,totale);
                ordini.add(ordine);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordini;

    }

    /**
     * Il metodo permette di eliminare un oggetto Ordine
     * memorizzato nel database
     * @param idOrdine id dell' oggetto Ordine che si vuole
     *                      eliminare dal database
     * */
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