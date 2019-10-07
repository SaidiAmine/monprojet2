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
import projet.helpdesk.dao.UtilisateurDao;

@WebServlet ( urlPatterns = { "/gestioncomptes" })
public class GestionComptes extends HttpServlet {
	@EJB
	UtilisateurDao utilisateurDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/GestionComptes.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utilisateur> lusers = utilisateurDao.chargerUsers();
		request.setAttribute("lusers", lusers);
		this.getServletContext().getRequestDispatcher("/WEB-INF/GestionComptes.jsp").forward(request, response);
	}
}
