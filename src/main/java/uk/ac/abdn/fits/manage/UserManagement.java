package uk.ac.abdn.fits.manage;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.user.UserManager;


public class UserManagement {
	
	private UserManager userManager = null;
	
	private static final UserManagement  instance= null;
	
	private UserManagement(){
		
	}
	
	public static UserManagement getInstance(){
		
		if(instance == null){
			
			return new UserManagement();
		}
		
		return instance;
	}
	
	private void initUserManager(){
		
		if(userManager == null){
			ApplicationContext ctx = 
			        new ClassPathXmlApplicationContext("hibernate.xml");
			userManager = (UserManager) ctx.getBean("userManagerImpl");
		}
	}
	
	public boolean validateLogin(String username, String password){
		
		if(userManager == null){
			initUserManager();
		}
		 User user = userManager.getUser(username);
		 
		 if(user != null){
			 
			 if(user.getPassword().equals(password)){
				 return true;
			 }
		 }
		
		return false;
	}
	
	
	
	
	
	public static void main(String[] args){
		
		ApplicationContext ctx = 
		        new ClassPathXmlApplicationContext("hibernate.xml");
		      UserManager userManager = 
		        (UserManager) ctx.getBean("userManagerImpl");
		      
		      User user = new User();
		      user.setUsername("John6");
		      user.setPassword("password2");
		      
		      userManager.insertUser(user);
		      
		      System.out.println("User inserted!");
		      
		      user = userManager.getUser("John6");
		      
		      System.out.println("\nUser fetched by username!"
		        + "\nId: " + user.getId()
		        + "\nUsername: " + user.getUsername()
		        + "\nPassword: " + user.getPassword());
		      
		      user = userManager.getUserById(user.getId());
		      
		      List<User> users = userManager.getUsers();
		      
		      System.out.println("users: " + users.get(0).getUsername());
		      
		      System.out.println("\nUser list fetched!"
		          + "\nUser count: " + users.size());
		
		
	}
	
	
}
