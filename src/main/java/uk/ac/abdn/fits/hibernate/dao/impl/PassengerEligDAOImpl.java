package uk.ac.abdn.fits.hibernate.dao.impl;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.PassengerEligDAO;
import uk.ac.abdn.fits.hibernate.model.PassengerElig;



@Service
public class PassengerEligDAOImpl implements PassengerEligDAO{
	
	
	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public void insertPassengerElig(PassengerElig elig) {
		sessionFactory.getCurrentSession().save(elig);
	}
	
	@Override
	public void updatePassengerElig(PassengerElig elig) {
		sessionFactory.getCurrentSession().update(elig);
		
	}

	
	@Override
	public PassengerElig getPassengerEligByOpId(int operatorId) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from PassengerElig where operator_id = :operator_id");
		  query.setParameter("operator_id", operatorId);
		  if(query.list().size() > 0){
			  return (PassengerElig)query.list().get(0);
		  }
		  return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PassengerElig> getPassengerEligs() {
		
		Criteria criteria = sessionFactory.
			      getCurrentSession().
			      createCriteria(PassengerElig.class);
		return criteria.list();
	}
}
