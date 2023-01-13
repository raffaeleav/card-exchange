package acquisto.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import acquisto.Carrello;
import acquisto.Offerta;
import registrazione.Utente;
import storage.FacadeDAO;


/**
 * La classe permette l'aggiunta di un'offerta al carrello tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di acquisto
 * @author Salvatore Sautariello
 */
@WebServlet("/aggiungiOffertaInCarrello")
public class AggiungiOffertaInCarrelloServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che contiene un'offerta,
     * i cui attributi combaciano con i parametri dell'offerta selezionata nel form della carta.jsp.
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        // Recupera l'id dell'utente corrente dalla sessione
        Utente user = (Utente) request.getSession().getAttribute("Utente");
        int idUtente = user.getIdUtente();

        // Recupera l'id dell'offerta da aggiungere al carrello dalla request
        int idOfferta = Integer.parseInt(request.getParameter("idOfferta"));

        FacadeDAO facadeDAO = new FacadeDAO();
        // Recupera il carrello dell'utente corrente dal database
        Carrello carrello = (Carrello) facadeDAO.doRetrieveByIdUtente(Carrello.class,idUtente);

        // Se il carrello non esiste, lo crea nel database utilizzando il metodo
        // doSave del DAO CarrelloDAO
        if (carrello == null) {
            carrello = new Carrello(0, idUtente);
            facadeDAO.doSave(Carrello.class,carrello);
        }


        // Recupera l'offerta da aggiungere al carrello dal database
        Offerta offerta = (Offerta) facadeDAO.doRetrieveById(Offerta.class, idOfferta);

        // Aggiunge l'offerta al carrello dell'utente
        facadeDAO.addOffertaInCarrello(Carrello.class,offerta, carrello.getIdCarrello());

        // Aggiorna il carrello nel database
        facadeDAO.doUpdate(Carrello.class,carrello.getIdCarrello(),carrello);

        // Reindirizza l'utente alla pagina del carrello
        response.sendRedirect("/WEB-INF/results/carrello.jsp"); //provvisorio

    }

}