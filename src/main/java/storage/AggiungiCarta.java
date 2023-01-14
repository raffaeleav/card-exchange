package storage;

import acquisto.Carta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        File immaginePKM= new File("/imgs/cards/pokemon/pokemon.png");
        File immagineYGO= new File("/imgs/cards/yugioh/yugioh.png");
        File immagineMGC= new File("/imgs/cards/magic/magic.png");
        String nome=req.getParameter("nome");
        String rarita=req.getParameter("rarita");
        String categoria=req.getParameter("categoria");
        String immagine="";
        if(categoria.equalsIgnoreCase("Pokemon")){
            immagine=immaginePKM.getPath();
        }
        if(categoria.equalsIgnoreCase("Yu-Gi-Oh")){
            immagine=immagineYGO.getPath();
        }  if(categoria.equalsIgnoreCase("Magic")){
            immagine=immagineMGC.getPath();
        }


        Carta newCarta=new Carta(nome,categoria,rarita,immagine);
        facadeDAO.doSave(Carta.class,newCarta);
        resp.sendRedirect("MostraPannelloAdmin");}
    }

