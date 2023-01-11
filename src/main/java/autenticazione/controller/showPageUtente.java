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
 * La classe permette l'indirizzamento verso la pagina dell'utente.
 * @author Francesco Di Domenico
 */

@WebServlet("/MostraPaginaUtente")
    public class showPageUtente extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * rimanda alla pagina utente tramite parametro RequestDispatcher .
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session= req.getSession();
            Utente utenteLoggato=(Utente)session.getAttribute("Utente");
            req.setAttribute("Utente",utenteLoggato);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/results/pagina-utente.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

