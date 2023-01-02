package autenticazione;

import registrazione.Utente;
import storage.UtenteDAO;

public class Autenticazione {
    // Verifica se le credenziali inserite corrispondono a un utente
    public static Utente verifyLogin(String email, String pass){
        UtenteDAO utenteDAO = new UtenteDAO();
        return utenteDAO.getUtenteByEmailPassword(email, pass);
    }
}
