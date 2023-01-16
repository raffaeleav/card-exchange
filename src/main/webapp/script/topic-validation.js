function topicValidation(){
    let field = document.forms["topic-form"]["topic-text"].value;

    if(field == "" || field.length > 35) {
        alert("Il campo di ricerca non deve essere vuoto o superare i 35 caratteri!");
        return false;
    }

    else
        return true;
}

function messageValidation(){
    let field = document.forms["modify-message-form"]["modify-message-text"].value;

    if(field == "" || field.length > 150) {
        alert("Il campo di ricerca non deve essere vuoto o superare i 150 caratteri!");
        return false;
    }

    else
        return true;
}