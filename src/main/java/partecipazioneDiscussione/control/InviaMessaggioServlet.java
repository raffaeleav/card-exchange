package partecipazioneDiscussione.control;

import creazioneDiscussione.Messaggio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import registrazione.Utente;
import storage.service.FacadeDAO;

import java.io.IOException;

/**
 * La classe permette l'invio di un messaggio in una discussione
 * tramite una servlet che viene richiamata dal comando della
 * funzione di invio messaggio
 * @author Raffaele Aviello
 */

@WebServlet("/invia-messaggio-servlet")
public class InviaMessaggioServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che
     * permette l'invio di un messaggio in una discussione tramite la classe FacadeDAO
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente user = (Utente) session.getAttribute("Utente");
        int userId = user.getIdUtente();
        String body = request.getParameter("message-text-body"), object = request.getParameter("message-text-object");
        String topicId = request.getParameter("topic-id");

        FacadeDAO facadeDAO = new FacadeDAO();
        Messaggio messaggio = new Messaggio(object, body, userId, Integer.parseInt(topicId));

        facadeDAO.doSave(Messaggio.class, messaggio);

        request.setAttribute("topic-id", topicId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/discussione.jsp");
        requestDispatcher.forward(request,response);
    }
}
