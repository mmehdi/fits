package uk.ac.abdn.fits.mvc.control.query;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uk.ac.abdn.fits.mvc.extensions.ajax.AjaxUtils;



@Controller
@RequestMapping("/choose_service")
@SessionAttributes("chooseServiceFormBean")
public class ChooseServiceController {
	
	// Invoked on every request
	private static final Logger logger = LoggerFactory.getLogger(ChooseServiceController.class);
	
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	 dateFormat.setLenient(false);
	 webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
	@ModelAttribute("chooseServiceFormBean")
	public ChooseServiceFormBean createQueryFormBean() {
		return new ChooseServiceFormBean();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form(Locale locale, Model model) {
		return new ModelAndView("query-fts");
	}
		
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView processSubmit(@Valid ChooseServiceFormBean chooseQueryFormBean, BindingResult result, 
								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
								Model model, RedirectAttributes redirectAttrs) {
		/* validate the query form*/
		if (result.hasErrors()) {
			return null;
		}
		
		if(chooseQueryFormBean.getService().equals("public")){
			return new ModelAndView("redirect:query_public");
		}else{
			return new ModelAndView("redirect:query_fts");
		}
	}
}
