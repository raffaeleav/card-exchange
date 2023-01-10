<%@ page import="registrazione.Utente" %><%--
  Created by IntelliJ IDEA.
  User: francesco
  Date: 10/01/2023
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% Utente utente = (Utente) session.getAttribute("Utente");%>
<div class="utentePanel">
    <h2 class="titolo">User panel</h2><hr> <!--Dati dell utente-->
    <table class="attUtente">
        <tr><td>Nome: <%=utente.getNome()%></td></tr>
        <tr><td>Cognome: <%=utente.getCognome()%></td></tr>
        <tr><td>E-mail: <%=utente.getEmail()%></td></tr>
        <tr><td>Username: <%=utente.getUsername()%></td></tr>
        <tr><td>Password: <%=utente.getPassword()%></td></tr>
    </table>
    <%if(utente.getIdUtente()>=2){%> <!--Utente normale puo cambiare i propri dati.   -->
    <form id="updt" method="post" action="GestioneUtenteServlet?id=<%=utente.getIdUtente()%>">
        <input type="text" name="cambiaNome" id="cambiaNome" value="<%=utente.getNome()%>" placeholder="modifica nome" required="required"> <br>
        <input type="text" name="cambiaCognome" id="cambiaCognome" value="<%=utente.getCognome()%>" placeholder="modifica cognome" required="required"> <br>
        <input type="text" name="cambiaUsername" id="cambiaUsername" value="<%=utente.getUsername()%>" placeholder="modifica username" required="required"> <br>
        <input type="password" name="cambiaPass" id="cambiaPass" value="<%=utente.getPassword()%>" placeholder="modifica password" required="required"> <br>
        <button class="submit" type="submit">Modifica<i class="fa-solid fa-wrench"></i></button>
    </form>
    <%}%>
    <%if(utente.getIdUtente()==1){%> <!-- Nel pannello compare un href al pannello admin se l utente che accede è un admin-->
    <button class="btn"><a href="admin.jsp" style="text-decoration: none;color: #e49314"> Admin Console </a></button><%}%>
    <form id="logOut" action="LogOut" method="post">
        <button class="submitExit" type="submit" value="gestisci">Log-out<i class="fa-solid fa-arrow-right-from-bracket"></i></button><br>
    </form>
</div>

</body>
</html>
