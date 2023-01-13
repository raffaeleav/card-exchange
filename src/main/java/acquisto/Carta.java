package acquisto;

/**
 * La classe modella il concetto di Carrello
 * @author Francesco Di Domenico
 */
public class Carta {
    private int idCarta;
    private String nome;
    private String categoria;
    private String rarita;
    private  String immagine;  //serve per il datapath dell immagine.

    public Carta() {
    }

    public Carta(int idCarta, String nome, String categoria, String rarita) {
        this.idCarta = idCarta;
        this.nome = nome;
        this.categoria = categoria;
        this.rarita = rarita;
    }
    /**
     * Costruttore della classe Carta
     * @param idCarta id della carta
     * @param nome  nome della carta;
     * @param categoria  categoria della carta;
     * @param rarita rarita della carta;
     * @param immagine url immagine della carta;
     * */
    public Carta(int idCarta, String nome, String categoria, String rarita, String immagine) {
        this.idCarta = idCarta;
        this.nome = nome;
        this.categoria = categoria;
        this.rarita = rarita;
        this.immagine = immagine;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getRarita() {
        return rarita;
    }

    public void setRarita(String rarita) {
        this.rarita = rarita;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
}
