package storage;

import acquisto.Offerta;
import acquisto.Ordine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe permette le operazioni riguardanti gli oggetti Ordine
 * in relazione al DBMS MySQL
 * @author Salvatore Sautariello
 */
public class OrdineDAO {
    private static final String INSERT_ORDINE_QUERY = "INSERT INTO Ordine(dataset, indirizzo, idUtente, totale) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ORDINE_BY_ID_QUERY = "SELECT * FROM Ordine WHERE idOrdine = ?";
    private static final String SELECT_ALL_ORDINI = " SELECT * FROM Ordine";
    private static final String SELECT_ORDINI_BY_ID_UTENTE_QUERY = "SELECT * FROM Ordine WHERE idUtente = ?";
    private static final String UPDATE_ORDINE_QUERY = "UPDATE Ordine SET dataset = ?, indirizzo = ?, idUtente = ?, totale = ? WHERE idOrdine = ?";
    private static final String DELETE_ORDINE_QUERY = "DELETE FROM Ordine WHERE idOrdine = ?";
    private static final String UPDATE_ORDINECONTIENEOFFERTA_QUERY= "INSERT INTO OrdineComprendeOfferta (idOrdine, idOfferta) VALUES (?,?)";

    private static final String SELECT_ORDINE_BY_DATA_TOTALE ="SELECT * FROM Ordine WHERE idUtente =? AND dataset = ?AND totale = ?";
    /**
     * Il metodo permette di memorizzare un oggetto Ordine
     * nel database
     * @param ordine l'ordine da memorizzare nel database
     * */
    public void doSave(Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_ORDINE_QUERY);
            statement.setString(1, ordine.getData());
            statement.setString(2, ordine.getIndirizzo());
            statement.setInt(3, ordine.getIdUtente());
            statement.setDouble(4, ordine.getTotale());
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
                String data = resultSet.getString("dataset");
                String indirizzo = resultSet.getString("indirizzo");
                double totale = resultSet.getDouble("totale");

                return new Ordine(idOrdine, data, indirizzo, idUtente, totale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Ordine doRetrieveByIdUtenteDataTotale(int idUtente, String data, double totale) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_ORDINE_BY_DATA_TOTALE );

            statement.setInt(1, idUtente);
            statement.setString(2, data);
            statement.setDouble(3,totale);
            ResultSet resultSet = statement.executeQuery();
            FacadeDAO facadeDAO = new FacadeDAO();
            if (resultSet.next()) {
                int idOrdine = resultSet.getInt("idOrdine");
                System.out.println(idOrdine);
                return (Ordine) facadeDAO.doRetrieveById(Ordine.class,idOrdine);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public List<Ordine> getOrdiniByIdUtente(int idUtente){
        List<Ordine> ordini = new ArrayList<>();
        try(Connection con = ConPool.getConnection()){

            //Preparo la query
            PreparedStatement ps = con.prepareStatement(SELECT_ORDINI_BY_ID_UTENTE_QUERY);
            ps.setInt(1, idUtente);
            //Eseguo la query sul DB
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Ordine ordine = new Ordine (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5));
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
            statement.setString(1, ordine.getData());
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
     * @param offerte lista delle offerte da aggiungere
     * */
    public void addOfferteToOrder(List<Offerta> offerte, int idOrdine) {

        try (Connection con = ConPool.getConnection()) {
             PreparedStatement stmt = con.prepareStatement(UPDATE_ORDINECONTIENEOFFERTA_QUERY);
            for (Offerta offerta : offerte) {
                stmt.setInt(1, idOrdine);
                stmt.setInt(2, offerta.getIdOfferta());
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
                String data = resultSet.getString("dataset");
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