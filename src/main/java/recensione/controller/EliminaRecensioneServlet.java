package recensione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.RecensioneDAO;

import java.io.IOException;

/**
 * La classe permette di eliminare una recensione tramite id.
 * @author Francesco Di Domenico
 */
@WebServlet("/removeRecensione")
public class EliminaRecensioneServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * prende il parametro idRecensione ed elimina la recensione nel DB.
     * Il dispatcher reindirizza poi ad un altra pagina.
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idRecensione= Integer.parseInt(req.getParameter("idRecensione"));
        RecensioneDAO recensioneDAO=new RecensioneDAO();
        recensioneDAO.doDelete(idRecensione);//Elimina recensione dal DB in base all idRecensione.
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("index.jsp");//reindirizza alla pagina index (da cambiare)
    }
}
