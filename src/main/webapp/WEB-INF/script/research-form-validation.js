function researchValidation(){
    // cambiare nomi dei form quando ci sara' la jsp
    let field = document.forms["reg-form"]["reg-name"].value;
    var correct = true;

    if(field == "" || field.length > 35) {
        correct = false;
    }

    return correct;
}