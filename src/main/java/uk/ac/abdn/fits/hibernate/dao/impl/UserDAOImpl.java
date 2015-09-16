package uk.ac.abdn.fits.hibernate.dao.impl;

/**
 * @author Cheng Zeng, University of Aberdeen
 * @author Mujtaba Mehdi, University of Aberdeen. Updated 20/09/2015

 */

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.omg.CORBA.INTF_REPOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.UserDAO;
import uk.ac.abdn.fits.hibernate.model.User;



@Service
public class UserDAOImpl implements UserDAO {

	
  @Autowired
  private SessionFactory sessionFactory;
  
  
  @Override
  public void insertUser(User user) {
    sessionFactory.getCurrentSession().save(user);
    
  }

  @Override
  public void updateUser(User user) {
	    sessionFactory.getCurrentSession().update(user);  	
  }
  
  @Override
  public User getUserById(int userId) {
    return (User) sessionFactory.
      getCurrentSession().
      get(User.class, userId);
  }
  
  @Override
  public User getUserByIdString(String userId) {
    return getUserById(Integer.parseInt(userId));
  }
  
  @Override
  public User getUser(String username) {
	  
	  Query query = sessionFactory.
		      getCurrentSession().
      createQuery("from User where username = :username");
	  query.setParameter("username", username);
	  if(query.list().size() > 0){
		  return (User)query.list().get(0);
	  }
	  return null;
  }
  
  @SuppressWarnings("unchecked")
@Override
  public List<User> getUsersByUsername(String username) {
	  
	  Query query = sessionFactory.
		      getCurrentSession().
      createQuery("from User where username = :username");
	  query.setParameter("username", username);
	  if(query.list().size() > 0){
		  return query.list();
	  }
	  return null;
  }
  
  @SuppressWarnings("unchecked")
public List<User> getUsersByEmail(String email) {
	  
	  Query query = sessionFactory.
		      getCurrentSession().
      createQuery("from User where email = :email");
	  query.setParameter("email", email);
	  if(query.list().size() > 0){
		  return query.list();
	  }
	  return null;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public List<User> getUsers() {
    Criteria criteria = sessionFactory.
      getCurrentSession().
      createCriteria(User.class);
    return criteria.list();
  }


}
