package acquisto.controller;

import acquisto.Ordine;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import recensione.Recensione;
import storage.FacadeDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe permette l'indirizzamento verso la pagina di tutti gli ordini effettuati da un utente.
 * @author Francesco Di Domenico
 */
@WebServlet("/MostraOrdini")
public class MostraOrdiniServlet extends HttpServlet {

    /**
     * @throws ServletException
     * @throws IOException
     * @param req oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ordine> listordini=new ArrayList<>();
        FacadeDAO facadeDAO=new FacadeDAO();
        int idUtente= Integer.parseInt(req.getParameter("idUtente"));
        listordini= facadeDAO.getOrdiniByIdUtente(Ordine.class,idUtente);
    }
}
