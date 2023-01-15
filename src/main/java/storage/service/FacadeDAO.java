package storage.service;

import acquisto.Carta;
import acquisto.Offerta;
import acquisto.Ordine;
import creazioneDiscussione.Discussione;
import creazioneDiscussione.Messaggio;
import recensione.Recensione;
import registrazione.Utente;
import scambio.Scambio;

import java.sql.SQLException;
import java.util.List;

/**
 * La classe realizza il design pattern Facade,
 * garantendo l' accesso alle classi del sottosistema
 * storage con quest' unica interfaccia
 * @author Raffaele Aviello
 * */

public class FacadeDAO {

    /**
     * Il metodo permette di ottenere tutte le istanze degli oggetti
     * memorizzate nel database
     * @param entityClass la classe dell' oggetto di cui si vuole
     *                    recuperare tutte le istanze
     * @return una lista qualsiasi di oggetti che estendono la
     *                    classe Object
     * */
    public List<?> doRetrieveAll(Class<?> entityClass){
        switch(entityClass.getName()){
            case "acquisto.Carta":
                return new CartaDAO().doRetrieveAll();

            case "creazioneDiscussione.Discussione":
                return new DiscussioneDAO().doRetrieveAll();

            case "creazioneDiscussione.Messaggio":
                return new MessaggioDAO().doRetrieveAll();

            case "acquisto.Offerta":
                return new OffertaDAO().doRetrieveAll();

            case "acquisto.Ordine":
                return new OrdineDAO().doRetrieveAll();

            case "recensione.Recensione":
                return new RecensioneDAO().doRetrieveAll();

            case "scambio.Scambio":
                return new ScambioDAO().doRetrieveAll();

            case "registrazione.Utente":
                return new UtenteDAO().doRetrieveAll();

            default:
                return null;
        }
    }

    /**
     * Il metodo permette di ottenere un oggetto memorizzato nel database
     * grazie al suo id
     * @param entityClass la classe dell' oggetto che si vuole recuperare
     * @param entityId id dell' oggetto che si vuole recuperare
     * @return un oggetto di tipo Object che rappresenta l' istanza recuperata
     * */
    public Object doRetrieveById(Class<?> entityClass, int entityId){
        switch(entityClass.getName()){
            case "acquisto.Carta":
                return new CartaDAO().doRetrieveById(entityId);

            case "creazioneDiscussione.Discussione":
                return new DiscussioneDAO().doRetrieveById(entityId);

            case "creazioneDiscussione.Messaggio":
                return new MessaggioDAO().doRetrieveById(entityId);

            case "acquisto.Offerta":
                return new OffertaDAO().doRetrieveById(entityId);

            case "acquisto.Ordine":
                return new OrdineDAO().doRetrieveById(entityId);

            case "recensione.Recensione":
                return new RecensioneDAO().doRetrieveById(entityId);

            case "scambio.Scambio":
                return new ScambioDAO().doRetrieveById(entityId);

            case "registrazione.Utente":
                return new UtenteDAO().doRetrieveById(entityId);

            default:
                return null;
        }
    }

    /**
     * Il metodo permette di memorizzare un oggetto nel database
     * grazie alla sua istanza
     * @param entityClass la classe dell' oggetto che si vuole memorizzare
     * @param entity istanza oggetto che si vuole memorizzare
     * */
    public void doSave(Class<?> entityClass, Object entity){
        switch(entityClass.getName()){
            case "acquisto.Carta":
                new CartaDAO().doSave((Carta) entity);
                break;

            case "creazioneDiscussione.Discussione":
                new DiscussioneDAO().doSave((Discussione) entity);
                break;

            case "creazioneDiscussione.Messaggio":
                new MessaggioDAO().doSave((Messaggio) entity);
                break;

            case "acquisto.Offerta":
                new OffertaDAO().doSave((Offerta) entity);
                break;

            case "acquisto.Ordine":
                new OrdineDAO().doSave((Ordine) entity);
                break;

            case "recensione.Recensione":
                new RecensioneDAO().doSave((Recensione) entity);
                break;

            case "scambio.Scambio":
                new ScambioDAO().doSave((Scambio) entity);
                break;

            case "registrazione.Utente":
                new UtenteDAO().doSave((Utente) entity);
                break;

            default:
                break;
        }
    }

