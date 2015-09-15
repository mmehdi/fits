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

import uk.ac.abdn.fits.hibernate.dao.SurchargeDistanceDAO;
import uk.ac.abdn.fits.hibernate.model.FareStructure;
import uk.ac.abdn.fits.hibernate.model.SurchargeDistance;


@Service
public class SurchargeDistanceDAOImpl implements SurchargeDistanceDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertSurchargeDistance(SurchargeDistance surcharge_dist) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().save(surcharge_dist);
	}

	@Override
	public SurchargeDistance getSurchargeDistanceByOpId(int operatorId) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from Surcharge_distance where operator_id = :operator_id");
		  query.setParameter("operator_id", operatorId);
		  if(query.list().size() > 0){
			  return (SurchargeDistance)query.list().get(0);
		  }
		  return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SurchargeDistance> getSurchargeDistances() {
		Criteria criteria = sessionFactory.
			      getCurrentSession().
			      createCriteria(FareStructure.class);
		return criteria.list();
	}

}
