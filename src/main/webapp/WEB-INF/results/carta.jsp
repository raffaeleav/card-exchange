<%@ page import="acquisto.*" %>
<%@ page import="storage.FacadeDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="registrazione.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/card.css">
    <%
    FacadeDAO facadeDAO = new FacadeDAO();
      int cardId = Integer.parseInt(request.getParameter("id-carta"));
      Carta carta = (Carta) facadeDAO.doRetrieveById(Carta.class,cardId);

    %>
    <title> <%=carta.getNome()%> in vendita su CardeXchange</title>
  </head>

  <body>
  <%@include file="/header.jsp"%>
  <div id="content">
    <div id="grid-container">
          <h1><%=carta.getNome()%></h1>
            <div class="image">
              <img src="<%=carta.getImmagine()%> }" alt="Immagine della carta <%=carta.getNome()%> " />
            </div>
          <table  id="cartaTable">
            <tr>
              <th>Nome</th>
              <td><%=carta.getNome()%></td>
            </tr>
            <tr>
              <th>Rarità</th>
              <td><%=carta.getRarita()%></td>
            </tr>
            <tr>
              <th>Categoria</th>
              <td><%=carta.getCategoria()%></td>
            </tr>
          </table>
          <h2>Offerte disponibili</h2>
          <table class ="tabellaOfferte" id="tabellaOfferte">
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

              Utente venditore;
              List<Offerta> offerte = (List <Offerta>) request.getAttribute("lista-offerte");

              for (Offerta offerta : offerte ) {
             venditore= (Utente) facadeDAO.doRetrieveById(Utente.class,offerta.getIdUtente());%>
            <tr>
              <td><%= venditore.getUsername()%> </td>
              <td><%= offerta.getIdOfferta() %> </td>
              <td><%= offerta.getCondizione() %> </td>
              <td><%= offerta.getPrezzo() %> </td>
              <td>
                <form action="aggiungiOffertaInCarrello" method="get">
                  <input type="hidden" name="offerta" value="<%=offerta.getIdOfferta()%>">
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
    </div>
  </div>
  <%@include file="/footer.jsp"%>
  </body>
</html>
