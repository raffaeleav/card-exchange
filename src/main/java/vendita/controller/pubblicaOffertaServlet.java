package vendita.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import acquisto.*;
import registrazione.Utente;
import storage.service.FacadeDAO;

/**
 * La classe permette la pubblicazione di un'offerta tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di vendita
 * @author Salvatore Sautariello
 */
@WebServlet("/pubblicaOfferta")
public class pubblicaOffertaServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che contiene un'offerta,
     * i cui attributi combaciano con i parametri inseriti nel form con la funzione di vendita.
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */

    FacadeDAO facadeDAO = new FacadeDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recupera l'id dell'utente corrente dalla sessione
        Utente user = (Utente) request.getSession().getAttribute("Utente");
        int idUtente = user.getIdUtente();
        // Recupera i parametri dalla richiesta
        String condizione = request.getParameter("condizione");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        int idCarta = Integer.parseInt(request.getParameter("id-carta"));

        // Crea un'istanza di Offerta
        Offerta offerta = new Offerta();
        offerta.setCondizione(condizione);
        offerta.setPrezzo(prezzo);
        offerta.setIdUtente(idUtente);
        offerta.setIdCarta(idCarta);

        // Inserisci l'offerta nel database
        facadeDAO.doSave(Offerta.class,offerta);

        // Reindirizza alla pagina della carta con l'elenco delle offerte
        List<Offerta> offersList = (List<Offerta>) facadeDAO.doRetrieveAllByIdCarta(Offerta.class, idCarta);
        request.setAttribute("lista-offerte", offersList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/carta.jsp");
        dispatcher.forward(request, response);
    }
}
