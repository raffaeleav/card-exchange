<%@ page import="java.util.*" %>
<%@ page import="acquisto.*" %>
<%@ page import="registrazione.*" %>
<%@ page import="storage.service.FacadeDAO" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css"/>
  <title>I Miei Ordini</title>
</head>


<script type="text/javascript">
    //visualizza l'alert di checkout effettuato con successo nel caso venga visualizzata tramite redirect dalla servlet checkout
  // Recupera il messaggio di successo dalla sessione
  var successMessage = '<%= session.getAttribute("successMessage") %>';
  // Se il messaggio di successo è stato impostato, visualizza un alert con il messaggio
  if (successMessage!="null") {
    alert(successMessage);

    // Rimuovi il messaggio di successo dalla sessione
    '<% session.removeAttribute("successMessage");%>';
  }
</script>

<body>
<%@include file="/header.jsp"%>
<div id="content">
    <div id="grid-container">
        <div id="myorders">
            <p>Ordini effettuati:</p>

            <table id="home-offers-table">
                    <thead>
                                <tr>
                                    <th>Data: </th>
                                    <th>Indirizzo di spedizione:</th>
                                    <th>Totale</th>
                                    <th>       </th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                  // Recupera l'id dell'utente
                                      FacadeDAO facadeDAO = new FacadeDAO();
                                      Utente utente= (Utente) session.getAttribute("Utente");
                                      List<Ordine> ordini = (ArrayList<Ordine>) facadeDAO.doRetrieveAllByIdUtente(Ordine.class,utente.getIdUtente());
                                  for (Ordine ordine : ordini) {
                                %>


                                <tr>
                                    <td><%= ordine.getData() %></td>
                                    <td> <%= ordine.getIndirizzo() %></td>
                                    <td><%= ordine.getTotale() %></td>
                                    <td><form method="post"action="MostraPaginaRecensione" >
                                        <input type="hidden" name="idOrdine" value="<%=ordine.getIdOrdine()%>">
                                        <input type="submit" value="Invia Recensione">
                                    </form></td>
                                    <%
                                        }
                                    %>
                                </tr>
                    </tbody>
            </table>
    </div>
    </div>
</div>
<%@include file="/footer.jsp"%>
</body>
</html>





