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

import uk.ac.abdn.fits.hibernate.dao.SurchargeOperatingTimeDAO;
import uk.ac.abdn.fits.hibernate.model.SurchargeOperatingTime;


@Service
public class SurchargeOperatingTimeDAOImpl implements SurchargeOperatingTimeDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertSurchargeOperatingTime(
			SurchargeOperatingTime operating_time) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().save(operating_time);
	}

	@Override
	public SurchargeOperatingTime getSurchargeOperatingTimeByOpId(
			int operatorId) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from Surcharge_operating_time where operator_id = :operator_id");
		  query.setParameter("operator_id", operatorId);
		  if(query.list().size() > 0){
			  return (SurchargeOperatingTime)query.list().get(0);
		  }
		  return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SurchargeOperatingTime> getSurchargeOperatingTimes() {
		Criteria criteria = sessionFactory.
			      getCurrentSession().
			      createCriteria(SurchargeOperatingTime.class);
		return criteria.list();
	}

}
