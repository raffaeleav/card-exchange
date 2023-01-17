package acquisto.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import acquisto.*;
import registrazione.Utente;
import storage.service.FacadeDAO;

/**
 * La classe permette il checkout del carrello tramite
 * una servlet che viene richiamata dal bottone della
 * funzione di acquisto
 * @author Salvatore Sautariello
 */
@WebServlet(name = "checkout", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
        /**
         * Il metodo permette di gestire la richiesta del client tramite una response che contiene un carrello,
         * i cui attributi combaciano con i parametri del carrello dell'utente della sessione corrente.
         *
         * @param request  oggetto che modella una richiesta HTTP
         * @param response oggetto che modella una risposta HTTP
         */
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

                // Recupera l'id dell'utente corrente dalla sessione
                Utente user = (Utente) request.getSession().getAttribute("Utente");
                int idUtente = user.getIdUtente();
                FacadeDAO facadeDAO = new FacadeDAO();
                List<Offerta> offertaList= (List <Offerta>)facadeDAO.doRetrieveAllByIdUtenteCarrello(Offerta.class,idUtente);

                //List<Offerta> offertaList = (List<Offerta>) facadeDAO.doRetrieveAllByIdUtenteCarrello(Offerta.class, idUtente);

                // Recupera il carrello dell'utente corrente dal database
                Carrello carrello = (Carrello) facadeDAO.doRetrieveByIdUtente(Carrello.class, idUtente);

                // Recupera i dati del form di checkout dalla request
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String indirizzo = request.getParameter("indirizzo");
                String numeroCivico = request.getParameter("numeroCivico");
                String cap = request.getParameter("cap");
                String citta = request.getParameter("citta");
                String provincia = request.getParameter("provincia");
                String tipoSpedizione = request.getParameter("tipoSpedizione");
                String metodoPagamento = request.getParameter("metodoPagamento");

                String indirizzoComplet = indirizzo.trim() + " " + numeroCivico.trim() + "\n" + cap.trim() + " " + citta.trim() + " " + provincia.trim();
                String indirizzoCompleto = URLEncoder.encode(indirizzoComplet, "UTF-8");

                String url = "https://nominatim.openstreetmap.org/search?q=" + indirizzoCompleto + "&format=json";
                if (!this.isAlphabet(nome) || !this.isAlphabet(cognome) || !this.checkAddress(url)){

                        request.getSession().setAttribute("ErrorParams","Indirizzo non esistente o campi nome e cognome non alfabetici");
                        request.getRequestDispatcher("/WEB-INF/results/carrello.jsp").forward(
                                request, response);
                        return;
                }

                // Cerca il totale del carrello
                String totales = request.getParameter("totale");
                Double totale = Double.parseDouble(totales);

                if (totale == 0) {
                        request.getSession().setAttribute("EmptyCartMessages", " Aggiungi qualche offerta al tuo carrello!");
                        request.getRequestDispatcher("/WEB-INF/results/carrello.jsp").forward(
                                request, response);
                } else {

                        // Calcola la data attuale
                        Date datea = Calendar.getInstance().getTime();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        String data = dateFormat.format(datea);

                        // Crea un nuovo oggetto Ordine con le offerte recuperate dal carrello
                        Ordine ordine = new Ordine(data, URLDecoder.decode(indirizzoCompleto,"UTF-8"), idUtente, totale);
                        // Aggiunge l'ordine al database
                        facadeDAO.doSave(Ordine.class, ordine);

                        //messaggio di successo in una variabile di sessione
                        request.getSession().setAttribute("successMessage", "Checkout effettuato con successo! Il tuo ordine è andato a buon fine. Ecco tutti gli ordini da te effettuati su CardExchange!");

                        // Reindirizza l'utente alla pagina riepilogo ordine con un alert di conferma dell'ordin


                        try {
                                ordine = facadeDAO.doRetrieveByIdUtenteDataTotale(Ordine.class, idUtente, data, totale);
                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }

                        facadeDAO.addOfferteToOrdine(Ordine.class, offertaList, ordine.getIdOrdine());


                        facadeDAO.svuotaCarrello(Carrello.class, carrello, carrello.getIdCarrello());

                        request.getRequestDispatcher("/WEB-INF/results/myorders.jsp").forward(
                                request, response);

                }

        }
        /**
         *Metodo che verifica se la stringa passata come parametro contiene solo caratteri alfabetici, spazi e apostrofi
         *@param str La stringa da verificare
         *@return x true se la stringa contiene solo caratteri alfabetici, spazi e apostrofi, false altrimenti
         *
         */

        public boolean isAlphabet(String str) {

                String regex = "^[a-zA-Z\s']+$";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(str.trim());
                boolean x =matcher.matches();
                return x;
        }


        /**
         *
        * Questo metodo utilizza l'API di OpenStreetMap Nominatim per controllare l'esistenza di un indirizzo specificato.
        * @param url1 l'URL della richiesta all'API di Nominatim, che include l'indirizzo da verificare.
        * @return true se l'indirizzo esiste, false altrimenti.
         *
         */


        public boolean checkAddress(String url1) throws IOException {

                        // Crea una nuova connessione all'URL dell'API di Nominatim
                        URL url = new URL(url1);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Accept", "application/json");

                        // Legge la risposta del server
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String output;
                        StringBuilder response = new StringBuilder();
                        while ((output = br.readLine()) != null) {
                                response.append(output);
                        }

                        // Analizza la risposta JSON e ottiene i dati desiderati
                        JSONArray jsonArray = new JSONArray(response.toString());
                        if (jsonArray.length() > 0) {
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String lat = jsonObject.getString("lat");
                                String lon = jsonObject.getString("lon");
                                conn.disconnect();
                                // indirizzo esiste
                                return true;
                        } else {
                                conn.disconnect();
                                // indirizzo non esiste
                                return false;
                        }

        }

}


