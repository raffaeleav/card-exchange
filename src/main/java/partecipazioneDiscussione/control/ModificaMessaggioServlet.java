package partecipazioneDiscussione.control;

import creazioneDiscussione.Messaggio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.service.FacadeDAO;

import java.io.IOException;

/**
 * La classe permette la modifica di un messaggio in una discussione
 * tramite una servlet che viene richiamata dal comando della
 * funzione di modifca di un messaggio
 * @author Raffaele Aviello
 */
@WebServlet("/modifica-messaggio-servlet")
public class ModificaMessaggioServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che
     * permette la modifica di un messaggio in una discussione tramite la classe FacadeDAO
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int messageId = Integer.parseInt(request.getParameter("message-id-modify"));
        String topicTitle = request.getParameter("topic-title-delete"),
                body = request.getParameter("modify-message-text");
        FacadeDAO facadeDAO = new FacadeDAO();

        Messaggio message = (Messaggio) facadeDAO.doRetrieveById(Messaggio.class, messageId);
        message.setOggetto(body);

        facadeDAO.doUpdate(Messaggio.class, messageId, message);

        request.setAttribute("topic-title", topicTitle);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/discussione.jsp");
        requestDispatcher.forward(request,response);
    }
}
