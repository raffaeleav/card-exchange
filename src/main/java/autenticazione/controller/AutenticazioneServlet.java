package autenticazione.controller;

import autenticazione.Autenticazione;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import registrazione.Utente;
import storage.CarrelloDAO;
import storage.FacadeDAO;

import java.io.IOException;
/**
 * La classe permette la procedura di login, tramite una servlet
 * invocata con il bottone di login
 * @author Michele Menzione
 */


@WebServlet(name = "AutenticazioneServlet", value = "/login")
public class AutenticazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @param request  un {@link HttpServletRequest} oggetto che contiene la richiesta, che il client ha fatto alla servlet
     * @param response un {@link HttpServletResponse} oggetto che contiene la risposta, che la servlet invia al client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("email");
        String pass = request.getParameter("password");

        Utente validate  = Autenticazione.verifyLogin(username, pass);

        if(validate != null) {
            request.getSession().setAttribute("Utente", validate);
            request.getRequestDispatcher("index.jsp").forward(
                    request, response);
        }else {
            String errore = "Credenziali errate!";
            request.setAttribute("msg", errore);
            request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(
                    request, response);
        }

    }
}
