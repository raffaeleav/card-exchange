package creazioneDiscussione;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe modella il concetto di discussione
 * @author Raffaele Aviello
 */

public class Discussione {
    private int idDiscussione, idUtente;
    private String titolo;

    /**
     * Costruttore della classe Discussione
     * @param idDiscussione id della discussione
     * @param titolo titolo della discussione
     * @param idUtente id dell' utente che ha creato la discussione
     * */
    public Discussione(int idDiscussione, int idUtente, String titolo) {
        this.idDiscussione = idDiscussione;
        this.idUtente = idUtente;
        this.titolo = titolo;
    }

    public Discussione(){}

    public int getIdDiscussione() {
        return idDiscussione;
    }

    public void setIdDiscussione(int idDiscussione) {
        this.idDiscussione = idDiscussione;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
