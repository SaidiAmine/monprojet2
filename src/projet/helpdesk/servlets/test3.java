package projet.helpdesk.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import projet.helpdesk.dao.UtilisateurDao;
import org.json.JSONException;
import org.json.JSONObject;


@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/test3"})
public class test3 extends HttpServlet {

	@EJB
	UtilisateurDao utilisateurDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("application/json");
        response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8");

        List<Object[]> lusers = utilisateurDao.chargerUsersParTickets();
        
        PrintWriter out = response.getWriter();

        JSONObject json = new JSONObject();
            try{

            json.put("status", 200);
            json.put("msg", "OK");  

            JSONObject map = new JSONObject();
            String nom="Nom";
            String nom1="NAME";
            String nom2="NAME";
            long valeur;
            for (Object[] result : lusers) {
    		    nom1=(String)result[0];
    		    nom2=(String)result[1];
    		    nom=nom1+" "+nom2;
    		    valeur = (long) result[2];
    		    map.put(nom,valeur);
    		}
            json.put("map", map);

        }catch(JSONException e){
            e.printStackTrace();
        }
        out.print(json.toString());
	}
}