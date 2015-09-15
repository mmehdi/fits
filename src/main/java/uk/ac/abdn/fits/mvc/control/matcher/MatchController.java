package uk.ac.abdn.fits.mvc.control.matcher;

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

import uk.ac.abdn.fits.mvc.control.form.register.RegisterFormBean;
import uk.ac.abdn.fits.mvc.extensions.ajax.AjaxUtils;


@Controller
@RequestMapping("/match")
public class MatchController {
	
	// Invoked on every request
	private static final Logger logger = LoggerFactory.getLogger(MatchController.class);
	
	
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form(Locale locale, Model model) {
		
		return new ModelAndView("matching");
		
	}
		
	
	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@Valid RegisterFormBean registerFormBean, BindingResult result, 
								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
								Model model, RedirectAttributes redirectAttrs) {
		
		return "";
	}

}
