package scambio.controller;

import acquisto.Carta;
import acquisto.Offerta;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import registrazione.Utente;
import storage.CartaDAO;
import storage.FacadeDAO;
import storage.OffertaDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowScambioServlet", value = "/ShowScambioServlet")
public class ShowScambioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String offertaRichiesta = request.getParameter("offerta");

        Utente utente = (Utente) request.getSession().getAttribute("Utente");

        OffertaDAO offertaDAO = new OffertaDAO();
        FacadeDAO DAO = new FacadeDAO();

        //Lista delle offerte dell'utente richiedente
        List <Offerta> offerte = offertaDAO.getOfferteByIdUtente(utente.getIdUtente());

        // Offerta selezionata allo scambio
        Offerta richiesta = (Offerta) DAO.doRetrieveById(Offerta.class,Integer.parseInt(offertaRichiesta));

        //Carta Richiesta
        Carta cartaRichiesta = (Carta) DAO.doRetrieveById(Carta.class,richiesta.getIdCarta());

        request.setAttribute("offerteUtente",offerte);
        request.setAttribute("offertaRichiesta",richiesta);
        request.setAttribute("cartaRichiesta",cartaRichiesta);

        request.getRequestDispatcher("/WEB-INF/results/scambio.jsp").forward(
                request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
