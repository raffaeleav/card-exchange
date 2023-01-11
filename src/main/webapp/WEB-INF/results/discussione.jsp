<%--
  Created by IntelliJ IDEA.
  User: Raffaele Aviello
  Date: 09/01/2023
  Time: 03:59
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
        <div id="grid-container">
            <ul>
                <!-- quando fai partecipazioneDiscussione aggiungere qui la lista dei messaggi -->
                <c:forEach items="${topics-list}" var="topic">
                    <li>
                        <br><br>
                            ${topic.titolo}
                        <br><br>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <%@include file="../../footer.jsp"%>
    </body>
</html>