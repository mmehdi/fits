package uk.ac.abdn.fits.reasoner.utils;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;

import uk.ac.abdn.fits.gmap.model.DirectionsResponse;
import uk.ac.abdn.fits.mvc.control.query.FtsQueryFormBean;
import uk.ac.abdn.fits.reasoner.utils.model.Request;
import uk.ac.abdn.fits.reasoner.utils.model.Route;
import uk.ac.abdn.fits.reasoner.utils.model.Step;
import uk.ac.abdn.fits.reasoner.utils.model.TransportOption;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilsHelper {
	
	public static ObjectMapper mapper = new ObjectMapper();
	
	
	public TransportOption createTransportOption(DirectionsResponse directions, double fare, FtsQueryFormBean queryForm){
		
		TransportOption option = new TransportOption();
//		option.setProviderId(directions.getSourceID());
//		option.setRoute(extractRoute(directions));
//		option.setRequest(extractRequest(queryForm));
//		option.setDisutilities(0);
//		option.setFare(fare);
//		
//		if(directions.getSourceType().equals("google_transit")){
//			option.setChanges(directions.getRoutes()[0].getLegs()[0].getSteps().length -1);
//		}else{
//			option.setChanges(0);
//		}
		
		return option;
	}
	
	public TransportOption createGoogleTransportOption(DirectionsResponse directions, double fare){
		
		TransportOption option = new TransportOption();
//		option.setProviderId(directions.getSourceID());
//		option.setRoute(extractRoute(directions));
//		option.setRequest(extractRequest(null));
//		option.setDisutilities(0);
//		option.setFare(fare);
//		
//		if(directions.getSourceType().equals("google_transit")){
//			option.setChanges(directions.getRoutes()[0].getLegs()[0].getSteps().length -1);
//		}else{
//			option.setChanges(0);
//		}
		
		return option;
	}
	
	
	
	public void writeObject(TransportOption option, File file){
		
		try {
			String json = mapper.writeValueAsString(option);
//			System.out.println(json);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json);
			bw.close();
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	}
	
	public TransportOption readObject(File file){
		
		TransportOption option = null;
		
		try {
			option = mapper.readValue(new File("D:\\springToolSuite\\workspace\\demo\\src\\main\\resources\\transport_option.json"), 
					TransportOption.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return option;
	}
	
	private Request extractRequest(FtsQueryFormBean queryForm){
		Request request = new Request();
		request.setOrigin_address(extractOriginAddr(queryForm));
		request.setDestn_address(extractDestinationAddr(queryForm));
		request.setLogged_time(Calendar.getInstance().getTime());
		request.setPrefered_options(parsePreferedOptions(queryForm.getPrefered_options()));
//		request.setPrefered_types_of_operators(parsePreferedTypeOfOperator(queryForm.getPrefered_operators()));
		
		return request;
	}
	
	
	private Route extractRoute(DirectionsResponse directions){
		
		uk.ac.abdn.fits.gmap.model.Leg leg = null;
		
		if(directions.getStatus().equals("OK")){
			leg = directions.getRoutes()[0].getLegs()[0];
		}
		
		Route route = new Route();
		Step[] steps = new Step[leg.getSteps().length];

		for(int i=0; i< steps.length; i++){
			steps[i] = copyStep(leg.getSteps()[i]);
		}
		
		route.setSteps(steps);
		route.setDuration(leg.getDuration());
		route.setDistance(leg.getDistance());
		route.setStart_location(leg.getStart_location());
		route.setEnd_location(leg.getEnd_location());
		route.setStart_address(leg.getStart_address());
		route.setEnd_address(leg.getEnd_address());
		route.setDeparture_time(leg.getDeparture_time());
		route.setArrival_time(leg.getArrival_time());
		
		return route;
	}
	
	private Step copyStep(uk.ac.abdn.fits.gmap.model.Step step){
		
		Step copy = new Step();
		
		copy.setTravel_mode(step.getTravel_mode());
		copy.setStart_location(step.getStart_location());
		copy.setEnd_location(step.getEnd_location());
		copy.setDuration(step.getDuration());
		copy.setHtml_instructions(step.getHtml_instructions());
		copy.setDistance(step.getDistance());
		copy.setTransit_details(step.getTransit_details());
		
		return copy;
	}
	
	
	
	public String[] parsePreferedOptions(String s){
		
		final int NUM_OF_PREFERED_OPTION = 3;
		
		String[] preference = new String[NUM_OF_PREFERED_OPTION];
		s = s.replaceAll("ranking\\[\\]=", "");
		StringTokenizer st = new StringTokenizer(s.trim(), "&");
		
		int value = -1;
		
		for(int i=0; i< NUM_OF_PREFERED_OPTION; i++){
			value = new Integer(st.nextToken().trim());
			if(value == 1){
				preference[i] = "Minimise travel time";
			}else if(value == 2){
				preference[i] = "Minimise fare";
			}else if(value == 3){
				preference[i] = "Minimise number of changes";
			}
		}
		
		return preference;
	}
	
	public String[] parsePreferedTypeOfOperator(String s){
		
		final int NUM_OF_PREFERED_TYPES_OF_OPERATORS = 4;
		
		String[] preference = new String[NUM_OF_PREFERED_TYPES_OF_OPERATORS];
		s = s.replaceAll("ranking\\[\\]=", "");
		StringTokenizer st = new StringTokenizer(s.trim(), "&");
		
		int value = -1;
		for(int i=0; i< NUM_OF_PREFERED_TYPES_OF_OPERATORS; i++){
			value = new Integer(st.nextToken().trim());
			if(value == 4){
				preference[i] = "Patient Transport Service";
			}else if(value == 5){
				preference[i] = "Fixed-route service";
			}else if(value == 6){
				preference[i] = "Door-to-door service";
			}else if(value == 7){
				preference[i] = "Car service";
			}
		}
		
		return preference;
	}
	
private String extractOriginAddr(FtsQueryFormBean queryFormBean){
		
		StringBuilder sb = new StringBuilder();
		
		if(queryFormBean.getStreet_number_f_outward()!= null){
			sb.append(queryFormBean.getStreet_number_f_outward());
			sb.append("+");
		}
		if(queryFormBean.getRoute_f_outward() != null){
			sb.append(queryFormBean.getRoute_f_outward());
			sb.append("+");
		}
		if(queryFormBean.getPostal_code_f_outward() != null){
			sb.append(queryFormBean.getPostal_code_f_outward());
			sb.append("+");
		}
		if(queryFormBean.getLocality_f_outward() != null){
			sb.append(queryFormBean.getLocality_f_outward());
			sb.append("+");
		}
		if(queryFormBean.getCountry_f_outward() != null){
			sb.append(queryFormBean.getCountry_f_outward());
		}
		
		return sb.toString().replaceAll(",", "%2C").replaceAll(" ", "+");
		
	}
	
	private String extractDestinationAddr(FtsQueryFormBean queryFormBean){
		
		StringBuilder sb = new StringBuilder();
		
		if(queryFormBean.getStreet_number_t_outward()!= null){
			sb.append(queryFormBean.getStreet_number_t_outward());
			sb.append("+");
		}
		if(queryFormBean.getRoute_t_outward() != null){
			sb.append(queryFormBean.getRoute_t_outward());
			sb.append("+");
		}
		if(queryFormBean.getPostal_code_t_outward() != null){
			sb.append(queryFormBean.getPostal_code_t_outward());
			sb.append("+");
		}
		if(queryFormBean.getLocality_t_outward() != null){
			sb.append(queryFormBean.getLocality_t_outward());
			sb.append("+");
		}
		if(queryFormBean.getCountry_t_outward() != null){
			sb.append(queryFormBean.getCountry_t_outward());
		}
		
		return sb.toString().replaceAll(",", "%2C").replaceAll(" ", "+");
		
	}
	
}
