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
import storage.FacadeDAO;

@WebServlet("/rimuoviOffertaDalCarrello")
public class RimuoviOffertaDalCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera l'id dell'utente corrente dalla sessione
        Utente user = (Utente) request.getSession().getAttribute("Utente");
        int idUtente = user.getIdUtente();

        // Recupera l'id dell'offerta da aggiungere al carrello dalla request
        int idOfferta = Integer.parseInt(request.getParameter("idOfferta"));

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
