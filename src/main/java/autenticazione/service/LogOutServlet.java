package autenticazione.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * La classe LogOutServlet gestisce la procedura di logout del sistema, invalidando la sessione corrente
 * e reindirizzando l'utente alla pagina index.
 *
 * @author Michele Menzione
 */


@WebServlet("/LogOut")
public class LogOutServlet extends HttpServlet {


    /**
     * Il metodo gestisce la richiesta del client per il logout, ottenendo la sessione corrente tramite
     * HttpServletRequest, invalidandola e successivamente reindirizzando l'utente alla pagina index tramite
     * HttpServletResponse.
     * @param req  : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        session.invalidate();
        resp.sendRedirect("index.jsp");
    }
}
