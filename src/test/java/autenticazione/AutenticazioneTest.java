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
        String Email = "ciao@gmail.it";
        String pass = "Pass";

        FacadeDAO mockFacade = mock(FacadeDAO.class);
        Autenticazione autenticazione = new Autenticazione(mockFacade);



        when(mockFacade.getUtenteByEmailPassword(Utente.class,Email,pass)).thenReturn(null);

        assertNull(autenticazione.verifyLogin(Email,pass));

    }

    @Test
    void verifyLoginTrue(){
        String email = "Email@gmail.com";
        String pass = "Pass";

        Utente utente = new Utente();
        utente.setUsername(email);
        utente.setPassword(pass);


        FacadeDAO mockFacade = mock(FacadeDAO.class);
        Autenticazione autenticazione = new Autenticazione(mockFacade);

        when(mockFacade.getUtenteByEmailPassword(Utente.class,email,pass)).thenReturn(utente);

        assertNotNull(autenticazione.verifyLogin(email,pass));

    }
  
}