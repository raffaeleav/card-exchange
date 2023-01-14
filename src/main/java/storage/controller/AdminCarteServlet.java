package storage.controller;

import acquisto.Carta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.FacadeDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * La classe permette di settare come attributo nella request la lista di tutti le carte
 * presenti nel database,tramite il facadeDAO.Una volta settata la lista delle carte,
 * richiede il dispatcher alla pagina adminGestioneCarte.
 * @author Francesco Di Domenico
 */
@WebServlet("/AdminCarteServlet")
public class AdminCarteServlet extends HttpServlet {

    /**
     * Il metodo permette di ottenere dal database tramite il facadeDAO
     * la lista delle carte,setta la lista come parametro nella request
     * e rimanda alla pagina dell admin per gestire  le carte tramite parametro req.
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Carta> listcarte= new ArrayList<>();
        FacadeDAO facadeDAO= new FacadeDAO();
        listcarte=(List<Carta>) facadeDAO.doRetrieveAll(Carta.class);
        req.setAttribute("listcarte",listcarte);
        req.getRequestDispatcher("/WEB-INF/results/adminGestioneCarte.jsp").forward(
                req, resp);
    }
}
