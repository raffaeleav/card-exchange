package acquisto;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import acquisto.Carrello;
import acquisto.Offerta;
import storage.CarrelloDao;
import storage.OffertaDao;


public class AggiungiOffertaCarrelloServlet extends HttpServlet {
