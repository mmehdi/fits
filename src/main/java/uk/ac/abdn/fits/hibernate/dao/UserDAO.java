package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * @author Mujtaba Mehdi, University of Aberdeen. Updated 20/09/2015
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.User;


@Transactional(readOnly = false)
public interface UserDAO {

	  void insertUser(User user);
	  void updateUser(User user);
	  User getUserById(int userId);
	  User getUserByIdString(String userId);

	  User getUser(String username);
	  List<User> getUsersByUsername(String username);
	  List<User> getUsersByEmail(String email);

	  List<User> getUsers();
}