function researchSelling() {
    document.querySelector("#formRicerca").addEventListener("submit", function(event){
        event.preventDefault();
        var xhr = new XMLHttpRequest();
        var nomeCarta = document.querySelector("#nomeCarta").value;
        var categoria = document.querySelector("#categoria").value;
        xhr.open("GET", "search-servlet-selling?carta=" + nomeCarta + "&categoria=" + categoria);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var risposta = JSON.parse(xhr.responseText);
                // gestisci i dati ricevuti e visualizzali nella pagina
                gestisciRisposta(risposta);
            } else {
                console.error('Errore nella richiesta: ' + xhr.status);
            }
        }
        xhr.send();
    });


    function gestisciRisposta(risposta) {
        var matchCarte = risposta;
        var tabellaRisultati = document.getElementById("tabellaRisultati");

        //ciclo sui risultati della ricerca
        for (var i = 0; i < matchCarte.length; i++) {
            var carta = matchCarte[i];
            var riga = document.createElement("tr");
            //crea una cella per ogni campo della carta
            var idCella = document.createElement("td");
            idCella.innerHTML = carta.id;
            riga.appendChild(idCella);

            var nomeCella = document.createElement("td");
            nomeCella.innerHTML = carta.nome;
            riga.appendChild(nomeCella);

            var raritàCella = document.createElement("td");
            raritàCella.innerHTML = carta.rarità;
            riga.appendChild(raritàCella);

            var immagineCella = document.createElement("td");
            immagineCella.innerHTML = "<img src='" + carta.immagine + "'>";
            riga.appendChild(immagineCella);

            //aggiungi la riga alla tabella
            tabellaRisultati.appendChild(riga);
        }
        var righeTabella = document.querySelectorAll("#tabellaRisultati tbody tr");
        for (var i = 0; i < righeTabella.length; i++) {
            righeTabella[i].addEventListener("click", selezionaRiga);
        }

    }

    function selezionaRiga() {
        deselezionaRighe();
        this.classList.add("selezionata");
        mostraSelettori();
    }

    function mostraSelettori() {
        var selezionato = document.querySelector("#tabellaRisultati tr.selezionato");
        if (selezionato) {
            document.querySelector("#selettore-condizione").style.display = "block";
            document.querySelector("#selettore-prezzo").style.display = "block";
        } else {
            document.querySelector("#selettore-condizione").style.display = "none";
            document.querySelector("#selettore-prezzo").style.display = "none";
        }
    }

    document.querySelector("#tabellaRisultati").addEventListener("click", function (event) {
        var riga = event.target.parentNode;
        var righe = document.querySelectorAll("#tabellaRisultati tr");
        for (var i = 0; i < righe.length; i++) {
            righe[i].classList.remove("selezionato");
        }
        riga.classList.add("selezionato");
        var idCarta = riga.querySelector("td").textContent;
        document.querySelector("#idCarta").value = idCarta;
        document.querySelector("#formOfferta").style.display = "block";
    });

}