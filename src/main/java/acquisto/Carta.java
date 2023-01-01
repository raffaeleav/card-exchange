package acquisto;

public class Carta {
    private int idCarta;
    private String nome;
    private String categoria;
    private String rarita;

    public Carta() {
    }

    public Carta(int idCarta, String nome, String categoria, String rarita) {
        this.idCarta = idCarta;
        this.nome = nome;
        this.categoria = categoria;
        this.rarita = rarita;
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
}
