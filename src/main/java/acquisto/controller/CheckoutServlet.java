package acquisto.controller;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import acquisto.Carrello;
import acquisto.Ordine;
import acquisto.Offerta;
import jakarta.servlet.http.HttpSession;
import storage.CarrelloDAO;
import storage.OrdineDAO;
import storage.OffertaDAO;


@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Recupera l'id dell'utente corrente dalla sessione
        int idUtente = (int) session.getAttribute("idUtente");

        CarrelloDAO carrelloDAO = new CarrelloDAO();
        OffertaDAO offertaDAO = new OffertaDAO();
        OrdineDAO ordineDAO = new OrdineDAO();
        // Recupera il carrello dell'utente corrente dal database utilizzando il metodo
        // getCarrelloByIdUtente del DAO CarrelloDAO
        Carrello carrello = carrelloDAO.getCarrelloByIdUtente(idUtente);

        // Recupera i dati del form di checkout dalla request
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String indirizzo = request.getParameter("indirizzo");
        String numeroCivico = request.getParameter("numeroCivico");
        String cap = request.getParameter("cap");
        String citta = request.getParameter("citta");
        String paese = request.getParameter("paese");
        String tipoSpedizione = request.getParameter("tipoSpedizione");
        String metodoPagamento = request.getParameter("metodoPagamento");

        // Calcola il totale del carrello
        double totale = 0;
        if (carrello != null) {
                totale = carrello.getTotale();
        }
        // Calcola la data attuale
        java.util.Date data = new java.util.Date();

        String indirizzoCompleto= nome.trim()+" "+" "+ cognome.trim()+"\n"+indirizzo.trim()+" "+numeroCivico.trim()+"\n"+cap.trim()+" "+citta.trim()+" "+paese.trim();

        // Recupera le offerte presenti nel carrello dell'utente dal database
        List<Offerta> offerte = offertaDAO.getOfferteByIdUtente(idUtente);

        // Crea un nuovo oggetto Ordine con le offerte recuperate dal carrello
        Ordine ordine = new Ordine(0, data, indirizzoCompleto, idUtente, offerte, totale);

        // Aggiunge l'ordine al database
        ordineDAO.doSave(ordine);


        //messaggio di successo in una variabile di sessione
        session.setAttribute("successMessage", "Checkout effettuato con successo! Il tuo ordine è andato a buon fine. Ecco tutti gli ordini da te effettuati su CardExchange!");

        // Reindirizza l'utente alla pagina di conferma dell'ordine
        response.sendRedirect("/WEB-INF/results/myorders.jsp");


        }
}