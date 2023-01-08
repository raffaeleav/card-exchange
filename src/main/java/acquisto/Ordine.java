package acquisto;
import java.util.Date;
import java.util.List;

public class Ordine {

    private int idOrdine;
    private Date data;
    private String indirizzo;
    private int idUtente;
    private List<Offerta> offerte;
    private double totale;

    public Ordine(int idOrdine, Date data, String indirizzo, int idUtente, List<Offerta> offerte, double totale) {
        this.idOrdine = idOrdine;
        this.data = data;
        this.indirizzo = indirizzo;
        this.idUtente = idUtente;
        this.offerte = offerte;
        this.totale = totale;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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

    public List<Offerta> getOfferte() {
        return offerte;
    }

    public void setOfferte(List<Offerta> offerte) {
        this.offerte = offerte;
    }

    public double getTotale(){
        return totale;
    }

    public void setTotale(double totale){
        this.totale = totale;
    }

}
