function validateReviewForm() {
    var rating = document.getElementsByName("rate");
    var text = document.getElementById("text").value;
    var valid = false;
    for(var i = 0; i < rating.length; i++) {
        if(rating[i].checked) {
            valid = true;
            break;
        }
    }
    if(!valid) {
        alert("Per favore seleziona una valutazione");
        return false;
    }
    if(text === "") {
        alert("Per favore inserisci il testo della recensione");
        return false;
    }
    if(text.length < 10 || text.length > 150) {
        alert("La lunghezza del testo della recensione deve essere compresa tra 10 e 150 caratteri");
        return false;
    }
    var regex = /^[a-zA-Z\s]+$/;
    if(!regex.test(text)) {
        alert("Il testo può contenere solo lettere e spazi");
        return false;
    }
    return true;
}