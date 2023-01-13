<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.* "%>
<%@ page import="java.util.*" %>
<%@ page import="acquisto.*" %>
<%@ page import="registrazione.*" %>
<%@ page import="storage.FacadeDAO" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/carrello.css"/>
  <title>I Miei Ordini</title>
</head>

//visualizza l'alert di checkout effettuato con successo nel caso venga visualizzata tramite redirect dalla servlet checkout
<script type="text/javascript">
  // Recupera il messaggio di successo dalla sessione
  var successMessage = '<%= session.getAttribute("successMessage") %>';

  // Se il messaggio di successo è stato impostato, visualizza un alert con il messaggio
  if (successMessage) {
    alert(successMessage);

    // Rimuovi il messaggio di successo dalla sessione
    '<% session.removeAttribute("successMessage");%>';
  }
</script>

<body>

<h1>I Miei Ordini</h1>

<%
  // Recupera l'id dell'ordine dalla sessione
  int idUtente = (int) session.getAttribute("idUtente");
  FacadeDAO facadeDAO = new FacadeDAO();
  List<Ordine> ordini = (List<Ordine>) facadeDAO.doRetrieveAllByIdUtente(Ordine.class, idUtente);
  for (Ordine ordine : ordini) {
%>
<h2>Ordine #<%= ordine.getIdOrdine() %></h2>
<p>Data: <%= ordine.getData() %></p>
<p>Indirizzo: <%= ordine.getIndirizzo() %></p>
<p>Totale: <%= ordine.getTotale() %></p>
<h3>Offerte</h3>
  <table>
      <tr>
        <th>Offerta #</th>
        <th>Carta # </th>
        <th>Nome: </th>
        <th>Venditore: </th>
        <th>Condizione: </th>
        <th>Prezzo: </th>
      </tr>

      <%

        List<Offerta> offerte = (List<Offerta>) facadeDAO.doRetrieveAllByIdOrdine(Ordine.class,ordine.getIdOrdine());
        for (Offerta offerta : offerte) {
      %>
      <tr>
        <td><%= offerta.getIdOfferta() %></td>
        <td><%= offerta.getIdCarta() %></td>
        <%
          // Recupero il nome della carta
          Carta carta = (Carta) facadeDAO.doRetrieveById(Carta.class,offerta.getIdCarta());
          String nomeCarta = carta.getNome();
          // Recupero il nome dell'utente
          Utente utente = (Utente) facadeDAO.doRetrieveById(Utente.class,ordine.getIdUtente());
          String venditore = utente.getNome();
        %>
        <td><%= nomeCarta%></td>
        <td><%= venditore %></td>
        <td><%= offerta.getPrezzo() %></td>
      </tr>
      <%
        }
      %>
  </table>



<%
  }
%>

</body>
</html>





