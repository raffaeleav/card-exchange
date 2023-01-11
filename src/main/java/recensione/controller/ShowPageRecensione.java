package recensione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * La classe permette l'indirizzamento verso la pagina di compilazione
 * form della recensione.
 * @author Francesco Di Domenico
 */

@WebServlet("/MostraPaginaRecensione")
public class ShowPageRecensione extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * rimanda ad un'altra
     * pagina a fine tramite parametro RequestDispatcher .
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/results/recensione.jsp");
        dispatcher.forward(req,resp);
    }
}
