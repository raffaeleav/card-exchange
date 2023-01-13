<!DOCTYPE html>
<html>
<head>

    <script type="text/javascript" src="src=${pageContext.request.contextPath}/script/research_selling.js"></script>
  <title>Vendita | CardeXchange</title>
</head>
  <body>

    <h1>Metti in vendita una carta su CardeXchange</h1>
    <h2>Pubblica un'offerta</h2>

      <form id="formRicerca" action="ricerca-servlet" method="GET">
          <label for="nomeCarta">Nome:</label>
          <input type="text" id="nomeCarta" name="carta">
          <label for="categoria">Categoria:</label>
          <select id="categoria" name="categoria">
            <option value="Pokemon">Pokemon</option>
            <option value="Yu-Gi-Oh!">Yu-Gi-Oh!</option>
            <option value="Magic: The Gathering">Magic: The Gathering</option>
          </select>
          <input type="submit" value="Cerca" onsubmit="research-selling()">

      </form>
    <form id="formOfferta" action="pubblicaOfferta" method="POST" style="display:none;">
        <input type="hidden" id="idCarta" name="idCarta" value="">
        <label for="condizione">Condizione:</label>
        <select id="condizione" name="condizione">
            <option value="Mint (M)">Mint (M)</option>
            <option value="Near Mint (NM)">Near Mint (NM)</option>
            <option value="Excellent (EX)">Excellent (EX)</option>
            <option value="Good (GD)">Good (GD)</option>
            <option value="Light Played (LP)">Light Played (LP)</option>
            <option value="Played (PL)">Played (PL)</option>
            <option value="Poor (PO)">Poor (PO)</option>
        </select>
        <label for="prezzo">Prezzo:</label>
        <input type="number" id="prezzo" name="prezzo">
        <input type="submit" value="Pubblica">
    </form>



  </body>
</html>
