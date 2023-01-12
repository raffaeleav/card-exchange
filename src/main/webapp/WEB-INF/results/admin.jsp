<%@ page import="registrazione.Utente" %><%--
  Created by IntelliJ IDEA.
  User: elomi
  Date: 10/01/2023
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>admin console</title>
  <script src="https://kit.fontawesome.com/f52bb1298e.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/admin.css">
</head>

<body>
<% Utente utente = (Utente) session.getAttribute("Utente");%>
<%if(utente.getIdUtente()==1){%>
<div class="admin_panel">
  <h3>ADMIN CONSOLE</h3><hr><br>
  <div class="forms">
    <form method="post" action="AdminCarteServlet">
      <p>Carte </p>
      <button class="submit" type="submit" value="gestisci" disabled>Gestisci <i class="fa-solid fa-wrench"></i></button>
    </form>
    <form method="post" action="AdminOfferteServlet">
      <p>Offerte </p>
      <button class="submit" type="submit" value="gestisci"disabled>Gestisci <i class="fa-solid fa-wrench"></i></button> <br>
    </form>
    <form method="post" action="AdminUtentiServlet">
      <p>Utenti </p>
      <button class="submit" type="submit" value="gestisci">Gestisci<i class="fa-solid fa-wrench"></i></button><br>
    </form>
    <form method="post" action="AdminRecensioniServlet">
      <p>Recensioni </p>
      <button class="submit" type="submit" value="gestisci">Gestisci<i class="fa-solid fa-wrench"></i></button><br>
    </form>
    <form method="post" action="AdminDiscussioniServlet">
      <p>Discussioni </p>
      <button class="submit" type="submit" value="gestisci" disabled>Gestisci<i class="fa-solid fa-wrench"></i></button><br>
    </form>

  </div>

</div><%}%><%if(utente.getIdUtente()!=1){%>
<div class="forUser">
  <h2>Non dovresti essere in questa pagina.</h2><hr>  <br>
  <a href="../../index.jsp">Clicca per tornare alla homepage</a>
</div><%}%>

</body>
</html>

