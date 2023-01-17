<%@ page import="acquisto.*" %>
<%@ page import="storage.service.FacadeDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="registrazione.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
    <%
      FacadeDAO facadeDAO = new FacadeDAO();
      int cardId = Integer.parseInt(request.getParameter("id-carta"));
      Carta carta = (Carta) facadeDAO.doRetrieveById(Carta.class,cardId);
    %>
    <title><%=carta.getNome()%> in vendita su Card eXchange</title>
  </head>

  <body>
    <%@include file="/header.jsp"%>

      <div id="content-homepage">

          <div id="card-statistics">

            <div id="card-img">
              <img src="<%=carta.getImmagine()%>">
            </div>

            <div id="card-info">
              <p>
                Nome: <%=carta.getNome()%>
                <br>
                Brand: <%=carta.getCategoria()%>
                <br>
                Rarità: <%=carta.getRarita()%>
              </p>
            </div>

          </div>



        <div id="home-offers">

          <%
            List<Offerta> offerte = (List <Offerta>) request.getAttribute("lista-offerte");

            if(!offerte.isEmpty()){
          %>

              <table class ="tabellaOfferte" id="tabellaOfferte">
                <thead>
                  <tr>
                    <th>Venditore</th>
                    <th>Condizione</th>
                    <th>Prezzo</th>
                    <th>Compra</th>
                    <th>Scambia</th>
                  </tr>
                </thead>
                <tbody>

                <%
                  Utente venditore;

                  for (Offerta offerta : offerte ) {
                    venditore = (Utente) facadeDAO.doRetrieveById(Utente.class, offerta.getIdUtente());
                %>

                <tr>
                  <td><%= venditore.getUsername()%> </td>
                  <td><%= offerta.getCondizione() %> </td>
                  <td><%=  offerta.getPrezzo() %> </td>
                  <td>
                    <form action="aggiungiOffertaInCarrello" method="get">
                      <input type="hidden" name="offerta" value="<%=offerta.getIdOfferta()%>">
                      <input type="submit" value="Aggiungi al carrello">
                    </form>
                  </td>
                  <td>
                    <form action="ShowScambioServlet" method="get">
                      <input type="hidden" name="offerta" value="<%=offerta.getIdOfferta()%>">
                      <input type="submit" value="Scambia">
                    </form>
                  </td>
                </tr>

                <%
                  }
                %>
                </tbody>
              </table>
          <%
            }
                  else{
          %>
          <p>Non ci sono offerte!</p>
          <%}%>
        </div>
      </div>

    <%@include file="/footer.jsp"%>
  </body>
</html>
