package autenticazione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import registrazione.Utente;

import java.io.IOException;
/**
 * La classe MostraPaginaUtente permette di mostrare la pagina del profilo utente.
 * @author Michele Menzione
 *
 */

@WebServlet("/MostraPaginaUtente")
    public class MostraPaginaUtente extends HttpServlet {

    /**
     Il metodo utilizza l'oggetto RequestDispatcher per inoltrare la richiesta alla pagina "paginaUtente.jsp",
     all'interno della quale verranno visualizzate le informazioni dell'utente attualmente loggato.
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/results/paginaUtente.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

