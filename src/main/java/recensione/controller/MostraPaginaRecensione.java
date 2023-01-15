package recensione.controller;

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
 * La classe permette l'indirizzamento verso la pagina di compilazione
 * form della recensione.
 * @author Francesco Di Domenico
 */

@WebServlet("/MostraPaginaRecensione")
public class MostraPaginaRecensione extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client,settando come parametri
     * l id dell utente della sessione, e l id dell ordine da recensire dove il server
     * rimanda alla pagina recensione parametro RequestDispatcher .
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Utente utente= (Utente) session.getAttribute("Utente");
        //int idOrdine=(int)req.getAttribute("idOrdine");
        int idOrdine= 1;//  da cambiare, serve parametro passato tramite button nella pagina my orders  int idOrdine=(int)req.getAttribute("idOrdine");
        int idUtente= utente.getIdUtente();
        req.setAttribute("idOrdine",idOrdine);
        req.setAttribute("idUtente",idUtente);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/results/recensione.jsp");
        dispatcher.forward(req,resp);
    }
}
