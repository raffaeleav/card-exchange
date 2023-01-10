<%--
  Created by IntelliJ IDEA.
  User: elomi
  Date: 10/01/2023
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet">
<head>
  <title>admin console</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/admin.css">
</head>

<body>


<div class="admin_panel">
  <h3>ADMIN CONSOLE</h3><hr><br>
  <div class="forms">
    <form method="post" action="gestioneCarteServlet">
      <p>Carte </p>
      <button class="submit" type="submit" value="gestisci">Gestisci <i class="fa-solid fa-wrench"></i></button>
    </form>
    <form method="post" action="gestioneOfferteServlet">
      <p>Categorie </p>
      <button class="submit" type="submit" value="gestisci">Gestisci <i class="fa-solid fa-wrench"></i></button> <br>
    </form>
    <form method="post" action="gestioneUtentiServlet">
      <p>Utenti </p>
      <button class="submit" type="submit" value="gestisci">Gestisci<i class="fa-solid fa-wrench"></i></button><br>
    </form>
    <form method="post" action="gestioneRecensioniServlet">
      <p>Recensioni </p>
      <button class="submit" type="submit" value="gestisci">Gestisci<i class="fa-solid fa-wrench"></i></button><br>
    </form>

  </div>

</div>

</body>
</html>

