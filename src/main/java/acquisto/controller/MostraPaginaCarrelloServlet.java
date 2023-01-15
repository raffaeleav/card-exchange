package acquisto.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import acquisto.Carrello;
import registrazione.Utente;
import storage.FacadeDAO;

/**
 * La classe permette la visualizzazione della pagina carrello tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di acquisto
 * @author Salvatore Sautariello
 */
@WebServlet("/mostraPaginaCarrello")
public class MostraPaginaCarrelloServlet extends HttpServlet {
    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che contiene un carrello,
     * i cui attributi combaciano con i parametri del carrello dell'utente della sessione corrente.
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //controlla se l'utente è loggato
        if(request.getSession().getAttribute("Utente") == null){
            request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(request, response);

        }
        // Reindirizza l'utente alla pagina del carrello
        request.getRequestDispatcher("/WEB-INF/results/carrello.jsp").forward(request, response);
    }


}