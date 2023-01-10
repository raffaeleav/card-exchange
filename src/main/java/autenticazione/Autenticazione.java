package autenticazione;

import registrazione.Utente;
import storage.FacadeDAO;
import storage.UtenteDAO;

/**
 * La classe permette la ricerca di una carta tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di ricerca.
 * @author Michele Menzione
 */
public class Autenticazione {
    /**
     * Il metodo permette di verificare se l'utente è registrato al sito. In caso positivo restituisce tutte le sue informazioni
     * @param pass, oggetto che identifica una credenziale di accesso
     * @param email, oggetto che identifica una credenziale di accesso
     * */
    public static Utente verifyLogin(String email, String pass){
        FacadeDAO facadeDAO = new FacadeDAO();
        return facadeDAO.getUtenteByEmailPassword(UtenteDAO.class,email, pass);
    }
}