    /**
     * Il metodo permette di eliminare un oggetto memorizzato nel database
     * grazie al suo id
     * @param entityClass la classe dell' oggetto che si vuole recuperare
     * @param entityId id dell' oggetto che si vuole recuperare
     * */
    public void doDelete(Class<?> entityClass, int entityId){
        switch(entityClass.getName()){
            case "acquisto.Carta":
                new CartaDAO().doDelete(entityId);
                break;

            case "creazioneDiscussione.Discussione":
                new DiscussioneDAO().doDelete(entityId);
                break;

            case "creazioneDiscussione.Messaggio":
                new MessaggioDAO().doDelete(entityId);
                break;

            case "acquisto.Offerta":
                new OffertaDAO().doDelete(entityId);
                break;

            case "acquisto.Ordine":
                new OrdineDAO().doDelete(entityId);
                break;

            case "recensione.Recensione":
                new RecensioneDAO().doDelete(entityId);
                break;

            case "scambio.Scambio":
                new ScambioDAO().doDelete(entityId);
                break;

            case "registrazione.Utente":
                new UtenteDAO().doDelete(entityId);
                break;

            default:
                break;
        }
    }

    /**
     * Il metodo permette di modificare un oggetto memorizzato nel database
     * grazie al suo id
     * @param entityClass la classe dell' oggetto che si vuole modificare
     * @param entityId id dell' oggetto che si vuole modificare
     * @param entity istanza dell' oggetto che contiene i nuovi campi da modificare
     * */
    public void doUpdate(Class<?> entityClass, int entityId, Object entity){
        switch(entityClass.getName()){
            case "acquisto.Carta":
                new CartaDAO().doUpdate(entityId, (Carta) entity);
                break;

            case "creazioneDiscussione.Messaggio":
                new MessaggioDAO().doUpdate(entityId, (Messaggio) entity);
                break;

            case "acquisto.Offerta":
                new OffertaDAO().doUpdate(entityId, (Offerta) entity);
                break;

            case "acquisto.Ordine":
                new OrdineDAO().doUpdate(entityId, (Ordine) entity);
                break;

            case "recensione.Recensione":
                new RecensioneDAO().doUpdate(entityId, (Recensione) entity);
                break;
            case "registrazione.Utente":
                new UtenteDAO().doUpdate(entityId, (Utente) entity);
                break;

            case "scambio.Scambio":
                new ScambioDAO().doUpdate(entityId, (Scambio) entity);
                break;

            default:
                break;
        }
    }

    public boolean getUtenteByEmail(Class<?> entityClass, String email){
        if(entityClass.getName().equals("registrazione.Utente"))
            return new UtenteDAO().getUtenteByEmail(email);

        return false;
    }

    public boolean getUtenteByUsername(Class<?> entityClass,String username){
        if(entityClass.getName().equals("registrazione.Utente"))
            return new UtenteDAO().getUtenteByUsername(username);

        return false;
    }

    public Utente getUtenteByEmailPassword(Class<?> entityClass, String email,String password){
        if(entityClass.getName().equals("registrazione.Utente")){
            return new UtenteDAO().getUtenteByEmailPassword(email, password);
        }
        return null;
    }

    /**
     * Il metodo restituisce una lista di recensioni filtrate per l'ID utente.
     * @param entityClass classe dell oggetto che si vuole cercare.
     * @param idUtente id dell utente che ha effettuato la recensione.
     * @return una lista di recensioni per l'id utente.
     * @autor Francesco Di Domenico
     */
    public List<Recensione> getRecensioneByIdUtente(Class<?> entityClass,int idUtente){
        if(entityClass.getName().equals("recensione.Recensione")){
            return new RecensioneDAO().getRecensioneByIdUtente(idUtente);
        }
    return null;
    }

