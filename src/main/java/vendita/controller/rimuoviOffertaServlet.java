package vendita.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import acquisto.Offerta;
import storage.service.FacadeDAO;

@WebServlet(name = "rimuovi-offerta", value = "/rimuovi-offerta")
public class rimuoviOffertaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idOffertas = request.getParameter("idOfferta");
        int idOfferta = Integer.parseInt(idOffertas);

        FacadeDAO facadeDAO = new FacadeDAO();
        facadeDAO.doDelete(Offerta.class, idOfferta);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/myoffers.jsp");
        requestDispatcher.forward(request, response);
    }
}
