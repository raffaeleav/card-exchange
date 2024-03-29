package recensione.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import recensione.Recensione;
import storage.service.FacadeDAO;

import java.io.IOException;

/**
 * La classe permette l'inserimento di una recensione tramite
 * una servlet che viene invocata dal bottone submit dopo aver
 * completato il form nella pagina di recensione .
 * @author Francesco Di Domenico
 */


@WebServlet("/AggiungiRecensione")//aggiunta prodotto al DB
public class AggiungiRecensione extends HttpServlet {
    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * salva i parametri immessi nel form della pagina di recensione,prende idOrdine ed idUtente
     * e grazie alla classe facadeDAO aggiunge la recensione al database e rimanda ad un'altra
     * pagina a fine compilazione tramite parametro resp.
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        FacadeDAO facadeDAO=new FacadeDAO();
        String testo=req.getParameter("text");
        String strValutazione=req.getParameter("rate");
        int idUtente= Integer.parseInt(req.getParameter("idUtente"));
        int idOrdine= Integer.parseInt(req.getParameter("idOrdine"));
        int  valutazione=Integer.parseInt(strValutazione);
        Recensione r=new Recensione(valutazione,testo,idUtente,idOrdine);
        facadeDAO.doSave(Recensione.class,r);
        resp.sendRedirect("index.jsp");
    }

}