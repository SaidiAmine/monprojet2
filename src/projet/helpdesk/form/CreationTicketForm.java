package projet.helpdesk.form;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import projet.helpdesk.beans.Ticket;
import projet.helpdesk.dao.TicketDao;

public class CreationTicketForm {

	private String resultat;
	private Map<String, String>erreurs=new HashMap<String, String>();
	
	private TicketDao ticketDao;
	public CreationTicketForm(TicketDao ticketDao){
		this.ticketDao=ticketDao;
	}
	
	public void majTicketEnTraitement(HttpServletRequest request){
		int id_technicien = Integer.parseInt(request.getParameter("id_technicien"));
		int id_ticket = getId_ticket(request);
		if(getErreurs().isEmpty()){
			ticketDao.majTicketEnTraitement(id_ticket, id_technicien);
		}
	}
	
	public void majTicket(HttpServletRequest request)
	{
		int id = getId_ticket(request);
		if(id!=0){
			Timestamp date_fermeture = new Timestamp( System.currentTimeMillis() );
			ticketDao.majEtatTicket3(id,date_fermeture);
		}
	}
	
	public List<Ticket> recupererTickets(HttpServletRequest request)
	{
		List<Ticket> liste;
		int id = getId(request);
		if(id!=0){
			liste = ticketDao.trouverTickets(id);
		}else liste=null;
		return liste;
	}
	
	public Ticket recupererTicket(HttpServletRequest request)
	{
		Ticket ticket = new Ticket();
		int id_ticket = getId_ticket(request);
		if(id_ticket!=0){
			ticket = ticketDao.trouverTicket(id_ticket);
		}else ticket=null;
		return ticket;
	}
	
	public Ticket soumettreTicket(HttpServletRequest request){
		String sujet = request.getParameter("sujet");
		String description = request.getParameter("description");    
		int idemp = getId(request);
		int priorite = getPriorite(request);
		
		Ticket ticket = new Ticket();
		ticket.setId_employe(idemp);
		ticket.setEtat(1);
		try{
			validationChamp(sujet);
		} catch ( Exception e){
			setErreur("sujet",e.getMessage());
		}
		ticket.setSujet(sujet);
		
		try{
			validationChamp(description);
		} catch(Exception e){
			setErreur("description",e.getMessage());
		}
		ticket.setDescription(description);
		
		try{
			validationPriorite(priorite);
		} catch ( Exception e){
			setErreur("priorite",e.getMessage());
		}
		ticket.setPriorite(priorite);
		
		Timestamp date = new Timestamp( System.currentTimeMillis() );
        ticket.setDate_envoi(date);
		
		if(erreurs.isEmpty()){
			ticketDao.creer(ticket);
			resultat="Ticket crée avec succés.";
		} else {
			resultat="Echec de la création du ticket";
		}
		return ticket;
	}
	
	private void validationChamp(String sujet_description) throws Exception
	{
		if(sujet_description == null || sujet_description.length()<3){
			throw new Exception ("Veuillez saisir le sujet de l'incident");
		}
	}
	private void validationPriorite(int priorite) throws Exception
	{
		if(priorite>3 || priorite<1)
		{
			throw new Exception ("Veuillez saisir la priorité de votre incident");
			}
	}

	public String getResultat() {
		return resultat;
	}
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
	private int getId(HttpServletRequest request)
	{
		String id = request.getParameter("idemp");
		if (!id.isEmpty()){
		int idemp = Integer.parseInt(id);
		return idemp;
		}
		else{
			setErreur("idemp","id employe introuvable");
			return 0;
		}
	}
	
	private int getId_ticket(HttpServletRequest request)
	{
		String id_tickets = request.getParameter("id_ticket");
		if (!id_tickets.isEmpty()){
		int id_ticket = Integer.parseInt(id_tickets);
		return id_ticket;
		}
		else{
			setErreur("id_ticket","id ticket introuvable");
			return 0;
		}
	}
	
	private int getPriorite(HttpServletRequest request)
	{
		String pr = request.getParameter("priorite");
		if(!pr.isEmpty())
		{
			int priorite = Integer.parseInt(pr);
			return priorite;
		}
		else return 0;
	}
	
}