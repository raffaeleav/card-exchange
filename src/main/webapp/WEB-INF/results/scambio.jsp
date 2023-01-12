<%@ page import="acquisto.Offerta" %>
<%@ page import="storage.CartaDAO" %>
<%@ page import="acquisto.Carta" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.FacadeDAO" %>
<%@ page import="registrazione.Utente" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>


<%--
  Created by IntelliJ IDEA.
  User: michelemenzione
  Date: 09/01/23
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%
    Utente u = (Utente) request.getSession().getAttribute("Utente");
    FacadeDAO dao = new FacadeDAO();
    List<Offerta>offerteUtente = (List<Offerta>) request.getAttribute("offerteUtente");
    Offerta offertaRichiesta = (Offerta) request.getAttribute("offertaRichiesta");
    Carta cartaRichiesta = (Carta) request.getAttribute("cartaRichiesta");



%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/scambio.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CardeXchange | Scambio </title>

    <script src="${pageContext.request.contextPath}/script/changeCard.js"></script>
</head>
<body>
<div class="container">

    <div class="inner">
        <h2>Carta Proposta </h2>
        <img class="img image-swap" src="https://images.pokemontcg.io/base1/1_hires.png" alt="" srcset="">
        <p class="description">Nome: <span id="nome" class="results"></span></p>
        <p class="description">Categoria: <span class="results categoria"></span></p>
        <p class="description">Rarità: <span id="rarity" class="results"></span></p>
        <p class="description">Condizione: <span class="results condizione"></span></p>
        <p class="description">Prezzo: <span class="results prezzo"></span></p>
    </div>
    <label>
        <select id="offerte" class="custom-select">
            <option>Scegli un offerta</option>
            <%for(Offerta o:offerteUtente ){
                Carta c = (Carta) dao.doRetrieveById(Carta.class,o.getIdCarta());
            %>
            <option img="<%=c.getImmagine()%>" value="<%=c.getNome()%>"
            categoria="<%=c.getCategoria()%>" rarity="<%=c.getRarita()%>"
            condizione="<%=o.getCondizione()%>" prezzo="<%=o.getPrezzo()%>" idOfferta="<%=o.getIdOfferta()%>">
                <%=c.getNome()%></option>
            <%}%>

        </select>
    </label>
</div>

<div class="container">
    <img style="width: 200px;" src="${pageContext.request.contextPath}/imgs/pages/PokemonChange.png" alt="">
    <div class="wrap">
        <form method="post" class="scambio-form" action="ScambioServlet">
            <input type="hidden" name="idUtenteMittente" value="<%=u.getIdUtente()%>">
            <input type="hidden" name="idOffertaMittente" value="<%=offertaRichiesta.getIdOfferta()%>">
            <input id="offertaScelta" type="hidden" name="idOffertaDestinatario" value="2">
                <input type="number" name="conguaglio"><br>
            <button class="button">Scambia</button></form>
    </div>
</div>

<div class="container">
    <div class="inner">
        <h2>Carta Richiesta </h2>
        <img class="img" src="<%=cartaRichiesta.getImmagine()%>" alt="" srcset="">
        <p class="description">Nome: <span class="results"><%=cartaRichiesta.getNome()%>  </span></p>
        <p class="description">Categoria: <span class="results"><%=cartaRichiesta.getCategoria()%></span></p>
        <p class="description">Rarità: <span class="results"><%=cartaRichiesta.getRarita()%> </span></p>
        <p class="description">Condizione: <span class="results"><%=offertaRichiesta.getCondizione()%></span></p>
        <p class="description">Prezzo: <span class="results"><%=offertaRichiesta.getPrezzo() + "€"%></span></p>
    </div>
</div>
</body>
</html>
