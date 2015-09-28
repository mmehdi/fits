package uk.ac.abdn.fits.mvc.control.query;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;





import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

import uk.ac.abdn.fits.business.client.OriginalRequest;
import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;
import uk.ac.abdn.fits.business.ws.FITSWebServiceBroker;
import uk.ac.abdn.fits.hibernate.dao.QueryLogDAO;
import uk.ac.abdn.fits.hibernate.model.OtherEligTable;
import uk.ac.abdn.fits.hibernate.model.QueryLog;
import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.operator.OperatorDataManager;
import uk.ac.abdn.fits.hibernate.user.UserManager;
import uk.ac.abdn.fits.mvc.extensions.ajax.AjaxUtils;
import uk.ac.abdn.fits.query.operator.PassengerAge;
import uk.ac.abdn.fits.query.operator.PassengerJourneyPurpose;
import uk.ac.abdn.fits.query.operator.PassengerMobilityStatus;



@Controller
@RequestMapping("/query_fts")
//@SessionAttributes("ftsQueryFormBean")
@SessionAttributes({"fname","lname","email","options", "relaxed_options", "date_of_travel", "origin_postcode", 
	"origin_postcode_rtn", "options_rtn", "relaxed_options_rtn","date_of_travel_rtn"})
public class FtsQueryController {
	
