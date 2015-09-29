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
import uk.ac.abdn.fits.hibernate.model.FareMileageBands;


@Service
public class FareMileageBandsDAOImpl implements FareMileageBandsDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertFareMileageBands(FareMileageBands fare_mileage_bands) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().save(fare_mileage_bands);
		
	}

	@Override
	public void deleteFareMileageBands(FareMileageBands fare_mileage_bands) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().delete(fare_mileage_bands);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<FareMileageBands> getFareMileageBandsByFareStrctId(
			int fare_structure_id) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from FareMileageBands where fare_structure_id = :fare_structure_id order by id");
		  query.setParameter("fare_structure_id", fare_structure_id);
		  if(query.list().size() > 0){
			  return query.list();
		  }
		  return null;
	}

}
