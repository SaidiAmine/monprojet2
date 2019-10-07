package projet.helpdesk.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import projet.helpdesk.form.FAQForm;
import projet.helpdesk.beans.FAQ;
import projet.helpdesk.dao.FAQDao;

// SERVLET POUR TECHNICIEN / TECHNICIEN-RESPONSABLE Création Question/Réponse.
@WebServlet ( urlPatterns = { "/creerfaq" })
public class CreerFAQ extends HttpServlet {
	
	@EJB
	private FAQDao faqDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/CreerFaq.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FAQForm faqform = new FAQForm(faqDao);
		FAQ faq = faqform.creerQR(request);
		if(faqform.isValide()){
			
			List<FAQ> lfaq = faqform.chargerFAQ();
			
			request.setAttribute("lfaq", lfaq);
			this.getServletContext().getRequestDispatcher("/ChargerFaq.jsp").forward(request, response);
		}else{
			
			List<FAQ> lfaq = faqform.chargerFAQ();
			
			request.setAttribute("lfaq", lfaq);
			request.setAttribute("erreurs",faqform.getErreurs());
			this.getServletContext().getRequestDispatcher("/WEB-INF/ChargerFaq.jsp").forward(request, response);
		}
	}
}
