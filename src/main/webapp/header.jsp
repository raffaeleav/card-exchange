<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta charset="UTF-8">
        <script src="script/search-validation.js"></script>
        <script src="https://kit.fontawesome.com/a250a51d87.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <div id="header">
            <div id="logo">
                <a href="index.jsp" id="logo-img"> <img src="imgs/logo.png"> </a>
            </div>

            <ul id="top-navbar">
                <!-- Home -->
                <li title="Home"> <a href="index.jsp"> <i class="fa-solid fa-house fa-lg"></i> </a> </li>
                <!-- Utente -->
                <li title="Area utente"> <a href="MostraPaginaLogin"> <i class="fa-solid fa-user fa-lg"></i> </a> </li>
                <!-- Carte in vendita -->
                <li title="Carte in vendita"> <a href=""> <i class="fa-solid fa-list fa-lg"></i> </a> </li>
                <!-- Vendi carta -->
                <li title="Vendi carta"> <a href=""> <i class="fa-solid fa-money-bill fa-lg"></i> </a> </li>
                <!-- Ordini effettuati -->
                <li title="Ordini effettuati"> <a href="MostraOrdini"> <i class="fa-solid fa-receipt fa-lg"></i> </a> </li>
                <!-- Forum -->
                <li title="Forum"> <a href="forum-servlet"> <i class="fa-solid fa-message fa-lg"></i> </a> </li>
                <!-- Carrello -->
                <li title="Carrello"> <a href="mostraPaginaCarrello"> <i class="fa-solid fa-cart-shopping fa-lg"></i> </a> </li>
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
