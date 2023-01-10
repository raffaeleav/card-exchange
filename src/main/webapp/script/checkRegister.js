function checkRegistrationParams(username, email, password) {
    // Define the regular expressions for each parameter
    const usernameRegex = /^[a-zA-Z0-9_-]{3,16}$/;
    const emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})/;

    // Check the integrity of each parameter
    const isUsernameValid = usernameRegex.test(username);
    const isEmailValid = emailRegex.test(email);
    const isPasswordValid = passwordRegex.test(password);

    // Return the result as an object
    return {
        isUsernameValid,
        isEmailValid,
        isPasswordValid
    };
}

function check(){
    // Get the values of the input fields
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Check the integrity of the registration parameters
    const result = checkRegistrationParams(username, email, password);

    // If one of the parameters is invalid, show an error message and clear the input fields
    if (!result.isUsernameValid) {
        alert('Errore username. Per favore controlla la correttezza.');
        alert('Almeno 3 caratteri, sono permessi anche caratteri -, _')
        document.getElementById('username').value = '';
        return false;
    }
    else if(!result.isEmailValid) {
        alert('Email non valida. Per favore controlla la correttezza.');
        document.getElementById('email').value = '';
        return false;
    }
    else
        return true;
}


