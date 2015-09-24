package uk.ac.abdn.fits.hibernate.user;

/**
 * @author Cheng Zeng, University of Aberdeen
 * @author Mujtaba Mehdi, University of Aberdeen. Updated 20/09/2015
 */

import java.util.List;

import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.model.UserRole;


public interface UserManager {
	
	void insertUser(User user);
	void updateUser(User user);

	  User getUserById(int userId);
	  
	  //method to work around form data
	  User getUserByIdString(String userId);

	  User getUser(String username);
		
	  List<User> getUsers();
	  
	  List<User> getUsersByUsername(String username);
	  List<User> getUsersByEmail(String email);

	  
	  void insertUserRole(UserRole userRole);
	  UserRole getUserRoleForUserId(int userId);
	void updateUserRole(UserRole user_role);

}
