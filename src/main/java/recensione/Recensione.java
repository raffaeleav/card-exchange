package recensione;

public class Recensione {
    private int idRecensione;
    private int valutazione;
    private String testo;
    public Recensione(int idRecensione,int valutazione,String testo){
        this.idRecensione=idRecensione;
        this.valutazione=valutazione;
        this.testo = testo;
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
