<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it-IT">
    <head>

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
                    <form id="search-form" name="search-form" action="ricerca-servlet"
                          onsubmit="return researchValidation()" method="get">
                        <input id="search-text" name="search-text" type="text">
                        <input id="search-button" type="submit" value="Cerca">
                    </form>
                </li>
            </ul>
        </div>
    </body>
</html>
