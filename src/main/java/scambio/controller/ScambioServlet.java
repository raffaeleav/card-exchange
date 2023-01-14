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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * La classe permette la procedura di Scambio di un utente tramite
 * una servlet che viene richiamata dal bottone di scambio.
 * @author Michele Menzione
 */
@WebServlet(name = "ScambioServlet", value = "/ScambioServlet")
public class ScambioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Il metodo implementa la verifica la correttezza dei parametri inseriti dall'utente;
     * In caso Negativo viene reindirizzato in una pagina di errore.
     * In caso Positivo esegue la procedura di scambio con la memorizzazione all'interno del DB e
     * l'invio di notifica tramite e-mail al mittente (Richiesta di Scambio - Inviato) e al destinatario (Richiesta di Scambio - Ricevuta)
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int conguaglio = 0;

        // Controlli input
        if(request.getParameter("idOffertaMittente").isBlank() || request.getParameter("idOffertaDestinatario").isBlank()
        || !this.isNumeric(request.getParameter("conguaglio"))){
            request.getRequestDispatcher("/WEB-INF/error/somethingWentWrong.jsp").forward(
                    request, response);
            return;
        }


        // Controlli Passati!

        conguaglio = Integer.parseInt(request.getParameter("conguaglio"));

        int idUtenteMittente = Integer.parseInt(request.getParameter("idUtenteMittente"));
        int idOffertaDestinatario = Integer.parseInt(request.getParameter("idOffertaDestinatario"));
        int idOffertaMittente = Integer.parseInt(request.getParameter("idOffertaMittente"));





        //Design Pattern
        FacadeDAO dao  = new FacadeDAO();
        // Da implementare in offerta

        Offerta UtenteDestinatario = (Offerta) dao.doRetrieveById(Offerta.class,idOffertaDestinatario);
        Carta cartaUtenteDestinatario = (Carta) dao.doRetrieveById(Carta.class,UtenteDestinatario.getIdCarta());

        Offerta UtenteMittente = (Offerta) dao.doRetrieveById(Offerta.class,idOffertaMittente);
        Carta cartaUtenteMittente = (Carta) dao.doRetrieveById(Carta.class,UtenteMittente.getIdCarta());


        Scambio s = new Scambio(idUtenteMittente,UtenteDestinatario.getIdUtente(),idOffertaMittente,idOffertaDestinatario,conguaglio);


        dao.doSave(Scambio.class,s);

        Utente destinatario = (Utente) new FacadeDAO().doRetrieveById(Utente.class,UtenteDestinatario.getIdUtente());
        Utente mittente = (Utente) new FacadeDAO().doRetrieveById(Utente.class,idUtenteMittente);

        // Processo di notifica
        try {
            GMailer gMailer = new GMailer();
            gMailer.sendMail(destinatario.getEmail(),"Richiesta Di Scambio - Ricevuta","Hai ricevuto una richiesta di scambio di carte da parte dell'utente " + mittente.getEmail() + ".\n" +
                    "La sua carta "+cartaUtenteDestinatario.getNome() + " per la tua carta" +
                    " " +cartaUtenteMittente.getNome()+ ".\n Visualizza il sito per 'Accettare' o 'Rifiutare' alla richiesta.");

            gMailer.sendMail(mittente.getEmail(),"Richiesta Di Scambio - Inviata","Hai effettuato con successo una richiesta di scambio"+ ".\n" +
                    "La tua carta "+cartaUtenteMittente.getNome() + " per la sua carta" +
                    " " +cartaUtenteDestinatario.getNome()+ ".\n Visualizza il sito per verificare lo stato della richiesta.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("index.jsp").forward(
                request, response);


    }

    /**
     * Questo metodo permette di testare se una stringa è conforme
     * al pattern regex (n > 0)
     * @param str - parametro da verificare
     * @return true/false in base all'esito del test
     */
    private boolean isNumeric(String str) {
        // Regex presente nel TS
        String regex = "^(\\d*\\.)?\\d+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }
}
