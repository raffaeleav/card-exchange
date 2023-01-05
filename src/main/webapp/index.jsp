<%@ page import="registrazione.Registrazione" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: michelemenzione
  Date: 02/01/23
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

<%
    Registrazione r = new Registrazione();
    int res;
    try {
        res = r.registraNuovoUtente("ss3sssd","d3sa","dsa","d3a","dawad");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    System.out.println(res);

    if(res > 0){
%>
<h1>Registrazione effettuata!</h1>
<%}else if (res == -1){%>
<h1>Registrazione fallita!, username gia in uso</h1>
<%}else{%>
<h1>Registrazione fallita!, Email gia in uso</h1>
<%}%>



</body>
</html>
