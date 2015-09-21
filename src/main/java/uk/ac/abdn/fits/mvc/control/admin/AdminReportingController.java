package uk.ac.abdn.fits.mvc.control.admin;

import java.math.BigInteger;

/**
 * @author Mujtaba Mehdi, University of Aberdeen
 * 
 */

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import uk.ac.abdn.fits.hibernate.dao.QueryLogDAO;
import uk.ac.abdn.fits.hibernate.dao.impl.QueryLogDAOImpl;
import uk.ac.abdn.fits.hibernate.model.OtherEligTable;
import uk.ac.abdn.fits.hibernate.model.QueryLog;
import uk.ac.abdn.fits.hibernate.model.QueryLogGroupedDTO;
import uk.ac.abdn.fits.hibernate.model.Role;
import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.model.UserRole;
import uk.ac.abdn.fits.hibernate.user.UserManager;
import uk.ac.abdn.fits.mvc.control.form.register.RegisterFormBean;
import uk.ac.abdn.fits.mvc.extensions.ajax.AjaxUtils;


@Controller
//@SessionAttributes("registerFormBean")
public class AdminReportingController {

	// Invoked on every request
	
	private static final Logger logger = LoggerFactory.getLogger(AdminReportingController.class);

	@RequestMapping(value="/reportsss",method=RequestMethod.GET)
	//public ModelAndView form(@PathVariable(value="start") String start, @PathVariable(value="end") String end, Locale locale, Model model) {
	public ModelAndView form(Locale locale, Model model) {
		
		List<QueryLogGroupedDTO> mobility_status_data = generateGroupedData(null, null, null);
		
		model.addAttribute("all_data", mobility_status_data);
		return new ModelAndView("reports");
		
	}
	
	@RequestMapping(value="/reports",method=RequestMethod.GET) 
	public ModelAndView form(Locale locale, Model model,HttpServletRequest request) {
		
		String startDate = request.getParameter("start");
		String endDate = request.getParameter("end");
		
		System.out.println("start date: "+startDate);
		List<QueryLogGroupedDTO> mobility_status_grouped_data = generateGroupedData("mobility_status",startDate,endDate);
		List<QueryLogGroupedDTO> age_grouped_data = generateGroupedData("age_group",startDate,endDate);
		List<QueryLogGroupedDTO> purpose_data = generateGroupedData("purpose",startDate,endDate);

		model.addAttribute("mobility_data", mobility_status_grouped_data);
		model.addAttribute("age_group_data", age_grouped_data);	
		model.addAttribute("purpose_data", purpose_data);	

		return new ModelAndView("reports");
		
	}
	
	
	private List<QueryLogGroupedDTO> generateGroupedData(String dataType, String startDate, String endDate){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		QueryLogDAO queryLogDAOImpl = (QueryLogDAO) ctx.getBean("QueryLogDAO");
		List<QueryLogGroupedDTO> query_log = null;
		//List<Map<String, Long>> query_log = new ArrayList<QueryLogGroupedDTO>();
		switch (dataType){
			case "mobility_status":
				query_log = queryLogDAOImpl.getMobilityStatusByDate(startDate,endDate);
				break;
			case "age_group":
				query_log = queryLogDAOImpl.getAgeGroupByDate(startDate,endDate);
				break;
			case "purpose":
				query_log = queryLogDAOImpl.getPurposeByDate(startDate,endDate);
				break;
			default:
				break;
		}
		
		return query_log;
	}
}
