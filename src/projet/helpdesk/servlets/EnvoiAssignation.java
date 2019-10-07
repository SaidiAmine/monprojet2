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
import projet.helpdesk.beans.Utilisateur;
import projet.helpdesk.dao.TicketDao;
import projet.helpdesk.dao.UtilisateurDao;
import projet.helpdesk.form.CreationTicketForm;

@WebServlet ( urlPatterns = { "/envoiassignation" })
public class EnvoiAssignation extends HttpServlet {

	@EJB
	UtilisateurDao utilisateurDao;
	
	@EJB
	TicketDao ticketDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreationTicketForm ticketform = new CreationTicketForm(ticketDao);
		ticketform.majTicketEnTraitement(request);
		
		List<Utilisateur> lutilisateur = utilisateurDao.chargerTechniciens();
		List<Ticket> lticket = ticketDao.trouverTicketsAssignation();
		
		List<Object[]> lencharge = utilisateurDao.trouverTechsTicksAss();
		
		request.setAttribute("lencharge", lencharge);
		request.setAttribute("lticket", lticket);
		request.setAttribute("lutilisateur", lutilisateur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/AssignerTicket.jsp").forward(request, response);
	}
}