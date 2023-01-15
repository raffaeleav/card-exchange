<%@ page import="acquisto.Carta" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Raffaele Aviello
  Date: 08/01/2023
  Time: 05:30
  To change this template use File | Settings | File Templates.
--%>
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

                            <form id="offer-form" action="mostra-pagina-carta-servlet" method="get">
                                <input type="hidden" name="id-carta" value="<%=card.getIdCarta()%>">
                                <input type="submit" id="offer-button" value="Vedi offerte">
                            </form>
                        </li>
                    <%}%>
                </ul>
            </div>
        </div>

        <%@include file="../../footer.jsp"%>
    </body>
</html>
