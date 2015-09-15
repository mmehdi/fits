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

import uk.ac.abdn.fits.hibernate.dao.*;
import uk.ac.abdn.fits.hibernate.model.FareStructure;


@Service
public class FareStructureDAOImpl implements FareStructureDAO{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertFareStructure(FareStructure fare) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().save(fare);
	}

	@Override
	public FareStructure getFareStructureByOpId(int id) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from FareStructure where operator_id = :operator_id");
		  query.setParameter("operator_id", id);
		  if(query.list().size() > 0){
			  return (FareStructure)query.list().get(0);
		  }
		  return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FareStructure> getFareStructures() {
		Criteria criteria = sessionFactory.
			      getCurrentSession().
			      createCriteria(FareStructure.class);
		return criteria.list();
	}

}
