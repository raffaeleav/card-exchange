<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="acquisto.Offerta" %>
<%@ page import="storage.OffertaDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="acquisto.Carrello" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/carrello.css"/>
    <title>Carrello</title>
</head>
<body>
<h1>Carrello</h1>
<table>
    <tr>
        <th>Nome offerta</th>
        <th>IdCarta</th>
        <th>Prezzo</th>
    </tr>
    <%
        // Recupera l'id dell'utente dalla sessione
        int idUtente = (int) session.getAttribute("idUtente");
        // Recupera le offerte del carrello utilizzando il metodo getOfferteByIdUtente del DAO OffertaDAO
        OffertaDAO offertaDAO = new OffertaDAO();
        List<Offerta> offerte;
        offerte = offertaDAO.getOfferteByIdUtente(idUtente);
        // Itera sulla lista delle offerte e visualizza le informazioni in una tabella
        for (Offerta offerta : offerte) {
    %>
    <tr>
        <td><%= offerta.getIdOfferta() %></td>
        <td><%= offerta.getIdCarta() %></td>
        <td><%= offerta.getPrezzo() %></td>
        <td>
            <form action="rimuoviOffertaDalCarrello" method="post">
                <input type="hidden" name="idOfferta" value="<%= offerta.getIdOfferta() %>">
                <input type="submit" value="Rimuovi">

            </form>
        </td>
    </tr>
    <% }
        %>
</table>
<form action="svuotaCarrello" method="post">
    <input type="submit" value="Svuota carrello">
</form>

<h2>Dati per il pagamento</h2>
<form action="checkout" method="post">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required>
    <br>
    <label for="cognome">Cognome:</label>
    <input type="text" id="cognome" name="cognome" required>
    <br>
    <label for="indirizzo">Indirizzo:</label>
    <input type="text" id="indirizzo" name="indirizzo" required>
    <br>
    <label for="numeroCivico">Numero civico:</label>
    <input type="text" id="numeroCivico" name="numeroCivico" required>
    <br>
    <label for="cap">CAP:</label>
    <input type="text" id="cap" name="cap" required>
    <br>
    <label for="citta">Città:</label>
    <input type="text" id="citta" name="citta" required>
    <br>
    <label for="paese">Paese:</label>
    <input type="text" id="paese" name="paese" required>
    <br>
    <label for="tipoSpedizione">Tipo di spedizione:</label>
    <select id="tipoSpedizione" name="tipoSpedizione" required>
        <option value="standard">Standard</option>
        <option value="express">Express</option>
    </select>
    <br>
    <label for="metodoPagamento">Metodo di pagamento:</label>
    <select id="metodoPagamento" name="metodoPagamento" required>
        <option value="cartaCredito">Carta di credito</option>
        <option value="paypal">PayPal</option>
    </select>
    <br>
    <%// Crea un oggetto Carrello e imposta le offerte del carrello
    Carrello carrello = new Carrello();
    carrello.setOfferte(offerte);
    // Recupera il totale del carrello utilizzando il metodo getTotaleCarrello
    double totale = carrello.getTotale();
    %>

    <!-- Visualizza il totale del carrello -->
    <p>Totale: <%= totale %> €</p>

        <input type="submit" value="Concludi il checkout">

    </form>


    <form action="showIndex" method="post">
        <input type="submit" value="Torna indietro e continua con lo shopping">
    </form>
</body>

</html>