<%@ page import="registrazione.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: elomi
  Date: 11/01/2023
  Time: 00:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%ArrayList<Utente> utenti = (ArrayList<Utente>) request.getAttribute("listutenti");%>
<div class="showUtenti">
    <h2 class="title">Utenti nel database:</h2><hr>
    <%for(Utente listutenti:utenti){%>
    <p>Dettagli utente:</p>
    ID:<%=listutenti.getIdUtente()%><br>
    Nome:<%=listutenti.getNome()%><br>
    Cognome:<%=listutenti.getCognome()%><br>
    E-mail:<%=listutenti.getEmail()%><br>
    password:<%=listutenti.getPassword()%><br>
    <div class="formContainer">
        <p>Modifica con :</p>
        <form id="updt" method="post"
    </div>
    <%}%>


</div>


</body>
</html>
