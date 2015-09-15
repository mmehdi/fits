package uk.ac.abdn.fits.hibernate.dao;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.UserRole;

@Transactional(readOnly = false)
public interface UserRoleDAO {
	
	public void insertUserRole(UserRole userRole);

}
