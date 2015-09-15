package uk.ac.abdn.fits.mvc.control.form.login;


/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import uk.ac.abdn.fits.manage.UserManagement;
import uk.ac.abdn.fits.mvc.extensions.ajax.AjaxUtils;


@Controller
//@SessionAttributes("loginFormBean")
public class LoginFormController {

	// Invoked on every request
	
	private static final Logger logger = LoggerFactory.getLogger(LoginFormController.class);

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
	@ModelAttribute("loginFormBean")
	public LoginFormBean createFormBean() {
		return new LoginFormBean();
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView form(Locale locale, Model model) {
		
		return new ModelAndView("login");
		
	}

	/**
	 * deprecated 
	 * 
	@RequestMapping(value = "/j_spring_security_check", method = RequestMethod.POST)
	public String handleLogin(@Valid LoginFormBean loginFormBean, BindingResult result, 
								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
								Model model, RedirectAttributes redirectAttrs) {
		
		System.out.println("/j_spring_security_check");
		if (result.hasErrors()) {
			return null;
		}
		
		UserManagement userManagement = UserManagement.getInstance();
		
		boolean isTrue = userManagement.validateLogin(loginFormBean.getJ_username(), loginFormBean.getJ_password());
		
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = null;
		
		if(isTrue){
			
			System.out.println("login: "+ loginFormBean.getJ_username()+" granted.");
			message = "Login successfully. ";
		}else{
			System.out.println("login: "+ loginFormBean.getJ_username()+" denied.");
			message = "Login denied. ";
		}
		
		// Success response handling
		if (ajaxRequest) {
			// prepare model for rendering success message in this request
			model.addAttribute("message", message);
			return null;
		} else {
			// store a success message for rendering on the next request after redirect
			// redirect back to the form to render the success message along with newly bound values
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/login";			
		}
	}
	*/
}
