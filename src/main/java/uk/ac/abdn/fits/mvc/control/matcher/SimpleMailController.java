package uk.ac.abdn.fits.mvc.control.matcher;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */



import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.abdn.fits.business.client.option.TOption;


@Controller
@RequestMapping("/sendmail")
public class SimpleMailController {
	
	// Invoked on every request
	private static final Logger logger = LoggerFactory.getLogger(SimpleMailController.class);
	
	
//	@ModelAttribute
//	public void ajaxAttribute(WebRequest request, Model model) {
//		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
//	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
//	@ModelAttribute("queryFormBean")
//	public QueryFormBean createQueryFormBean() {
//		return new QueryFormBean();
//	}
	
	
		
	
	@RequestMapping(method=RequestMethod.POST)
	public void processMailRequest(HttpSession session
//								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
//								Model model, 
//								RedirectAttributes redirectAttrs
								) {
		
		System.out.println("a request to send an email is received.");
		
		System.out.println("*** Session data ***");
		Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()){
			String s = e.nextElement();
			System.out.println(s);
			System.out.println("**" + session.getAttribute(s));
		}

		System.out.println("*** print the options ***");
		List<TOption> not_relaxed = (List<TOption>)session.getAttribute("options");

		for(int i=0;i<not_relaxed.size();i++){
			System.out.println(not_relaxed.get(i).getSourceType());
		}
		
		
//		return "";
	}

	
	public static void main(String[] args){
		ApplicationContext context = 
    			new ClassPathXmlApplicationContext("file:D:\\workspaces\\github\\dot.r\\workspace\\fits\\ke\\src\\main\\webapp\\WEB-INF\\spring\\appServlet\\javamail.xml");
    	
		
		
	}
}
