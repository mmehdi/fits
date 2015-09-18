package uk.ac.abdn.fits.mvc.control.admin;

/**
 * @author Mujtaba Mehdi, University of Aberdeen
 * 
 */

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uk.ac.abdn.fits.hibernate.model.OtherEligTable;
import uk.ac.abdn.fits.hibernate.model.Role;
import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.model.UserRole;
import uk.ac.abdn.fits.hibernate.user.UserManager;
import uk.ac.abdn.fits.mvc.control.form.register.RegisterFormBean;
import uk.ac.abdn.fits.mvc.extensions.ajax.AjaxUtils;


@Controller
@RequestMapping("/reports")
//@SessionAttributes("registerFormBean")
public class AdminReportingController {

	// Invoked on every request
	
	private static final Logger logger = LoggerFactory.getLogger(AdminReportingController.class);

	@RequestMapping(method=RequestMethod.GET)
	//public ModelAndView form(@PathVariable(value="start") String start, @PathVariable(value="end") String end, Locale locale, Model model) {
	public ModelAndView form(Locale locale, Model model) {
		
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		//UserManager userManager = (UserManager) ctx.getBean("QueryDAOImpl");
		
		return new ModelAndView("reports");
		
	}
}
