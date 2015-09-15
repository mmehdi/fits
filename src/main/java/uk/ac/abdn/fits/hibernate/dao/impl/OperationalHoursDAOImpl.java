package uk.ac.abdn.fits.hibernate.dao.impl;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.*;
import uk.ac.abdn.fits.hibernate.model.OperationalHours;
import uk.ac.abdn.fits.hibernate.model.Operator;


@Service
public class OperationalHoursDAOImpl implements OperationalHoursDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertOperationalHours(OperationalHours operational_hours) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().save(operational_hours);
	}

	@Override
	public List<OperationalHours> getOperationalHoursByOpId(int operator_id) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from OperationalHours where operator_id = :operator_id");
		  query.setParameter("operator_id", operator_id);
		  if(query.list().size() > 0){
			  return query.list();
		  }
		  return null;
	}

}
