package acquisto.controller;


import acquisto.Ordine;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import registrazione.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.service.FacadeDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * La classe permette l'indirizzamento verso la pagina di tutti gli ordini effettuati da un utente.
 * @author Francesco Di Domenico
 * @author Salvatore Sautariello
 */
@WebServlet("/MostraOrdini")
public class MostraOrdiniServlet extends HttpServlet {

    /**
     * @throws ServletException, IOException
     * @param request oggetto di richiesta HTTP
     * @param response : oggetto di risposta HTTP
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FacadeDAO facadeDAO = new FacadeDAO();
        if(request.getSession().getAttribute("Utente") == null){
            request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(
                    request, response);
            return;
        }

        Utente utente=(Utente)  request.getSession().getAttribute("Utente");
        List<Ordine> orderList = (List<Ordine>) facadeDAO.doRetrieveAllByIdUtente(Ordine.class, utente.getIdUtente());

        request.setAttribute("list-ordini", orderList);
        request.getRequestDispatcher("/WEB-INF/results/myorders.jsp").forward(request, response);
    }
}
