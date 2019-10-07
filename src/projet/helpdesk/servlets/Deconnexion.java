package projet.helpdesk.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns= {"/deconnexion"})
public class Deconnexion extends HttpServlet {
    public static final String VUE = "/WEB-INF/Auth.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	//this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    	doPost(request,response);
    }
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	if (session == null) {
    	    // Not created yet. Now do so yourself.
    		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    	} else {
    	    // Already created.
    		session.invalidate();
    		/* Redirection vers le Site du Zéro ! */
    		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    	}
    	
    	/* Récupération et destruction de la session en cours 
        HttpSession session = request.getSession();
        session.invalidate();

        /* Redirection vers le Site du Zéro ! 
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);*/
    }
}