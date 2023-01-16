function searchValidationSelling(){
    let field = document.forms["search-form-selling"]["search-text-selling"].value;

    if(field == "" || field.length > 35) {
        alert("Il campo di ricerca non deve essere vuoto o superare i 35 caratteri!");
        return false;
    }

    else
        return true;
}