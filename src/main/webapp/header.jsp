<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta charset="UTF-8">
        <script src="script/research-form-validation.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <body>
        <div id="header">
            <ul id="top-navbar">
                <li> <a href="index.jsp">Home</a> </li>
                <li> <a href="">Account</a> </li>
                <li> <a href="">In vendita</a> </li>
                <li> <a href="">Vendi</a> </li>
                <li> <a href="">Ordini effettuati</a> </li>
                <li> <a href="forum-servlet">Forum</a> </li>
                <li> <a href="">Carrello</a> </li>
            </ul>

            <ul id="bottom-navbar">
                <li id="search-bar">
                    <form id="search-form" name="search-form" action="search-servlet"
                          onsubmit="return searchValidation()" method="get">
                        <input id="search-text" name="search-text" type="text">
                        <button id="search-button" type="submit">
                            <i class="fas fa-search fa-lg"></i>
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </body>
</html>
