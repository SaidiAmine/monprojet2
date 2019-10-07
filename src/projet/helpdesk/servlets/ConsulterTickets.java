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
import projet.helpdesk.form.CreationTicketForm;

/***
 *  SERVLET POUR ACTEUR EMPLOYE - Consultation de la totalité des tickets déposés.
 * */
@WebServlet(urlPatterns= {"/consultertickets"})
public class ConsulterTickets extends HttpServlet {
	
	private String VUE = "/WEB-INF/ListeTickets.jsp";
	
	@EJB
	private TicketDao ticketDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreationTicketForm ticketform = new CreationTicketForm(ticketDao);
		List<Ticket> lticket = ticketform.recupererTickets(request);
		
		boolean resultat;
		if(lticket.isEmpty())
		{
			//resultat="Vous n'avez soumit aucun ticket";
			resultat = false;
			request.setAttribute("resultat", resultat);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ListeTicketsVide.jsp").forward(request, response);
		}else{
			//String VUE = "/ListeTickets.jsp";
			resultat=true;
			request.setAttribute("resultat", resultat);
			request.setAttribute("lticket", lticket);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

}
