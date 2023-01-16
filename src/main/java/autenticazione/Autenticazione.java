package autenticazione;

import registrazione.Utente;
import storage.FacadeDAO;
import storage.UtenteDAO;

/**
 * La classe permette la verifica dell'operazione di login
 * tramite l'utilizzo del design pattern
 * @author Michele Menzione
 */
public class Autenticazione {
    private FacadeDAO facadeDAO;
    public Autenticazione(FacadeDAO facadeDAO) {
        this.facadeDAO = facadeDAO;

    }

    /**
     * Il metodo permette di verificare se l'utente è registrato al sito.
     * In caso positivo restituisce tutte le sue informazioni
     * @param pass, oggetto che identifica una credenziale di accesso
     * @param email, oggetto che identifica una credenziale di accesso
     * */
    public Utente verifyLogin(String email, String pass){
        return facadeDAO.getUtenteByEmailPassword(Utente.class,email, pass);
    }
}
