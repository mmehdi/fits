package uk.ac.abdn.fits.mvc.control.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author Mujtaba Mehdi, University of Aberdeen
 * 
 */


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.google.gson.Gson;

import uk.ac.abdn.fits.hibernate.dao.QueryLogDAO;
import uk.ac.abdn.fits.hibernate.model.QueryLog;
import uk.ac.abdn.fits.hibernate.model.QueryLogGroupedDTO;


@Controller
//@SessionAttributes("registerFormBean")
public class AdminReportingController {

	
	@RequestMapping(value="/reports",method=RequestMethod.GET) 
	public ModelAndView form(Locale locale, Model model,HttpServletRequest request) {
		
		String startDate = request.getParameter("start");
		String endDate = request.getParameter("end");
		
		//System.out.println("start date: "+startDate);
		List<QueryLogGroupedDTO> mobility_status_grouped_data = generateGroupedData("mobility_status",startDate,endDate);
		List<QueryLogGroupedDTO> age_grouped_data = generateGroupedData("age_group",startDate,endDate);
		List<QueryLogGroupedDTO> purpose_data = generateGroupedData("purpose",startDate,endDate);
		List<QueryLogGroupedDTO> date_data = generateGroupedData("date",startDate,endDate);
		List<QueryLog> all_data = generateQueryLogData(startDate,endDate);
		
		writeToCSV(all_data);
		
		int return_journeys_count=0;
		int outward_journeys_count =0;
		
		return_journeys_count = countReturnJourneys(all_data);
		outward_journeys_count = all_data.size()-return_journeys_count;
	  	
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat new_formatter = new SimpleDateFormat("dd-MMM-yyyy");

		String start_date_descriptive;
		String end_date_descriptive;
		try{
			if(startDate==null || startDate.length()<=0)
				startDate=date_data.get(0).getColumn_name();
			
			start_date_descriptive = new_formatter.format(formatter.parse(startDate));
			model.addAttribute("start_date_descriptive", start_date_descriptive);
			
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		try{
			if(endDate==null || endDate.length()<=0)
				endDate=date_data.get(date_data.size()-1).getColumn_name();

			end_date_descriptive = new_formatter.format(formatter.parse(endDate));
			model.addAttribute("end_date_descriptive", end_date_descriptive);
			
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

		model.addAttribute("total_journeys", all_data.size());	
		model.addAttribute("outward_journeys", outward_journeys_count);	
		model.addAttribute("return_journeys", return_journeys_count);	




		return new ModelAndView("reports");
		
	}
	
	//http://www.codejava.net/frameworks/spring/spring-mvc-with-csv-file-download-example
	
	   @RequestMapping(value = "/downloadCSV",method=RequestMethod.GET)
	    public void downloadCSV(HttpServletResponse response,HttpServletRequest request) throws IOException {
	 
			String startDate = request.getParameter("start");
			String endDate = request.getParameter("end");
				
	        //String csvFileName = "query_log.csv";
	        String csvFileName = getFileName(startDate,endDate);
	        response.setContentType("text/csv");
	 
	        // creates mock data
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                csvFileName);
	        response.setHeader(headerKey, headerValue);

	        List<QueryLog> all_data = generateQueryLogData(startDate,endDate);

	 
	        // uses the Super CSV API to generate CSV data from the model data
	        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
	                CsvPreference.EXCEL_PREFERENCE);
	 
	        String[] header = { "id", "from_postcode", "from_address", "to_postcode",
	                "to_address", "age_group","mobility_status", "purpose","timestamp","is_return" };
	 
	        csvWriter.writeHeader(header);
	 
	        for (QueryLog query : all_data) {
	            csvWriter.write(query, header);
	        }
	 
	        csvWriter.close();
	    }
	   
	private List<QueryLog> generateQueryLogData(String startDate, String endDate){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		QueryLogDAO QueryLog = (QueryLogDAO) ctx.getBean("QueryLogDAO");
		List<QueryLog> query_log = QueryLog.getQueryLogByDateRange(startDate, endDate);
		return query_log;
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
	private int countReturnJourneys(List<QueryLog>query_log){
		int count=0;
		for(QueryLog query: query_log){
			//System.out.println("id = " + query.getTimestamp());
			if(query.isIs_return())
					count=count+1;
		}
		return count;
	}
	
	/*
	 * method not being used anymore
	 */
	private void writeToCSV(List<QueryLog> query_log_array)
    {
	    String CSV_SEPARATOR = ",";
    	String path = this.getClass().getClassLoader().getResource("").getPath();
    	path = path+"../files";
    	File  dir = new File(path);
    	if(!dir.exists()){
    		dir.mkdirs();
    	}

        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path+File.separator+"log.csv"), "UTF-8"));
            for (QueryLog query : query_log_array)
            {
                StringBuffer oneLine = new StringBuffer();
                //oneLine.append(query.getId() <=0 ? "" : query.getId());
                oneLine.append(query.getId());
                oneLine.append(CSV_SEPARATOR);
                //oneLine.append(query.getFrom_postcode().trim().length() == 0? "" : query.getFrom_postcode());
                oneLine.append(StringEscapeUtils.escapeCsv(query.getFrom_postcode()));
                oneLine.append(CSV_SEPARATOR);
                //oneLine.append(query.getTo_postcode().trim().length() == 0? "" : query.getTo_postcode());
                oneLine.append(StringEscapeUtils.escapeCsv(query.getTo_postcode()));
                oneLine.append(CSV_SEPARATOR);
                //oneLine.append(query.getFrom_address().trim().length() == 0? "" : query.getFrom_address());
                oneLine.append(StringEscapeUtils.escapeCsv(query.getFrom_address()));
                oneLine.append(CSV_SEPARATOR);
                //oneLine.append(query.getTo_address().trim().length() == 0? "" : query.getTo_address());
                oneLine.append(StringEscapeUtils.escapeCsv(query.getTo_address()));
                oneLine.append(CSV_SEPARATOR);
                //oneLine.append(query.getAge_group().trim().length() == 0? "" : query.getAge_group());
                oneLine.append(StringEscapeUtils.escapeCsv(query.getAge_group()));
                oneLine.append(CSV_SEPARATOR);
                //oneLine.append(query.getMobility_status().trim().length() == 0? "" : query.getMobility_status());
                oneLine.append(StringEscapeUtils.escapeCsv(query.getMobility_status()));
                oneLine.append(CSV_SEPARATOR);
                //oneLine.append(query.getPurpose().trim().length() == 0? "" : query.getPurpose());
                oneLine.append(StringEscapeUtils.escapeCsv(query.getPurpose()));
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

	   private String getFileName(String startDate, String endDate){
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat new_formatter = new SimpleDateFormat("ddMMyyyy");

			String start_date_descriptive="";
			String end_date_descriptive="";
			try{
				start_date_descriptive = new_formatter.format(formatter.parse(startDate));		
		
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			
			try{
				end_date_descriptive = new_formatter.format(formatter.parse(endDate));
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
	       
			String csvFileName = "query_log-"+start_date_descriptive+"_"+end_date_descriptive+".csv";

			return csvFileName;
		}
}
