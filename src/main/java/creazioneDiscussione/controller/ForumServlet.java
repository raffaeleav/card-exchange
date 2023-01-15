package creazioneDiscussione.controller;

import creazioneDiscussione.Discussione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.service.FacadeDAO;

import java.io.IOException;
import java.util.List;

/**
 * La classe permette l' accesso alla sezione del sito
 * riguardante il Forum prelevando tutte le istanze di Discussione per
 * mostrarle nella jsp a cui il metodo doGet reindirizza
 * @author Raffaele Aviello
 */

@WebServlet("/forum-servlet")
public class ForumServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che
     * permette di mostrare le discussioni della sezione Forum
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FacadeDAO facadeDAO = new FacadeDAO();

        List<Discussione> topics = (List<Discussione>) facadeDAO.doRetrieveAll(Discussione.class);
        request.setAttribute("topics-list", topics);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/forum.jsp");
        requestDispatcher.forward(request, response);
    }
}
