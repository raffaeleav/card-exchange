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
        <form id="updt" method="post" action="ModificaUtenteServlet?idUtente=<%=listutenti.getIdUtente()%>">
            <input type="text" name="cambiaNome" id="cambiaNome" value="<%=listutenti.getNome()%>" placeholder="modifica nome" required="required"> <br>
            <input type="text" name="cambiaCognome" id="cambiaCognome" value="<%=listutenti.getCognome()%>" placeholder="modifica cognome" required="required"> <br>
            <input type="text" name="cambiaUsername" id="cambiaUsername" value="<%=listutenti.getUsername()%>" placeholder="modifica username" required="required"> <br>
            <input type="password" name="cambiaPass" id="cambiaPass" value="<%=listutenti.getPassword()%>" placeholder="modifica password" required="required"> <br>
            <input type="text" name="cambiaEmail" id="cambiaEmail" value="<%=listutenti.getEmail()%>" placeholder="modifica email" required="required"> <br>
            <button class="submit" type="submit">Modifica<i class="fa-solid fa-wrench"></i></button>
        </form>
        <%if (listutenti.getIdUtente()>=2){%>
        <form method="post" action="EliminaUtente?idUtente=<%=listutenti.getIdUtente()%>">
                <button class="submitExit" type="submit" value="Elimina account">Elimina Account <i class="fa-solid fa-trash"></i></button><br>
            </form>  <%}%>
    </div>
    <%}%>
</div>
</body>
</html>
