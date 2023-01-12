<%--
  Created by IntelliJ IDEA.
  User: michelemenzione
  Date: 06/01/23
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
  <link rel="icon" href="${pageContext.request.contextPath}/imgs/icons/card.svg">
  <title>CardXchange | Login </title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/login.css">
</head>

<body>
<<div class="login-page">
  <div class="form">
    <form method="post" class="login-form" action="login">
      <input name="email" type="text" placeholder="E-mail"/>
      <input name="password" type="password" placeholder="Password"/>
      <button>login</button>
      <p class="message">Non sei registrato? <a href="ShowRegistrazioneServlet">Crea un Account</a></p>
      <%
        if (request.getAttribute("msg") != null) {
          String error = (String) request.getAttribute("msg");
      %>
      <p class="error"><%= error%>
      </p>
      <% }%>
    </form>
    <img class="img" src="${pageContext.request.contextPath}/imgs/pages/login.png" alt="">
  </div>
</div>
</body>
</html>

