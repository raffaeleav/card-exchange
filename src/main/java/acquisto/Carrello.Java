package acquisto;
import java.util.ArrayList;
import java.util.List;

public class Carrello {

  private int idCarrello;
  private int idUtente;
  private List<Offerta> offerte;

    public Carrello(int idCarrello, int idUtente) {
      this.idCarrello = idCarrello;
      this.idUtente = idUtente;
      this.offerte = new ArrayList<>();
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

  public List<Offerta> getOfferte() {
      return offerte;
  }

  public void setOfferte(List<Offerta> offerte) {
      this.offerte = offerte;
  }

  public void aggiungiOfferta(Offerta offerta) {
      offerte.add(offerta);
  }

  public void rimuoviOfferta(Offerta offerta) {
      offerte.remove(offerta);
  }

}
