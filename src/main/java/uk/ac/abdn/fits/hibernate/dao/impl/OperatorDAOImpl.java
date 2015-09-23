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
import uk.ac.abdn.fits.hibernate.model.Operator;



@Service
public class OperatorDAOImpl implements OperatorDAO{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public void insertOperator(Operator operator) {
		sessionFactory.getCurrentSession().save(operator);
	}

	@Override
	public void updateOperator(Operator operator) {
		sessionFactory.getCurrentSession().update(operator);
	}

	
	@Override
	public Operator getOperatorById(int operatorId) {
		 return (Operator) sessionFactory.
			      getCurrentSession().
			      get(Operator.class, operatorId);
	}

	@Override
	public Operator getOperator(String name) {
		 Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from Operator where name = :name");
		  query.setParameter("name", name);
		  if(query.list().size() > 0){
			  return (Operator)query.list().get(0);
		  }
		  return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Operator> getOperators() {
		Criteria criteria = sessionFactory.
			      getCurrentSession().
			      createCriteria(Operator.class);
		System.out.println(sessionFactory.toString());
		return criteria.list();
	}

	@Override
	public List<Operator> getOperatorByUserId(int userId) {
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from Operator where user_id = :user_id");
		  query.setParameter("user_id", userId);
		  if(query.list().size() > 0){
			  return query.list();
		  }
		  return null;
	}

}
