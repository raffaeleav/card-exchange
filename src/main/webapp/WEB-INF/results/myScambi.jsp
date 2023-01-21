<%@ page import="java.util.*" %>
<%@ page import="acquisto.*" %>
<%@ page import="storage.service.FacadeDAO" %>
<%@ page import="registrazione.Utente" %>
<%@ page import="scambio.Scambio" %>

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
                      List<Scambio> scambi = (List<Scambio>) facadeDAO.getAllScambiByIdDestinatario(Scambio.class, utente.getIdUtente());
                      if (!scambi.isEmpty()){
                    %>

                    <p>Proposte di scambio ricevute:</p>
                          <table id="myoffers-table">
                              <thead>
                                    <tr>
                                      <th>Nome Carta Richiesta</th>
                                      <th>Condizione</th>
                                        <th>Nome Carta Proposta</th>
                                        <th>Condizione</th>
                                      <th>Conguaglio</th>
                                      <th>Accetta scambio</th>
                                      <th>Rifiuta scambio</th>
                                    </tr>
                              </thead>

                            <%
                              for (Scambio scambio : scambi) {


                                  Offerta  offertaRichiesta= (Offerta) facadeDAO.doRetrieveById(Offerta.class,scambio.getIdOffertaMittente());
                                  Carta cartaRichiesta = (Carta) facadeDAO.doRetrieveById(Carta.class,offertaRichiesta.getIdCarta());

                                  Offerta offertaProposta = (Offerta) facadeDAO.doRetrieveById(Offerta.class,2);
                                  Carta cartaProposta = (Carta) facadeDAO.doRetrieveById(Carta.class,offertaProposta.getIdCarta());
                            %>

                              <tbody>
                                    <tr>
                                      <td><%= cartaRichiesta.getNome() %></td>
                                      <td><%= offertaRichiesta.getCondizione() %></td>
                                        <td><%= cartaProposta.getNome() %></td>
                                        <td><%= offertaProposta.getCondizione() %></td>
                                        <td><%= scambio.getConguaglio() %></td>

                                      <td>
                                        <form action="ScambioServlet" method="get">
                                          <input type="hidden" name="idScambio" value="<%=scambio.getIdRichiestaScambio()%>">
                                          <input type="submit" name="action" value="Accetta">
                                        </form>
                                      </td>
                                        <td>
                                            <form action="ScambioServlet" method="get">
                                                <input type="hidden" name="idScambio" value="<%=scambio.getIdRichiestaScambio()%>">
                                                <input type="submit" name="action" value="Rifiuta">
                                            </form>
                                        </td>
                                    </tr>
                              </tbody>

                            <%
                                    }
                                }

                                else {
                            %>
                                <p>Non hai ricevuto nessuna richiesta di scambio!</p>
                            <%
                              }
                            %>
                          </table>
                </div>
        </div>

    <%@include file="/footer.jsp"%>
    </body>
</html>