package autenticazione;

import org.junit.jupiter.api.Test;
import registrazione.Utente;
import storage.service.FacadeDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AutenticazioneTest {

    @Test
    void verifyLoginFalse(){
        // Parametri in input
        String Email = "ciao@gmail.it";
        String pass = "Pass";

        // Mock del database
        FacadeDAO mockFacade = mock(FacadeDAO.class);
        Autenticazione autenticazione = new Autenticazione(mockFacade);


        // Utente non presente nel database
        when(mockFacade.getUtenteByEmailPassword(Utente.class,Email,pass)).thenReturn(null);

        assertNull(autenticazione.verifyLogin(Email,pass));

    }

    @Test
    void verifyLoginTrue(){
        // Parametri in input
        String email = "Email@gmail.com";
        String pass = "Pass";

        // Utente presente all'interno nel db
        Utente utente = new Utente();
        utente.setEmail(email);
        utente.setPassword(pass);


        FacadeDAO mockFacade = mock(FacadeDAO.class);
        Autenticazione autenticazione = new Autenticazione(mockFacade);

        // Utente presente nel db
        when(mockFacade.getUtenteByEmailPassword(Utente.class,email,pass)).thenReturn(utente);

        assertEquals(email,autenticazione.verifyLogin(email,pass).getEmail());
        assertEquals(pass,autenticazione.verifyLogin(email,pass).getPassword());

    }
  
}