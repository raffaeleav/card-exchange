<%@ page import="acquisto.Carta" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.service.FacadeDAO" %>
<%@ page import="java.util.Random" %>
<%@ page import="acquisto.Offerta" %>
<%@ page import="registrazione.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta charset="UTF-8">
        <title>Card eXchange</title>
        <link rel="stylesheet" type="text/css" href="styles/style.css">
        <script src="script/search-validation.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <%
        FacadeDAO facadeDAO = new FacadeDAO();
        List<Carta> cards = (ArrayList<Carta>) facadeDAO.doRetrieveAll(Carta.class);
        Random rand = new Random();
        int cardNumber = rand.nextInt(cards.size());
        Carta card = cards.get(cardNumber);

        int next;
        Random random = new Random();
        Utente venditore;
        List<Offerta> offers = (List<Offerta>) facadeDAO.doRetrieveAllByIdCarta(Offerta.class, card.getIdCarta());
    %>

    <body>
    <%@include file="header.jsp"%>

    <div id="content-homepage">
        <div id="card-statistics">
            <div id="card-img">
                <img src="<%=card.getImmagine()%>">
            </div>


            <div id="card-info">
                <p>
                    Nome: <%=card.getNome()%>
                    <br>
                    Brand: <%=card.getCategoria()%>
                    <br>
                    Rarità: <%=card.getRarita()%>
                </p>

                <form id="offer-form" action="mostra-pagina-carta-servlet" method="get">
                    <input type="hidden" name="id-carta" value="<%=card.getIdCarta()%>">
                    <input type="submit" id="offer-button" value="Vedi tutte le offerte">
                </form>
            </div>
        </div>

            <%
                if(!offers.isEmpty()){
            %>

        <div id="home-offers">

            <table id="home-offers-table">
                <thead>
                <tr>
                    <th>Venditore</th>
                    <th>Condizione</th>
                    <th>Prezzo</th>
                    <th>Compra</th>
                    <th>Scambia</th>
                </tr>
                </thead>

                <%
                    int homeOffersLength;

                    if(offers.size() >= 5)
                        homeOffersLength = 5;

                    else
                        homeOffersLength = offers.size();

                        for (int i = 0; i < homeOffersLength; i++) {
                            Offerta offer = offers.get(i);
                            venditore = (Utente) facadeDAO.doRetrieveById(Utente.class, offer.getIdUtente());
                %>


                <tbody>

                <tr>
                    <td><%= venditore.getUsername()%> </td>
                    <td><%= offer.getCondizione() %> </td>
                    <td><%= offer.getPrezzo() %> </td>
                    <td>
                        <form action="aggiungiOffertaInCarrello" method="get">
                            <input type="hidden" name="idOfferta" value="<%=offer.getIdOfferta()%>">
                            <input type="submit" value="Aggiungi al carrello">
                        </form>
                    </td>
                    <td>
                        <form action="ShowScambioServlet" method="get">
                            <input type="hidden" name="offerta" value="<%=offer.getIdOfferta()%>">
                            <input type="submit" value="Scambia">
                        </form>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>

        </div>
    </div>

    <%@include file="footer.jsp"%>
    </body>
</html>
