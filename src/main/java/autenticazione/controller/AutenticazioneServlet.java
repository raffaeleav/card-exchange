package autenticazione.controller;

import acquisto.Carrello;
import autenticazione.Autenticazione;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import registrazione.Utente;
import storage.service.FacadeDAO;

import java.io.IOException;
/**

 La classe AutenticazioneServlet gestisce la procedura di login tramite una servlet invocata con il bottone di login.
 Utilizza i metodi di Autenticazione per verificare le credenziali dell'utente
 e impostare l'attributo "Utente" nella sessione corrente in caso di successo.
 In caso di credenziali errate, reindirizza l'utente alla pagina di login con un messaggio di errore.
 @author Michele Menzione
 */


@WebServlet(name = "AutenticazioneServlet", value = "/login")
public class AutenticazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     Metodo chiamato quando una richiesta di tipo GET viene inviata al server
     @param request un {@link HttpServletRequest} oggetto che contiene la richiesta, che il client ha fatto alla servlet
     @param response un {@link HttpServletResponse} oggetto che contiene la risposta, che la servlet invia al client
     @throws ServletException
     @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("email");
        String pass = request.getParameter("password");

        FacadeDAO facadeDAO = new FacadeDAO();
        Utente validate  = Autenticazione.verifyLogin(username, pass);


        if(validate != null) {
            request.getSession().setAttribute("Utente", validate);
            Carrello carrello = (Carrello) facadeDAO.doRetrieveByIdUtente(Carrello.class, validate.getIdUtente());
            if(carrello==null) {
                carrello=new Carrello(validate.getIdUtente(),0 );
                facadeDAO.doSave(Carrello.class,carrello);
            }
            request.getSession().setAttribute("Carrello",carrello);
            request.getRequestDispatcher("index.jsp").forward(
                    request, response);
        }else {
            String errore = "Credenziali errate!";
            request.setAttribute("msg", errore);
            request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(
                    request, response);
        }

    }
}
