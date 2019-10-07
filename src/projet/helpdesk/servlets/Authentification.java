package projet.helpdesk.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projet.helpdesk.beans.Utilisateur;
import projet.helpdesk.dao.UtilisateurDao;
import projet.helpdesk.form.ConnexionForm;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/connexion"})
public class Authentification extends HttpServlet
{
	private String VUE = "/WEB-INF/Auth.jsp";
	private String Employe="Employe", 
			Technicien="Technicien", 
			TechnicienR="Technicien-Responsable", 
			Admin="Admin";
	@EJB
	private UtilisateurDao     utilisateurDao;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ConnexionForm connexion = new ConnexionForm(utilisateurDao);
		HttpSession session = request.getSession();
		Utilisateur  emp = connexion.connecterUtilisateur(request);
		
		String msg;
		
		boolean validitee=connexion.getValid();
		if(!connexion.getErreurs().isEmpty() || connexion.getValid()==false)
		{
			request.setAttribute("validitee", validitee);
			msg="Mot de passe ou adresse incorrecte.";
			request.setAttribute("message", msg);
			session.setAttribute("masession", null);
			request.setAttribute("erreurs", connexion.getErreurs());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else {
			request.setAttribute("validitee", validitee);
			request.setAttribute("erreurs", connexion.getErreurs());
			msg="";
			request.setAttribute("message", msg);
			session.setAttribute("masession", emp);
			if (emp.getType().equals(Employe)){
					VUE="/AccueilEmploye.jsp";
			} else if (emp.getType().equals(Technicien)){
					VUE="/AccueilTechnicien.jsp";
			} else if (emp.getType().equals(TechnicienR)){
					VUE="/AccueilTechnicienR.jsp";
			} else if (emp.getType().equals(Admin)){
					VUE="/AccueilAdmin.jsp";
			}
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}	
	}
}