package projet.helpdesk.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.helpdesk.beans.Ticket;
import projet.helpdesk.dao.TicketDao;
import org.json.JSONException;
import org.json.JSONObject;


@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/test4"})
public class test4 extends HttpServlet {

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

        List<Ticket> lticket = ticketDao.chargerTicketsParDate();
        
        PrintWriter out = response.getWriter();

        JSONObject json = new JSONObject();
            try{

            json.put("status", 200);
            json.put("msg", "OK");  

            JSONObject map = new JSONObject();
           /* String nom="Nom";
            String nom1="NAME";
            String nom2="NAME";*/
            String ladate;
            int id;
            long valeur;
            Date datetick = new Date();
            Calendar cal = Calendar.getInstance();
           // System.out.println();
            for (Ticket result : lticket) {
    		   // nom=Integer.toString(result.getId_ticket());
    		    id=result.getId_ticket();
    		    ///**************
    		    datetick= result.getDate_envoi();
    		    cal.setTime(datetick);
    		    int year = cal.get(Calendar.YEAR);
    		    int month = cal.get(Calendar.MONTH);
    		    int day = cal.get(Calendar.DAY_OF_MONTH);
    		    ladate=""+Integer.toString(year)+Integer.toString(month)+Integer.toString(day);
            	//valeur=Integer.parseInt(ladate);
    		    map.put(ladate,id);
    		}
            json.put("map", map);

        }catch(JSONException e){
            e.printStackTrace();
        }
        out.print(json.toString());
	}
}