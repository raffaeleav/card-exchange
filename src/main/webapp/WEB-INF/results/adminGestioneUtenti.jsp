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
    <script src="https://kit.fontawesome.com/f52bb1298e.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<%ArrayList<Utente> utenti = (ArrayList<Utente>) request.getAttribute("listutenti");%>
<div id="content">
    <div id="grid-container">

        <ul>
            <%for(Utente listutenti:utenti){%>
            <li>
                <br><br>
                <p>Dettagli utente:</p>
                ID:<%=listutenti.getIdUtente()%><br>
                Nome:<%=listutenti.getNome()%><br>
                Cognome:<%=listutenti.getCognome()%><br>
                E-mail:<%=listutenti.getEmail()%><br>
                password:<%=listutenti.getPassword()%><br>
                <br><br>

                <form id="updt" method="post" action="ModificaUtenteServlet?idUtente=<%=listutenti.getIdUtente()%>">
                    <input type="text" name="cambiaNome" id="cambiaNome" value="<%=listutenti.getNome()%>" placeholder="modifica nome" required="required"> <br>
                    <input type="text" name="cambiaCognome" id="cambiaCognome" value="<%=listutenti.getCognome()%>" placeholder="modifica cognome" required="required"> <br>
                    <input type="text" name="cambiaUsername" id="cambiaUsername" value="<%=listutenti.getUsername()%>" placeholder="modifica username" required="required"> <br>
                    <input type="password" name="cambiaPass" id="cambiaPass" value="<%=listutenti.getPassword()%>" placeholder="modifica password" required="required"> <br>
                    <input type="text" name="cambiaEmail" id="cambiaEmail" value="<%=listutenti.getEmail()%>" placeholder="modifica email" required="required"> <br>
                    <input type="submit" value="Modifica utente">
                </form>
                <%if (listutenti.getIdUtente()>=2){%>
                <form method="post" action="EliminaUtente?idUtente=<%=listutenti.getIdUtente()%>">
                    <input type="submit" value="Elimina Account">
                </form>
                <form method="post" action="AdminRecensioniServlet?idUtente=<%=listutenti.getIdUtente()%>">
                    <input type="submit" value="Recensioni utente">
                </form>
                <form method="post" action="AdminOfferteServlet?idUtente=<%=listutenti.getIdUtente()%>">
                    <input type="submit" value="Offerte utente" disabled>
                </form><%}%>
            </li>

            <%}%> </ul>
         </div>
</div>
</body>
</html>
