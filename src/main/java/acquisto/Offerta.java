package acquisto;

public class Offerta {
  private int idOfferta;
  private String condizione;
  private double prezzo;
  private int idUtente;
  private int idCarta;


  public Offerta(int idOfferta, String condizione, double prezzo, int idUtente, int idCarta) {
    this.idOfferta = idOfferta;
    this.condizione = condizione;
    this.prezzo = prezzo;
    this.idUtente = idUtente;
    this.idCarta = idCarta;

  }

  public int getIdOfferta() {
    return idOfferta;
  }

  public void setIdOfferta(int idOfferta) {
    this.idOfferta = idOfferta;
  }

  public String getCondizione() {
    return condizione;
  }

  public void setCondizione(String condizione) {
    this.condizione = condizione;
  }

  public double getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(double prezzo) {
    this.prezzo = prezzo;
  }

  public int getIdUtente() {
    return idUtente;
  }

  public void setIdUtente(int idUtente) {
    this.idUtente = idUtente;
  }

  public int getIdCarta() {
    return idCarta;
  }

  public void setIdCarta(int idCarta) {
    this.idCarta = idCarta;
  }
}
