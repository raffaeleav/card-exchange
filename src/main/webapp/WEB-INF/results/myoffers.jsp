<%@ page import="java.util.*" %>
<%@ page import="acquisto.*" %>
<%@ page import="storage.service.FacadeDAO" %>
<%@ page import="registrazione.Utente" %>

<!DOCTYPE html>
<html>
    <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css"/>
      <title>Le mie offerte</title>
    </head>

    <body>
        <%@include file="/header.jsp"%>

        <div id="content">
                  <div id="myoffers">

                    <%
                      FacadeDAO facadeDAO = new FacadeDAO();
                      Utente utente = (Utente) session.getAttribute("Utente");
                      List<Offerta> offerte = (ArrayList<Offerta>) facadeDAO.doRetrieveAllByIdUtente(Offerta.class, utente.getIdUtente());
                      if (!offerte.isEmpty()){
                    %>

                    <p>Le mie carte in vendita:</p>
                          <table id="myoffers-table">
                              <thead>
                                    <tr>
                                      <th>Nome</th>
                                      <th>Condizione</th>
                                      <th>Prezzo</th>
                                      <th>Rimuovi offerta</th>
                                    </tr>
                              </thead>

                            <%
                              for (Offerta offerta : offerte) {
                                Carta carta = (Carta) facadeDAO.doRetrieveById(Carta.class, offerta.getIdCarta());
                            %>

                              <tbody>
                                    <tr>
                                      <td><%= carta.getNome() %></td>
                                      <td><%= offerta.getCondizione() %></td>
                                      <td><%= offerta.getPrezzo() %></td>
                                      <td>
                                        <form action="rimuovi-offerta" method="get">
                                          <input type="hidden" name="idOfferta" value="<%=offerta.getIdOfferta()%>">
                                          <input type="submit" value="Rimuovi offerta">
                                        </form>
                                      </td>
                                    </tr>
                              </tbody>

                            <%
                                    }
                                }

                                else {
                            %>
                                <p>Non hai pubblicato nessuna offerta!</p>

                                <form action="mostraPaginaVenditaServlet" method="get">
                                    <input type="submit" value="Pubblica">
                                </form>
                            <%
                              }
                            %>
                          </table>
                </div>
        </div>

    <%@include file="/footer.jsp"%>
    </body>
</html>