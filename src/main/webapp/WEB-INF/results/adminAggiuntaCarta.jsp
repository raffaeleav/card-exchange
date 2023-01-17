
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
            <select name="rarita">
              <option value="Comune">Comune</option>
              <option value="Non Comune">Non Comune</option>
              <option value="Rara">Rara</option>
              <option value="Foil">Foil</option>
              <option value="Promo">Promo</option>
              <option value="Crystal">Crystal</option>
              <option value="Shining">Shining</option>
            </select><br>
            <select id="categoria" name="categoria">
              <option value="Pokemon">Pokemon</option>
              <option value="Yu-Gi-Oh!" >Yu-Gi-Oh!</option>
              <option value="Magic:The Gathering" >Magic: The Gathering</option>
            </select> <br>



            <input type="submit" value="Aggiungi Carta" style="background-color:#FFD100;color:#202020;">
          </form></div>
    </ul>
  </div>
</div>
</body>
</html>
