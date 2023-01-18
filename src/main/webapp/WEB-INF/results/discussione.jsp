<%@ page import="storage.service.FacadeDAO" %>
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
        <script src="${pageContext.request.contextPath}/script/topic-validation.js"></script>
    </head>

    <%
        FacadeDAO facadeDAO = new FacadeDAO();
        String id = (String) request.getAttribute("topic-id");
        int topicId = Integer.parseInt(id);

        Discussione discussione = (Discussione) facadeDAO.doRetrieveById(Discussione.class, topicId);
        String topicTitle = discussione.getTitolo();

        List<Messaggio> messages = facadeDAO.doRetrieveMessageListByTopicId(Discussione.class, topicId);

        synchronized (session){
        Utente user = (Utente) session.getAttribute("Utente");
    %>

    <body>
    <%@include file="../../header.jsp"%>

    <div id="content">
        <div id="add-message">
            <form id="message-form" action="invia-messaggio-servlet" method="get">
                <p> Invia un messaggio: </p>

                <label for="message-text-object">Oggetto:</label>
                <input id="message-text-object" name="message-text-object" type="text">

                <label for="message-text-body">Corpo:</label>
                <input id="message-text-body" name="message-text-body" type="text">

                <input type="hidden" name="topic-id" value="<%=topicId%>">
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
                        <form id="modify-message-form" action="modifica-messaggio-servlet"
                              onsubmit="return messageValidation()" method="get">
                            <input type="hidden" name="topic-id-modify" value="<%=topicId%>">
                            <input type="hidden" name="message-id-modify" value="<%=message.getIdMessaggio()%>">

                            <label for="modify-message-text">Corpo del messaggio:</label>
                            <input type="text" id="modify-message-text" name="modify-message-text" required>

                            <input type="submit" id="modify-message-button" value="Modifica messaggio">
                        </form>

                        <form action="elimina-messaggio-servlet" method="get">
                            <input type="hidden" name="topic-id-delete" value="<%=topicId%>">
                            <input type="hidden" name="message-id-delete" value="<%=message.getIdMessaggio()%>">

                            <input type="submit" id="delete-message-button" value="Elimina messaggio">
                        </form>
                    <%
                        }
                    %>
                </li>
                <%
                        }
                    }
                %>
            </ul>
        </div>
    </div>

    <%@include file="../../footer.jsp"%>
    </body>
</html>