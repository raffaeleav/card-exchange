package storage;

import acquisto.Carta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/**
 * La classe permette di modificare una carta tramite id della carta,attraverso
 * il facadeDAO.
 * @author Francesco Di Domenico
 */
@WebServlet("/ModificaCartaServlet")
public class ModificaCartaServlet extends HttpServlet {


    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * prende il parametro idCarta modifica qyella carta utilizzando il facadeDAO ed i parametri
     * presi dal form per modificare la carta nel database.
     * Successivamente il dispatcher reindirizza poi alla pagina del pannello admin.
     *
     * @param req  : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int idCarta=Integer.parseInt(req.getParameter("idCarta"));
       String newNome=req.getParameter("cambiaNome");
       String newRarita=req.getParameter("cambiaRarita");
       String newCategoria=req.getParameter("cambiaCategoria");
        String newImmagine=req.getParameter("cambiaImmagine");
       Carta cartaModificata=new Carta(idCarta,newNome,newCategoria,newRarita,newImmagine);
       FacadeDAO facadeDAO=new FacadeDAO();
       facadeDAO.doUpdate(Carta.class,idCarta,cartaModificata);
        resp.sendRedirect("MostraPannelloAdmin");}

    }

