<%--
  Created by IntelliJ IDEA.
  User: Raffaele Aviello
  Date: 08/01/2023
  Time: 05:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Card eXchange</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css"/>
    <script src="${pageContext.request.contextPath}/script/search-form-validation.js"></script>
  </head>

  <body>
    <%@include file="../../header.jsp"%>

    <div id="content">
      <div id="content-text">
        <p>Per creare o partecipare ad una discussione devi aver effettuato il login!</p>
      </div>
    </div>

    <%@include file="../../footer.jsp"%>
  </body>
</html>