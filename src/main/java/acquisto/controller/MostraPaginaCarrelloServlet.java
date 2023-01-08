package acquisto.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import acquisto.Carrello;
import storage.CarrelloDAO;

@WebServlet("/mostraPaginaCarrello")
public class MostraPaginaCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera l'id dell'utente corrente dalla sessione
        int idUtente = (int) request.getSession().getAttribute("idUtente");

        // Recupera il carrello dell'utente corrente dal database utilizzando il metodo
        // getCarrelloByIdUtente del DAO CarrelloDAO
        Carrello carrello = CarrelloDAO.getCarrelloByIdUtente(idUtente);

        // Imposta il carrello come attributo della request
        request.setAttribute("carrello", carrello);

        // Reindirizza l'utente alla pagina del carrello
        request.getRequestDispatcher("/WEB-INF/results/carrello.jsp").forward(request, response);
    }


}