package uk.ac.abdn.fits.hibernate.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.UserRoleDAO;
import uk.ac.abdn.fits.hibernate.model.UserRole;

@Service
public class UserRoleDAOImpl implements UserRoleDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertUserRole(UserRole userRole) {
		 sessionFactory.getCurrentSession().save(userRole);
	}

}
