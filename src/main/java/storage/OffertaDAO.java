package storage

import acquisto.Offerta;
import storage.ConPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
La classe OffertaDAO rappresenta il Data Access Object (DAO) per le entità Offerta. Si tratta di una classe che definisce i metodi per gestire le operazioni CRUD (create, read, update, delete) sulle offerta nel database.

In particolare, la classe include i seguenti metodi:
addOfferta
getOffertaById
getAllOfferte
deleteOfferta
Per la connessione al database viene utilizzata la classe ConPool.
*/

public class OffertaDao {

  //elenco delle query 
  private static final String INSERT_OFFERTA_QUERY = "INSERT INTO Offerta(condizione, prezzo, idUtente, idCarta, idOrdine) VALUES (?, ?, ?, ?, ?)";
  private static final String SELECT_OFFERTA_BY_ID_QUERY = "SELECT * FROM Offerta WHERE idOfferta = ?";
  private static final String SELECT_ALL_OFFERTE_QUERY = "SELECT * FROM Offerta";
  private static final String DELETE_OFFERTA_QUERY = "DELETE FROM Offerta WHERE idOfferta = ?";

  ////inserisce una nuova offerta nel database
  public void addOfferta(Offerta offerta) { 
    try (Connection con = ConPool.getConnection()) {
      PreparedStatement statement = con.prepareStatement(INSERT_OFFERTA_QUERY);
      statement.setString(1, offerta.getCondizione());
      statement.setInt(2, offerta.getPrezzo());
      statement.setInt(3, offerta.getIdUtente());
      statement.setInt(4, offerta.getIdCarta());
      statement.setInt(5, offerta.getIdOrdine());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //recupera un'offerta dal database in base all'id specificato
  public Offerta getOffertaById(int idOfferta) {
    try (Connection con = ConPool.getConnection()) {
      PreparedStatement statement = con.prepareStatement(SELECT_OFFERTA_BY_ID_QUERY);
      statement.setInt(1, idOfferta);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String condizione = resultSet.getString("condizione");
        int prezzo = resultSet.getInt("prezzo");
        int idUtente = resultSet.getInt("idUtente");
        int idCarta = resultSet.getInt("idCarta");
        int idOrdine = resultSet.getInt("idOrdine");
        return new Offerta(idOfferta, condizione, prezzo, idUtente, idCarta, idOrdine);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  //recupera tutte le offerta presenti nel database
  public List < Offerta > getAllOfferte() {
  List < Offerta > offerte = new ArrayList < > ();
  try (Connection con = ConPool.getConnection()) {
    PreparedStatement statement = con.prepareStatement(SELECT_ALL_OFFERTE_QUERY);
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
      int idOfferta = resultSet.getInt("idOfferta");
      String condizione = resultSet.getString("condizione");
      int prezzo = resultSet.getInt("prezzo");
      int idUtente = resultSet.getInt("idUtente");
      int idCarta = resultSet.getInt("idCarta");
      int idOrdine = resultSet.getInt("idOrdine");
      Offerta offerta = new Offerta(idOfferta, condizione, prezzo, idUtente, idCarta, idOrdine);
      offerte.add(offerta);
    }
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return offerte;
}
  
  // elimina un'offerta dal database in base all'id specificato
  public void deleteOfferta(int idOfferta) {
  try (Connection con = ConPool.getConnection()) {
    PreparedStatement statement = con.prepareStatement(DELETE_OFFERTA_QUERY);
    statement.setInt(1, idOfferta);
    statement.executeUpdate();
  } catch (SQLException e) {
    e.printStackTrace();
  }
}
  
  
  
}






