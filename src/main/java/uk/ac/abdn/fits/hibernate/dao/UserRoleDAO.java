package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Mujtaba Mehdi, University of Aberdeen. Updated 20/09/2015
 */

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.UserRole;

@Transactional(readOnly = false)
public interface UserRoleDAO {
	
	public void insertUserRole(UserRole userRole);
	public void updateUserRole(UserRole userRole);

}
