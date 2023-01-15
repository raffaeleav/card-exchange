<%@ page import="acquisto.Carta" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.FacadeDAO" %>
<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta charset="UTF-8">
        <title>Card eXchange</title>
        <link rel="stylesheet" type="text/css" href="styles/style.css">
        <script src="script/search-validation.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <%
        FacadeDAO facadeDAO = new FacadeDAO();
        List<Carta> cards = (ArrayList<Carta>) facadeDAO.doRetrieveAll(Carta.class);
        Random rand = new Random();
        int cardNumber = rand.nextInt(cards.size());
        Carta card = cards.get(cardNumber);
    %>
    <body>
    <%@include file="header.jsp"%>

    <div id="content">
        <div id="card-statistics">
            <div id="card-info">
                <br><br>
                <%=card.getNome()%>
                <br><br>
                <img src="<%=card.getImmagine()%>">
            </div>
        </div>

        <div id="home-offers">

        </div>
    </div>

    <%@include file="footer.jsp"%>
    </body>
</html>
