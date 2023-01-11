package registrazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import registrazione.Utente;
import storage.FacadeDAO;


import java.io.IOException;
/**
 * La classe permette di modificare un utente tramite id della utente,attraverso
 * il facadeDAO.
 * @author Francesco Di Domenico
 */
@WebServlet("/ModificaUtenteServlet")
public class ModificaUtenteServlet extends HttpServlet {
    /**
     * Il metodo permette di gestire la richiesta del client,dove il server
     * prende il parametro idutente e attraverso la session conosce il tipo
     * di utente loggato che sta svolgendo l azione di modificare l'utente nel DB.
     * Salvando i parametri del form di modifica utente utilizza il facadeDAO per modificare l utente nel database.
     * Successivamente:
     * se l utente è l admin rendirizza tramite parametro resp alla servlet PannelloAdmin dopo aver modificato l utente.
     * se l utente non è l admin reindirizza tramite parametro resp alla jsp index dopo aver modificato l utente.
     * Il dispatcher reindirizza poi ad un altra pagina.
     *
     * @param req  : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        Utente utenteLoggato=(Utente) session.getAttribute("Utente");
       String newNome=req.getParameter("cambiaNome");
       String newCognome=req.getParameter("cambiaCognome");
       String newUsername=req.getParameter("cambiaUsername");
       String newPass=req.getParameter("cambiaPass");
       String newEmail=req.getParameter("cambiaEmail");
       int idUtente=Integer.parseInt(req.getParameter("idUtente"));
       Utente utenteModificato=new Utente(idUtente,newUsername,newPass,newNome,newCognome,newEmail);
        FacadeDAO facadeDAO=new FacadeDAO();
        facadeDAO.doUpdate(Utente.class,idUtente,utenteModificato);

        if(utenteLoggato.getIdUtente()==1){
       resp.sendRedirect("PannelloAdmin");}
        else {resp.sendRedirect("index.jsp");}
    }
}
