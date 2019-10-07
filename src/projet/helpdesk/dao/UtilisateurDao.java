package projet.helpdesk.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import javax.ejb.Stateless;

import projet.helpdesk.beans.Utilisateur;

@Stateless
public class UtilisateurDao {
    private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.email=:email";
    private static final String PARAM_EMAIL           = "email";
    
    private static final String JPQL_SELECT_TECHNICIENS = "SELECT u FROM Utilisateur u WHERE (u.type='Technicien' OR u.type='Technicien-Responsable')";
    
    private static final String JPQL_SELECT_TECHNICIENS_TICKETS_ASS = "SELECT u.nom, u.prenom, u.idemp, COUNT(t.id_ticket) "
														    		+ "FROM Utilisateur u, Ticket t "
														    		+ "WHERE u.idemp=t.id_technicien "
														    		+ "AND u.idemp > 0 "
														    		+ "AND (t.etat=1 OR t.etat=4) "
														    		+ "GROUP BY u.idemp, u.nom, u.prenom";
    
    private static final String JPQL_SELECT_ALL_TECHNICIENS ="SELECT u FROM Utilisateur u WHERE u.type='Technicien'";

    private static final String JPQL_SELECT_ALL_USERS ="SELECT u FROM Utilisateur u";
    
    private static final String JPQL_SELECT_STATS_TECHNICIENS = "SELECT u.nom, u.prenom, COUNT(u.idemp) FROM Utilisateur u, Ticket t WHERE u.idemp = t.id_technicien GROUP BY u.nom, u.prenom";
    
    private static final String JPQL_SELECT_STATS_UTILISATEURS = "SELECT u.nom, u.prenom, COUNT(u.idemp) FROM Utilisateur u, Ticket t WHERE u.idemp=t.id_employe GROUP BY u.nom, u.prenom";
    
    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "bdd_helpdesk_PU" )
    private EntityManager       em;
    
    public List<Object[]> chargerUsersParTickets() throws DAOException {
        List<Object[]> liste;
    	//List<Object[]> results = em.createQuery("").getResultList();
        TypedQuery<Object[]> query = em.createQuery(JPQL_SELECT_STATS_UTILISATEURS, Object[].class);
        try {
            liste = (List<Object[]>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch(Exception e) {
            throw new DAOException(e);
        }
        return liste;
    }
    
    public List<Object[]> chargerTechsParTickets() throws DAOException {
        List<Object[]> liste;
    	//List<Object[]> results = em.createQuery("").getResultList();
        TypedQuery<Object[]> query = em.createQuery(JPQL_SELECT_STATS_TECHNICIENS, Object[].class);
        try {
            liste = (List<Object[]>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch(Exception e) {
            throw new DAOException(e);
        }
        return liste;
    }

    public void suppCompte(int id_employe) throws IllegalArgumentException, DAOException {
    	Query query = em.createQuery(
    		      "DELETE FROM Utilisateur u " +
    		      "WHERE u.idemp=:idemp");
    		try{  
    			query.setParameter("idemp", id_employe).executeUpdate();
    		} catch ( Exception e) {
    			throw new DAOException( e );
    		}
    }
    
    public List<Utilisateur> chargerUsers() throws DAOException{
    	List<Utilisateur> lusers = null;
    	Query query = em.createQuery(JPQL_SELECT_ALL_USERS);
    	try {
            lusers = (List<Utilisateur>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return lusers;
    	
    }
    
    public void majTechEnTechr(int id_technicien) throws IllegalArgumentException, DAOException {
    	Query query = em.createQuery(
    		      "UPDATE Utilisateur SET type = 'Technicien-Responsable', tech=1 " +
    		      "WHERE id_technicien=:id_technicien");
    		try{  
    			query.setParameter("id_technicien", id_technicien).executeUpdate();
    		} catch ( Exception e) {
    			throw new DAOException( e );
    		}
    }
    
    public List<Utilisateur> trouverToutTechnicien() throws DAOException{
    	List<Utilisateur> ltechniciens = null;
    	Query query = em.createQuery(JPQL_SELECT_ALL_TECHNICIENS);
    	try {
            ltechniciens = (List<Utilisateur>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return ltechniciens;
    	
    }
    
    public List<Object[]> trouverTechsTicksAss() throws DAOException {
        List<Object[]> liste;
    	//List<Object[]> results = em.createQuery("").getResultList();
        TypedQuery<Object[]> query = em.createQuery(JPQL_SELECT_TECHNICIENS_TICKETS_ASS, Object[].class);
        try {
            liste = (List<Object[]>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch(Exception e) {
            throw new DAOException(e);
        }
        return liste;
    }
    
    
    // Enregistrement d'un nouvel utilisateur
    public void creer( Utilisateur utilisateur ) throws DAOException {
        try {
            em.persist( utilisateur );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    // Recherche d'un utilisateur à partir de son adresse email
    public Utilisateur trouver( String email ) throws DAOException {
        Utilisateur utilisateur = null;
        Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
        requete.setParameter( PARAM_EMAIL, email );
        try {
            utilisateur = (Utilisateur) requete.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return utilisateur;
    }
    
    public List<Utilisateur> chargerTechniciens() throws DAOException {
        List<Utilisateur> lutilisateur = null;
        Query requete = em.createQuery( JPQL_SELECT_TECHNICIENS );
        try {
            lutilisateur = (List<Utilisateur>) requete.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return lutilisateur;
    }
}