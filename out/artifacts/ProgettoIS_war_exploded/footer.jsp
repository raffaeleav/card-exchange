<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <script src="https://kit.fontawesome.com/f52bb1298e.js" crossorigin="anonymous"></script>
</head>
<style>
/*css footer*/
*{
padding: 0;
margin: 0;
box-sizing: border-box;
}

body{

font-family: sans-serif;
font-weight: 300;
}

.footer{
position:relative;
bottom: auto;
bottom: 0;
margin-bottom: 0;
margin-top: 35px;
padding: 10px 10px 0px 10px;
width: 100%;
}

.footer .social{
text-align: center;
color: #FFD100;

}

.footer .social a{
color: inherit;
font-size: 30px;
width: 40px;
height: 40px;
line-height: 38px;
display: inline-block;
border-radius: 50%;
text-align: center;
margin: 0 8px;
}

.footer ul{
margin-top: 0;
padding: 3px;
list-style: none;
font-size: 18px;
line-height: 1.6;
margin-bottom: 0;
text-align: center;
}

.footer ul a {
color:#FFD100;
}

.footer ul li{
display: inline-block;
padding: 0 15px;
}

.footer .copyright{
margin-top: 15px;
text-align: center;
font-size: 12px;
color: #FFD100;
}

</style>
<body>
<div class="footer">
    <div class="social">
        <a id="twitter" href=""><i class="fa-brands fa-twitter"></i> </a>
        <a id="facebook" href=""><i class="fa-brands fa-facebook-f"></i></a>
        <a id="instagram" href=""><i class="fa-brands fa-instagram"></i></a>
    </div>
    <ul class="lista">
        <li>
            <div class="chiSiamo">
                <a href="#">About us</a>
            </div>
        </li>
    </ul>
    <div class="copyright"><i class="fa-solid fa-copyright"></i><a>Copyright 2022 CardEXchange - All rights reserved</a></div>
</div>

</body>
</html>