package acquisto.controller;

import java.util.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import acquisto.*;
import registrazione.Utente;
import storage.FacadeDAO;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Recupera l'id dell'utente corrente dalla sessione
        Utente user = (Utente) request.getSession().getAttribute("Utente");
        int idUtente = user.getIdUtente();

        FacadeDAO facadeDAO = new FacadeDAO();

        // Recupera il carrello dell'utente corrente dal database
        Carrello carrello = (Carrello) facadeDAO.doRetrieveByIdUtente(Carrello.class,idUtente);

        // Recupera i dati del form di checkout dalla request
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String indirizzo = request.getParameter("indirizzo");
        String numeroCivico = request.getParameter("numeroCivico");
        String cap = request.getParameter("cap");
        String citta = request.getParameter("citta");
        String provincia = request.getParameter("paese");
        String tipoSpedizione = request.getParameter("tipoSpedizione");
        String metodoPagamento = request.getParameter("metodoPagamento");

        // Calcola il totale del carrello
        double totale = 0;
        if (carrello != null) {
                totale = facadeDAO.calculateTotaleInCarrello(Carrello.class, carrello.getIdCarrello());
        }
        // Calcola la data attuale
        Date data = new java.util.Date();

        String indirizzoCompleto= nome.trim()+" "+" "+ cognome.trim()+"\n"+indirizzo.trim()+" "+numeroCivico.trim()+"\n"+cap.trim()+" "+citta.trim()+" "+provincia.trim();

        // Crea un nuovo oggetto Ordine con le offerte recuperate dal carrello
        Ordine ordine = new Ordine(0, data, indirizzoCompleto, idUtente,totale);



        // Aggiunge l'ordine al database
        facadeDAO.doSave(Ordine.class,ordine);

        //messaggio di successo in una variabile di sessione
        request.getSession().setAttribute("successMessage", "Checkout effettuato con successo! Il tuo ordine è andato a buon fine. Ecco tutti gli ordini da te effettuati su CardExchange!");

        // Reindirizza l'utente alla pagina di conferma dell'ordine
        response.sendRedirect("/WEB-INF/results/myorders.jsp");


        }
}