package scambio.controller;

import acquisto.Carta;
import acquisto.Offerta;
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

        int idUtenteMittente = Integer.parseInt(request.getParameter("idUtenteMittente")),
        idOffertaMittente = Integer.parseInt(request.getParameter("idOffertaMittente")),
        idOffertaDestinatario = Integer.parseInt(request.getParameter("idOffertaDestinatario")),conguaglio = 0;

        if( request.getParameter("conguaglio").length() > 0)
                conguaglio = Integer.parseInt(request.getParameter("conguaglio"));

        System.out.println(idUtenteMittente);


        //Design Pattern
        FacadeDAO dao  = new FacadeDAO();
        // Da implementare in offerta

        Offerta UtenteDestinatario = (Offerta) dao.doRetrieveById(Offerta.class,idOffertaDestinatario);
        Carta cartaUtenteDestinatario = (Carta) dao.doRetrieveById(Carta.class,UtenteDestinatario.getIdCarta());

        Offerta UtenteMittente = (Offerta) dao.doRetrieveById(Offerta.class,idOffertaMittente);
        Carta cartaUtenteMittente = (Carta) dao.doRetrieveById(Carta.class,UtenteMittente.getIdCarta());


        Scambio s = new Scambio(idUtenteMittente,UtenteDestinatario.getIdUtente(),idOffertaMittente,idOffertaDestinatario,conguaglio);


        dao.doSave(Scambio.class,s);
        //Notifica?
        Utente destinatario = (Utente) new FacadeDAO().doRetrieveById(Utente.class,UtenteDestinatario.getIdUtente());
        Utente mittente = (Utente) new FacadeDAO().doRetrieveById(Utente.class,idUtenteMittente);
        try {
            GMailer gMailer = new GMailer();
            gMailer.sendMail(destinatario.getEmail(),"Richiesta Di Scambio - Ricevuta","Hai ricevuto una richiesta di scambio di carte da parte dell'utente " + mittente.getEmail() + ".\n" +
                    "La sua carta "+cartaUtenteDestinatario.getNome() + " per la tua carta" +
                    " " +cartaUtenteMittente.getNome()+ ".\n Visualizza il sito per 'Accettare' o 'Rifiutare' alla richiesta.");

            gMailer.sendMail(mittente.getEmail(),"Richiesta Di Scambio - Inviata","Hai effettuato con successo una richiesta di scambio"+ ".\n" +
                    "La tua carta "+cartaUtenteDestinatario.getNome() + " per la sua carta" +
                    " " +cartaUtenteMittente.getNome()+ ".\n Visualizza il sito per verificare lo stato della richiesta.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("index.jsp").forward(
                request, response);


    }
}
