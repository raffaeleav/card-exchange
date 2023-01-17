package storage.controller;

import acquisto.Carta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.service.FacadeDAO;

import java.io.File;
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
        String immaginePKM="https://i.ibb.co/6wb7vB2/immagine-2023-01-17-195211439.png";
        String immagineYGO="https://i.ibb.co/QFBrnyw/immagine-2023-01-17-195606929.png";
        String immagineMGC="https://i.ibb.co/f1p6ZvV/immagine-2023-01-17-195457719.png";


        int idCarta=Integer.parseInt(req.getParameter("idCarta"));
       String newNome=req.getParameter("cambiaNome");
       String newRarita=req.getParameter("cambiaRarita");
       String newCategoria=req.getParameter("cambiaCategoria");
        String immagine="";

        if(newCategoria.equalsIgnoreCase("Pokemon")){
            immagine=immaginePKM;
        }
        if(newCategoria.equalsIgnoreCase("Yu-Gi-Oh!")){
            immagine=immagineYGO;
        }
        if(newCategoria.equalsIgnoreCase("Magic:The Gathering")){
            immagine=immagineMGC;
        }


       Carta cartaModificata=new Carta(idCarta,newNome,newCategoria,newRarita,immagine);
       FacadeDAO facadeDAO=new FacadeDAO();
       facadeDAO.doUpdate(Carta.class,idCarta,cartaModificata);
        resp.sendRedirect("MostraPannelloAdmin");}

    }

