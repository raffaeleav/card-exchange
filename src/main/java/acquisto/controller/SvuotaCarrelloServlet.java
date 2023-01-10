package acquisto.controller;

import acquisto.Carrello;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import storage.CarrelloDAO;

@WebServlet("/svuotaCarrello")
public class SvuotaCarrelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera l'id dell'utente corrente dalla sessione
        int idUtente = (int) request.getSession().getAttribute("idUtente");

        CarrelloDAO carrelloDAO = new CarrelloDAO();
        // Recupera il carrello dell'utente corrente dal database utilizzando il metodo
        // getCarrelloByIdUtente del DAO CarrelloDAO
        Carrello carrello = carrelloDAO.getCarrelloByIdUtente(idUtente);

        //Svuota il carrello delle sue offerte utilizzando il metodo svuotaCarrello della classe Carrello.
        carrello.svuotaCarrello();

        // Reindirizza l'utente alla pagina del carrello
        request.getRequestDispatcher("/WEB-INF/results/carrello.jsp").forward(request, response);
    }

}
