package registrazione;
import storage.RegistrazioneDAO;
import storage.UtenteDAO;

import java.sql.SQLException;

public class Registrazione {
    //Metodo che permette la registrazione di un nuovo utente
    public int registraNuovoUtente(String username, String passwordhash, String nome,
                                   String cognome, String email) throws SQLException {
        //Verifica se l'username inserito è presente all'interno del DB
        if(this.isUsernameTaken(username))
            //Se presente ritorna un valore di "errore"
            return -1;

        //Verifica se l'email inserita è presente all'interno del DB
        if(this.isEmailTaken(email))
            //Se presente ritorna un valore di "errore"
            return -2;

        // Se i controlli soprastanti sono andati a buon fine, esegue l'inserimento dell'utente all'interno del DB
        else
            //Return 0 se l'inserimento nel DB NON è STATO ESEGUITO
            //Return > ZERO se l'inserimento nel DB è STATO ESEGUITO
            return RegistrazioneDAO.registerUser(username, passwordhash, nome, cognome, email);
    }

    //Metodo che permette di verificare se un'username è presente all'interno del DB
    public boolean isUsernameTaken(String username) {
        UtenteDAO utenteDAO = new UtenteDAO();

        return utenteDAO.getUtenteByUsername(username);
    }
    //Metodo che permette di verificare se un'email è presente all'interno del DB
    public boolean isEmailTaken(String email) {
        UtenteDAO utenteDAO = new UtenteDAO();

        return utenteDAO.getUtenteByUsername(email);
    }
}
