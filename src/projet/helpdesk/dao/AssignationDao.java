package projet.helpdesk.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.helpdesk.beans.Assignation;


public class AssignationDao {
	
	

	@PersistenceContext( unitName = "bdd_helpdesk_PU" )
    private EntityManager       em;
	
	public void creer( Assignation assign ) throws IllegalArgumentException, DAOException {
    	try {
    		em.persist(assign);
        } catch ( Exception e ) {
            throw new DAOException( e );
        }     
    }
}
