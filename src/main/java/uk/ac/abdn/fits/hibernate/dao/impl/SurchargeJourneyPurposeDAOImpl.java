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

import uk.ac.abdn.fits.hibernate.dao.SurchargeJourneyPurposeDAO;
import uk.ac.abdn.fits.hibernate.model.SurchargeJourneyPurpose;


@Service
public class SurchargeJourneyPurposeDAOImpl implements
		SurchargeJourneyPurposeDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertSurchargeJourneyPurpose(
			SurchargeJourneyPurpose journey_purpose) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().save(journey_purpose);
	}

	@Override
	public SurchargeJourneyPurpose getSurchargeJourneyPurposeByOpId(
			int operatorId) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from Surcharge_journey_purpose where operator_id = :operator_id");
		  query.setParameter("operator_id", operatorId);
		  if(query.list().size() > 0){
			  return (SurchargeJourneyPurpose)query.list().get(0);
		  }
		  return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SurchargeJourneyPurpose> getSurchargeJourneyPurposes() {
		Criteria criteria = sessionFactory.
			      getCurrentSession().
			      createCriteria(SurchargeJourneyPurpose.class);
		return criteria.list();
	}

}
