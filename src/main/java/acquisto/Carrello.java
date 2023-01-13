package acquisto;

import java.util.ArrayList;
import java.util.List;
import storage.OffertaDAO;

public class Carrello {

    private int idCarrello;
    private int idUtente;
    private double totale;

    public Carrello(int idCarrello, int idUtente) {
        this.idCarrello = idCarrello;
        this.idUtente = idUtente;
        this.totale = 0;
    }

    public Carrello() {

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

    public double getTotale() {
        return totale;
    }
    public void setTotale(double totale) {
        this.totale = totale;
    }


}