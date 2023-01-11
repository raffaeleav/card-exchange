package chat.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * La classe permette l'indirizzamento verso la pagina di chat.
 * @author Francesco Di Domenico
 */

@WebServlet("/MostraPaginaChat")
public class MostraPaginaChat extends HttpServlet {
    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * rimanda ad un'altra
     * pagina a fine tramite parametro RequestDispatcher .
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher= req.getRequestDispatcher("chat.jsp");
        dispatcher.forward(req,resp);
    }
}
