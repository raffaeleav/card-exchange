
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css"/>
</head>
<body>


<div id="content">
  <div id="grid-container">
    <ul>
        <div class="addCard" style="text-align: center;margin: auto">
          <form method="post" action="AggiungiCarta">
            <input type="text" name="nome" placeholder="Scrivi nome" required><br>
            <input type="text" name="rarita" placeholder="Scrivi Rarita" required><br>
            <input type="text" name="categoria" placeholder="Scrivi categoria" required><br>
            <input type="hidden" name="immagine">
            <input type="submit" value="Aggiungi Carta" style="background-color:#FFD100;color:#202020;">
          </form></div>
    </ul>
  </div>
</div>
</body>
</html>
