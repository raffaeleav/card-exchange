package recensione;

/**
 * La classe modella il concetto di Recensione
 * @author Salvatore Sautariello
 */

public class Recensione {
    private int idRecensione;
    private int valutazione;
    private String testo;
    private int idUtente;
    private int idOrdine;

    /**
     *    Costruttore completo della classe recensione
     * @param idRecensione id della recensione
     * @param idOrdine id dell ordine recensito
     * @param idUtente id dell utente che effettua la recensione
     * @param testo testo nel form della recensione
     * @param valutazione intero valutazione stelle
     * @author Francesco Di Domenico
     */
    public Recensione(int idRecensione,int valutazione,String testo,int idUtente,int idOrdine){
        this.idRecensione=idRecensione;
        this.valutazione=valutazione;
        this.testo = testo;
        this.idUtente = idUtente;
        this.idOrdine = idOrdine;
    }

    /**
     *    Costruttore della classe recensione per AggiungiRecensione (Servlet)
     * @param idOrdine id dell ordine recensito
     * @param idUtente id dell utente che effettua la recensione
     * @param testo testo nel form della recensione
     * @param valutazione intero valutazione stelle
     * @author Francesco Di Domenico
     */

    public Recensione(int valutazione,String testo,int idUtente,int idOrdine){
        this.valutazione=valutazione;
        this.testo=testo;
        this.idUtente=idUtente;
        this.idOrdine=idOrdine;
    }
    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Recensione() {
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
