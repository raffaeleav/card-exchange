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
        File immaginePKM= new File("/imgs/cards/pokemon/pokemon.png");
        File immagineYGO= new File("/imgs/cards/yugioh/yugioh.png");
        File immagineMGC= new File("/imgs/cards/magic/magic.jpg");

        int idCarta=Integer.parseInt(req.getParameter("idCarta"));
       String newNome=req.getParameter("cambiaNome");
       String newRarita=req.getParameter("cambiaRarita");
       String newCategoria=req.getParameter("cambiaCategoria");
        String immagine="";
        if(newCategoria.equalsIgnoreCase("Pokemon")){
            immagine=immaginePKM.getPath();
        }
        if(newCategoria.equalsIgnoreCase("Yu-Gi-Oh")){
            immagine=immagineYGO.getPath();
        }  if(newCategoria.equalsIgnoreCase("Magic")){
            immagine=immagineMGC.getPath();
        }
       Carta cartaModificata=new Carta(idCarta,newNome,newCategoria,newRarita,immagine);
       FacadeDAO facadeDAO=new FacadeDAO();
       facadeDAO.doUpdate(Carta.class,idCarta,cartaModificata);
        resp.sendRedirect("MostraPannelloAdmin");}

    }

