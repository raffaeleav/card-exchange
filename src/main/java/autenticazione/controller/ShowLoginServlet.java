package autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

/**
 * La classe permette la procedura di login, tramite una servlet
 * invocata con il bottone di login
 * @author Michele Menzione
 */

@WebServlet(name = "ShowLoginServlet", value = "/ShowLoginServlet")
public class ShowLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("Utente") != null){
            request.getSession().invalidate();
        }
        request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(
                request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}