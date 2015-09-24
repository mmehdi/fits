package uk.ac.abdn.fits.mvc.control.admin;

/**
 * @author Mujtaba Mehdi, University of Aberdeen
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.user.UserManager;


@Controller
@RequestMapping("/admin")
//@SessionAttributes("registerFormBean")
public class AdminController {

	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form(Locale locale, Model model) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");
		
		List<User> users = userManager.getUsers();
		model.addAttribute("Users", users);
		
		return new ModelAndView("admin");
		
	}

		
	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@Valid EditUserFormBean editUserFormBean, BindingResult result, 
								Model model, RedirectAttributes redirectAttrs) {
		
		if (result.hasErrors()) {
			return null;
		}

		List <String> messages = new ArrayList<String>() ;
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");
		
		User user = userManager.getUserByIdString(editUserFormBean.getUser_id());
		
		if(userManager.getUsersByUsername(editUserFormBean.getUserName()).size()>1)
			messages.add("Username already in use");
		if(userManager.getUsersByEmail(editUserFormBean.getEmail()).size()>1)
			messages.add("Email already in use");
		if(editUserFormBean.getUserName()==null)
			messages.add("Username cannot be blank");
		if(editUserFormBean.getPassword()==null)
			messages.add("Password cannot be blank");
		
		if(messages.size()==0){
			user.setUsername(editUserFormBean.getUserName());
			user.setFname(editUserFormBean.getFname());
			user.setLname(editUserFormBean.getLname());
			user.setEmail(editUserFormBean.getEmail());
			user.setPhone_number(editUserFormBean.getPhone());			
			
			redirectAttrs.addFlashAttribute("success", "yes");
		}
		else
			redirectAttrs.addFlashAttribute("success", "no");
	
		redirectAttrs.addFlashAttribute("userName", editUserFormBean.getUserName());
		redirectAttrs.addFlashAttribute("messages", messages);
	
		return "redirect:/admin";	
	}
	
}
