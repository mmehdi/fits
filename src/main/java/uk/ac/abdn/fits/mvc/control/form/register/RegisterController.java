package uk.ac.abdn.fits.mvc.control.form.register;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uk.ac.abdn.fits.hibernate.model.Role;
import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.model.UserRole;
import uk.ac.abdn.fits.hibernate.user.UserManager;
import uk.ac.abdn.fits.mvc.extensions.ajax.AjaxUtils;


@Controller
@RequestMapping("/register")
@SessionAttributes("registerFormBean")
public class RegisterController {

	// Invoked on every request
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
	@ModelAttribute("registerFormBean")
	public RegisterFormBean createFormBean() {
		return new RegisterFormBean();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form(Locale locale, Model model) {
		
		return new ModelAndView("register");
		
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@Valid RegisterFormBean registerFormBean, BindingResult result, 
								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
								Model model, RedirectAttributes redirectAttrs) {
		
		if (result.hasErrors()) {
			return null;
		}
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = "Form submitted successfully. " ;
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");
		
		User user = new User();
		user.setUsername(registerFormBean.getUserName());
		user.setFname(registerFormBean.getFname());
		user.setLname(registerFormBean.getLname());
		user.setEmail(registerFormBean.getEmail());
		user.setPhone_number(registerFormBean.getPhone());
		if(registerFormBean.getPassword() !=null && registerFormBean.getPasswordRepeat() != null
				&& registerFormBean.getPassword().equals(registerFormBean.getPasswordRepeat())){
			user.setPassword(registerFormBean.getPassword());
		}else{
			message = "Register Error: the two passwords are not the same.";
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/register";	
		}
		user.setEnabled(true);
//		Role role = new Role();
//		role.setId(3);
//		role.setRole("user");
//		user.setRole(role);
		userManager.insertUser(user);
		//System.out.println("user "+ user.getUsername() +" is created.");
		user = userManager.getUser(registerFormBean.getUserName());
		UserRole userRole = new UserRole();
		userRole.setUser_id(user.getId());
		userRole.setRole_id(registerFormBean.getUser_role());
		userManager.insertUserRole(userRole);
		//System.out.println("user role is created.");
		
		
		
		// Success response handling
		if (ajaxRequest) {
			// prepare model for rendering success message in this request
			model.addAttribute("message", message);
			return null;
		} else {
			
			if(user.getFname()!=null && user.getLname()!=null){
				redirectAttrs.addFlashAttribute("name", user.getFname()+" "+ user.getLname());
			}else if(user.getFname()!=null){
				redirectAttrs.addFlashAttribute("name", user.getFname());
			}else{
				redirectAttrs.addFlashAttribute("name", "Customer");
			}
			redirectAttrs.addFlashAttribute("suc", "suc");
			redirectAttrs.addFlashAttribute("username", user.getUsername());
//			model.addAttribute("suc", "suc");
//			model.addAttribute("username", user.getUsername());
			return "redirect:/register";		
			
			// store a success message for rendering on the next request after redirect
			// redirect back to the form to render the success message along with newly bound values
//			redirectAttrs.addFlashAttribute("message", message);
//			return "redirect:/register";			
		}
	}
	
}
