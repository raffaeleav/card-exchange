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
 * La classe permette l inserimento di una carta nel database,tramite il form di compilazione,
 * gestito dall admin.
 * @author Francesco Di Domenico
 */
@WebServlet("/AggiungiCarta")
public class AggiungiCarta extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client,dove il server prende dalla request i
     * parametri della carta ed effettua un controllo sulla categoria
     * così facendo setta il datapath dell immagine di default nella stringa immagine
     * ,utilizza il facadeDAO per effettuare la doSave,ed infine
     * rimanda alla pagina di gestione carte dell admin  tramite  parametro Resp .
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FacadeDAO facadeDAO=new FacadeDAO();

        String immaginePKM="https://i.ibb.co/6wb7vB2/immagine-2023-01-17-195211439.png";
        String immagineYGO="https://i.ibb.co/QFBrnyw/immagine-2023-01-17-195606929.png";
        String immagineMGC="https://i.ibb.co/f1p6ZvV/immagine-2023-01-17-195457719.png";
        String nome=req.getParameter("nome");
        String rarita=req.getParameter("rarita");
        String categoria=req.getParameter("categoria");
        String immagine="";
        if(categoria.equalsIgnoreCase("Pokemon")){
            immagine=immaginePKM;
        }
        if(categoria.equalsIgnoreCase("Yu-Gi-Oh!")){
            immagine=immagineYGO;
        }
        if(categoria.equalsIgnoreCase("Magic:The Gathering")){
            immagine=immagineMGC;
        }










        Carta newCarta=new Carta(nome,categoria,rarita,immagine);
        facadeDAO.doSave(Carta.class,newCarta);
        resp.sendRedirect("MostraPannelloAdmin");}
    }

