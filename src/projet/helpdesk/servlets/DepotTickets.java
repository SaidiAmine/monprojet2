package projet.helpdesk.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.helpdesk.beans.Ticket;
import projet.helpdesk.dao.TicketDao;

// SERVLET DEPOT TICKETS POUR TECHNICIENS - Tickets en attente de prise en charge
@WebServlet(urlPatterns= {"/depottickets"})
public class DepotTickets extends HttpServlet {
private String VUE = "/WEB-INF/DepotTickets.jsp";
	
	@EJB
	private TicketDao ticketDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String id_techniciens = request.getParameter("id_technicien");
		int id_technicien = Integer.parseInt(id_techniciens);
		List<Ticket> liste = ticketDao.trouverTicketsTech(id_technicien);
		request.setAttribute("lticket", liste);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
