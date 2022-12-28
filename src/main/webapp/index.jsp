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
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="https://github.com/">Hello Servlet</a>
<%@include file="footer.jsp"%>
</body>
</html>