package uk.ac.abdn.fits.mvc.control.query;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

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



@Controller
@RequestMapping("/query_public")
@SessionAttributes("publicQueryFormBean")
public class PublicQueryController {
//	
//	// Invoked on every request
//	private static final Logger logger = LoggerFactory.getLogger(PublicQueryController.class);
//	
//	private QueryManagement query_management = QueryManagement.getInstance();
//	
//	
//	@ModelAttribute
//	public void ajaxAttribute(WebRequest request, Model model) {
//		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
//	}
//	
//	@InitBinder
//	public void initBinder(WebDataBinder webDataBinder) {
//	 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//	 dateFormat.setLenient(false);
//	 webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//	 }
//
//	// Invoked initially to create the "form" attribute
//	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
//	@ModelAttribute("publicQueryFormBean")
//	public PublicQueryFormBean createQueryFormBean() {
//		return new PublicQueryFormBean();
//	}
//	
//	@RequestMapping(method=RequestMethod.GET)
//	public ModelAndView form(Locale locale, Model model) {
//		
//		return new ModelAndView("query-public");
//		
//	}
//		
//	@RequestMapping(method=RequestMethod.POST)
//	public String processSubmit(@Valid PublicQueryFormBean publicQueryFormBean, BindingResult result, 
//								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
//								Model model, RedirectAttributes redirectAttrs) {
//		
//		/* validate the query form*/
//		if (result.hasErrors()) {
//			return null;
//		}
//		// Typically you would save to a db and clear the "form" attribute from the session 
//		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
//		String message = "Form submitted successfully.  Bound " + publicQueryFormBean;
//		String origin_address, destn_address;
//		Calendar calendar;
//		
//		origin_address = extractOriginAddr(publicQueryFormBean);
//		
//		destn_address = extractDestinationAddr(publicQueryFormBean);
//		
//		calendar = getCalendar(publicQueryFormBean);
//		
//		List<DirectionsResponse> responses = query(origin_address, destn_address, calendar);
//		String tbody = formatDirectionsResponse(responses);
//		model.addAttribute("caption", "Public tranport" );
//		model.addAttribute("tbody", tbody );
//		
//		return "matching";
//	}
//	
//	
//	private List<DirectionsResponse> query(String origin_address, String destn_address, Calendar calendar){
//		return query_management.query(origin_address, destn_address, calendar);
//	}
//
//	
//	private String formatDirectionsResponse(List<DirectionsResponse> responses){
//		
//		
//		StringBuilder sb = new StringBuilder();
//		String travelInfo = null;
//		
//		int maxwidth = 625;
//		
////		double[] similarity = rescale_similarity(responses);
////		
////		double maxval = similarity[0];
//		
//		int width = 0;
//		
//		DecimalFormat df = new DecimalFormat("0.00");
//		
//		for(int i=0; i< responses.size() && i < 5; i++){
//			
//			if(responses.get(i).getStatus().equals("OK")){
//				
//				width = 0;
//			
////				if(responses.get(i).getSourceType().equals("google_transit")){
//					
//					travelInfo = convertGoogleTransitInfo(responses.get(i).getRoutes()[0].getLegs()[0]);
//					
//					sb.append("<tr>");
//					sb.append("<td class=\"first\">");
//					sb.append("<a class=\"info\" href=\"#\" title=\""+"Public service that is open to the public, and operates with fixed schedules and routes. The source is from Google Transit."+"\">"+"Public service"+"</a>");
//					sb.append("</td>");
//					sb.append("<td class=\"value first\"><img src=\"/abdn/resources/img/bar.png\" alt=\"\" height=\"16\" width=\""+"100%"+"\">"
//			        				+ "<br>" 
//			        				+ "<div class=\"terms\">"+travelInfo+"</div>"
//			        			+ "</td>");
//					sb.append("</tr>");
//					sb.append("\n");
////				}
//			}
//		}
//		
//		return sb.toString();
//	}
//	
//	private String convertGoogleTransitInfo(Leg leg){
//		
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append("<table cellspacing=\"0\" cellpadding=\"0\" id=\"oft\">");
//		sb.append("<thead>");
//		sb.append("<tr>");
//		sb.append("<th class=\"dep\" width=\"10%\"><abbr title=\"Departs\">Dep.</abbr></th>");
//		sb.append("<th class=\"from\">From</th>");
//		sb.append("<th class=\"to\">To</th>");
//		sb.append("<th class=\"arr\" width=\"10%\"><abbr title=\"Arrives\">Arr.</abbr></th>");
//		sb.append("<th class=\"dur\" width=\"9%\"><abbr title=\"Duration\">Dur.</abbr></th>");
//		sb.append("<th class=\"chg\" width=\"6%\"><abbr title=\"Changes\">Chg.</abbr></th>");
//		sb.append("<th class=\"chg\" width=\"10%\">Fare</th>");
//		sb.append("</tr>");
//		sb.append("</thead>");
//		sb.append("<tbody>");
//		sb.append("<td class=\"dep\">");
//		sb.append(leg.getDeparture_time().getText());
//		sb.append("</td>");
//		sb.append("<td class=\"from\">");
//		sb.append(leg.getStart_address());
//		sb.append("</td>");
//		sb.append("<td class=\"to\">");
//		sb.append(leg.getEnd_address());
//		sb.append("</td>"); 
//		sb.append("<td class=\"arr\">");
//		sb.append(leg.getArrival_time().getText());
//		sb.append("</td>");
//		sb.append("<td class=\"dur\">");
//		sb.append(leg.getDuration().getText());
//		sb.append("</td>");
//		sb.append("<td class=\"chg\">");
//		sb.append("<a class=\"show-option\" href=\"#\" title=\"\">");
//		sb.append(leg.getSteps().length -1);
//		sb.append("</a>");
//		sb.append("</td>");
//		sb.append("<td class=\"fare\">");
//		sb.append("Standard bus fares");
//		sb.append("</td>");
//		sb.append("</tbody>");
//		sb.append("</table>");
//		
//		return sb.toString();
//	}
//	
//	private String extractOriginAddr(PublicQueryFormBean queryFormBean){
//		
//		StringBuilder sb = new StringBuilder();
//		
//		if(queryFormBean.getStreet_number_f()!= null){
//			sb.append(queryFormBean.getStreet_number_f());
//			sb.append("+");
//		}
//		if(queryFormBean.getRoute_f() != null){
//			sb.append(queryFormBean.getRoute_f());
//			sb.append("+");
//		}
//		if(queryFormBean.getPostal_code_f() != null){
//			sb.append(queryFormBean.getPostal_code_f());
//			sb.append("+");
//		}
//		if(queryFormBean.getLocality_f() != null){
//			sb.append(queryFormBean.getLocality_f());
//			sb.append("+");
//		}
//		if(queryFormBean.getCountry_f() != null){
//			sb.append(queryFormBean.getCountry_f());
//		}
//		
//		return sb.toString().replaceAll(",", "%2C").replaceAll(" ", "+");
//		
//	}
//	
//	private String extractDestinationAddr(PublicQueryFormBean queryFormBean){
//		
//		StringBuilder sb = new StringBuilder();
//		
//		if(queryFormBean.getStreet_number_t()!= null){
//			sb.append(queryFormBean.getStreet_number_t());
//			sb.append("+");
//		}
//		if(queryFormBean.getRoute_t() != null){
//			sb.append(queryFormBean.getRoute_t());
//			sb.append("+");
//		}
//		if(queryFormBean.getPostal_code_t() != null){
//			sb.append(queryFormBean.getPostal_code_t());
//			sb.append("+");
//		}
//		if(queryFormBean.getLocality_t() != null){
//			sb.append(queryFormBean.getLocality_t());
//			sb.append("+");
//		}
//		if(queryFormBean.getCountry_t() != null){
//			sb.append(queryFormBean.getCountry_t());
//		}
//		
//		return sb.toString().replaceAll(",", "%2C").replaceAll(" ", "+");
//		
//	}
//	
//	private Calendar getCalendar(PublicQueryFormBean queryFormBean){
//		
//		Calendar c = Calendar.getInstance();
//		
//		String year, month, day;
//		
//		StringTokenizer st = new StringTokenizer(queryFormBean.getDatepicker(), "/");
//		
//		month = st.nextToken();
//		day = st.nextToken();
//		year = st.nextToken();
//		
//		c.set(Calendar.YEAR, new Integer(year));
//		c.set(Calendar.MONTH, new Integer(month)-1);
//		c.set(Calendar.DAY_OF_MONTH, new Integer(day));
//		c.set(Calendar.HOUR_OF_DAY, getHour(queryFormBean));
//		c.set(Calendar.MINUTE, getMin(queryFormBean));
//		c.set(Calendar.SECOND, 0);
//		c.set(Calendar.MILLISECOND, 0);
//		
////		System.out.println("retrieve calendar "+c.getTime().toGMTString());
//		return c;
//		
//	}
//	
//	private int getHour(PublicQueryFormBean queryFormBean){
//		
//		String time = queryFormBean.getTimepicker();
//		StringTokenizer st = null;
//		
//		String nextToken = null;
//		if(time.endsWith("pm")){
//			
//			st = new StringTokenizer(time.replaceFirst("pm", ""), ":");
//			nextToken = st.nextToken();
//			
//			if(nextToken.equals("12")){
//				return new Integer(nextToken);
//			}else{
//				return 12 + new Integer(nextToken); 
//			}
//		}else {
//			
//			st = new StringTokenizer(time.replaceFirst("am", ""), ":");
//			return new Integer(st.nextToken());
//		}
//		
//	}
//	
//	private int getMin(PublicQueryFormBean queryFormBean){
//		
//		String time = queryFormBean.getTimepicker();
//		StringTokenizer st = null;
//		
//		if(time.endsWith("pm")){
//			
//			st = new StringTokenizer(time.replaceFirst("pm", ""), ":");
//			st.nextToken();
//			return new Integer(st.nextToken()); 
//		}else {
//			st = new StringTokenizer(time.replaceFirst("am", ""), ":");
//			st.nextToken();
//			return new Integer(st.nextToken());
//		}
//		
//	}
//	
}
