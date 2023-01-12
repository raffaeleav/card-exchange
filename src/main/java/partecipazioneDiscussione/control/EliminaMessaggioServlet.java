package partecipazioneDiscussione.control;

import creazioneDiscussione.Messaggio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.FacadeDAO;

import java.io.IOException;

@WebServlet("/elimina-messaggio-servlet")
public class EliminaMessaggioServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int messageId = Integer.parseInt(request.getParameter("messagge-id"));
        String topicTitle = request.getParameter("topic-title");
        FacadeDAO facadeDAO = new FacadeDAO();

        facadeDAO.doDelete(Messaggio.class, messageId);

        request.setAttribute("topic-title", topicTitle);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/discussione.jsp");
        requestDispatcher.forward(request,response);
    }
}
