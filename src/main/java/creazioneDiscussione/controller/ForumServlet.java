package creazioneDiscussione.controller;

import creazioneDiscussione.Discussione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import registrazione.Utente;
import storage.FacadeDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/forum")
public class ForumServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FacadeDAO facadeDAO = new FacadeDAO();

        List<Discussione> topics = (List<Discussione>) facadeDAO.doRetrieveAll(Discussione.class);
        request.setAttribute("topics-list", topics);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/forum.jsp");
        requestDispatcher.forward(request, response);
    }
}
