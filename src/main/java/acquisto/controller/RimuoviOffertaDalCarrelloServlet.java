package acquisto.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import acquisto.Carrello;
import acquisto.Offerta;
import storage.CarrelloDAO;
import storage.OffertaDAO;

@WebServlet("/rimuoviOffertaDalCarrello")
public class RimuoviOffertaDalCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera l'id dell'utente corrente dalla sessione
        int idUtente = (int) request.getSession().getAttribute("idUtente");

        // Recupera l'id dell'offerta da rimuovere dal carrello dalla request
        int idOfferta = Integer.parseInt(request.getParameter("idOfferta"));

        // Recupera il carrello dell'utente corrente dal database utilizzando il metodo
        // getCarrelloByIdUtente del DAO CarrelloDAO
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        Carrello carrello = carrelloDAO.getCarrelloByIdUtente(idUtente);

        // Recupera l'offerta da rimuovere al carrello dal database utilizzando il metodo
        // getOffertaById del DAO OffertaDAO
        OffertaDAO offertaDAO= new OffertaDAO();
        Offerta offerta = offertaDAO.doRetrieveById(idOfferta);

        // Rimuove l'offerta al carrello dell'utente
        carrello.rimuoviOfferta(offerta);

        carrello.getTotale();

        // Aggiorna il carrello nel database
        carrelloDAO.doUpdate(carrello);

        // Reindirizza l'utente alla pagina del carrello
        request.getRequestDispatcher("/WEB-INF/results/carrello.jsp").forward(request, response);

    }

}
