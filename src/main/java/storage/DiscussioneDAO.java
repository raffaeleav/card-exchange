package storage;

import creazioneDiscussione.Discussione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe permette le operazioni riguardanti gli oggetti Discussione
 * in relazione al DBMS MySQL
 * @author Raffaele Aviello
 */

public class DiscussioneDAO {

    /**
     * Il metodo permette di ottenere tutti gli oggetti Discussione
     * memorizzati nel database
     */
    public List<Discussione> doRetrieveAll(){
        try {
            Connection connection = ConPool.getConnection();
            List<Discussione> topics = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Discussione;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Discussione topic = new Discussione();

                topic.setIdDiscussione(resultSet.getInt(1));
                topic.setIdUtente(resultSet.getInt(2));
                topic.setTitolo(resultSet.getString(3));

                topics.add(topic);
            }

            connection.close();
            return topics;
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo permette di ottenere un oggetto Discussione con l'id
     * specificato
     * @param topicId id dell' oggetto Discussione che si vuole
     *                      reperire dal database
     */
    public Discussione doRetrieveById(int topicId){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Discussione WHERE idDiscussione = ?;");

            preparedStatement.setInt(1, topicId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Discussione topic = new Discussione();

                topic.setIdDiscussione(resultSet.getInt(1));
                topic.setIdUtente(resultSet.getInt(2));
                topic.setTitolo(resultSet.getString(3));

                return topic;
            }

            return null;
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo permette di memorizzare un oggetto Discussione
     * nel database
     * @param topic la discussione da memorizzare nel database
     * */
    public void doSave(Discussione topic){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Discussione(idUtente, titolo) VALUES (?, ?);");

            preparedStatement.setInt(1, topic.getIdUtente());
            preparedStatement.setString(2, topic.getTitolo());

            if(preparedStatement.executeUpdate() != 1){
                throw new RuntimeException("Errore nel salvataggio della discussione.");
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo permette di eliminare un oggetto Discussione
     * memorizzato nel database
     * @param topicId id dell' oggetto Discussione che si vuole
     *                      eliminare dal database
     * */
    public void doDelete(int topicId){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Discussione WHERE idDiscussione = ?;");

            preparedStatement.setInt(1, topicId);

            if(preparedStatement.executeUpdate() != 1){
                throw new RuntimeException("Errore nell' eliminazione della discussione.");
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
