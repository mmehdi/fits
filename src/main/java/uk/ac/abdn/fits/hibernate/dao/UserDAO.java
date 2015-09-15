package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.User;


@Transactional(readOnly = false)
public interface UserDAO {

	  void insertUser(User user);
	  User getUserById(int userId);
	  User getUser(String username);
	  List<User> getUsers();
}