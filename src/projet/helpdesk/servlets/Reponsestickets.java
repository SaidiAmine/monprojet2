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
import projet.helpdesk.dao.ReponseTicketDao;
import projet.helpdesk.dao.TicketDao;
import projet.helpdesk.form.CreationReponseForm;
import projet.helpdesk.form.CreationTicketForm;

//SERVLET REPONSES TICKETS POUR TOUT ACTEUR
@WebServlet(urlPatterns={"/reponsesticket"})
public class Reponsestickets extends HttpServlet {
	
	@EJB
	private TicketDao ticketDao;
	
	@EJB
	private ReponseTicketDao reponseDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreationTicketForm ticketform = new CreationTicketForm(ticketDao);
		Ticket ticket = ticketform.recupererTicket(request);
		
		CreationReponseForm reponse = new CreationReponseForm(reponseDao);
		List<Object[]> listereponse = reponse.recupererJointure(request);
		
		if(ticket==null)
		{
		response.sendRedirect("/monprojet2/connexion");
		} else {
			request.setAttribute("lreponse", listereponse);
			request.setAttribute("ticket", ticket);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ReponsesTickets.jsp").forward(request, response);
		}
	}
}