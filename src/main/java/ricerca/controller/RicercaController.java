package ricerca.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ricerca.CartaDAOStub;
import ricerca.CartaStub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * La classe permette la ricerca di una carta tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di ricerca.
 * @author Raffaele Aviello
 */

@WebServlet("/ricerca-controller")
public class RicercaController extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che contiene una lista
     * di carte, il cui nome combacia con la stringa ricercata con la funzione di ricerca.
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // da rivedere il nome del parametro nel codice html
        String researchText = request.getParameter("carta");

        // stub del DAO e della classe Carta
        List<CartaStub> matches = new ArrayList<>();
        CartaDAOStub cardDao = new CartaDAOStub();

        for(CartaStub card: cardDao.doRetrieveAll()){
            // eliminazione degli spazi bianchi + nomi in lowercase per evitare di 'perdere'
            // carte per spazi sbagliati ecc..
            String formattedCardName = card.getNome().toLowerCase().trim();
            String formattedResearchText = researchText.toLowerCase().trim();

            if(formattedCardName.contains(formattedResearchText))
                matches.add(card);
        }

        request.setAttribute("risultati-ricerca", matches);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/risultati-ricerca.jsp");
        dispatcher.forward(request, response);
    }

}
