package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import acquisto.*;

/**
 * La classe permette le operazioni riguardanti gli oggetti Offerta
 * in relazione al DBMS MySQL
 * @author Salvatore Sautariello
 */

public class OffertaDAO {

    //elenco delle query
    private static final String INSERT_OFFERTA_QUERY = "INSERT INTO Offerta(condizione, prezzo, idUtente, idCarta) VALUES (?, ?, ?, ?)";
    private static final String SELECT_OFFERTA_BY_ID_QUERY = "SELECT * FROM Offerta WHERE idOfferta = ?";
    private static final String SELECT_OFFERTE_BY_ID_ORDINE_QUERY = "SELECT o.* FROM Offerta o JOIN ordinecomprendeofferta oco ON o.idOfferta = oco.idOfferta WHERE oco.idOrdine = ?";
    /*
    la query seleziona tutte le colonne (*) dalla tabella "Offerta" o,
    che vengono unite alle colonne della tabella "CarrelloContieneOfferta" cco sulla base dell'uguaglianza dell'idOfferta,
    che a sua volta vengono unite alle colonne della tabella "Carrello" c sulla base dell'uguaglianza dell'idCarrello.
    Infine, la query filtra i risultati utilizzando il parametro idUtente fornito.
     */

    private static final String SELECT_OFFERTE_BY_ID_UTENTE =
            "SELECT idOfferta,condizione,prezzo,o.idUtente,idCarta FROM Offerta o,Utente u WHERE u.idUtente = o.idUtente and o.idUtente=?";
    private static final String SELECT_OFFERTE_BY_ID_UTENTE_CARRELLO_QUERY =
            "SELECT * FROM Offerta o JOIN carrellocontieneofferta cco ON o.idOfferta = cco.idOfferta JOIN Carrello c ON cco.idCarrello = c.idCarrello WHERE c.idUtente = ?";
    private static final String SELECT_ALL_OFFERTE_QUERY = "SELECT * FROM Offerta";
    //private static final String SELECT_OFFERTE_BY_ID_CARRELLO_QUERY = "SELECT * FROM Offerta o INNER JOIN CarrelloContieneOfferta cco ON o.idOfferta = cco.idOfferta WHERE cco.idCarrello = ?";
    private static final String SELECT_OFFERTE_BY_ID_CARTA_QUERY = "SELECT * FROM Offerta WHERE idCarta = ?";
    private static final String UPDATE_OFFERTA_QUERY = "UPDATE Offerta SET condizione = ?, prezzo = ?, idUtente = ?, idCarta = ? WHERE idOfferta = ?";
    private static final String DELETE_OFFERTA_QUERY = "DELETE FROM Offerta WHERE idOfferta = ?";

