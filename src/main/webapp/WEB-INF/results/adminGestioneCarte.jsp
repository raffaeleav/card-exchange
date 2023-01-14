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
            </li>
            <%}%>
        </ul>
    </div>
</div>
<%@include file="../../footer.jsp"%>
</body>
</html>
