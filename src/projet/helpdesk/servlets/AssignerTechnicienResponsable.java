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

@WebServlet(urlPatterns={"/assignertechr"})
public class AssignerTechnicienResponsable extends HttpServlet {
	@EJB
	UtilisateurDao utilisateurDao;
	@EJB
	TicketDao ticketDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/AssignerTicket.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_techniciens = request.getParameter("id_technicien");
		int id_technicien = Integer.parseInt(id_techniciens);
		utilisateurDao.majTechEnTechr(id_technicien);
		////// SERVLET DAFFICHAGE ASSIGNATION RECOPIEE //////
		List<Utilisateur> lutilisateur = utilisateurDao.chargerTechniciens();	// Liste Techniciens + techr
		List<Utilisateur> ltechniciens=utilisateurDao.trouverToutTechnicien(); // Liste techniciens
		List<Ticket> lticket = ticketDao.trouverTicketsAssignation();			// Liste ticket pris en charge
		
		List<Object[]> lencharge = utilisateurDao.trouverTechsTicksAss();		// Liste techniciens avec tickets en charge
		
		request.setAttribute("ltechniciens", ltechniciens);
		request.setAttribute("lencharge", lencharge);
		request.setAttribute("lticket", lticket);
		request.setAttribute("lutilisateur", lutilisateur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/AssignerTicket.jsp").forward(request, response);
		
		//this.getServletContext().getRequestDispatcher("/WEB-INF/AssignerTicket.jsp").forward(request, response);
	}
}