	// Invoked on every request
	private static final Logger logger = LoggerFactory.getLogger(FtsQueryController.class);
	public static final int NUM_OF_PREFERED_OPTION = 3;
	public static int count = 0;
	private FITSWebServiceBroker fits_webservice_broker = new FITSWebServiceBroker();
	
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}
	
	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
	@ModelAttribute("ftsQueryFormBean")
	public FtsQueryFormBean createQueryFormBean() {
		return new FtsQueryFormBean();
	}
		
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	 dateFormat.setLenient(false);
	 webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }

	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form(Locale locale, Model model) {
		
		model.addAttribute("command", new FtsQueryFormBean());
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		OperatorDataManager operatorDataManager = 
		        (OperatorDataManager) ctx.getBean("operatorDataManagerImpl");
		List<OtherEligTable> other_eligs = operatorDataManager.getOperatorOtherEligs();
		List<String> otherIssues = new ArrayList<String>();
		for(OtherEligTable elig: other_eligs){
			otherIssues.add(elig.getElig());
		}
		model.addAttribute("other_issues", otherIssues);
		return new ModelAndView("query-fts");
	}
		
	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(
			@Valid
			FtsQueryFormBean queryFormBean, 
			BindingResult result, HttpServletRequest httpRequest,
			@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
			Principal principal,
			HttpSession session,
			Model model, RedirectAttributes redirectAttrs) {
		/* validate the query form*/
		if (result.hasErrors()) {
			System.err.println(result.getFieldError());
			return "query-fts";
		}
		/* create the command object for this query */
		QueryCommand query = createQuery(queryFormBean);
		/* search for the options with the fits broker */
		List<TOption> options = fits_webservice_broker.query(query);
		/* process the travel of day, origin postcode, and caption which will be shown in the resulting page*/
		Calendar travel_of_day = null;
		if(query.getOriginalRequest().getDepartureTime()!= null){
			travel_of_day = query.getOriginalRequest().getDepartureTime();
		}else{
			travel_of_day = query.getOriginalRequest().getArriveTime();
		}
		DateFormat df = new SimpleDateFormat("MMM dd, yyyy - hh:mm");
		String formattedDate = df.format(travel_of_day.getTime()); 
		model.addAttribute("date_of_travel", formattedDate);
		
		formattedDate = df.format(getTimeReturn(queryFormBean).getTime());
		model.addAttribute("date_of_travel_rtn", formattedDate);

		
		/* the origin postcode will be used to show the taxi service info */
		if(queryFormBean.getPostal_code_f_outward() != null){
			model.addAttribute("origin_postcode", queryFormBean.getPostal_code_f_outward().replaceAll("\\s", ""));
		}
		model.addAttribute("caption", "Transport options ranked using preferences" );
		/* process the email address which will be picked up when user click 'email this page' */
		String email = (String)session.getAttribute("email");
		if(email != null){
			model.addAttribute("email", email);
		}else if(queryFormBean.getEmail()!=null && queryFormBean.getEmail().trim().length()>0){
			if(queryFormBean.getEmail()!=null){
				model.addAttribute("email", queryFormBean.getEmail());
			}
		}else{
			String name = principal.getName();
			ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
			UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");
			User user = userManager.getUser(name);
			if(user!= null && user.getEmail()!=null){
				model.addAttribute("email", user.getEmail());
			}
		}
		
		List<TOption> relaxed_options = new ArrayList<TOption>();
		List<TOption> not_relaxed = new ArrayList<TOption>();
		for(TOption option: options){
			if(option.isRelaxed()){
				relaxed_options.add(option);
				continue;
			}
			not_relaxed.add(option);
		}
		model.addAttribute("options", not_relaxed);
		model.addAttribute("relaxed_options", relaxed_options);
		
		/* process return journey */ 
		if(queryFormBean.isReturn_jrny_required()){
			/* create the command object for the return journey*/
			query = createQueryReturnJourney(queryFormBean);
			options = fits_webservice_broker.query(query);
			/* process the postcode for the return journey in the taxi service */
			if(queryFormBean.getPostal_code_f_return() != null){
				model.addAttribute("origin_postcode_rtn", queryFormBean.getPostal_code_f_return().replaceAll("\\s", ""));
			}
			relaxed_options = new ArrayList<TOption>();
			not_relaxed = new ArrayList<TOption>();
			for(TOption option: options){
				if(option.isRelaxed()){
					relaxed_options.add(option);
					continue;
				}
				not_relaxed.add(option);
			}
			model.addAttribute("options_rtn", not_relaxed);
			model.addAttribute("relaxed_options_rtn", relaxed_options);
		}
		model.addAttribute("fname", queryFormBean.getFirst_name());
		model.addAttribute("lname", queryFormBean.getLast_name());
		count++;
		return "matching";
	}
	
	private QueryCommand createQuery(FtsQueryFormBean queryFormBean){
		
		//store query log
		storeQueryLog(queryFormBean, false);
		
		QueryCommand query = null;
		OriginalRequest request = new OriginalRequest();
		long logged_time = Calendar.getInstance().getTimeInMillis();
		request.setId("r"+ logged_time);
		request.setLoggedTime(logged_time);
		request.setOriginAddress(getOriginAddr(queryFormBean));
		request.setDestnAddress(getDestnAddr(queryFormBean));
		if(queryFormBean.getTime_constraint().equals("depart_after")){
			request.setDepartureTime(getTime(queryFormBean));
		}else{ // arrive_by
			request.setArriveTime(getTime(queryFormBean));
		}
		request.setAgeGroup(getAgeGroup(queryFormBean));
		request.setMobilityStatus(getMobilityStatus(queryFormBean));
		request.setPurpose(getJourneyPurpose(queryFormBean));
		request.setOtherEligIssue(queryFormBean.getOther_issue());
		request.setHasReturn(queryFormBean.isReturn_jrny_required());
		String[] preference = new String[NUM_OF_PREFERED_OPTION];
		PreferedOption[] preference_enum = parsePreferedOptions(queryFormBean);
		for(int i=0; i< NUM_OF_PREFERED_OPTION; i++){
			preference[i] = PreferedOption.toString(preference_enum[i]);
		}
		request.setPreference(preference);
		query = new QueryCommand(fits_webservice_broker, request);
		

		
		return query;
	}
	
	private QueryCommand createQueryReturnJourney(FtsQueryFormBean queryFormBean){
		QueryCommand query = null;
		OriginalRequest request = new OriginalRequest();
		long logged_time = Calendar.getInstance().getTimeInMillis();
		request.setId("r"+ logged_time);
		request.setLoggedTime(logged_time);
		request.setOriginAddress(getOriginAddrReturn(queryFormBean));
		request.setDestnAddress(getDestnAddrReturn(queryFormBean));
		request.setDepartureTime(getTimeReturn(queryFormBean)); // only depart after in return journey
		request.setAgeGroup(getAgeGroup(queryFormBean));
		request.setMobilityStatus(getMobilityStatus(queryFormBean));
		request.setPurpose(getJourneyPurpose(queryFormBean));
		request.setOtherEligIssue(queryFormBean.getOther_issue());
		String[] preference = new String[NUM_OF_PREFERED_OPTION];
		PreferedOption[] preference_enum = parsePreferedOptions(queryFormBean);
		for(int i=0; i< NUM_OF_PREFERED_OPTION; i++){
			preference[i] = PreferedOption.toString(preference_enum[i]);
		}
		request.setPreference(preference);
		query = new QueryCommand(fits_webservice_broker, request);
		
		//store query log
		storeQueryLog(queryFormBean, true);
		
		return query;
	}
	
	/*
	 * stores the FITS query into database table query_log for reporting
	 */
	private void storeQueryLog(FtsQueryFormBean queryFormBean, boolean is_return){
		
		try{
			QueryLog query_log = new QueryLog();
			
			query_log.setIs_return(is_return);
			
			if(is_return){
				query_log.setFrom_address((getOriginAddrReturn(queryFormBean)).replace("+", " "));
				query_log.setTo_address((getDestnAddrReturn(queryFormBean)).replace("+", " "));
				query_log.setFrom_postcode(queryFormBean.getPostal_code_f_return());
				query_log.setTo_postcode(queryFormBean.getPostal_code_t_return());
				query_log.setTimestamp(new java.sql.Timestamp(getTimeReturn(queryFormBean).getTimeInMillis()- (60 * 60 * 1000)));
			}
			else{
				query_log.setFrom_address((getOriginAddr(queryFormBean)).replace("+", " "));
				query_log.setTo_address((getDestnAddr(queryFormBean)).replace("+", " "));
				query_log.setFrom_postcode(queryFormBean.getPostal_code_f_outward());
				query_log.setTo_postcode(queryFormBean.getPostal_code_t_outward());
				query_log.setTimestamp(new java.sql.Timestamp(getTime(queryFormBean).getTimeInMillis()- (60 * 60 * 1000)));

			}
			query_log.setMobility_status(queryFormBean.getMobility_status());
			query_log.setAge_group(queryFormBean.getAge_group());
			query_log.setPurpose(queryFormBean.getJourney_purpose());
			
			
			ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
			QueryLogDAO queryLogDAO = (QueryLogDAO) ctx.getBean("QueryLogDAO");
			
			queryLogDAO.insertQueryLog(query_log);

		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		//query_log.setTimestamp(new Timestamp());
		
	}
	private String getOriginAddr(FtsQueryFormBean queryFormBean){
		
		StringBuilder sb = new StringBuilder();
		if(queryFormBean.getStreet_number_f_outward()!= null && queryFormBean.getStreet_number_f_outward().trim().length()>0){
			sb.append(queryFormBean.getStreet_number_f_outward());
			sb.append("+");
		}
		if(queryFormBean.getRoute_f_outward() != null && queryFormBean.getRoute_f_outward().trim().length()>0){
			sb.append(queryFormBean.getRoute_f_outward());
			sb.append("+");
		}
		if(queryFormBean.getPostal_code_f_outward() != null && queryFormBean.getPostal_code_f_outward().trim().length()>0){
			sb.append(queryFormBean.getPostal_code_f_outward());
			sb.append("+");
		}
		if(queryFormBean.getLocality_f_outward() != null && queryFormBean.getLocality_f_outward().trim().length()>0){
			sb.append(queryFormBean.getLocality_f_outward());
			sb.append("+");
		}
		if(queryFormBean.getCountry_f_outward() != null && queryFormBean.getCountry_f_outward().trim().length()>0){
			sb.append(queryFormBean.getCountry_f_outward());
		}
		return sb.toString().replaceAll(",", "%2C").replaceAll(" ", "+");
	}
	
	private String getDestnAddr(FtsQueryFormBean queryFormBean){
		
		StringBuilder sb = new StringBuilder();
		if(queryFormBean.getStreet_number_t_outward()!= null && queryFormBean.getStreet_number_t_outward().trim().length()>0){
			sb.append(queryFormBean.getStreet_number_t_outward());
			sb.append("+");
		}
		if(queryFormBean.getRoute_t_outward() != null && queryFormBean.getRoute_t_outward().trim().length()>0){
			sb.append(queryFormBean.getRoute_t_outward());
			sb.append("+");
		}
		if(queryFormBean.getPostal_code_t_outward() != null && queryFormBean.getPostal_code_t_outward().trim().length()>0){
			sb.append(queryFormBean.getPostal_code_t_outward());
			sb.append("+");
		}
		if(queryFormBean.getLocality_t_outward() != null && queryFormBean.getLocality_t_outward().trim().length()>0){
			sb.append(queryFormBean.getLocality_t_outward());
			sb.append("+");
		}
		if(queryFormBean.getCountry_t_outward() != null && queryFormBean.getCountry_t_outward().trim().length()>0){
			sb.append(queryFormBean.getCountry_t_outward());
		}
		return sb.toString().replaceAll(",", "%2C").replaceAll(" ", "+");
	}
	
	private String getOriginAddrReturn(FtsQueryFormBean queryFormBean){
		
		StringBuilder sb = new StringBuilder();
		if(queryFormBean.getStreet_number_f_return()!= null && queryFormBean.getStreet_number_f_return().trim().length()>0){
			sb.append(queryFormBean.getStreet_number_f_return());
			sb.append("+");
		}
		if(queryFormBean.getRoute_f_return() != null && queryFormBean.getRoute_f_return().trim().length()>0){
			sb.append(queryFormBean.getRoute_f_return());
			sb.append("+");
		}
		if(queryFormBean.getPostal_code_f_return() != null && queryFormBean.getPostal_code_f_return().trim().length()>0){
			sb.append(queryFormBean.getPostal_code_f_return());
			sb.append("+");
		}
		if(queryFormBean.getLocality_f_outward() != null && queryFormBean.getLocality_f_outward().trim().length()>0){
			sb.append(queryFormBean.getLocality_f_return());
			sb.append("+");
		}
		if(queryFormBean.getCountry_f_outward() != null && queryFormBean.getCountry_f_outward().trim().length()>0 ){
			sb.append(queryFormBean.getCountry_f_return());
		}
		return sb.toString().replaceAll(",", "%2C").replaceAll(" ", "+");
	}
	
	private String getDestnAddrReturn(FtsQueryFormBean queryFormBean){
		
		StringBuilder sb = new StringBuilder();
		if(queryFormBean.getStreet_number_t_return()!= null && queryFormBean.getStreet_number_t_return().trim().length()>0 ){
			sb.append(queryFormBean.getStreet_number_t_return());
			sb.append("+");
		}
		if(queryFormBean.getRoute_t_return() != null && queryFormBean.getRoute_t_return().trim().length()>0 ){
			sb.append(queryFormBean.getRoute_t_return());
			sb.append("+");
		}
		if(queryFormBean.getPostal_code_t_return() != null && queryFormBean.getPostal_code_t_return().trim().length()>0 ){
			sb.append(queryFormBean.getPostal_code_t_return());
			sb.append("+");
		}
		if(queryFormBean.getLocality_t_return() != null && queryFormBean.getLocality_t_return().trim().length()>0 ){
			sb.append(queryFormBean.getLocality_t_return());
			sb.append("+");
		}
		if(queryFormBean.getCountry_t_return() != null && queryFormBean.getCountry_t_return().trim().length()>0 ){
			sb.append(queryFormBean.getCountry_t_return());
		}
		return sb.toString().replaceAll(",", "%2C").replaceAll(" ", "+");
	}
	
	private Calendar getTime(FtsQueryFormBean queryFormBean){
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.UK);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String str = queryFormBean.getDatepicker()+" "+queryFormBean.getTimepicker();
		try {
			c.setTime(dateFormat.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		System.out.println("time picker - retrieve calendar "+c.getTime().toGMTString());
		return c;
		
	}
	
	private Calendar getTimeReturn(FtsQueryFormBean queryFormBean){
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.UK);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String str = queryFormBean.getDatepicker_rtn()+" "+queryFormBean.getTimepicker_rtn();
		try {
			c.setTime(dateFormat.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		System.out.println("time picker return retrieve calendar return journey: "+c.getTime().toGMTString());
		return c;
	}
	
	
	private PassengerAge getAgeGroup(FtsQueryFormBean queryFormBean){
		String value = queryFormBean.getAge_group();
		if(value.equals("under 16")){
			return PassengerAge.valueOf(0);
		}else if(value.equals("16 to 21")){
			return PassengerAge.valueOf(1);
		}else if(value.equals("22 to 54")){
			return PassengerAge.valueOf(2);
		}else if(value.equals("55 to 59")){
			return PassengerAge.valueOf(3);
		}else if(value.equals("60 and above")){
			return PassengerAge.valueOf(4);
		}
		return null;
	}
	
	
	private PassengerMobilityStatus getMobilityStatus(FtsQueryFormBean queryFormBean){
		String value = queryFormBean.getMobility_status();
		if(value.equals("Able bodied")){
			return PassengerMobilityStatus.valueOf(0);
		}else if(value.equals("Disabled - wheelchair user")){
			return PassengerMobilityStatus.valueOf(1);
		}else if(value.equals("Disabled - other")){
			return PassengerMobilityStatus.valueOf(2);
		}else if(value.equals("Mobility impaired - unable to use regular PT")){
			return PassengerMobilityStatus.valueOf(3);
		}
		return null;
	}
	
	private PassengerJourneyPurpose getJourneyPurpose(FtsQueryFormBean queryFormBean){
		String value = queryFormBean.getJourney_purpose();
		if(value.equals("Health Appointment")){
			return PassengerJourneyPurpose.valueOf(0);
		}else if(value.equals("Shopping")){
			return PassengerJourneyPurpose.valueOf(1);
		}else if(value.equals("Social/Leisure")){
			return PassengerJourneyPurpose.valueOf(2);
		}else if(value.equals("School/Education")){
			return PassengerJourneyPurpose.valueOf(3);
		}else if(value.equals("Work/Commuting")){
			return PassengerJourneyPurpose.valueOf(3);
		}else if(value.equals("Other")){
			return PassengerJourneyPurpose.valueOf(3);
		}
		return null;
	}
	
	private int getHour(FtsQueryFormBean queryFormBean){
		
		String time = queryFormBean.getTimepicker();
		StringTokenizer st = null;
		String nextToken = null;
		if(time.endsWith("pm")){
			st = new StringTokenizer(time.replaceFirst("pm", ""), ":");
			nextToken = st.nextToken();
			if(nextToken.equals("12")){
				return new Integer(nextToken);
			}else{
				return 12 + new Integer(nextToken); 
			}
		}else {
			st = new StringTokenizer(time.replaceFirst("am", ""), ":");
			return new Integer(st.nextToken());
		}
	}
	
	private int getMin(FtsQueryFormBean queryFormBean){
		
		String time = queryFormBean.getTimepicker();
		StringTokenizer st = null;
		if(time.endsWith("pm")){
			st = new StringTokenizer(time.replaceFirst("pm", ""), ":");
			st.nextToken();
			return new Integer(st.nextToken()); 
		}else {
			st = new StringTokenizer(time.replaceFirst("am", ""), ":");
			st.nextToken();
			return new Integer(st.nextToken());
		}
	}
	
	public PreferedOption[] parsePreferedOptions(FtsQueryFormBean queryFormBean){
		
		String s = queryFormBean.getPrefered_options();
		PreferedOption[] preference = new PreferedOption[NUM_OF_PREFERED_OPTION];
		s = s.replaceAll("ranking\\[\\]=", "");
		StringTokenizer st = new StringTokenizer(s.trim(), "&");
		int value = -1;
		for(int i=0; i< NUM_OF_PREFERED_OPTION; i++){
			value = new Integer(st.nextToken().trim());
			preference[i] = PreferedOption.valueOf(value);
		}
		return preference;
	}
	
	
}
