<%@ page import="storage.FacadeDAO" %>
<%@ page import="creazioneDiscussione.Discussione" %>
<%@ page import="java.util.List" %>
<%@ page import="creazioneDiscussione.Messaggio" %>
<%@ page import="registrazione.Utente" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Collections" %><%--
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
        <script src="${pageContext.request.contextPath}/script/search-validation.js"></script>
    </head>

    <%
        FacadeDAO facadeDAO = new FacadeDAO();
        List<Discussione> topics = (List<Discussione>) facadeDAO.doRetrieveAll(Discussione.class);
        int topicId = 1;

        for(Discussione topic : topics)
            if(topic.getTitolo().equals(request.getParameter("topic-title")) )
                topicId = topic.getIdDiscussione();

        List<Messaggio> messages = facadeDAO.doRetrieveMessageListByTopicId(topicId);
    %>

    <body>
    <%@include file="../../header.jsp"%>

    <div id="content">
        <div id="grid-container">
            <ul>
                <%
                    Collections.reverse(messages);
                    for(Messaggio message : messages){%>
                <li>
                    <h3>
                        <%=message.getOggetto()%>
                    </h3>

                    <p>
                        <%=message.getCorpo()%>
                    </p>

                    <%
                        Utente user = (Utente) session.getAttribute("Utente");

                        if( user != null && (user.getIdUtente() == message.getIdUtente() || user.getIdUtente() == 1) ){
                    %>
                        <form action="" method="get">
                            <label for="modify-message-button"></label>
                            <input type="submit" id="modify-message-button" value="Modifica messaggio">
                        </form>

                        <form action="" method="get">
                            <label for="modify-message-button"></label>
                            <input type="submit" id="delete-message-button" value="Elimina messaggio">
                        </form>
                    <%
                        }
                    %>
                </li>
                <%}%>
            </ul>
        </div>
    </div>

    <%@include file="../../footer.jsp"%>
    </body>
</html>