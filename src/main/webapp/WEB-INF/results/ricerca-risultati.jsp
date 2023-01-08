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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    </head>

    <body>
        <%@include file="../../header.jsp"%>

        <div id="content">
            <div id="grid-container">
                <ul>
                    <c:forEach items="${card-matches}" var="card">
                        <li>
                            <br><br>
                                ${card.nome} ${card.categoria} ${card.rarita}
                            <br><br>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <%@include file="../../footer.jsp"%>
    </body>
</html>
