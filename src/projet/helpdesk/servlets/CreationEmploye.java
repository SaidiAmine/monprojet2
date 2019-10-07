package projet.helpdesk.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import projet.helpdesk.beans.Utilisateur;
import projet.helpdesk.dao.*;
import projet.helpdesk.form.*;

@WebServlet ( urlPatterns = { "/creeremploye" })
public class CreationEmploye extends HttpServlet {
	
	@EJB
	private UtilisateurDao     utilisateurDao;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	doPost(request, response);	
	}
	
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		InscriptionForm inscription = new InscriptionForm(utilisateurDao);
		Utilisateur employe = inscription.inscrireUtilisateur(request);
		String msg=null;
		boolean erreur;
		
		if (!inscription.getErreurs().isEmpty())
		{
			String msg2=inscription.getResultat();
			msg = "<span class=\"erreur\">Veuillez spécifier les champs obligatoires et; "+msg2+"</span> </br>";
			erreur = true;
			request.setAttribute("emp", employe);
			request.setAttribute("message", msg);
			request.setAttribute("erreur", erreur);
			request.setAttribute("erreurs", inscription.getErreurs());
			/// SERVLET GESTION COMPTES RECOPIEE
	        List<Utilisateur> lusers = utilisateurDao.chargerUsers();
			request.setAttribute("lusers", lusers);
			this.getServletContext().getRequestDispatcher("/WEB-INF/GestionComptes.jsp").forward(request, response);
			
		}
		else
		{
			erreur=false;
			request.setAttribute("emp", employe);
			request.setAttribute("erreur", erreur);
			/// SERVLET GESTION COMPTES RECOPIEE
	        List<Utilisateur> lusers = utilisateurDao.chargerUsers();
			request.setAttribute("lusers", lusers);
			this.getServletContext().getRequestDispatcher("/WEB-INF/afficherEmploye.jsp").forward(request, response);
		}
		}
}
