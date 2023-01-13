<%@ page import="acquisto.*" %>
<%@ page import="storage.FacadeDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="registrazione.Utente" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/card.css"/>
    <title> ${nomeCarta} in vendita su CardeXchange</title>
  </head>

  <body>
    <h1>${nomeCarta}</h1>
    <img src="${immagineCarta}" alt="Immagine della carta ${immagine}" />
    <table>
      <tr>
        <th>Nome</th>
        <td>${nomeCarta}</td>
      </tr>
      <tr>
        <th>Tipo</th>
        <td>${tipoCarta}</td>
      </tr>
      <tr>
        <th>Rarità</th>
        <td>${raritàCarta}</td>
      </tr>
      <tr>
        <th>Categoria</th>
        <td>${categoriaCarta}</td>
      </tr>
    </table>
    <h2>Offerte disponibili</h2>
    <table id="tabellaOfferte">
      <thead>
      <tr>
        <th>Venditore</th>
        <th>Offerta #</th>
        <th>Condizione</th>
        <th>Prezzo</th>
        <th>Compra & Scambia</th>
      </tr>
      </thead>
      <tbody>
      <%
        FacadeDAO facadeDAO = new FacadeDAO();
        Utente venditore;
        List<Offerta> offerte = (List <Offerta>) request.getAttribute("lista-offerte");

        for (Offerta offerta : offerte ) { %>
      <%= venditore= (Utente) facadeDAO.doRetrieveById(Utente.class,offerta.getIdUtente())%>
      <tr>
        <td><%= venditore.getUsername()%> </td>
        <td><%= offerta.getIdOfferta() %> </td>
        <td><%= offerta.getCondizione() %> </td>
        <td><%= offerta.getPrezzo() %> </td>
        <td>
          <form action="aggiungiOffertaInCarrello" method="post">
            <input type="hidden" name="idOfferta" value="${offerta.idOfferta}">
            <input type="submit" value="Aggiungi al carrello">
          </form>
          <form action="ShowScambioServlet" method="get">
            <input type="hidden" name="offerta" value="<%=offerta.getIdOfferta()%>">
            <input type="submit" value="Scambia">
          </form>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </body>
</html>
