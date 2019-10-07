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
@WebServlet ( urlPatterns = { "/supprimerqr" })
public class SupprimerFaq extends HttpServlet {

	@EJB
	private FAQDao faqDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);//this.getServletContext().getRequestDispatcher("/WEB-INF/ChargerFaq.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_qrs = request.getParameter("id_qr");
		if(id_qrs!=null){
			int id_qr=Integer.parseInt(id_qrs);
			faqDao.supprimer(id_qr);
			
			FAQForm faqform = new FAQForm(faqDao);
			List<FAQ> lfaq = faqform.chargerFAQ();
			
			request.setAttribute("lfaq", lfaq);
			
			//response.sendRedirect("/monprojet2/afficherfaq");
			this.getServletContext().getRequestDispatcher("/WEB-INF/ChargerFaq.jsp").forward(request, response);
		}
		
	}
}
