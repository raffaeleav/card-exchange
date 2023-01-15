package recensione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import recensione.service.Recensione;
import storage.service.FacadeDAO;

import java.io.IOException;

/**
 * La classe permette di eliminare una recensione tramite id della recensione,attraverso
 * il facadeDAO.
 * @author Francesco Di Domenico
 */
@WebServlet("/EliminaRecensione")
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
        FacadeDAO facadeDAO=new FacadeDAO();
        facadeDAO.doDelete(Recensione.class,idRecensione);//Elimina recensione dal DB in base al tipo di classe passata e idRecensione.
        resp.sendRedirect("MostraPannelloAdmin");//reindirizza alla pagina pannello admin
    }
}
