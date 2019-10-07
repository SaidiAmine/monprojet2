package projet.helpdesk.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.helpdesk.beans.FAQ;
import projet.helpdesk.dao.FAQDao;
import projet.helpdesk.form.FAQForm;

// SERVLET POUR TOUT UTILISATEUR - CONSULTATION FAQ
@WebServlet ( urlPatterns = { "/afficherfaq" })
public class AfficherFaq extends HttpServlet {

	@EJB
	private FAQDao faqDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ChargerFaq.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FAQForm faqform = new FAQForm(faqDao);
		List<FAQ> lfaq = faqform.chargerFAQ();
		
		request.setAttribute("lfaq", lfaq);
		this.getServletContext().getRequestDispatcher("/WEB-INF/ChargerFaq.jsp").forward(request, response);
		
	}	
}
