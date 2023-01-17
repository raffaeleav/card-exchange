<%@ page import="acquisto.Carta" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Card eXchange</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css"/>
  <script src="${pageContext.request.contextPath}/script/search-form-validation.js"></script>
</head>

<%List<Carta> cards = (ArrayList<Carta>) request.getAttribute("card-matches");%>
<body>
<%@include file="../../header.jsp"%>

<div id="content">
  <div id="grid-container">
    <ul>
      <%for(Carta card : cards){%>
      <li>
        <br><br>
        <%=card.getNome()%>
        <br><br>
        <img src="<%=card.getImmagine()%>">

              <form id="offer-form" action="pubblicaOfferta" method="get" >
                    <label for="condizione">Condizione:</label>
                    <select id="condizione" name="condizione">
                      <option value="Mint (M)">Mint (M)</option>
                      <option value="Near Mint (NM)">Near Mint (NM)</option>
                      <option value="Excellent (EX)">Excellent (EX)</option>
                      <option value="Good (GD)">Good (GD)</option>
                      <option value="Light Played (LP)">Light Played (LP)</option>
                      <option value="Played (PL)">Played (PL)</option>
                      <option value="Poor (PO)">Poor (PO)</option>
                    </select>
                    <label for="prezzo">Prezzo:</label>
                    <input type="number" id="prezzo" name="prezzo" min="0.01" step="0.01" required>
                    <input type="hidden" name="id-carta" value="<%=card.getIdCarta()%>">
                    <input type="submit" value="Pubblica">
              </form>
      </li>
      <%}%>
    </ul>
  </div>
</div>

<%@include file="../../footer.jsp"%>
</body>
</html>
