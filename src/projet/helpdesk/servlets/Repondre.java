package projet.helpdesk.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import projet.helpdesk.beans.ReponseTicket;
import projet.helpdesk.beans.Ticket;
import projet.helpdesk.dao.ReponseTicketDao;
import projet.helpdesk.dao.TicketDao;
import projet.helpdesk.form.CreationReponseForm;
import projet.helpdesk.form.CreationTicketForm;

@WebServlet(urlPatterns={"/creerreponse"})
public class Repondre extends HttpServlet {
	
	@EJB
	private ReponseTicketDao reponseDao;
	
	@EJB
	private TicketDao ticketDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreationReponseForm reponseform = new CreationReponseForm(reponseDao, ticketDao);
		ReponseTicket reponse = reponseform.soumettreReponse(request);
		
		CreationTicketForm ticketform = new CreationTicketForm(ticketDao);
		Ticket ticket = ticketform.recupererTicket(request);
		
		//CreationReponseForm reponsea = new CreationReponseForm(reponseDao);
		List<Object[]> listereponse = reponseform.recupererJointure(request);
		
		
		if(!reponseform.getErreurs().isEmpty() || reponse==null)
		{
			request.setAttribute("lreponse", listereponse);
			request.setAttribute("ticket", ticket);
			request.setAttribute("erreurs",reponseform.getErreurs());
			this.getServletContext().getRequestDispatcher("/WEB-INF/ReponsesTickets.jsp").forward(request, response);//this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}else
		{
			String resultat=reponseform.getResultat();
			request.setAttribute("resultat", resultat);
			request.setAttribute("lreponse", listereponse);
			request.setAttribute("ticket", ticket);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ReponsesTickets.jsp").forward(request, response);
		}
	}
}