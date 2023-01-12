package registrazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ShowRegistrazioneServlet", value = "/ShowRegistrazioneServlet")
public class ShowRegistrazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("Utente") != null){
            request.getSession().invalidate();
        }
        request.getRequestDispatcher("/WEB-INF/results/registrazione.jsp").forward(
                request, response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
