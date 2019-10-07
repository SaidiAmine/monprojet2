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

import projet.helpdesk.dao.TicketDao;

import org.json.JSONException;
import org.json.JSONObject;




@WebServlet(urlPatterns={"/test"})
public class test extends HttpServlet {
	
	@EJB
	TicketDao ticketDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("application/json");
        response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8");

        List<Object[]> lticket = ticketDao.chargerTicketsParEtat();
        
        PrintWriter out = response.getWriter();

        JSONObject json = new JSONObject();
        try{

            json.put("status", 200);
            json.put("msg", "OK");  

            JSONObject map = new JSONObject();
            String nom="NAME";
            long valeur;
            for (Object[] result : lticket) {
    		    
    		    if((int)result[0]==1)
    		    	nom="En attente de prise en charge";
    		    else if((int)result[0]==2)
    		    	nom="En attente de validation";
    		    else if((int)result[0]==3)
    		    	nom="Cloturé";
    		    else if((int)result[0]==4)
    		    	nom="En cours de traitement";
    		    valeur = (long) result[1];
    		    map.put(nom,valeur);
    		}
            json.put("map", map);

        }catch(JSONException e){
            e.printStackTrace();
        }
        out.print(json.toString());
	}
}