<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
    .text-form{
        resize: none;
    }

    .rate {
        float: left;
        height: 46px;
        padding: 0 10px;
    }
    .rate:not(:checked) > input {
        position:absolute;
        top:-9999px;
    }
    .rate:not(:checked) > label {
        float:right;
        width:1em;
        overflow:hidden;
        white-space:nowrap;
        cursor:pointer;
        font-size:30px;
        color:#D6D6D6;
    }
    .rate:not(:checked) > label:before {
        content: '★ ';
    }
    .rate > input:checked ~ label {
        color: #FFD100;
    }
    .rate:not(:checked) > label:hover,
    .rate:not(:checked) > label:hover ~ label {
        color: #FFD100;
    }
    .rate > input:checked + label:hover,
    .rate > input:checked + label:hover ~ label,
    .rate > input:checked ~ label:hover,
    .rate > input:checked ~ label:hover ~ label,
    .rate > label:hover ~ input:checked ~ label {
        color: #c59b08;
    }
</style>
<body>

<div class="recensioneContainer">
    <form method="post" action="addRecensione">
        <div class="rate">
            <input type="radio" id="star5" name="rate" value="5" />
            <label for="star5" title="text"></label>
            <input type="radio" id="star4" name="rate" value="4" />
            <label for="star4" title="text"></label>
            <input type="radio" id="star3" name="rate" value="3" />
            <label for="star3" title="text"></label>
            <input type="radio" id="star2" name="rate" value="2" />
            <label for="star2" title="text"></label>
            <input type="radio" id="star1" name="rate" value="1" />
            <label for="star1" title="text"></label>
        </div><br>
        <textarea class="text-form" id="testo" name="text" rows="3" placeholder="Inserisci testo" required maxlength="150"></textarea><br>
        <input class="submit" type="submit" value="Invia">
    </form>
</div>

</body>
</html>
