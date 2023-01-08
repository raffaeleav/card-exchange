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
     * @param oggetto oggetto del messaggio
     * @param corpo corpo del messaggio
     * @param idUtente id dell' utente che ha scritto il messaggio
     * @param idDiscussione id della discussione in cui si sta inviando il messaggio
     * */
    public void doSave(String oggetto, String corpo, int idUtente, int idDiscussione){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO Messaggio(oggetto, corpo, idUtente, idDiscussione) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, corpo);
            preparedStatement.setString(2, corpo);
            preparedStatement.setInt(3, idUtente);
            preparedStatement.setInt(4, idDiscussione);

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
     * @param idMessaggio id dell' oggetto Messaggio che si vuole
     *                      eliminare dal database
     * */
    public void doDelete(int idMessaggio, int idUtente){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Messaggio WHERE idMessaggio = ? AND idUtente = ?;");

            preparedStatement.setInt(1, idMessaggio);
            preparedStatement.setInt(1, idUtente);

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
     * @param idMessaggio id dell' oggetto Messaggio che si vuole
     *                      modificare dal database
     * @param oggetto oggetto del messaggio
     * @param corpo corpo del messaggio
     * @param idUtente id dell' utente che ha scritto il messaggio
     * @param idDiscussione id della discussione in cui si trova il messaggio
     * */
    public void doUpdate(int idMessaggio, String oggetto, String corpo, int idUtente, int idDiscussione){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE Messaggio SET oggetto = ?, corpo = ? " +
                                    "WHERE idMessaggio = ? " +
                                    "AND idUtente = ? and idDiscussione = ?;");

            preparedStatement.setString(1, oggetto);
            preparedStatement.setString(2, corpo);
            preparedStatement.setInt(3, idMessaggio);
            preparedStatement.setInt(4, idUtente);
            preparedStatement.setInt(5, idDiscussione);

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
