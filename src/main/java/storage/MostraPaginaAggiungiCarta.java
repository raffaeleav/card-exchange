package storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * La classe permette l'indirizzamento verso la pagina di compilazione
 * form per creare una nuova carta sul dataBase.
 * @author Francesco Di Domenico
 */
@WebServlet("/MostraPaginaAggiuntaCarta")
public class MostraPaginaAggiungiCarta extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * rimanda alla pagina di aggiunta carta tramite  parametro RequestDispatcher .
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/results/adminAggiuntaCarta.jsp").forward(req,resp);
    }
}
