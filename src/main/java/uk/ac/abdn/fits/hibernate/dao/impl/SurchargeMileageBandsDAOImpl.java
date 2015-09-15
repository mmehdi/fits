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

import uk.ac.abdn.fits.hibernate.dao.SurchargeMileageBandsDAO;
import uk.ac.abdn.fits.hibernate.model.SurchargeMileageBands;


@Service
public class SurchargeMileageBandsDAOImpl implements SurchargeMileageBandsDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertSurchargeMileageBands(
			SurchargeMileageBands surcharge_mileage_bands) {
		
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().save(surcharge_mileage_bands);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SurchargeMileageBands> getSurchargeMileageBandsByOpId(
			int operator_id) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from Surcharge_mileage_bands where operator_id = :operator_id");
		  query.setParameter("operator_id", operator_id);
		  if(query.list().size() > 0){
			  return query.list();
		  }
		  return null;
	}

}
