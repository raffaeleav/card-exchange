package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

class DiscussioneDAOTest {

    @Test
    void doDeleteByIdMinusThan0() {
        DiscussioneDAO topicDAO = new DiscussioneDAO();

        int sizeBefore = topicDAO.doRetrieveAll().size();

        topicDAO.doDelete(-1);

        int sizeAfter = topicDAO.doRetrieveAll().size();

        Assertions.assertEquals(sizeBefore - 1, sizeAfter, "Test fallito.");
    }

    @Test
    void doDeleteByIdEqualTo0() {
        DiscussioneDAO topicDAO = new DiscussioneDAO();

        int sizeBefore = topicDAO.doRetrieveAll().size();

        topicDAO.doDelete(0);

        int sizeAfter = topicDAO.doRetrieveAll().size();

        Assertions.assertEquals(sizeBefore - 1, sizeAfter, "Test fallito.");
    }

    @Test
    void doDeleteByIdIncludedInSize() {
        DiscussioneDAO topicDAO = new DiscussioneDAO();

        int sizeBefore = topicDAO.doRetrieveAll().size();

        Random rand = new Random();
        int id = rand.nextInt(sizeBefore) + 1;

        topicDAO.doDelete(id);

        int sizeAfter = topicDAO.doRetrieveAll().size();

        Assertions.assertEquals(sizeBefore - 1, sizeAfter, "Test fallito.");
    }

    @Test
    void doDeleteByIdGreaterThanSize() {
        DiscussioneDAO topicDAO = new DiscussioneDAO();

        int sizeBefore = topicDAO.doRetrieveAll().size();

        topicDAO.doDelete(sizeBefore + 1);

        int sizeAfter = topicDAO.doRetrieveAll().size();

        Assertions.assertEquals(sizeBefore - 1, sizeAfter, "Test fallito.");
    }

}