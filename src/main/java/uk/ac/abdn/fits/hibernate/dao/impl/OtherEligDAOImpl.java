package uk.ac.abdn.fits.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.OtherEligDAO;
import uk.ac.abdn.fits.hibernate.model.Operator;
import uk.ac.abdn.fits.hibernate.model.OtherEligTable;

@Service
public class OtherEligDAOImpl implements OtherEligDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertOtherElig(OtherEligTable otherElig) {
		sessionFactory.getCurrentSession().save(otherElig);
	}

	@Override
	public OtherEligTable getOtherEligById(int id) {
		 return (OtherEligTable) sessionFactory.
			      getCurrentSession().
			      get(OtherEligTable.class, id);
	}

	@Override
	public List<OtherEligTable> getOtherEligByOperatorId(int operatorId) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from OtherEligTable where operator_id = :operator_id");
		  query.setParameter("operator_id", operatorId);
		  if(query.list().size() > 0){
			  return query.list();
		  }
		  return null;
	}

	@Override
	public List<OtherEligTable> getAllOtherEligTable() {
		
		Criteria criteria = sessionFactory.
			      getCurrentSession().
			      createCriteria(OtherEligTable.class);
		return criteria.list();
	}

}
