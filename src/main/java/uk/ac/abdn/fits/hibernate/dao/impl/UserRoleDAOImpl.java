package uk.ac.abdn.fits.hibernate.dao.impl;
/**
 *  @author Mujtaba Mehdi, University of Aberdeen. Updated 20/09/2015
 */

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.UserRoleDAO;
import uk.ac.abdn.fits.hibernate.model.Operator;
import uk.ac.abdn.fits.hibernate.model.UserRole;

@Service
public class UserRoleDAOImpl implements UserRoleDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertUserRole(UserRole userRole) {
		 sessionFactory.getCurrentSession().save(userRole);
	}
	
	@Override
	public UserRole getUserRoleForUserId(int userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from UserRole where user_id = :user_id");
		  query.setParameter("user_id", userId);
		  if(query.list().size() > 0){
			  return (UserRole)query.list().get(0);
		  }
		  return null;	
	}

	@Override
	public void updateUserRole(UserRole userRole) {
		 sessionFactory.getCurrentSession().update(userRole);
		
	}

}
