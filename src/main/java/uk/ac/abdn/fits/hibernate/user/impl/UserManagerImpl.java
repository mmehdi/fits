package uk.ac.abdn.fits.hibernate.user.impl;

/**
 * @author Cheng Zeng, University of Aberdeen
 * @author Mujtaba Mehdi, University of Aberdeen. Updated 20/09/2015
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.dao.UserDAO;
import uk.ac.abdn.fits.hibernate.dao.UserRoleDAO;
import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.model.UserRole;
import uk.ac.abdn.fits.hibernate.user.UserManager;

public class UserManagerImpl implements UserManager{

	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	  
	@Transactional
	public void insertUser(User user) {
		userDAO.insertUser(user);
	}
	

	@Transactional
	public void updateUser(User user) {
		userDAO.updateUser(user);
		
	}
	
	
	@Transactional
	public void insertUserRole(UserRole userRole){
		userRoleDAO.insertUserRole(userRole);
	}
	
	@Transactional
	public User getUserById(int userId) {
		return userDAO.getUserById(userId);
	}
	  
	@Transactional
	public User getUserByIdString(String userId) {
		return userDAO.getUserByIdString(userId);
	}
	
	@Transactional
	public User getUser(String username) {
	    
		return userDAO.getUser(username);
	  }

	  @Transactional
	  public List<User> getUsers() {
	    return userDAO.getUsers();
	  }

	  @Transactional
	public List<User> getUsersByUsername(String username) {
	    return userDAO.getUsersByUsername(username);

	}

	  @Transactional
	public List<User> getUsersByEmail(String email) {
	    return userDAO.getUsersByEmail(email);
	}

	  
	  
}
