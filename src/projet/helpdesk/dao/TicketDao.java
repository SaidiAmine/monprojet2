package projet.helpdesk.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ejb.Stateless;

import java.sql.Timestamp;
import java.util.List;

import projet.helpdesk.beans.Ticket;

@Stateless
public class TicketDao {
	/**ID_TICKET*  ID_EMPLOYE*  ID_TECHNICIEN - DESCRIPTION*  - SUJET*  - DATE_ENVOI* - DATE_FERMETURE - ETAT* - PRIORITE* **/
	//
	private static final String JPQL_SELECT_PAR_TICKET = "SELECT t FROM Ticket t WHERE t.id_ticket =:id_ticket";
	private static final String PARAM_TICKET           = "id_ticket";
	
	// Requete 
	private static final String JPQL_SELECT_PAR_EMP = "SELECT t FROM Ticket t WHERE t.id_employe = :id_employe";
	private static final String PARAM_EMP           = "id_employe";
	
	// Requete pour lister ticket pas pris en charge pour techniciens
	private static final String JPQL_SELECT_TICKETS_TECH = "SELECT t from Ticket t WHERE (t.etat=1 OR t.etat=2 OR t.etat=4) AND t.id_technicien=:id_technicien";
	private static final String PARAM_TECH           = "id_technicien";
	//Requete lister tickets pas pris en charge pour assignation
	private static final String JPQL_SELECT_TICKETS_ASSIGN = "SELECT t from Ticket t WHERE t.etat=1";
	
	private static final String JPQL_SELECT_TICKETS_ETAT = "SELECT t.etat, COUNT(t.id_ticket) FROM Ticket t GROUP BY t.etat";
	
	private static final String JPQL_SELECT_TICKES_SOUMI_PAR_DATE = "SELECT t FROM Ticket t";
	//private static final String JPQL_SELECT_STATS_1 ="SELECT u.nom, u.prenom, COUNT(t.id_ticket) FROM Utilisateur u, Ticket t WHERE u.idemp = t.id_technicien GROUP BY u.nom, u.prenom";
	
	@PersistenceContext( unitName = "bdd_helpdesk_PU" )
    private EntityManager       em;
	
	@SuppressWarnings("unchecked")
	public List<Ticket> chargerTicketsParDate() throws DAOException {
        List<Ticket> lticket;
    	Query query = em.createQuery(JPQL_SELECT_TICKES_SOUMI_PAR_DATE);
        try {
            lticket = (List<Ticket>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch(Exception e) {
            throw new DAOException(e);
        }
        return lticket;
    }
	
	public List<Object[]> chargerTicketsParEtat() throws DAOException {
        List<Object[]> liste;
    	//List<Object[]> results = em.createQuery("").getResultList();
        TypedQuery<Object[]> query = em.createQuery(JPQL_SELECT_TICKETS_ETAT, Object[].class);
        //query.setParameter(PARAM_TICKET, id_ticket);
        try {
            liste = (List<Object[]>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch(Exception e) {
            throw new DAOException(e);
        }
        return liste;
    }
	
	
	public int majTicketEnTraitement(int id_ticket, int id_technicien) throws IllegalArgumentException, DAOException {
    	Query query = em.createQuery(
    		      "UPDATE Ticket SET etat = 4, id_technicien=:id_technicien" +
    		      " WHERE id_ticket=:id_ticket");
    		try{  
    			query.setParameter("id_technicien", id_technicien);
    			int entier = query.setParameter("id_ticket", id_ticket).executeUpdate();
    			return entier;
    		} catch ( Exception e) {
    			throw new DAOException( e );
    		}
    	
    }
	
	public List<Ticket> trouverTicketsAssignation() throws DAOException {
        List<Ticket> lticket;
    	Query query = em.createQuery(JPQL_SELECT_TICKETS_ASSIGN);
        try {
            lticket = (List<Ticket>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch(Exception e) {
            throw new DAOException(e);
        }
        return lticket;
    }
	
    public Ticket trouverTicket( int id_ticket ) throws DAOException {
    
        Ticket ticket = null;
        Query requete = em.createQuery(JPQL_SELECT_PAR_TICKET);
        requete.setParameter(PARAM_TICKET, id_ticket);
        try {
            ticket = (Ticket) requete.getSingleResult();
            
        } catch ( NoResultException e ) {
            return null;
        } catch (Exception e){
            throw new DAOException(e);
        }
        return ticket;
    }
    
    @SuppressWarnings("unchecked")
	public List<Ticket> trouverTickets( int id_employe ) throws DAOException {
        List<Ticket> lticket;
    	Query query = em.createQuery(JPQL_SELECT_PAR_EMP);
        query.setParameter(PARAM_EMP, id_employe);
        try {
            lticket = (List<Ticket>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch(Exception e) {
            throw new DAOException(e);
        }
        return lticket;
    }
    
    @SuppressWarnings("unchecked")
	public List<Ticket> trouverTicketsTech(int id_technicien) throws DAOException {
        List<Ticket> lticket;
    	Query query = em.createQuery(JPQL_SELECT_TICKETS_TECH);
    	query.setParameter(PARAM_TECH, id_technicien);
        try {
            lticket = (List<Ticket>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch(Exception e) {
            throw new DAOException(e);
        }
        return lticket;
    }
    
    public void creer( Ticket ticket ) throws IllegalArgumentException, DAOException {
    	try {
    		em.persist(ticket);
        } catch ( Exception e ) {
            throw new DAOException( e );
        }     
    }
    
    public int majEtatTicket3(int id_ticket, Timestamp date_fermeture) throws IllegalArgumentException, DAOException {
    	/*Query querydate = em.createQuery(
    			"UPDATE ticket SET date_fermeture = "*/
    	/**
    	 * Mettre a jour date fermeture
    	 * 
    	 * **/
    	Query query = em.createQuery(
    		      "UPDATE Ticket SET etat = 3, date_fermeture=:date_fermeture " +
    		      "WHERE id_ticket=:id_ticket");
    		try{  
    			query.setParameter("date_fermeture", date_fermeture);
    			int entier = query.setParameter("id_ticket", id_ticket).executeUpdate();
    			return entier;
    		} catch ( Exception e) {
    			throw new DAOException( e );
    		}
    	
    }
    
    public int majEtatTicket2(int id_ticket) throws IllegalArgumentException, DAOException {
    	Query query = em.createQuery(
    		      "UPDATE Ticket SET etat = 2 " +
    		      "WHERE id_ticket=:id_ticket");
    		try{  
    			int entier = query.setParameter("id_ticket", id_ticket).executeUpdate();
    			return entier;
    		} catch ( Exception e) {
    			throw new DAOException( e );
    		}
    	
    }
    
}
