package uk.ac.abdn.fits.mvc.control.admin;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.model.UserRole;
import uk.ac.abdn.fits.hibernate.user.UserManager;

@Controller
@RequestMapping(value="/edit_user/{user_id}")
@SessionAttributes("editUserFormBean")
public class EditUserFormController {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form(@PathVariable(value="user_id") String user_id, Locale locale, Model model) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");		
		
		if(user_id!=null){
			User user = userManager.getUserByIdString(user_id);
			
			model.addAttribute("user_id", user_id);
			model.addAttribute("username", user.getUsername());
			model.addAttribute("fname", user.getFname());
			model.addAttribute("lname", user.getLname());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("phone", user.getPhone_number());
			model.addAttribute("password", user.getPassword());
			model.addAttribute("user_role", user.getRole().getId().toString());

		}
		
		return new ModelAndView("edit_user");
		
	}
	
	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
	@ModelAttribute("editUserFormBean")
	public EditUserFormBean createFormBean() {
		return new EditUserFormBean();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String edit(@PathVariable(value="user_id") String user_id, @ModelAttribute EditUserFormBean editUserFormBean, BindingResult result, 
			Model model, RedirectAttributes redirectAttrs) {
		
		ArrayList <String> messages = new ArrayList<String>() ;
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");
		
		User user = userManager.getUserByIdString(editUserFormBean.getUser_id());
		
		//System.out.println("inside edit user form user name = " + editUserFormBean.getFname());

		
		if(userManager.getUsersByUsername(editUserFormBean.getUserName()).size()>1)
			messages.add("Username already in use");
		if(userManager.getUsersByEmail(editUserFormBean.getEmail()).size()>1)
			messages.add("Email already in use");
		if(editUserFormBean.getUserName()==null)
			messages.add("Username cannot be blank");
		if(editUserFormBean.getPassword()==null || editUserFormBean.getPassword().length()<=0)
			messages.add("Password cannot be blank");
		if(editUserFormBean.getFname()==null || editUserFormBean.getFname().length()<=0)
			messages.add("First name cannot be blank");
		if(editUserFormBean.getLname()==null || editUserFormBean.getLname().length()<=0)
			messages.add("Last name cannot be blank");
		
		if(messages.size()==0){
			user.setUsername(editUserFormBean.getUserName());
			user.setFname(editUserFormBean.getFname());
			user.setLname(editUserFormBean.getLname());
			user.setEmail(editUserFormBean.getEmail());
			user.setPhone_number(editUserFormBean.getPhone());
			user.setPassword(editUserFormBean.getPassword());

			UserRole user_role= userManager.getUserRoleForUserId(user.getId());
		
			user_role.setRole_id(Integer.toString(editUserFormBean.getUser_role()));
			userManager.updateUser(user);
			userManager.updateUserRole(user_role);

			user_role= userManager.getUserRoleForUserId(user.getId());

			//System.out.println("xxxxxxxxxxxxxxxxx role id: " + user_role.getRole_id());
			//System.out.println("xxxxxxxxxxxxxxxxx user id: " + user_role.getUser_id());

			
			
			redirectAttrs.addFlashAttribute("success", true);
		}
		else{
			redirectAttrs.addFlashAttribute("success", false);
		}
	
		redirectAttrs.addFlashAttribute("userName", editUserFormBean.getUserName());
		redirectAttrs.addFlashAttribute("messages", messages);
	
		return "redirect:/edit_user/"+user.getId();		

	}
}