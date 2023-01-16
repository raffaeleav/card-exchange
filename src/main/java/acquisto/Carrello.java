package acquisto;

/**
 * La classe modella il concetto di Carrello
 * @author Salvatore Sautariello
 */
public class Carrello {
    private int idCarrello;
    private int idUtente;
    private double totale;

    /**
     * Costruttore della classe Carrello
     * @param idCarrello id del carrello
     * @param idUtente id dell' utente della sessione corrente
     * */
    public Carrello(int idCarrello, int idUtente,double totale) {
        this.idCarrello = idCarrello;
        this.idUtente = idUtente;
        this.totale = totale;
    }

    //Costruttore per registrazione
    public Carrello(int idUtente,double totale) {
        this.idUtente = idUtente;
        this.totale = totale;
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