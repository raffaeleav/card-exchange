package scambio;

public class Scambio {
    private int idRichiestaScambio, idUtenteMittente, idUtenteDestinatario,idOfferta;

    public Scambio( int idUtenteMittente, int idUtenteDestinatario, int idOfferta) {
        this.idUtenteMittente = idUtenteMittente;
        this.idUtenteDestinatario = idUtenteDestinatario;
        this.idOfferta = idOfferta;
    }
    public Scambio(int idRichiestaScambio, int idUtenteMittente, int idUtenteDestinatario, int idOfferta) {
        this.idRichiestaScambio = idRichiestaScambio;
        this.idUtenteMittente = idUtenteMittente;
        this.idUtenteDestinatario = idUtenteDestinatario;
        this.idOfferta = idOfferta;
    }

    public Scambio() {

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

    public int getIdOfferta() {
        return idOfferta;
    }

    public void setIdOfferta(int idOfferta) {
        this.idOfferta = idOfferta;
    }
}
