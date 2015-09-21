package uk.ac.abdn.fits.mvc.control.admin;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * @author Mujtaba Mehdi, University of Aberdeen
 * 
 */

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Column;
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

import com.google.gson.Gson;

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
		List<QueryLogGroupedDTO> date_data = generateGroupedData("date",startDate,endDate);

		try{
			if(startDate==null || startDate.length()<=0)
				startDate=date_data.get(0).getColumn_name();
			if(endDate==null || endDate.length()<=0)
				endDate=date_data.get(date_data.size()-1).getColumn_name();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		model.addAttribute("start_date", startDate);
		model.addAttribute("end_date", endDate);
		
		String mobility_data_json = new Gson().toJson(mobility_status_grouped_data);
		mobility_data_json=mobility_data_json.replace("column_name", "label");
		mobility_data_json=mobility_data_json.replace("count", "value");

		String age_group_data_json = new Gson().toJson(age_grouped_data);
		age_group_data_json=age_group_data_json.replace("column_name", "label");
		age_group_data_json=age_group_data_json.replace("count", "value");

		String purpose_data_json = new Gson().toJson(purpose_data);
		purpose_data_json=purpose_data_json.replace("column_name", "label");
		purpose_data_json=purpose_data_json.replace("count", "value");

		String date_data_json = new Gson().toJson(date_data);
		date_data_json=date_data_json.replace("column_name", "day");
		date_data_json=date_data_json.replace("count", "value");


		model.addAttribute("mobility_data", mobility_status_grouped_data);
		model.addAttribute("age_group_data", age_grouped_data);	
		model.addAttribute("purpose_data", purpose_data);	
		model.addAttribute("date_data", date_data);	

		model.addAttribute("mobility_data_json", mobility_data_json);
		model.addAttribute("age_group_data_json", age_group_data_json);	
		model.addAttribute("purpose_data_json", purpose_data_json);	
		model.addAttribute("date_data_json", date_data_json);	



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
			case "date":
				query_log = queryLogDAOImpl.getAllQueryLogDataGroupByDate(startDate,endDate);
				break;
			default:
				break;
		}
		
		return query_log;
	}
	
	
	  @Column(name="age_group", nullable = false)
	  private String age_group;
	 
	  @Column(name="mobility_status", nullable = false)
	  private String mobility_status;
	  
	  @Column(name="purpose", nullable = false)
	  private String purpose;
	  
	  @Column(name="is_return", nullable = true)
	  private boolean is_return;
	  

	@Column(name="timestamp", nullable = true)
	  private Timestamp timestamp;
	  
	  
	
	private void writeToCSV(List<QueryLog> query_log_array)
    {
	    String CSV_SEPARATOR = ",";

        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("query_log.csv"), "UTF-8"));
            for (QueryLog query : query_log_array)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(query.getId() <=0 ? "" : query.getId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(query.getFrom_postcode().trim().length() == 0? "" : query.getFrom_postcode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(query.getTo_postcode().trim().length() == 0? "" : query.getTo_postcode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(query.getFrom_address().trim().length() == 0? "" : query.getFrom_address());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(query.getTo_address().trim().length() == 0? "" : query.getTo_address());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(query.getAge_group().trim().length() == 0? "" : query.getAge_group());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(query.getMobility_status().trim().length() == 0? "" : query.getMobility_status());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(query.getPurpose().trim().length() == 0? "" : query.getPurpose());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (Exception e){
        	e.printStackTrace();
        }
    }
}
