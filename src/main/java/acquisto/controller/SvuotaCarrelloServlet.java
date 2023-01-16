package acquisto.controller;

import acquisto.Carrello;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import registrazione.Utente;
import storage.service.FacadeDAO;

/**
 * La classe permette di svuotare il carrello dalle sue offerte tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di acquisto
 * @author Salvatore Sautariello
 */
@WebServlet("/svuotaCarrello")
public class SvuotaCarrelloServlet extends HttpServlet {
    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che contiene un carrello,
     * i cui attributi combaciano con i parametri de carrello dell'utente della sessione corrente.
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera l'id dell'utente corrente dalla sessione
        Utente user = (Utente) request.getSession().getAttribute("Utente");
        int idUtente = user.getIdUtente();

        FacadeDAO facadeDAO = new FacadeDAO();
        // Recupera il carrello dell'utente corrente dal database
        Carrello carrello = (Carrello) facadeDAO.doRetrieveByIdUtente(Carrello.class,idUtente);

        //Svuota il carrello delle sue offerte utilizzando il metodo svuotaCarrello della classe Carrello.
        facadeDAO.svuotaCarrello(Carrello.class, carrello, carrello.getIdCarrello());

        // Reindirizza l'utente alla pagina del carrello
        request.getRequestDispatcher("/WEB-INF/results/carrello.jsp").forward(request, response);
    }

}
