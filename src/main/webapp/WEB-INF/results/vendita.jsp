<!DOCTYPE html>
<html>
<head>

    <script type="text/javascript" src="src=${pageContext.request.contextPath}/script/search-validation-selling.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css"/>
  <title>Vendita | CardeXchange</title>
</head>
    <body>
        <%@include file="../../header.jsp"%>

          <div id="content">
              <div id="grid-container">
                  <div id="create-topic">

                      <p>Metti in vendita una carta su Card eXchange, cerca una carta e poi pubblica un'offerta!</p>
                      <form id="search-form-selling" name="search-form-selling" action="search-servlet-selling"
                                        onsubmit="return searchValidationSelling()" method="get">

                          <label >Nome:</label>
                          <input type="search-text-selling" id="search-text-selling" name="search-text-selling" required>
                          <br>
                          <label for="categoria">Categoria:</label>
                          <select id="categoria" name="categoria">
                              <option value="Pokemon">Pokemon</option>
                              <option value="Yu-Gi-Oh!">Yu-Gi-Oh!</option>
                              <option value="Magic: The Gathering">Magic: The Gathering</option>
                          </select>

                          <button id="search-button" type="submit">Cerca </button>
                      </form>
                  </div>
              </div>
          </div>

          <%@include file="../../footer.jsp"%>
    </body>
</html>
