<%@ page import="storage.FacadeDAO" %>
<%@ page import="creazioneDiscussione.Discussione" %>
<%@ page import="java.util.List" %>
<%@ page import="creazioneDiscussione.Messaggio" %>
<%@ page import="registrazione.Utente" %>
<%@ page import="java.util.Collections" %>
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
        <script src="${pageContext.request.contextPath}/script/search-validation.js"></script>
    </head>

    <%
        FacadeDAO facadeDAO = new FacadeDAO();
        List<Discussione> topics = (List<Discussione>) facadeDAO.doRetrieveAll(Discussione.class);
        int topicId = 0;
        String topicTitle = null;

        for(Discussione topic : topics)
            if(topic.getTitolo().equals(request.getParameter("topic-title")) ) {
                topicId = topic.getIdDiscussione();
                topicTitle = topic.getTitolo();
            }

        List<Messaggio> messages = facadeDAO.doRetrieveMessageListByTopicId(topicId);

        Utente user = (Utente) session.getAttribute("Utente");
    %>

    <body>
    <%@include file="../../header.jsp"%>

    <div id="content">
        <div id="add-message">
            <form id="message-form" action="invia-messaggio-servlet" method="get">

                <label for="message-text-object">Oggetto:</label>
                <input id="message-text-object" name="message-text-object" type="text">

                <label for="message-text-body">Corpo:</label>
                <input id="message-text-body" name="message-text-body" type="text">

                <input type="hidden" id="topic-title" value="<%=topicTitle%>">
                <input id="message-button" type="submit" value="Invia">
            </form>
        </div>

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
                        if( user != null && (user.getIdUtente() == message.getIdUtente() || user.getIdUtente() == 1) ){
                    %>
                        <form action="" method="get">
                            <label for="modify-message-button"></label>
                            <input type="submit" id="modify-message-button" value="Modifica messaggio">
                        </form>

                        <form action="elimina-messaggio-servlet" method="get">
                            <label for="delete-message-button"></label>
                            <input type="submit" id="delete-message-button" value="Elimina messaggio">
                            <input type="hidden" id="topic-title-delete" value="<%=topicTitle%>">
                            <input type="hidden" id="message-id" value="<%=message.getIdMessaggio()%>">
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