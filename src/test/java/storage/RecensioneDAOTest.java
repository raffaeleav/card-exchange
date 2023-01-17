package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recensione.Recensione;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class RecensioneDAOTest {

    @Test
    void doSaveTestNull() {
        Recensione r=null;
        RecensioneDAO dao=new RecensioneDAO();
        List<Recensione> listRecensioni=dao.doRetrieveAll();
        int size=listRecensioni.size();
        dao.doSave(r);
        List<Recensione> listRecensioni2=dao.doRetrieveAll();
        Assertions.assertEquals(size+1,listRecensioni2.size());
    }

    @Test
    void doSaveTestNotNull() {
        Recensione r=new Recensione(4,"prova di una recensione",1,1);
        RecensioneDAO dao=new RecensioneDAO();
        List<Recensione> listRecensioni=dao.doRetrieveAll();
        int size=listRecensioni.size();
        dao.doSave(r);
        List<Recensione> listRecensioni2=dao.doRetrieveAll();
        Assertions.assertEquals(size+1,listRecensioni2.size());
    }
}