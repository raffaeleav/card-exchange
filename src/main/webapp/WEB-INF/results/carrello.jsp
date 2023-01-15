<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="acquisto.*" %>
<%@ page import="storage.service.FacadeDAO" %>
<%@ page import="registrazione.*" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css" />
    <script src="${pageContext.request.contextPath}/script/checkPurchase.js"></script>
    <script type="text/javascript">
        //visualizza l'alert di checkout effettuato con successo nel caso venga visualizzata tramite redirect dalla servlet checkout
        // Recupera il messaggio di successo dalla sessione
        var emptyCartMessage = '<%= session.getAttribute("ErrorParams") %>';
        // Se il messaggio  è stato impostato, visualizza un alert con il messaggio
        if (emptyCartMessage!="null") {
            alert(emptyCartMessage);

            // Rimuovi il messaggio dalla sessione
            '<% session.removeAttribute("EmptyCartMessage");%>';
        }
    </script>
    <title>Carrello | CardeXchange</title>

</head>

<body>
<%@include file="/header.jsp"%>
<div class="cart-page">
    <h1>Carrello</h1>
    <div class="offers">
        <table>
            <tr>
                <th>Nome offerta</th>
                <th>IdCarta</th>
                <th>Prezzo</th>
            </tr>

            <%
                FacadeDAO facadeDAO = new FacadeDAO();
                // Recupera l'id dell'utente dalla sessione
                Utente user = (Utente) request.getSession().getAttribute("Utente");

                // Recupera il carrello dalla request
                Carrello carrello = (Carrello) facadeDAO.doRetrieveByIdUtente(Carrello.class,user.getIdUtente());
                request.setAttribute("Carrello",carrello);
                int idUtente = user.getIdUtente();

                // Recupera le offerte del carrello

                List<Offerta> offerte= (List <Offerta>)facadeDAO.doRetrieveAllByIdUtenteCarrello(Offerta.class,idUtente);


                // Itera sulla lista delle offerte e visualizza le informazioni in una tabella
                for (Offerta offerta : offerte) {
            %>
            <tr>
                <td><%= offerta.getIdOfferta() %> </td>
                <td><%= offerta.getIdCarta() %> </td>
                <td><%= offerta.getPrezzo() %> </td>
                <td>
                    <form action= "rimuoviOffertaDalCarrello" method="get">

                        <input type="hidden" name="offerta" value="<%= offerta.getIdOfferta() %>">
                        <input type="submit" value="Rimuovi">
                    </form>


                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>

    <form action="svuotaCarrello" method="post">
        <input type="submit" value="Svuota carrello">
    </form>

    <div class ="form">
        <h2>Dati per il pagamento</h2>
        <form action="checkout" method="get" id="cart-form" name="cart-form">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required>
            <br>
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" required>
            <br>
            <label for="indirizzo">Indirizzo:</label><input type="text" id="indirizzo" name="indirizzo" required>
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
            <label for="provincia">Provincia:</label>
            <input type="text" id="provincia" name="provincia" required>
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
                <%
                        //calcola il totale del carrello
                        //carrello.setTotale(facadeDAO.calculateTotaleInCarrello(Carrello.class, carrello.getIdCarrello()));

                    %>
    </div>

    <!-- Visualizza il totale del carrello -->
    <p>Totale: <%= carrello.getTotale() %> € </p>




        <input type="hidden" name="offerte" value ="<%= offerte%>">
        <input type="hidden" name="totale" value="<%= carrello.getTotale() %>">
        <input type="submit" value="Concludi il checkout">


    <input onclick="location.href='index.jsp';" type="submit" value="Torna indietro e continua con lo shopping">

</div>
<%@include file="/footer.jsp"%>
</body>
</html>