package uk.ac.abdn.fits.hibernate.user;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.model.UserRole;


public interface UserManager {
	
	void insertUser(User user);
	
	  User getUserById(int userId);
		
	  User getUser(String username);
		
	  List<User> getUsers();
	  
	  void insertUserRole(UserRole userRole);
	  
	  

}
