package storage.controller;

import acquisto.Carta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import recensione.Recensione;
import storage.FacadeDAO;

import java.io.IOException;


/**
 * La classe permette di eliminare una carta tramite id della carta,attraverso
 * il facadeDAO.
 * @author Francesco Di Domenico
 */
@WebServlet("/EliminaCarta")
public class EliminaCartaServlet extends HttpServlet {


    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * prende il parametro idCarta ed elimina la carta nel DB.
     * Il dispatcher reindirizza poi alla pagina del pannello admin.
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCarta= Integer.parseInt(req.getParameter("idCarta"));
        FacadeDAO facadeDAO=new FacadeDAO();
        facadeDAO.doDelete(Carta.class,idCarta);//Elimina Carta dal DB in base al tipo di classe passata e idCarta.
        resp.sendRedirect("MostraPannelloAdmin");//reindirizza alla pagina pannello admin
    }
}
