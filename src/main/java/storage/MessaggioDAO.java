package storage;

import creazioneDiscussione.Messaggio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe permette le operazioni riguardanti gli oggetti Messaggio
 * in relazione al DBMS MySQL
 * @author Raffaele Aviello
 */

public class MessaggioDAO {

    /**
     * Il metodo permette di ottenere tutti gli oggetti Messaggio
     * memorizzati nel database
     */
    public List<Messaggio> doRetrieveAll(){
        try {
            Connection connection = ConPool.getConnection();
            List<Messaggio> messages = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Messaggio;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Messaggio message = new Messaggio();

                message.setIdMessaggio(resultSet.getInt(1));
                message.setOggetto(resultSet.getString(2));
                message.setCorpo(resultSet.getString(3));
                message.setIdUtente(resultSet.getInt(4));
                message.setIdDiscussione(resultSet.getInt(5));

                messages.add(message);
            }

            connection.close();
            return messages;
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo permette di ottenere un oggetto Messaggio con l'id
     * specificato
     * @param idMessaggio id dell' oggetto Messaggio che si vuole
     *                      reperire dal database
     */
    public Messaggio doRetrieveById(int idMessaggio){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Messaggio WHERE idMessaggio = ?;");
            preparedStatement.setInt(1, idMessaggio);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Messaggio message = new Messaggio();

                message.setIdMessaggio(resultSet.getInt(1));
                message.setOggetto(resultSet.getString(2));
                message.setCorpo(resultSet.getString(3));
                message.setIdUtente(resultSet.getInt(4));
                message.setIdDiscussione(resultSet.getInt(5));

                return message;
            }

            return null;
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo permette di memorizzare un oggetto Messaggio
     * nel database
     * @param message il messaggio da memorizzare nel database
     * */
    public void doSave(Messaggio message){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO Messaggio(oggetto, corpo, idUtente, idDiscussione) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, message.getOggetto());
            preparedStatement.setString(2, message.getCorpo());
            preparedStatement.setInt(3, message.getIdUtente());
            preparedStatement.setInt(4, message.getIdDiscussione());

            if(preparedStatement.executeUpdate() != 1){
                throw new RuntimeException("Errore nel salvataggio del messaggio.");
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo permette di eliminare un oggetto Messaggio
     * memorizzato nel database
     * @param messageId id dell' oggetto Messaggio che si vuole
     *                      eliminare dal database
     * */
    public void doDelete(int messageId){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Messaggio WHERE idMessaggio = ?;");

            preparedStatement.setInt(1, messageId);

            if(preparedStatement.executeUpdate() != 1){
                throw new RuntimeException("Errore nell' eliminazione del messaggio.");
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo permette di modificare un oggetto Messaggio
     * memorizzato nel database
     * @param messageId id dell' oggetto Messaggio che si vuole
     * @param messagge oggetto che contiene i campi da modificare
     * */
    public void doUpdate(int messageId, Messaggio messagge){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE Messaggio SET oggetto = ?, corpo = ? " +
                                    "WHERE idMessaggio = ? " +
                                    "AND idUtente = ? and idDiscussione = ?;");

            Messaggio oldMessage = (Messaggio) new FacadeDAO().doRetrieveById(Messaggio.class, messageId);

            preparedStatement.setString(1, messagge.getOggetto());
            preparedStatement.setString(2, messagge.getCorpo());
            preparedStatement.setInt(3, messageId);
            preparedStatement.setInt(4, oldMessage.getIdUtente());
            preparedStatement.setInt(5, oldMessage.getIdDiscussione());

            if(preparedStatement.executeUpdate() != 1){
                throw new RuntimeException("Errore nella modifica del messaggio.");
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
