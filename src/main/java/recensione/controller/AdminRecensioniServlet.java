package recensione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import recensione.service.Recensione;
import storage.service.FacadeDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe permette di settare come attributo nella request la lista di tutte le recensioni effettuate da un utente
 * presenti nel database,tramite il facadeDAO.Una volta settata la lista delle recensioni,
 * richiede il dispatcher alla pagina adminGestioneRecensioni.
 * @author Francesco Di Domenico
 */

@WebServlet("/AdminRecensioniServlet")
public class AdminRecensioniServlet extends HttpServlet {

    /**
     * Il metodo permette di ottenere dal database tramite il facadeDAO
     * la lista delle recensioni filtrate per id dell utente,setta la lista come parametro nella request
     * e rimanda alla pagina dell admin per gestire le recensioni tramite parametro req.
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Recensione> listrecensioni=new ArrayList<>();
        FacadeDAO facadeDAO=new FacadeDAO();
        int idUtente= Integer.parseInt(req.getParameter("idUtente"));
        listrecensioni= (List<Recensione>) facadeDAO.getRecensioneByIdUtente(Recensione.class,idUtente);
        req.setAttribute("listrecensioni",listrecensioni);
        req.getRequestDispatcher("/WEB-INF/results/adminGestioneRecensioni.jsp").forward(
                req, resp);
    }
}
