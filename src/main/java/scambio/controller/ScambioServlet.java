package scambio.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import registrazione.Utente;
import scambio.GMailer;
import scambio.Scambio;
import storage.FacadeDAO;

import java.io.IOException;


/**
 * Questa servlet viene acceduta quando si esegue una richiesta di scambio, la quale permette il salvataggio nel DB
 * e l'invio di una notifica
 */
@WebServlet(name = "ScambioServlet", value = "/ScambioServlet")
public class ScambioServlet extends HttpServlet {
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

        int idUtenteMittente = Integer.parseInt(request.getParameter("idUtenteMittente"));
        int idOffertaMittente = Integer.parseInt(request.getParameter("idOffertaMittente"));
        int idOffertaDestinatario = Integer.parseInt(request.getParameter("idOffertaDestinatario"));
        double conguaglio = Double.parseDouble(request.getParameter("conguaglio"));

        //Design Pattern
        FacadeDAO dao  = new FacadeDAO();
        // Da implementare in offerta
        int idUtenteDestinatario = 0;

        Scambio s = new Scambio(idUtenteMittente,idUtenteDestinatario,idOffertaMittente,idOffertaDestinatario,conguaglio);


        dao.doSave(Scambio.class,s);
        //Notifica?
        Utente destinatario = (Utente) new FacadeDAO().doRetrieveById(Utente.class,idUtenteDestinatario);
        Utente mittente = (Utente) new FacadeDAO().doRetrieveById(Utente.class,idUtenteMittente);
        try {
            GMailer gMailer = new GMailer();
            gMailer.sendMail(destinatario.getEmail(),"Richiesta Di Scambio","Hai ricevuto una richiesta di scambio di carte da parte dell'utente" + mittente.getEmail() + "." +
                    "La sua carta 'A' per la tua carta 'B'. Utilizza il pulsante 'Accetta' o 'Rifiuta' per rispondere alla richiesta.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("index.jsp").forward(
                request, response);


    }
}
