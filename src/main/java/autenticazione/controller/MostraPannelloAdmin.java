package autenticazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/**
 * La classe permette l'indirizzamento verso la pagina del pannello admin.
 * @author Francesco Di Domenico
 */
@WebServlet("/MostraPannelloAdmin")
public class MostraPannelloAdmin extends HttpServlet {

    /**
     * Il metodo permette di  rimandare alla pagina pannello dell'admin per gestire gli utenti tramite parametro req.
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameter("Utente");
        req.getRequestDispatcher("/WEB-INF/results/admin.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
