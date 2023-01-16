package creazioneDiscussione.service;

import creazioneDiscussione.Discussione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.DiscussioneDAO;
import storage.service.FacadeDAO;

import java.io.IOException;
import java.util.List;

/**
 * La classe permette l' eliminazione di una discussione tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di eliminazione discussione
 * @author Raffaele Aviello
 */

@WebServlet("/elimina-discussione-servlet")
public class EliminazioneDiscussioneServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che
     * permette l' eliminazione di una discussione tramite la classe FacadeDAO
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("topic-id"));
        FacadeDAO facadeDAO = new FacadeDAO();
        facadeDAO.doDelete(DiscussioneDAO.class, topicId);

        List<Discussione> topics = (List<Discussione>) facadeDAO.doRetrieveAll(Discussione.class);
        request.setAttribute("topics-list", topics);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/forum.jsp");
        requestDispatcher.forward(request, response);
    }
}
