package registrazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

/**
 * La classe permette la visualizzazione della procedura di registrazione di un utente tramite
 * una servlet che viene richiamata dal bottone di visualizzazione registrazione.
 * @author Michele Menzione
 */
@WebServlet(name = "ShowRegistrazioneServlet", value = "/ShowRegistrazioneServlet")
public class ShowRegistrazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/results/registrazione.jsp").forward(
                request, response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
