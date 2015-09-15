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

import uk.ac.abdn.fits.hibernate.dao.SurchargeAgeGroupDAO;
import uk.ac.abdn.fits.hibernate.model.SurchargeAgeGroup;


@Service
public class SurchargeAgeGroupDAOImpl implements SurchargeAgeGroupDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertSurchargeAgeGroup(SurchargeAgeGroup age_group) {
		
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().save(age_group);
	}

	@Override
	public SurchargeAgeGroup getSurchargeAgeGroupByOpId(int operatorId) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from Surcharge_age_group where operator_id = :operator_id");
		  query.setParameter("operator_id", operatorId);
		  if(query.list().size() > 0){
			  return (SurchargeAgeGroup)query.list().get(0);
		  }
		  return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SurchargeAgeGroup> getSurchargeAgeGroups() {
		Criteria criteria = sessionFactory.
			      getCurrentSession().
			      createCriteria(SurchargeAgeGroup.class);
		return criteria.list();
	}

}
