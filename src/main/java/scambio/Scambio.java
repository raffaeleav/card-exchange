package scambio;
/**
 * La classe Scambio rappresenta una RichiestaDiScambio nel sistema di Card eXchange,
 * contiene informazioni come idUtenteMittente, idUtenteDestinatario, idOffertaMittente.
 * Include metodi di getter e setter, utilizzati per visualizzare e/o settare parametri dell'istanza.
 * @author Michele Menzione
 */

public class Scambio {
    private int idRichiestaScambio, idUtenteMittente, idUtenteDestinatario, idOffertaMittente,
    idOffertaDestinatario;
    double conguaglio;


    public Scambio(int idUtenteMittente, int idUtenteDestinatario, int idOffertaMittente, int idOffertaDestinatario, double conguaglio) {
        this.idUtenteMittente = idUtenteMittente;
        this.idUtenteDestinatario = idUtenteDestinatario;
        this.idOffertaMittente = idOffertaMittente;
        this.idOffertaDestinatario = idOffertaDestinatario;
        this.conguaglio = conguaglio;
    }

    public Scambio(int idRichiestaScambio, int idUtenteMittente, int idUtenteDestinatario, int idOffertaMittente, int idOffertaDestinatario, double conguaglio) {
        this.idRichiestaScambio = idRichiestaScambio;
        this.idUtenteMittente = idUtenteMittente;
        this.idUtenteDestinatario = idUtenteDestinatario;
        this.idOffertaMittente = idOffertaMittente;
        this.idOffertaDestinatario = idOffertaDestinatario;
        this.conguaglio = conguaglio;
    }



    public int getIdRichiestaScambio() {
        return idRichiestaScambio;
    }

    public void setIdRichiestaScambio(int idRichiestaScambio) {
        this.idRichiestaScambio = idRichiestaScambio;
    }

    public int getIdUtenteMittente() {
        return idUtenteMittente;
    }

    public void setIdUtenteMittente(int idUtenteMittente) {
        this.idUtenteMittente = idUtenteMittente;
    }

    public int getIdUtenteDestinatario() {
        return idUtenteDestinatario;
    }

    public void setIdUtenteDestinatario(int idUtenteDestinatario) {
        this.idUtenteDestinatario = idUtenteDestinatario;
    }

    public int getIdOffertaMittente() {
        return idOffertaMittente;
    }

    public void setIdOffertaMittente(int idOffertaMittente) {
        this.idOffertaMittente = idOffertaMittente;
    }

    public int getIdOffertaDestinatario() {
        return idOffertaDestinatario;
    }

    public void setIdOffertaDestinatario(int idOffertaDestinatario) {
        this.idOffertaDestinatario = idOffertaDestinatario;
    }

    public double getConguaglio() {
        return conguaglio;
    }

    public void setConguaglio(double conguaglio) {
        this.conguaglio = conguaglio;
    }

    public Scambio() {

    }

}
