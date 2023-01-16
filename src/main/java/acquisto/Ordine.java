package acquisto;
import java.sql.Date;


/**
 * La classe modella il concetto di Ordine
 * @author Salvatore Sautariello
 */
public class Ordine {
    private int idOrdine;
    private String data;
    private String indirizzo;
    private int idUtente;
    private double totale;

    /**
     * Costruttore della classe Ordine
     * @param idOrdine  id dell'ordine;
     * @param data  data in cui è stato effettuato l'ordine;
     * @param indirizzo  indirizzo dell'utente che ha effettuato ordine;
     * @param idUtente id dell'Utente che ha effettuato dell'ordine;
     * @param totale  totale dell'ordine;
     * */
    public Ordine(int idOrdine, String data, String indirizzo, int idUtente,  double totale) {
        this.idOrdine = idOrdine;
        this.data = data;
        this.indirizzo = indirizzo;
        this.idUtente = idUtente;
        this.totale = totale;
    }

    public Ordine(String data, String indirizzo, int idUtente,  double totale) {

        this.data = data;
        this.indirizzo = indirizzo;
        this.idUtente = idUtente;
        this.totale = totale;
    }

    public Ordine() {

    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public String getData() {
        return data;
    }

    public void setString(String data) {
        this.data = data;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public double getTotale(){
        return totale;
    }

    public void setTotale(double totale){
        this.totale = totale;
    }

}