    /**
     * Il metodo restituisce una lista di ordini filtrate per l'ID utente.
     * @param entityClass classe dell oggetto che si vuole cercare.
     * @param idUtente id dell utente che ha effettuato la recensione.
     * @return una lista di ordini per l'id utente.
     * @autor Francesco Di Domenico
     */
    public List<Ordine> getOrdiniByIdUtente(Class<Ordine> entityClass, int idUtente) {
        if(entityClass.getName().equals("acquisto.Ordine")){
            return new OrdineDAO().getOrdiniByIdUtente(idUtente);
        }
        return null;
    }

    public List<?> getAllScambiByIdOfferta(Class<?> entityClass,int idOfferta){
        if(entityClass.getName().equals("scambio.Scambio")){
            return new ScambioDAO().getAllScambiByIdOfferta(idOfferta);
        }
        return null;

    }

    public List<?> getAllScambiByIdMittente(Class<?> entityClass,int idUtenteMittente){
        if(entityClass.getName().equals("scambio.Scambio")){
            return new ScambioDAO().getAllScambiByIdMittente(idUtenteMittente);
        }
        return null;

    }

    public List<?> getAllScambiByIdDestinatario(Class<?> entityClass,int idUtenteDestinatario){
        if(entityClass.getName().equals("scambio.Scambio")){
            return new ScambioDAO().getAllScambiByIdDestinatario(idUtenteDestinatario);
        }
        return null;
    }


    public Object doRetrieveByIdUtente(Class<?> entityClass,int idUtente){
        if(entityClass.getName().equals("acquisto.Carrello")){
            return new CarrelloDAO().getCarrelloByIdUtente(idUtente);
        }
        return null;
    }

    public void addOffertaInCarrello(Class<?> entityClass, Object entity, int idCarrello){
        if(entityClass.getName().equals("acquisto.Carrello")){
            new CarrelloDAO().aggiungiOfferta((Offerta) entity, idCarrello);
        }
    }

    public void removeOffertaFromCarrello(Class<?> entityClass, Object entity, int idCarrello){
        if(entityClass.getName().equals("acquisto.Carrello")){
            new CarrelloDAO().rimuoviOfferta((Offerta) entity, idCarrello);
        }
    }
    public double calculateTotaleInCarrello(Class<?> entityClass,int idCarrello) {
        double totale = 0.00;
        if(entityClass.getName().equals("acquisto.Carrello")){
            return totale = new CarrelloDAO().calcolaTotale(idCarrello);
        }
        return totale;
    }

    public void svuotaCarrello(Class<?> entityClass, Object entity, int idCarrello) {
        if(entityClass.getName().equals("acquisto.Carrello")){
            new CarrelloDAO().svuotaCarrello(idCarrello);

        }
    }

    public List<?> doRetrieveAllByIdUtente(Class<?> entityClass, int idUtente) throws SQLException {
        switch (entityClass.getName()) {
            case "acquisto.Offerta":
                return new OffertaDAO().getOfferteByIdUtente(idUtente);

            case "acquisto.Ordine":
                return new OrdineDAO().getOrdiniByIdUtente(idUtente);

            default:
                return null;
        }
    }

    public List<?> doRetrieveAllByIdOrdine(Class<?> entityClass, int idOrdine) throws SQLException {
        if(entityClass.getName().equals("acquisto.Offerta")){
            return new OffertaDAO().getOfferteByIdOrdine(idOrdine);
        }

        return null;
    }

    public List<?> doRetrieveAllByIdCarta(Class<?> entityClass, int idCarta) {
        if (entityClass.getName().equals("acquisto.Offerta")) {
            return new OffertaDAO().getOfferteByIdCarta(idCarta);
        }

        return null;
    }

    /**
     * Il metodo permette di recuperare tutti i messaggi che sono
     *                      appartengono ad una discussione
     * @param topicId id della discussione di cui si vogliono recuperare
     *                      messaggi
     * @return un oggetto List contenente i messaggi appartenenti alla discussione
     *                      con idDiscussione = topicID
     * */
    public List<Messaggio> doRetrieveMessageListByTopicId(Class<?> entityClass, int topicId){
        if(entityClass.getName().equals("creazioneDiscussione.Discussione")) {
            DiscussioneDAO topicDAO = new DiscussioneDAO();
            List<Messaggio> messages = topicDAO.doRetrieveMessageListByTopicId(topicId);

            return messages;
        }

        else return null;
    }
}