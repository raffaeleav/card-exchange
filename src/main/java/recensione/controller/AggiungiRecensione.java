package recensione.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import recensione.Recensione;
import storage.FacadeDAO;

import java.io.IOException;

/**
 * La classe permette l'inserimento di una recensione tramite
 * una servlet che viene invocata dal bottone submit dopo aver
 * completato il form nella pagina di recensione .
 * @author Francesco Di Domenico
 */


@WebServlet("/AggiungiRecensione")//aggiunta prodotto al DB
public class AggiungiRecensione extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Il metodo permette di gestire la richiesta del client,dove il server
         * salva i parametri immessi nel form della pagina di recensione e grazie
         * alla classe recensioneDAO aggiunge la recensione al database e rimanda ad un'altra
         * pagina a fine compilazione tramite parametro resp.
         * @param req : oggetto di richiesta HTTP
         * @param resp : oggetto di risposta HTTP
         */
        HttpSession session;
        FacadeDAO facadeDAO=new FacadeDAO();
        String testo=req.getParameter("text");
        String strValutazione=req.getParameter("rate");
        //int idUtente=req.getParameter("idUtente"); ancora da settare
        //int idOrdine=req.getParameter("idOrdine");ancora da settare
        int  valutazione=Integer.parseInt(strValutazione);
        Recensione r=new Recensione(valutazione,testo,2,1);// N.B idutente e idOrdine da cambiare
        facadeDAO.doSave(Recensione.class,r);
        resp.sendRedirect("index.jsp"); //rimanda alla homepage (provvisorio)
    }

}