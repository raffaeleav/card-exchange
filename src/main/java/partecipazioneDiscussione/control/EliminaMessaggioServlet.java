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
 * La classe permette l'eliminazione di un messaggio in una discussione
 * tramite una servlet che viene richiamata dal comando della
 * funzione di eliminazione di un messaggio
 * @author Raffaele Aviello
 */
@WebServlet("/elimina-messaggio-servlet")
public class EliminaMessaggioServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che
     * permette l'eliminazione di un messaggio in una discussione tramite la classe FacadeDAO
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int messageId = Integer.parseInt(request.getParameter("message-id"));
        String topicTitle = request.getParameter("topic-title-delete");
        FacadeDAO facadeDAO = new FacadeDAO();

        facadeDAO.doDelete(Messaggio.class, messageId);

        request.setAttribute("topic-title", topicTitle);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/discussione.jsp");
        requestDispatcher.forward(request,response);
    }
}
