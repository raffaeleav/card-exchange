<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta charset="UTF-8">
        <script src="script/search-validation.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <body>
        <div id="header">
            <ul id="top-navbar">
                <!-- Home -->
                <li> <a href="index.jsp"> Card eXchange </a> </li>
                <!-- Utente -->
                <li> <a href=""> <i class="fa-solid fa-user fa-lg"></i> </a> </li>
                <!-- Carte in vendita -->
                <li> <a href=""> <i class="fa-solid fa-list fa-lg"></i> </a> </li>
                <!-- Vendi carta -->
                <li> <a href=""> <i class="fa-solid fa-lock fa-lg"></i> </a> </li>
                <!-- Ordini effettuati -->
                <li> <a href=""> <i class="fa-solid fa-clipboard fa-lg"></i> </a> </li>
                <!-- Forum -->
                <li> <a href="forum-servlet"> <i class="fa-solid fa-comment fa-lg"></i> </a> </li>
                <!-- Carrello -->
                <li> <a href=""> <i class="fa-solid fa-cart-arrow-down fa-lg"></i> </a> </li>
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
