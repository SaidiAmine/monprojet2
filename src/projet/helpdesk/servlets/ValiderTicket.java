package projet.helpdesk.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.helpdesk.dao.ReponseTicketDao;
import projet.helpdesk.dao.TicketDao;
import projet.helpdesk.form.CreationReponseForm;
import projet.helpdesk.form.CreationTicketForm;
//SERVLET POUR EMPLOYE - VALIDATION D'UN TICKET
@WebServlet(urlPatterns={"/validerticket"})
public class ValiderTicket extends HttpServlet {
	
	@EJB
	private TicketDao ticketDao;
	
	@EJB
	private ReponseTicketDao reponseDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id_ticket"));
		CreationTicketForm ticketform = new CreationTicketForm(ticketDao);
		ticketform.majTicket(request);
		CreationReponseForm reponseform = new CreationReponseForm(reponseDao);
		reponseform.majReponse(request);
		
		response.sendRedirect("/monprojet2/reponsesticket?id_ticket="+id);
		//this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}

