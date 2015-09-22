package uk.ac.abdn.fits.mvc.control;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homepage(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		return "home";
	}
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView register(Locale locale, Model model) {
		
		return new ModelAndView("home");
	}
	
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about(Locale locale, Model model) {
		
		return new ModelAndView("about");
	}
	
	
	/*@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(Locale locale, Model model) {
		
		return new ModelAndView("admin");
	}*/
	
	
	
	
	
	
	
}
