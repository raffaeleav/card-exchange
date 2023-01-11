var borderOK='3px solid #FFD100';
var borderNO='3px solid #c80009';
var testoOK=false;




function validaTesto(){
    var text=document.getElementsByClassName("text").value;
    var testo=document.getElementById("testo").value;

    if(text.length<10||text.length>=150){
        document.forms['text'].style.border=borderNO;
        testoOK=false;
    }
    else {
        document.forms['text'].style.border=borderOK;
        testoOK=true;}
    abilitaInvioRecensione();

    }

function abilitaInvioRecensione() {
    if(testoOK===true){
        document.getElementById("submit").disabled=false;
        document.getElementById("submit").style.border=borderOK;
    }
    else {
        document.getElementById("submit").disabled=true;
        document.getElementById("submit").style.border=borderNO;
    }
}