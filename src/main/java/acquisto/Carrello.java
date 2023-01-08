package acquisto;

import java.util.ArrayList;
import java.util.List;
import storage.OffertaDAO;

public class Carrello {

    private int idCarrello;
    private int idUtente;
    private List<Offerta> offerte;
    private double totale;

    public Carrello(int idCarrello, int idUtente) {
        this.idCarrello = idCarrello;
        this.idUtente = idUtente;
        this.offerte = new ArrayList<>();
        this.totale = 0;
    }

    public int getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrello) {
        this.idCarrello = idCarrello;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public List<Offerta> getOfferte() {
        // Recupera le offerte presenti nel carrello dal database
        // utilizzando il metodo getOfferte del DAO OffertaDAO
        List<Offerta> offerte = OffertaDAO.getOfferteByIdUtente(this.idUtente);
        return offerte;
    }

    public void setOfferte(List<Offerta> offerte) {
        this.offerte = offerte;
    }

    public void aggiungiOfferta(Offerta offerta) {
        offerte.add(offerta);
    }

    public void rimuoviOfferta(Offerta offerta) {
        offerte.remove(offerta);
    }
    public void svuotaCarrello() {
        offerte.clear();
        totale = 0.00;
    }

  /*
   Itera sulla lista delle offerte del carrello e somma i prezzi delle singole offerte.
   Questo calcolo nel metodo getter, in modo da avere sempre il totale aggiornato ogni volta che viene richiesto.
   Non è necessario implementare un setter per il totale, poiché il totale dovrebbe essere calcolato automaticamente
   a partire dalle offerte presenti nel carrello e non dovrebbe essere modificato direttamente.
  */
    public double getTotale() {
        double totale = 0;
        for (Offerta offerta : offerte) {
            totale += offerta.getPrezzo();
        }
        return totale;
    }

}