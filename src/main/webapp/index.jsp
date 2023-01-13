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

    <body>
    <%@include file="header.jsp"%>

    <div id="content">
    </div>

    <%@include file="footer.jsp"%>
    <a href="ShowScambioServlet?offerta=1">Scambio</a>
    <a href="ShowLoginServlet">Login</a>
    <a href="ShowRegistrazioneServlet">Registrati</a>
    </body>
</html>
