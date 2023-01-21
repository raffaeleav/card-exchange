package scambio.controller;

import acquisto.Carta;
import acquisto.Offerta;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import registrazione.Utente;
import storage.service.FacadeDAO;

import java.io.IOException;
import java.util.List;

/**
 * La classe permette la visualizzazione della procedura di Scambio di un utente tramite
 * una servlet che viene richiamata dal bottone di visualizza scambio.
 * @author Michele Menzione
 */

@WebServlet(name = "ShowScambioServlet", value = "/ShowScambioServlet")
public class ShowScambioServlet extends HttpServlet {
    /**
     * Il metodo permette la visualizzazione della richiesta di scambio per l'offerta selezionata,
     * settando i vari parametri necessari per lo scambio
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO controllo se utente è null (utente non loggato)
        if(request.getSession().getAttribute("Utente") == null){
            request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(
                    request, response);
            return;
        }
        String action = request.getParameter("action");
        if(action != null && request.getParameter("action").equals("visualizza")){
            request.getRequestDispatcher("/WEB-INF/results/myScambi.jsp").forward(
                    request, response);
            return;
        }

        Utente utente = (Utente) request.getSession().getAttribute("Utente");
        if(request.getParameter("offerta") == null || request.getParameter("offerta").isBlank()){
            request.getRequestDispatcher("/WEB-INF/error/somethingWentWrong.jsp").forward(
                    request, response);
            return;
        }

        String offertaRichiesta = request.getParameter("offerta");
        FacadeDAO DAO = new FacadeDAO();

        //Lista delle offerte dell'utente richiedente
        List <Offerta> offerte = null;

        offerte = (List<Offerta>) DAO.doRetrieveAllByIdUtente(Offerta.class, utente.getIdUtente());


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
