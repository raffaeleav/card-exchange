package scambio.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import scambio.Scambio;
import storage.OffertaDAO;
import storage.ScambioDAO;

import java.io.IOException;

/**
 * Questa servlet viene acceduta quando si esegue una richiesta di scambio, la quale permette il salvataggio nel DB
 */
@WebServlet(name = "ScambioServlet", value = "/ScambioServlet")
public class ScambioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idUtenteMittente = Integer.parseInt(request.getParameter("idUtenteMittente"));
        int idOffertaMittente = Integer.parseInt(request.getParameter("idOffertaMittente"));
        int idOffertDestinatario = Integer.parseInt(request.getParameter("idOffertaDestinatario"));
        double conguaglio = Double.parseDouble(request.getParameter("conguaglio"));


        OffertaDAO offertaDAO = new OffertaDAO();
        // Da implementare in offerta
        int idUtenteDestinatario = 0;

        Scambio s = new Scambio(idUtenteMittente,idUtenteDestinatario,idOffertaMittente,idOffertDestinatario,conguaglio);

        ScambioDAO scambioDAO  = new ScambioDAO();

        scambioDAO.doSave(s);
        //Notifica?

        request.getRequestDispatcher("index.jsp").forward(
                request, response);


    }
}
