package uk.ac.abdn.fits.hibernate.dao.impl;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.*;
import uk.ac.abdn.fits.hibernate.model.OperatingArea;



@Service
public class OperatingAreaDAOImpl implements OperatingAreaDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertOperatingArea(OperatingArea operating_area) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().save(operating_area);
	}

	@Override
	public void updateOperatingArea(OperatingArea operating_area) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().update(operating_area);
	}
	@Override
	public void deleteOperatingArea(OperatingArea operating_area) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().delete(operating_area);
	}
	
	
	@Override
	public OperatingArea getOperatingAreaByOpId(int operator_id) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from OperatingArea where operator_id = :operator_id");
		  query.setParameter("operator_id", operator_id);
		  if(query.list().size() > 0){
			  return (OperatingArea)query.list().get(0);
		  }
		  return null;
	}
	
}
