package projet.helpdesk.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import projet.helpdesk.beans.FAQ;
import projet.helpdesk.dao.FAQDao;

public class FAQForm {
	
	private static final String CHAMP_Q ="question";
	private static final String CHAMP_R="reponse";
	private boolean valide = false;
	
	private static Map<String, String>erreurs=new HashMap<String, String>();
	private FAQDao faqDao;

	public FAQForm ( FAQDao faqDao ){
		this.faqDao = faqDao;
	}
	
	public List<FAQ> chargerFAQ(){
		List<FAQ> lfaq;
		lfaq = faqDao.chargerFAQ();
		return lfaq;
	}
	
	public FAQ creerQR( HttpServletRequest request ){
		FAQ qr = new FAQ();
		
		String question = getValeurChamps(request,CHAMP_Q);
		qr.setQuestion(question);
		
		String reponse = getValeurChamps(request,CHAMP_R);
		qr.setReponse(reponse);
		
		int id_employe = getId(request);
		qr.setId_technicien(id_employe);
		
		if(erreurs.isEmpty()){
			setValide(true);
			faqDao.creer(qr);
		}
		return qr;
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	private static void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
	
	private static String getValeurChamps(HttpServletRequest request, String nomChamp){
		String champ = request.getParameter(nomChamp);
		 if ( champ == null || champ.trim().length() == 0 ) {
			 	setErreur(nomChamp,"Champs manquants");
	            return null;
	        } else {
	            return champ.trim();
	        }
	}
	
	
	private int getId(HttpServletRequest request)
	{
		String valeur = request.getParameter("id_employe");
		if (valeur.trim()!=null)
		{
			int id = Integer.parseInt(valeur);
			return id;
		}
		else {
		setErreur("id_employe","Id employe non trouvable");
		return 0;
		}
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}
}
