package projet.helpdesk.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import projet.helpdesk.beans.ReponseTicket;

@Stateless
public class ReponseTicketDao {
	//<c:if test ="${liste=='hola'}">
		private static final String JPQL_SELECT_PAR_TICKET = "SELECT r FROM ReponseTicket r WHERE r.id_ticket =:id_ticket";
		private static final String PARAM_TICKET           = "id_ticket";
		
		

		private static final String JPQL_SELECT ="SELECT u.nom, u.prenom, r.texte, r.date_post, u.type, u.idemp, r.id_reponse, r.valide FROM Utilisateur u, ReponseTicket r WHERE u.idemp = r.id_employe AND r.id_ticket=:id_ticket";
		
		@PersistenceContext( unitName = "bdd_helpdesk_PU" )
	    private EntityManager       em;
		
		public List<Object[]> trouverJointure( int id_ticket ) throws DAOException {
	        List<Object[]> liste;
	    	//List<Object[]> results = em.createQuery("").getResultList();
	        TypedQuery<Object[]> query = em.createQuery(JPQL_SELECT, Object[].class);
	        query.setParameter(PARAM_TICKET, id_ticket);
	        try {
	            liste = (List<Object[]>) query.getResultList();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch(Exception e) {
	            throw new DAOException(e);
	        }
	        return liste;
	    }
		
		public List<ReponseTicket> trouverReponses( int id_ticket ) throws DAOException {
	        List<ReponseTicket> lreponse;
	    	Query query = em.createQuery(JPQL_SELECT_PAR_TICKET);
	        query.setParameter(PARAM_TICKET, id_ticket);
	        try {
	            lreponse = (List<ReponseTicket>) query.getResultList();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch(Exception e) {
	            throw new DAOException(e);
	        }
	        return lreponse;
	    }
	    
	    public void creer( ReponseTicket reponse ) throws IllegalArgumentException, DAOException {
	    	try {
	    		em.persist(reponse);
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }     
	    }
	    
	    public int majValidReponse(int id_reponse) throws IllegalArgumentException, DAOException {
	    	Query query = em.createQuery(
	    		      "UPDATE ReponseTicket SET valide = 1 " +
	    		      "WHERE id_reponse=:id_reponse");
	    		try{  
	    			int entier = query.setParameter("id_reponse", id_reponse).executeUpdate();
	    			return entier;
	    		} catch ( Exception e) {
	    			throw new DAOException( e );
	    		}
	    	
	    }
}