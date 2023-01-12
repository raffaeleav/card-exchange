<%@ page import="recensione.Recensione" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: elomi
  Date: 12/01/2023
  Time: 00:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <script src="https://kit.fontawesome.com/f52bb1298e.js" crossorigin="anonymous"></script>
</head>
<body>
<%ArrayList<Recensione> recensioni = (ArrayList<Recensione>) request.getAttribute("listrecensioni");%>
<div class="showRecensioni">
  <h2 class="title">Recensioni nel database:</h2><hr>
  <%for(Recensione listrecensioni:recensioni){%>
  <p>Dettagli recensione:</p>
  ID recensione:<%=listrecensioni.getIdRecensione()%><br>
  Valutazione:<%=listrecensioni.getValutazione()%><br>
  Testo:<%=listrecensioni.getTesto()%><br>
  ID ordine:<%=listrecensioni.getIdOrdine()%><br>
  ID utente:<%=listrecensioni.getIdUtente()%><br>
    <form method="post" action="EliminaRecensione?idRecensione=<%=listrecensioni.getIdRecensione()%>">
      <button class="submitExit" type="submit" value="Elimina recensione">Elimina Recensione <i class="fa-solid fa-trash"></i></button><br>
    </form>
  </div>
  <%}%>
</div>


</body>
</html>
