package projet.helpdesk.form;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import projet.helpdesk.beans.ReponseTicket;
import projet.helpdesk.dao.ReponseTicketDao;
import projet.helpdesk.dao.TicketDao;

public class CreationReponseForm {

	private String resultat;
	private Map<String, String>erreurs=new HashMap<String, String>();
	
	private ReponseTicketDao reponseDao;
	private TicketDao ticketDao;
	
	public CreationReponseForm(ReponseTicketDao reponseDao){
		this.reponseDao=reponseDao;
	}
	
	public CreationReponseForm(ReponseTicketDao reponseDao, TicketDao ticketDao){
		this.reponseDao=reponseDao;
		this.ticketDao=ticketDao;
	}
	
	public void majReponse(HttpServletRequest request)
	{
		int id = getId_reponse(request);
		if(id!=0){
			reponseDao.majValidReponse(id);
		}
	}
	
	public List<Object[]> recupererJointure(HttpServletRequest request)
	{
		List<Object[]> ljointure;
		int id = getId_ticket(request);
		if(id!=0){
			ljointure = reponseDao.trouverJointure(id);
		}else ljointure=null;
		return ljointure;
	}
	
	
	public List<ReponseTicket> recupererReponses(HttpServletRequest request)
	{
		List<ReponseTicket> lreponse;
		int id = getId_ticket(request);
		if(id!=0){
			lreponse = reponseDao.trouverReponses(id);
		}else lreponse=null;
		return lreponse;
	}
	
	public ReponseTicket soumettreReponse(HttpServletRequest request){
		String texte = request.getParameter("texte");    
		int id_employe = getId(request);
		int id_ticket = getId_ticket(request);
		String type = request.getParameter("type");
			
		ReponseTicket reponse = new ReponseTicket();
	
		try{
			validationChamp(texte);
		} catch ( Exception e){
			setErreur("texte",e.getMessage());
		}
		reponse.setTexte(texte);
		reponse.setId_employe(id_employe);
		reponse.setId_ticket(id_ticket);
		reponse.setType(type);
		
		Timestamp date = new Timestamp( System.currentTimeMillis() );
		reponse.setDate_post(date);
		
		if(erreurs.isEmpty()){
			reponseDao.creer(reponse);
			String tech = "Technicien", techr="Technicien-Responsable";
			if(getType(request).equals(tech) || getType(request).equals(techr))
			{
				ticketDao.majEtatTicket2(id_ticket);
			}
			resultat="Ticket crée avec succés.";
		} else {
			resultat="Echec de la création du ticket";
		}
		return reponse;
	}
	
	private void validationChamp(String texte) throws Exception
	{
		if(texte == null || texte.length()<3){
			throw new Exception ("Veuillez saisir le texte");
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
		String id = request.getParameter("id_employe");
		if (!id.isEmpty()){
		int idemp = Integer.parseInt(id);
		return idemp;
		}
		else{
			setErreur("id_employe","id employe introuvable");
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
	
	private String getType(HttpServletRequest request)
	{
		String type = request.getParameter("type");
		if (!type.isEmpty()){
		return type;
		}
		else{
			setErreur("type","type introuvable");
			return null;
		}
	}
	
	private int getId_reponse(HttpServletRequest request)
	{
		String id_tickets = request.getParameter("id_reponse");
		if (!id_tickets.isEmpty()){
		int id_ticket = Integer.parseInt(id_tickets);
		return id_ticket;
		}
		else{
			setErreur("id_ticket","id ticket introuvable");
			return 0;
		}
	}
}