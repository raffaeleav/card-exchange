<%@ page import="creazioneDiscussione.Discussione" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="registrazione.Utente" %>
<%@ page import="storage.FacadeDAO" %>
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
    </head>

    <%List<Discussione> topics = (ArrayList<Discussione>) request.getAttribute("topics-list");%>
    <body>
        <%@include file="../../header.jsp"%>

        <div id="content">
            <div id="create-topic">
                <form id="topic-form" action="creazione-discussione-servlet">
                    <label>Crea una discussione:</label>

                    <input id="topic-text" name="topic-text" type="text">
                    <input id="topic-button" type="submit" value="Crea">
                </form>
            </div>

            <div id="grid-container-topic">
                <ul>
                    <%for(Discussione topic : topics){%>
                    <li>
                        <br><br>
                            <%=topic.getTitolo()%>
                        <br><br>

                        <form action="" method="get">
                            <input type="submit" id="join-topic" value="Partecipa alla discussione">
                        </form>

                        <%
                            Utente user = (Utente) session.getAttribute("Utente");
                            if(user != null && (topic.getIdUtente() == user.getIdUtente() || user.getIdUtente() == 1) ){
                        %>
                        <form action="elimina-discussione-servlet" method="get">
                            <input type="submit" id="delete-topic" value="Elimina discussione">
                            <input type="hidden" id="topic-id" value="<%=topic.getIdDiscussione()%>">
                        </form>
                        <%
                            }
                        %>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>

        <%@include file="../../footer.jsp"%>
    </body>
</html>
