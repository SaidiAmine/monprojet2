package projet.helpdesk.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import projet.helpdesk.beans.FAQ;

@Stateless
public class FAQDao {
	
	private static final String JPQL_SELECT_ALL = "SELECT f FROM FAQ f";
	
	private static final String JPQL_SELECT_QR = "SELECT f FROM FAQ f WHERE id_qr=:id_qr";
	private static final String PARAMETER = "id_qr";
	
	private static final String JPQL_DELETE_QR ="DELETE FROM FAQ f WHERE f.id_qr=:id_qr";
			
			/**
			 * Query query = em.createQuery(
      "DELETE FROM Country c WHERE c.population < :p");
  int deletedCount = query.setParameter(p, 100000).executeUpdate();
			 * **/
	
	@PersistenceContext( unitName = "bdd_helpdesk_PU" )
    private EntityManager       em;

	
	public void supprimer( int id_qr ) throws IllegalArgumentException, DAOException {
    	try {
    		Query query = em.createQuery(JPQL_DELETE_QR);
    		query.setParameter(PARAMETER, id_qr).executeUpdate();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }     
    }
	
	public void creer( FAQ faq ) throws IllegalArgumentException, DAOException {
    	try {
    		em.persist(faq);
        } catch ( Exception e ) {
            throw new DAOException( e );
        }     
    }
	
	@SuppressWarnings("unchecked")
	public List<FAQ> chargerFAQ() throws DAOException {
		List<FAQ> listefaq;
		Query query = em.createQuery(JPQL_SELECT_ALL);
		try {
            listefaq = (List<FAQ>) query.getResultList();
        } catch ( NoResultException e ) {
            return null;
        } catch(Exception e) {
            throw new DAOException(e);
        }
        return listefaq;
	}
	
	public FAQ trouverQR(int id_qr) throws DAOException{
		FAQ qr;
		Query query = em.createQuery(JPQL_SELECT_QR);
		query.setParameter(PARAMETER, id_qr);
		 try {
	            qr = (FAQ) query.getSingleResult();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch(Exception e) {
	            throw new DAOException(e);
	        }
	        return qr;
	}
	
}