    /**
     * Il metodo permette di memorizzare un oggetto Offerta
     * nel database
     * @param offerta l'offerta da memorizzare nel database
     * */
    public void doSave(Offerta offerta) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_OFFERTA_QUERY);
            statement.setString(1, offerta.getCondizione());
            statement.setDouble(2, offerta.getPrezzo());
            statement.setInt(3, offerta.getIdUtente());
            statement.setInt(4, offerta.getIdCarta());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Il metodo permette di ottenere un oggetto Offerta con l'id
     * specificato
     * @param idOfferta id dell' oggetto Offerta che si vuole
     *                      reperire dal database
     * @return un oggetto Offerta il cui id coincide con quello specificato
     *                      come parametro
     */
    public Offerta doRetrieveById(int idOfferta) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_OFFERTA_BY_ID_QUERY);
            statement.setInt(1, idOfferta);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String condizione = resultSet.getString("condizione");
                double prezzo = resultSet.getDouble("prezzo");
                int idUtente = resultSet.getInt("idUtente");
                int idCarta = resultSet.getInt("idCarta");
                return new Offerta(idOfferta, condizione, prezzo, idUtente, idCarta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Il metodo permette di ottenere tutti gli oggetti Offerta
     * memorizzati nel database
     * @return Una lista di oggetti Offerta che contiene tutte
     *                      le istanze di oggetti Offerta nel database
     */
    public List < Offerta > doRetrieveAll() {
        List < Offerta > offerte = new ArrayList < > ();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_ALL_OFFERTE_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idOfferta = resultSet.getInt("idOfferta");
                String condizione = resultSet.getString("condizione");
                double prezzo = resultSet.getDouble("prezzo");
                int idUtente = resultSet.getInt("idUtente");
                int idCarta = resultSet.getInt("idCarta");
                Offerta offerta = new Offerta(idOfferta, condizione, prezzo, idUtente, idCarta);
                offerte.add(offerta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerte;
    }

    /**
     * Il metodo permette di ottenere un oggetto Offerta con l'idUtente
     * specificato
     * @param idUtente idUtente della lista di oggetto Offerta che si vuole
     *                      reperire dal database
     * @return Una lista di oggetti Offerta che contiene le istanze di
     *                      oggetti Offerta con l'idUtente specificato nel database
     */
    public List<Offerta> getOfferteByIdUtente(int idUtente) throws SQLException{
        List<Offerta> offerte = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_OFFERTE_BY_ID_UTENTE);
            statement.setInt(1, idUtente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idOfferta = resultSet.getInt("idOfferta");
                String condizione = resultSet.getString("condizione");
                double prezzo = resultSet.getInt("prezzo");
                int idCarta = resultSet.getInt("idCarta");
                Offerta offerta = new Offerta(idOfferta, condizione, prezzo, idUtente, idCarta);
                offerte.add(offerta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerte;
    }

    /**
     * Il metodo permette di ottenere un oggetto Offerta con l'idUtente
     * specificato
     * @param idOrdine idOrdine dell'oggetto Ordine cui si vuole repererire la lista di oggetti Offerta
     * @return Una lista di oggetti Offerta che contiene le istanze di
     *                      oggetti Offerta con l'idOrdine specificato nel database
     */
    public List<Offerta> getOfferteByIdOrdine(int idOrdine) throws SQLException {
        // Crea una lista vuota di offerte
        List<Offerta> offerte = new ArrayList<>();

        // Apre una connessione al database
        Connection conn = ConPool.getConnection();

        // Crea un PreparedStatement utilizzando la query creata in precedenza
        PreparedStatement stmt = conn.prepareStatement(SELECT_OFFERTE_BY_ID_ORDINE_QUERY);

        // Imposta il parametro della query con l'id dell'ordine passato come argomento
        stmt.setString(1, String.valueOf(idOrdine));

        // Esegue la query e ottiene il risultato
        ResultSet rs = stmt.executeQuery();

        // Itera sui risultati della query e crea un oggetto Offerta per ogni riga del risultato
        while (rs.next()) {
            int idOfferta = rs.getInt("idOfferta");
            String condizione = rs.getString("condizione");
            double prezzo = rs.getDouble("prezzo");
            int idUtente = rs.getInt("idUtente");
            int idCarta = rs.getInt("idCarta");


            Offerta offerta = new Offerta(idOfferta, condizione, prezzo, idUtente, idCarta);
            offerte.add(offerta);
        }

        return offerte;
    }


    /**
     * Il metodo permette di ottenere un oggetto Offerta con l'idUtente
     * specificato
     * @param idCarta idCarta della lista di oggetto Offerta che si vuole
     *      *                      reperire dal database
     * @return Una lista di oggetti Offerta che contiene le istanze di
     *                      oggetti Offerta con l'idCarta specificato nel database
     */
    public List<Offerta> getOfferteByIdCarta(int idCarta) {
        List<Offerta> offerte = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_OFFERTE_BY_ID_CARTA_QUERY);
            statement.setInt(1, idCarta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idOfferta = resultSet.getInt("idOfferta");
                int idUtente = resultSet.getInt("idUtente");
                String condizione = resultSet.getString("condizione");
                double prezzo = resultSet.getDouble("prezzo");
                Offerta offerta = new Offerta(idOfferta, condizione, prezzo, idUtente, idCarta);
                offerte.add(offerta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerte;
    }

    public List<Offerta> getOfferteByIdUtenteCarrello(int idUtente)
    {
        List<Offerta> offerte = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_OFFERTE_BY_ID_UTENTE_CARRELLO_QUERY);
            statement.setInt(1, idUtente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idOfferta = resultSet.getInt("idOfferta");
                int vend = resultSet.getInt("idUtente");
                String condizione = resultSet.getString("condizione");
                double prezzo = resultSet.getDouble("prezzo");
                int idCarta = resultSet.getInt("idCarta");
                Offerta offerta = new Offerta(idOfferta, condizione, prezzo, vend, idCarta);
                offerte.add(offerta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerte;
    }



    /**
     * Il metodo permette di modificare un oggetto Offerta
     * memorizzato nel database
     * @param idOfferta id dell' oggetto Offerta che si vuole
     * @param offerta oggetto che contiene i campi da modificare
     * */
    public void doUpdate(int idOfferta, Offerta offerta){
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement statement = con.prepareStatement(UPDATE_OFFERTA_QUERY);
                statement.setInt(1,  idOfferta);
                statement.setString(2, offerta.getCondizione());
                statement.setDouble(3, offerta.getPrezzo());
                statement.setInt(4, offerta.getIdUtente());
                statement.setDouble(5, offerta.getIdCarta());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    /**
     * Il metodo permette di eliminare un oggetto Offerta
     * memorizzato nel database
     * @param idOfferta id dell' oggetto Offerta che si vuole
     *                      eliminare dal database
     * */
    public void doDelete(int idOfferta) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(DELETE_OFFERTA_QUERY);
            statement.setInt(1, idOfferta);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}






