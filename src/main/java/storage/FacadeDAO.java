package storage;

import acquisto.Carta;
import creazioneDiscussione.Discussione;
import creazioneDiscussione.Messaggio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FacadeDAO {

    public static void main(String[] args) {
        FacadeDAO dao = new FacadeDAO();
        List<Carta> discussiones = (List<Carta>) dao.doRetrieveAll(Carta.class);

        System.out.println(discussiones.get(0).getNome());
    }

    public List<?> doRetrieveAll(Class<?> classe){
        switch(classe.getName()){
            case "acquisto.Carta":
                return new CartaDAO().doRetrieveAll();

            default:
                return null;
        }
    }

    public Discussione doRetrieveById(Class<Discussione> discussioneClass, int id){
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();

        return discussioneDAO.doRetrieveById(id);
    }

}
