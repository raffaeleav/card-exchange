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

@WebServlet("/aggiungiOffertaInCarrello")
public class AggiungiOffertaInCarrelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera l'id dell'utente corrente dalla sessione
        int idUtente = (int) request.getSession().getAttribute("idUtente");

        // Recupera l'id dell'offerta da aggiungere al carrello dalla request
        int idOfferta = Integer.parseInt(request.getParameter("idOfferta"));

        CarrelloDAO carrelloDAO=new CarrelloDAO();
        // Recupera il carrello dell'utente corrente dal database utilizzando il metodo
        // getCarrelloByIdUtente del DAO CarrelloDAO
        Carrello carrello = carrelloDAO.getCarrelloByIdUtente(idUtente);

        // Se il carrello non esiste, lo crea nel database utilizzando il metodo
        // doSave del DAO CarrelloDAO
        if (carrello == null) {
            carrello = new Carrello(0, idUtente);
            carrelloDAO.doSave(carrello);
        }

        OffertaDAO offertaDAO=new OffertaDAO();
        // Recupera l'offerta da aggiungere al carrello dal database utilizzando il metodo
        // getOffertaById del DAO OffertaDAO
        Offerta offerta = offertaDAO.doRetrieveById(idOfferta);

        // Aggiunge l'offerta al carrello dell'utente
        carrello.aggiungiOfferta(offerta);

        // Aggiorna il carrello nel database
        carrelloDAO.doUpdate(carrello);

        // Reindirizza l'utente alla pagina del carrello
        response.sendRedirect("/WEB-INF/results/carrello.jsp"); //provvisorio

    }

}