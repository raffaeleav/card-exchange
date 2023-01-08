package storage;

import acquisto.Carta;
import acquisto.Offerta;
import acquisto.Ordine;
import creazioneDiscussione.Discussione;
import creazioneDiscussione.Messaggio;
import recensione.Recensione;
import registrazione.Utente;
import scambio.Scambio;

import java.util.List;

public class FacadeDAO {

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
                return new UtenteDAO.doRetrieveAll();

            default:
                return null;
        }
    }

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
                return new UtenteDAO.doRetrieveById(entityId);

            default:
                return null;
        }
    }

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
                new UtenteDAO.doSave((Utente) entity);
                break;

            default:
                break;
        }
    }

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
                new UtenteDAO.doDelete(entityId);
                break;

            default:
                break;
        }
    }

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

            case "scambio.Scambio":
                new ScambioDAO().doUpdate(entityId, (Scambio) entity);
                break;

            default:
                break;
        }
    }


}