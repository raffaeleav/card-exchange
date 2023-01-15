function checkPurchasingParams(nome, cognome, indirizzo) {
    // Define the regular expressions for each parameter
    var regex = /^[a-zA-Z\s']+$/;

    // Define APIs key to use OpenCage Data APIs
    const chiaveAPI = "a9fe494899fc4d50a4fcf6ae9c33440f";


    // Check the integrity of textual parameter
    const isNomeValid = regex.test(nome);
    const isCognomeValid = regex.test(cognome);

    // Check the integrity of address using OpenCage Data APIs
    var isAddressValid = 0 ;
    fetch('https://api.opencagedata.com/geocode/v1/json?q=' + indirizzo + '&key=' + chiaveAPI)
    .then(response => response.json())
    .then(data => {
        if(data.results.length > 0) {
            //indirizzo valido
            isAddressValid = Boolean(1);

        } else {
            //indirizzo non valido
            isAddressValid = Boolean(0);
        }
        });

     // Return the result as an object
    return {
        isNomeValid,
        isCognomeValid,
        isAddressValid
    };
}



function check(){
    // Get the values of the input fields
    let nome = document.forms["cart-form"]["name"].value;
    let cognome = document.forms["cart-form"]["cognome"].value;
    let indirizzoCompleto= document.forms["cart-form"]["indirizzo"].value.trim()+" "+document.forms["cart-form"]["numeroCivico"].trim()+" "+document.forms["cart-form"]["cap"].trim()+" "+document.forms["cart-form"]["citta"].trim()+" "+document.forms["cart-form"]["provincia"].trim();

    alert(nome,cognome,indirizzoCompleto);
    // Check the integrity of the registration parameters
    let result = checkPurchasingParams(nome,cognome,indirizzoCompleto);

    // If one of the parameters is invalid, show an error message and clear the input fields
    if (!result.isNomeValid) {
        alert('Errore Nome. Per favore controlla la correttezza.');
        alert('Sono permessi solo caratteri alfabetici, spazi e apostrofi')
        document.forms["cart-form"]["name"].value = '';
        return false;
    }
    else if(!result.isCognomeValid) {
        alert('Errore Cognome. Per favore controlla la correttezza.');
        alert('Sono permessi solo caratteri alfabetici, spazi e apostrofi')
        document.forms["cart-form"]["cognome"].value = '';
        return false;
    }
    else if(!result.isIndirizzoValid){
        alert('Errore Indirizzo. Per favore controlla che sia un indirizzo esistente.');
        alert('Controlla che i campi indirizzo, numero civico, cap, città, provincia siano tutti corretti. ')
         document.forms["cart-form"]["indirizzo"].value=" ";
         document.forms["cart-form"]["numeroCivico"].value=" ";
         document.forms["cart-form"]["cap"].value="";
         document.forms["cart-form"]["citta"].value="";
         document.forms["cart-form"]["provincia"].value="";

        return false;
    }
    else{return true;}

}


