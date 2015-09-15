package uk.ac.abdn.fits.hibernate.dao.impl;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.RoleDAO;
import uk.ac.abdn.fits.hibernate.model.Role;


@Service
public class RoleDAOImpl implements RoleDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	  
	
	@Override
	public Role getRole(int id) {
		Role role = (Role) sessionFactory.getCurrentSession().load(Role.class, id);  
        return role;  
	}

}
