<%--
  Created by IntelliJ IDEA.
  User: michelemenzione
  Date: 13/01/23
  Time: 02:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <style>
    body{
      background-image: url("${pageContext.request.contextPath}/imgs/pages/errorBackground.png");

      /* Center and scale the image nicely */
      background-position: top;
      background-repeat: no-repeat;
      background-size: 65%;
    }
  </style>
  <script src="https://kit.fontawesome.com/ed82670ed2.js" crossorigin="anonymous"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
<div class="container">

    <span style="font-size: 32px; color: black;">
    <a href="${pageContext.request.contextPath}/index.jsp" style="text-decoration:none;"><i class="fa-solid fa-house"></i></a>
    </span>

</div>

</body>
</html>
