package projet.helpdesk.form;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import projet.helpdesk.beans.*;
import projet.helpdesk.dao.UtilisateurDao;


public class ConnexionForm {
private static final String CHAMP_EMAIL = "email";
private static final String CHAMP_MDP ="mdp";
private String resultat;
private Map<String, String>erreurs=new HashMap<String, String>();
private UtilisateurDao utilisateurDao;
private boolean valid;
public String getResulat(){
	return resultat;
}
public Map<String,String> getErreurs(){
	return erreurs;
}

public ConnexionForm (UtilisateurDao utilisateurDao)
{
	this.utilisateurDao=utilisateurDao;
}

public Utilisateur connecterUtilisateur(HttpServletRequest request){
	String email = request.getParameter(CHAMP_EMAIL);
	String mdp = request.getParameter(CHAMP_MDP);
	Utilisateur utilisateur = utilisateurDao.trouver(email);
	try{
		validationEmail(email);
	}catch(Exception e){
		setErreur(CHAMP_EMAIL,e.getMessage());
		setValid(false);
	}
	try{
		validationMotDePasse(mdp);
	}catch (Exception e) {
		setErreur(CHAMP_MDP,e.getMessage());
		setValid(false);
	}
	if(getErreurs().isEmpty())
	{
	 	ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	    passwordEncryptor.setAlgorithm("SHA-256");
	    passwordEncryptor.setPlainDigest(false);
	    setValid(passwordEncryptor.checkPassword(mdp,utilisateur.getMdp()));
	    }
	return utilisateur;
}

private void validationEmail(String email) throws Exception{
	if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ){
	throw new Exception("Email invalide");
	}	
}

private void validationMotDePasse( String motDePasse ) throws Exception {
    if ( motDePasse != null ) {
        if ( motDePasse.length() < 3 ) {
            throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
        }
    } else {
        throw new Exception( "Merci de saisir votre mot de passe." );
    }
}

private void setErreur(String champ, String message){
	erreurs.put(champ, message);
}
public boolean getValid() {
	return valid;
}
public void setValid(boolean valid) {
	this.valid = valid;
}

}
