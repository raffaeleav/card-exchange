package ricerca.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import acquisto.Carta;
import storage.CartaDAO;
import storage.FacadeDAO;

/**
 * La classe permette la ricerca di una carta tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di ricerca
 * @author Raffaele Aviello
 */

@WebServlet("/ricerca-servlet")
public class RicercaServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che contiene una lista
     * di carte, il cui nome combacia con la stringa ricercata con la funzione di ricerca.
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String researchText = request.getParameter("carta");

        FacadeDAO facadeDAO = new FacadeDAO();
        List<Carta> cardsList = (List<Carta>) facadeDAO.doRetrieveAll(Carta.class), matches = new ArrayList<>();

        for (Carta card: cardsList){
            String formattedCardName = card.getNome().toLowerCase().trim();
            String formattedResearchText = researchText.toLowerCase().trim();

            if(formattedCardName.contains(formattedResearchText))
                matches.add(card);
        }

        if (matches.size() == 0){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/ricerca-senza-risultati.jsp");
            dispatcher.forward(request, response);
        }

        request.setAttribute("card-matches", matches);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/ricerca-risultati.jsp");
        dispatcher.forward(request, response);
    }

}
