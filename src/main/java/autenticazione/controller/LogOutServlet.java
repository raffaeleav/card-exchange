package autenticazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * La classe permette l'invalidazione della sessione,con successivo reindirizzamento alla homepage.
 * @author Francesco Di Domenico
 */

@WebServlet("/LogOut")
public class LogOutServlet extends HttpServlet {


    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * prende attraverso la richiesta,ottiene la session e successivamente
     * la invalida,infine reindirizza tramite resp alla jsp index.
     *
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
