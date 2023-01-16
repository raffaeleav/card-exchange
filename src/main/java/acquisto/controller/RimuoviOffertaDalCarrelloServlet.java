package acquisto.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import acquisto.Carrello;
import acquisto.Offerta;
import registrazione.Utente;
import storage.service.FacadeDAO;

/**
 * La classe permette la rimozione di un'offerta dal carrello tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di acquisto
 * @author Salvatore Sautariello
 */
@WebServlet("/rimuoviOffertaDalCarrello")
public class RimuoviOffertaDalCarrelloServlet extends HttpServlet {
    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che contiene un'offerta,
     * i cui attributi combaciano con i parametri dell'offerta selezionata nel carrello.
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera l'id dell'utente corrente dalla sessione
        Utente user = (Utente) request.getSession().getAttribute("Utente");
        int idUtente = user.getIdUtente();

        // Recupera l'id dell'offerta da aggiungere al carrello dalla request
        String offertas = request.getParameter("offerta");
        int idOfferta = Integer.parseInt(offertas);

        FacadeDAO facadeDAO = new FacadeDAO();
        // Recupera il carrello dell'utente corrente dal database
        Carrello carrello = (Carrello) facadeDAO.doRetrieveByIdUtente(Carrello.class,idUtente);

        // Recupera l'offerta da rimuovere dal carrello dal database
        Offerta offerta = (Offerta) facadeDAO.doRetrieveById(Offerta.class, idOfferta);

        // Rimuove l'offerta al carrello dell'utente
        facadeDAO.removeOffertaFromCarrello(Carrello.class,offerta, carrello.getIdCarrello());

        // Aggiorna il carrello nel database
        facadeDAO.doUpdate(Carrello.class,carrello.getIdCarrello(),carrello);

        // Reindirizza l'utente alla pagina del carrello
        request.getRequestDispatcher("/WEB-INF/results/carrello.jsp").forward(request, response); //provvisorio

    }

}
