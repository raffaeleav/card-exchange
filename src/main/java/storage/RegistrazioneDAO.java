package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrazioneDAO {
    // Permette la registrazione di un nuovo utente
    public static int registerUser(String username, String passwordhash, String nome,
                                   String cognome, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        int rs;
        conn = ConPool.getConnection();

        try {
            pst = conn.prepareStatement("INSERT INTO Utente (username, passwordhash, nome, cognome, email) VALUES(?,?,?,?,?)");

            pst.setString(1, username);
            pst.setString(2, passwordhash);
            pst.setString(3, nome);
            pst.setString(4, cognome);
            pst.setString(5, email);

            rs = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
}
