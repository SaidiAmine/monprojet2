package projet.helpdesk.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class listerClients extends HttpServlet {
    public static final String ATT_UTILISATEUR = "utilisateur";
    //public static final String ATT_FORM   = "form";

    public static final String VUE        = "/listeClients.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, affichage de la liste des clients */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}