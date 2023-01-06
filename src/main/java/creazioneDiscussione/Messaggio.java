package creazioneDiscussione;

/**
 * La classe modella il concetto di messaggio
 * @author Raffaele Aviello
 */

public class Messaggio {
    private int idMessaggio;
    private String oggetto, corpo;
    private int idUtente,idDiscussione;

    /**
     * Costruttore della classe Messaggio
     * @param idMessaggio id del messaggio
     * @param oggetto oggetto del messaggio
     * @param corpo corpo del messaggio
     * @param idDiscussione id della discussione in cui e' contenuto il messaggio
     * @param idUtente id dell' utente che ha scritto il messaggio
     * */
    public Messaggio(int idMessaggio, String oggetto, String corpo, int idUtente, int idDiscussione) {
        this.idMessaggio = idMessaggio;
        this.oggetto = oggetto;
        this.corpo = corpo;
        this.idUtente = idUtente;
        this.idDiscussione = idDiscussione;
    }

    public int getIdMessaggio() {
        return idMessaggio;
    }

    public void setIdMessaggio(int idMessaggio) {
        this.idMessaggio = idMessaggio;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdDiscussione() {
        return idDiscussione;
    }

    public void setIdDiscussione(int idDiscussione) {
        this.idDiscussione = idDiscussione;
    }
}
