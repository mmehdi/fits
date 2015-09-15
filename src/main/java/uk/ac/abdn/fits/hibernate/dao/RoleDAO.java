package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.Role;


@Transactional(readOnly = false)
public interface RoleDAO {
	
	 public Role getRole(int id);  
	 
}
