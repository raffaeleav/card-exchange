package acquisto.controller;

import acquisto.Carrello;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import registrazione.Utente;
import storage.FacadeDAO;

@WebServlet("/svuotaCarrello")
public class SvuotaCarrelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera l'id dell'utente corrente dalla sessione
        Utente user = (Utente) request.getSession().getAttribute("Utente");
        int idUtente = user.getIdUtente();

        FacadeDAO facadeDAO = new FacadeDAO();
        // Recupera il carrello dell'utente corrente dal database
        Carrello carrello = (Carrello) facadeDAO.doRetrieveByIdUtente(Carrello.class,idUtente);

        //Svuota il carrello delle sue offerte utilizzando il metodo svuotaCarrello della classe Carrello.
        facadeDAO.svuotaCarrello(Carrello.class, carrello, carrello.getIdCarrello());

        // Reindirizza l'utente alla pagina del carrello
        request.getRequestDispatcher("/WEB-INF/results/carrello.jsp").forward(request, response);
    }

}
