var borderOK='3px solid #FFD100';
var borderNO='3px solid #c80009';
var testoOK=false;




function validaTesto(){
    var reg= /[A-Za-zÀ-ú0-9!?. ]/;
    var testo=document.getElementById("testo").value;

    if(reg.test(testo)===false){
        document.forms['testo'].style.border=borderNO;
        testoOK=false;
    }
    else {
        document.forms['testo'].style.border=borderOK;
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