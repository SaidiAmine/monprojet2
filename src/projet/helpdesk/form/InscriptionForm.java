package projet.helpdesk.form;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import projet.helpdesk.beans.Utilisateur;
import projet.helpdesk.dao.DAOException;
import projet.helpdesk.dao.UtilisateurDao;

public class InscriptionForm {
	private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "mdp";
    private static final String CHAMP_CONF   = "confirmation";
    private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_POSTE = "poste";
	private static final String CHAMP_DEPT = "dept";
	private static final String CHAMP_AGENCE = "agence";
	private static final String CHAMP_TYPE ="type";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();
    
    private UtilisateurDao      utilisateurDao;

    public InscriptionForm( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public Utilisateur inscrireUtilisateur( HttpServletRequest request ) {
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );
        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp(request, CHAMP_PRENOM);
        String poste = getValeurChamp(request,CHAMP_POSTE);
        String dept = getValeurChamp(request,CHAMP_DEPT);
        String agence = getValeurChamp(request,CHAMP_AGENCE);
        String type = getValeurChamp(request,CHAMP_TYPE);
        
        Utilisateur utilisateur = new Utilisateur();
try{
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );

        traiterMotsDePasse( motDePasse, confirmation, utilisateur );

        try {
            validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        utilisateur.setNom( nom );
        
        try { 
        	validationPrenom(prenom);
        } catch (Exception e) {
        	setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        utilisateur.setPrenom(prenom);
        utilisateur.setDept(dept);
        utilisateur.setPoste(poste);
        utilisateur.setAgence(agence);
        
        try{
        	validationType( type);
        } catch ( Exception e) {
        	setErreur(CHAMP_TYPE,e.getMessage());
        }
        utilisateur.setType(type);
        
        utilisateur.setTech(0);
        String Tech = "Technicien";  
        if(type.equals(Tech))
        	utilisateur.setTech(1);
        
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        utilisateur.setDate_inscr(date);
        
        if ( erreurs.isEmpty() ) {
        	utilisateurDao.creer( utilisateur );
            resultat = "Succès de l'inscription.";
        } else {
            resultat = "Échec de l'inscription.";
        }
        
}catch ( DAOException e) {
    resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
    e.printStackTrace();
}
        return utilisateur;
    }
    
    private void validationEmail( String email ) throws FormValidationException {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            } else if ( utilisateurDao.trouver( email ) != null ) {
                throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }

    private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( motDePasse.length() < 3 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
        }
    }
    
    private void traiterMotsDePasse( String motDePasse, String confirmation, Utilisateur utilisateur ) {
        try {
            validationMotsDePasse( motDePasse, confirmation );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( "SHA-256" );
        passwordEncryptor.setPlainDigest( false );
        String motDePasseChiffre = passwordEncryptor.encryptPassword( motDePasse );

        utilisateur.setMdp( motDePasseChiffre );
    }
    
    
    

    private void validationNom( String nom ) throws Exception {
        if (( nom != null && nom.length() < 3) || nom==null) {
            throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }
    
    private void validationPrenom (String prenom) throws Exception {
    	if (( prenom != null && prenom.length() < 3) || prenom==null) {
            throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
    
    private void validationType(String type) throws Exception{
    	if(type==null)
    	{
    		throw new Exception ("Veuillez saisir le type d'utilisateur.");
    	}
    }
}
