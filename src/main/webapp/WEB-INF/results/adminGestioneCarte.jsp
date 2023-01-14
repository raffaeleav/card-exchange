<%@ page import="java.util.List" %>
<%@ page import="acquisto.Carta" %><%--
  Created by IntelliJ IDEA.
  User: elomi
  Date: 14/01/2023
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestione carte</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css"/>
</head>
<body>
<%List<Carta> listcarte=(List<Carta>) request.getAttribute("listcarte");%>

<%@include file="../../header.jsp"%>

<div id="content">
    <div class="addCard" style="text-align: center">
    <form method="post" action="MostraPaginaAggiuntaCarta">
        <input type="submit" value="Aggiungi Carta" style="background-color:#FFD100;color:#202020;">
    </form></div>
    <div id="grid-container">
        <ul>
            <%for(Carta carta : listcarte){%>
            <li>
                <br><br>
                Id:<%=carta.getIdCarta()%><br>
                Nome:<%=carta.getNome()%><br>
                Rarità: <%=carta.getRarita()%><br>
                Categoria: <%=carta.getCategoria()%>
                <br><br>
                <img src="${pageContext.request.contextPath}<%=carta.getImmagine()%>">

            <form id="updt" method="post" action="ModificaCartaServlet?idCarta=<%=carta.getIdCarta()%>">
                <input type="text" name="cambiaNome"  value="<%=carta.getNome()%>" placeholder="modifica nome" required="required"> <br>
                <input type="text" name="cambiaRarita"  value="<%=carta.getRarita()%>" placeholder="modifica rarità" required="required"> <br>
                <input type="text" name="cambiaCategoria"  value="<%=carta.getCategoria()%>" placeholder="modifica categoria" required="required"> <br>
                <input type="hidden" name="immagine">
                <input type="submit" value="Modifica">
            </form>
            <form method="post" action="EliminaCarta?idCarta=<%=carta.getIdCarta()%>">
                <input type="submit" value="Elimina Carta">
          </form></li>
            <%}%>
        </ul>
    </div>
</div>
<%@include file="../../footer.jsp"%>
</body>
</html>
