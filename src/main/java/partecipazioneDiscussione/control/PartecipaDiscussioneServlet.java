package partecipazioneDiscussione.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import registrazione.Utente;

import java.io.IOException;

/**
 * La classe permette la partecipazione ad una discussione tramite
 * una servlet che viene richiamata dal comando della
 * funzione di partecipazione discussione
 * @author Raffaele Aviello
 */

@WebServlet("/partecipa-discussione-servlet")
public class PartecipaDiscussioneServlet extends HttpServlet {

    /**
     * Il metodo permette di gestire la richiesta del client tramite una response che
     * permette la partecipazione ad una discussione tramite la classe FacadeDAO
     * @param request oggetto che modella una richiesta HTTP
     * @param response oggetto che modella una risposta HTTP
     * */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topicId = request.getParameter("topic-id"), address;
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("Utente");

        if(user == null)
            address = "/WEB-INF/results/discussione-senza-login.jsp";

        else {
            address = "/WEB-INF/results/discussione.jsp";
            request.setAttribute("topic-id", topicId);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(address);
        requestDispatcher.forward(request,response);
    }
}
