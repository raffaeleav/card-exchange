$(document).ready(function(){
    $("#offerte").change(function(){
        var imgsrc = $(this).children("option:selected").attr("img");
        $('.image-swap').attr("src", imgsrc);

        var idOfferta = $(this).children("option:selected").attr("idOfferta");
        $('#offertaScelta').attr("value", idOfferta);

        var nome = $(this).children("option:selected").attr("value");
        $('#nome').html(nome);

        var categoria = $(this).children("option:selected").attr("categoria");
        $('.categoria').html(categoria);

        var rarita = $(this).children("option:selected").attr("rarity");
        $('#rarity').text(rarita);

        var condizione = $(this).children("option:selected").attr("condizione");
        $('.condizione').html(condizione);

        var prezzo = $(this).children("option:selected").attr("prezzo");
        $('.prezzo').html(prezzo);




    });
});