package vendita.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import acquisto.Carta;
import storage.service.FacadeDAO;

/**
 * La classe permette la ricerca di una carta tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di vendita
 * @author Salvatore Sautariello
 */

@WebServlet("/search-servlet-selling")
public class ricercaPerVendita extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che contiene una lista
     * di carte, il cui nome combacia con la stringa ricercata con la funzione di ricerca.
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String researchText = request.getParameter("search-text-selling"),
                researchCategory = request.getParameter("categoria");

        FacadeDAO facadeDAO = new FacadeDAO();
        List<Carta> cardsList = (List<Carta>) facadeDAO.doRetrieveAll(Carta.class);
        List<Carta> matches = new ArrayList<>();

        for (Carta card: cardsList){
            String formattedCardName = card.getNome().toLowerCase().trim();
            String formattedResearchText = researchText.toLowerCase().trim();
            String formattedCategoryCard = card.getCategoria().toLowerCase().trim();
            String formattedCategoryInput = researchCategory.toLowerCase().trim().substring(0,3);

            if(formattedCardName.contains(formattedResearchText) && formattedCategoryCard.contains(formattedCategoryInput))
                matches.add(card);
        }

        if (matches.isEmpty()){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error/ricerca-senza-risultati.jsp");
            dispatcher.forward(request, response);
        }

        request.setAttribute("card-matches", matches);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/ricerca-risultati-vendita.jsp");
        dispatcher.forward(request, response);
    }

}
