<%@ page import="storage.CartaDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="acquisto.Carta" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<style>
    *{
        padding: 0;
        box-sizing: border-box;
        margin: 0;
    }

    body{
        background-color:#202020;
        justify-content: center;
        align-items: center;
    }
</style>
<body>
<%@include file="header.jsp"%>
<h1><%= "Hello World!" %></h1>
<br/>
<%CartaDAO cartaDAO=new CartaDAO();%>
<%ArrayList<Carta> carte= new ArrayList<Carta>();%>
<%carte =cartaDAO.getAllCarte();%>
<%Carta c1= new Carta();%>
<%c1=cartaDAO.getCartaById(3);%>
<h1 style="color: aqua"> <%=c1.getNome()%> </h1>

<% for (Carta c:carte){%>
Nome: <%=c.getNome()%><br> <%}%>
<%@include file="footer.jsp"%>
</body>
</html>