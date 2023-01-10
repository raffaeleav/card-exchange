package autenticazione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import registrazione.Utente;
import storage.FacadeDAO;

import java.io.IOException;


/**
 * La classe permette di eliminare un utente tramite id della utente,attraverso
 * il facadeDAO.
 * @author Francesco Di Domenico
 */
@WebServlet("/eliminaAccount")
public class EliminaUtenteServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * prende il parametro idutente ed elimina l'utente nel DB.
     * Il dispatcher reindirizza poi ad un altra pagina.
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idUtente=Integer.parseInt(req.getParameter("idUtente"));
        FacadeDAO facadeDAO=new FacadeDAO();
        facadeDAO.doDelete(Utente.class,idUtente);
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("index.jsp");//reindirizza alla pagina index (da cambiare)
    }
}
