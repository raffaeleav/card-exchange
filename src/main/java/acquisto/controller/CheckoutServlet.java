package acquisto.controller;

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
import jakarta.servlet.http.HttpSession;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import acquisto.*;
import registrazione.Utente;
import storage.FacadeDAO;

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
                if(!(this.isAlphabet(nome)&&this.isAlphabet(cognome))&&this.checkAddress(indirizzoCompleto,url)){

                        request.getSession().setAttribute("ErrorParams","Indirizzo non esistente o campi nome e cognome non alfabetici");
                        request.getRequestDispatcher("/WEB-INF/results/carrello.jsp").forward(
                                request, response);
                        return;
                }

                // Calcola il totale del carrello
                String totales = request.getParameter("totale");
                Double totale = Double.parseDouble(totales);

                if (totale == 0) {
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
        private boolean isAlphabet(String str) {

                String regex = "/^[a-zA-Z\s']+$/";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(str);
                boolean x = matcher.matches();
                return !x;
        }


        private boolean checkAddress(String indirizzoCompleto,String url) throws IOException {
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpResponse response1 = httpClient.execute(httpGet);

                // Ottieni la risposta in formato JSON
                JSONArray jsonArray = new JSONArray(EntityUtils.toString(response1.getEntity()));

                if (jsonArray.length() > 0) {
                        // Prendi il primo risultato (più preciso)
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        // Verifica se l'indirizzo è presente nella risposta
                        if (jsonObject.has(indirizzoCompleto)) {
                                JSONObject address = jsonObject.getJSONObject("address");
                                String street = address.getString("road");
                                String city = address.getString("city");

                                //controlla se l'indirizzo è valido
                                if(street != null && city != null) {
                                        return true;
                                } else {
                                        return false;
                                }
                        } else {
                                return false ;
                        }
                } else {
                        return false;
                }

        }

}


