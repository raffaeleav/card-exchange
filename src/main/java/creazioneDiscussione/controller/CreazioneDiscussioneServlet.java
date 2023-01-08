package creazioneDiscussione.controller;

import creazioneDiscussione.Discussione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import storage.DiscussioneDAO;

import java.io.IOException;

/**
 * La classe permette la creazione di una discussione tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di creazione discussione
 * @author Raffaele Aviello
 */

@WebServlet("/creazione-discussione-servlet")
public class CreazioneDiscussioneServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        int idUser = (int) httpSession.getAttribute("user-id");
        String title = request.getParameter("topic-title");
        DiscussioneDAO topicDAO = new DiscussioneDAO();

        topicDAO.doSave(idUser, title);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/discussione.jsp");
        requestDispatcher.forward(request, response);
    }
}
