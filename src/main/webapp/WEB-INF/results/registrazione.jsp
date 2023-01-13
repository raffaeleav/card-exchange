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
    <title>CardXchange | Registrazione </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/register.css">
</head>
<body>
<script src="${pageContext.request.contextPath}/script/checkRegister.js"></script>
<!-- Add the function to a script tag in the HTML file -->
<!-- Add an HTML form to the page -->
<<div class="register-page">
    <p>Registrazione</p>
    <div class="form">
        <form onsubmit="return check()" action="registrazione" method="post" id="registration-form">
            <!-- Add input fields for the username, email, and password parameters -->
            <label for="username">Username</label><input type="text" id="username" name="username"><br>
            <label for="email">Email</label><input type="text" id="email" name="email"><br>
            <label for="nome">Nome</label><input type="text" id="nome" name="nome"><br>
            <label for="cognome">Cognome</label><input type="text" id="cognome" name="cognome"><br>
            <label for="password">Password</label><input type="password" id="password" name="password"><br>

            <!-- Add a submit button to the form -->
            <button>Invia</button>
            <p class="message">Sei registrato? <a href="ShowLoginServlet">Esegui il login</a></p>
            <%
                if (request.getAttribute("msg") != null) {
                    String error = (String) request.getAttribute("msg");
            %>
            <p class="error"><%= error%>
            </p>
            <% }%>
        </form>
    </div>
    <img class="img" src="${pageContext.request.contextPath}/imgs/pages/registerImg.png" alt="">
</div>


<!-- Add an event listener to the form that calls the checkRegistrationParams() function when the form is submitted -->


</body>
</html>

