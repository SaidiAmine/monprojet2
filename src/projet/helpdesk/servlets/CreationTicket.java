package projet.helpdesk.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projet.helpdesk.beans.Ticket;
import projet.helpdesk.dao.*;
import projet.helpdesk.form.CreationTicketForm;

@SuppressWarnings("serial")
@WebServlet ( urlPatterns = { "/creerticket" })
public class CreationTicket extends HttpServlet {
	
	@EJB
	private TicketDao ticketDao;
	public static final String VUE="/WEB-INF/creerTicket.jsp";
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreationTicketForm creationticket = new CreationTicketForm(ticketDao);
		Ticket ticket = creationticket.soumettreTicket(request);
		boolean valide;
		if(!creationticket.getErreurs().isEmpty()){
			valide=false;
			request.setAttribute("ticket", ticket);
			request.setAttribute("valide", valide);
			request.setAttribute("erreurs", creationticket.getErreurs());
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}else
		{
			List<Ticket> lticket = creationticket.recupererTickets(request);
			HttpSession session = request.getSession();
			valide=true;
			session.setAttribute("ticket", ticket);
			request.setAttribute("lticket", lticket);
			request.setAttribute("valide", valide);
			request.setAttribute("erreurs", creationticket.getErreurs());
			this.getServletContext().getRequestDispatcher("/WEB-INF/ListeTickets.jsp").forward(request, response);
		}
	}
}
