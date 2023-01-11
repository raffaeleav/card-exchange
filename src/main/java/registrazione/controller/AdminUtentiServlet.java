package registrazione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import registrazione.Utente;
import storage.FacadeDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe permette di settare come attributo nella request la lista di tutti gli utenti
 * presenti nel database,tramite il facadeDAO.Una volta settata la lista degli utenti,
 * richiede il dispatcher alla pagina adminGestioneUtenti.
 * @author Francesco Di Domenico
 */

@WebServlet("/AdminUtentiServlet")
public class AdminUtentiServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * salva i parametri immessi nel form della pagina di recensione e grazie
     * alla classe recensioneDAO aggiunge la recensione al database e rimanda ad un'altra
     * pagina a fine compilazione tramite parametro resp.
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Utente> listutenti=new ArrayList<>();
        FacadeDAO facadeDAO=new FacadeDAO();
        listutenti= (List<Utente>) facadeDAO.doRetrieveAll(Utente.class);
        req.setAttribute("listutenti",listutenti);
        req.getRequestDispatcher("/WEB-INF/results/adminGestioneUtenti.jsp").forward(
                req, resp);

    }
}
