package projet.helpdesk.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projet.helpdesk.beans.*;
import projet.helpdesk.dao.UtilisateurDao;

@WebServlet ( urlPatterns = { "/supprimercompte" })
public class SuppressionUtilisateur extends HttpServlet {
	@EJB
	UtilisateurDao utilisateurDao;
   
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
       doPost(request, response);
    }
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String id_employes = request.getParameter("id_employe");
        int id_employe = Integer.parseInt(id_employes);
        utilisateurDao.suppCompte(id_employe);
        /// SERVLET GESTION COMPTES RECOPIEE
        List<Utilisateur> lusers = utilisateurDao.chargerUsers();
		request.setAttribute("lusers", lusers);
		this.getServletContext().getRequestDispatcher("/WEB-INF/GestionComptes.jsp").forward(request, response);
    }
}