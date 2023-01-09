package registrazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import registrazione.Utente;
import storage.UtenteDAO;

import java.io.IOException;

/**
 * La classe permette la procedura di registrazione di un utente tramite
 * una servlet che viene richiamata dal bottone di registrazione.
 * @author Michele Menzione
 */


@WebServlet(name = "RegistrazioneServlet", value = "/registrazione")
public class RegistrazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Parametri presi dal form
        String email = request.getParameter("email"), nome = request.getParameter("nome"),
        cognome = request.getParameter("cognome"), username = request.getParameter("username"),
                pass = request.getParameter("password");


        //Verifiche

        UtenteDAO utenteDAO = new UtenteDAO();

        if(utenteDAO.getUtenteByEmail(email)){
            String errore = "Email già utilizzata!";
            request.setAttribute("msg", errore);
            request.getRequestDispatcher("/WEB-INF/results/registrazione.jsp").forward(
                    request, response);
            return;
        }

        if(utenteDAO.getUtenteByUsername(username)){
            String errore = "Username già utilizzata!";
            request.setAttribute("msg", errore);
            request.getRequestDispatcher("/WEB-INF/results/registrazione.jsp").forward(
                    request, response);
            return;
        }

        Utente u = new Utente(username,pass,nome,cognome,email);
        utenteDAO.doSave(u);
        request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(
                request, response);



    }
}
