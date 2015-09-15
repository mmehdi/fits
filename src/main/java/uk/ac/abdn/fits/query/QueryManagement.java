package uk.ac.abdn.fits.query;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;



public class QueryManagement {
//
//	
//	protected static QueryManagement instance = null;
//	
//	private ResourceQuery resource = null;
//	
//	public static QueryManagement getInstance(){
//		
//		if(instance  == null){
//			instance = new QueryManagement();
//			return instance;
//		}
//		
//		return instance;
//	}
//	
//
//	private QueryManagement(){
//		
//		resource = ResourceQuery.getInstance();
//	}
//	
//	
//	public List<DirectionsResponse> query(String origin_address, String destn_address, 
//			Calendar calendar){
//		
//		List<DirectionsResponse> responses = resource.search_google_transit(origin_address, destn_address, calendar);
//		for(DirectionsResponse response: responses){
//			response.setTravelling_time(calendar);
//		}
//		return responses;
//	}
//	
//	public List<DirectionsResponse> query(String origin_address, String destn_address, 
//			Calendar calendar, PassengerAge age_group, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose journey_purpose){
//		
//		List<DirectionsResponse> responses = resource.search_for_fts(origin_address, destn_address, calendar, age_group, mobility_status, journey_purpose);
//		for(DirectionsResponse response: responses){
//			response.setTravelling_time(calendar);
//		}
//		return responses;
//	}
//	
//	/**
//	public List<String> queryRelaxedOption(String origin_address, String destn_address, 
//			Calendar calendar, PassengerAge age_group, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose journey_purpose){
//		PassengerDemand psd_demand = new PassengerDemand();
//		psd_demand.setOrigin_address(origin_address);
//		psd_demand.setDestn_address(destn_address);
//		psd_demand.setDepartAfter(calendar);
//		psd_demand.setAgeGroup(age_group);
//		psd_demand.setMobilityStatus(mobility_status);
//		psd_demand.setPurpose(journey_purpose);
//		
//		ArrayList<String> relaxedOperatingAreaOpt = new ArrayList<String>();
//		
////		DirectionsResponse response = google_transit(origin_address, destn_address, ""+calendar.get(Calendar.YEAR), 
////				""+(calendar.get(Calendar.MONTH)), ""+calendar.get(Calendar.DAY_OF_MONTH), 
////				""+calendar.get(Calendar.HOUR_OF_DAY), ""+calendar.get(Calendar.MINUTE), ""+calendar.get(Calendar.SECOND), "drive");
//		
//		
//		
//		return null;
//	}
//	
//	public void relaxedOperatingArea(List<Operator> operators, PassengerDemand psg_demand) throws IOException{
//		
//		String line = null;
//		ArrayList<RelaxedOption> relaxedOptions = new ArrayList<RelaxedOption>();
//		RelaxedOption relaxedOption = null;
//		
//		for(Operator operator: operators){
////			bw.write("Passenger, Direction, Amount, Shortest distance(o-d)\n");
//			relaxedOption = relaxedOperatingArea(operator, psg_demand);
//			if(relaxedOption != null){
//				relaxedOptions.add(relaxedOption);
//			}else{
//				System.err.println("error! The result line is empty.");
//			}
//		}
//	} 
//	
//	
//	public RelaxedOption relaxOperatingArea(Operator operator, PassengerDemand psg_demand){
//		RelaxedOption relaxed_option = new RelaxedOption();
//		final int miles = 5;
//		OSRef osRef = psg_demand.getOrigin().toOSRef();
//		Point origin = new Point(osRef.getEasting(), osRef.getNorthing());
//		osRef = psg_demand.getDestn().toOSRef();
//		Point destn = new Point(osRef.getEasting(), osRef.getNorthing());
//		
//		
//		
//	}
//	
//	*/
//	public List<DirectionsResponse> reasoning(List<DirectionsResponse> responses, FtsQueryFormBean queryForm){
//		
//		UtilsHelper utilsHelper = new UtilsHelper();
//		List<TransportOption> tranport_options = new ArrayList<TransportOption>();
//		TransportOption option = null;
//		
//		for(DirectionsResponse response: responses){
//			option = utilsHelper.createTransportOption(response, calculate_fare(response), queryForm);
//			tranport_options.add(option);
//		}
//		
//		String jess_result = pass_to_reasoner(tranport_options);
//		return parseJessResult(responses, jess_result);
//	}
//	
//	
//	private String pass_to_reasoner(List<TransportOption> tranport_options){
//		
//		String para = getParameter(tranport_options);
//		
//		jessReasoner reasoner = new jessReasoner();
//		
//		String result_from_reasoner = null;
//		try {
//			result_from_reasoner = reasoner.startReasonerAgent(para);
//		} catch (JessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(result_from_reasoner);
//		
//		return result_from_reasoner;
//	}
//	
//	
//	private String getParameter(List<TransportOption> tranport_options){
//		
//		StringBuilder para = new StringBuilder();
//		
//		TransportOption option = null;
//		
//		option = tranport_options.get(0);
//		String optionId = ""+option.getRequest().getLogged_time().getTime();
//		for(int i=0; i<  tranport_options.size(); i++){
//			option = tranport_options.get(i);
////			para.append(""+option.getRequest().getLogged_time().getTime()); // unix time
//			para.append(optionId); // unix time 
//			para.append(",");
//			para.append(""+option.getProviderId()+"");
//			para.append(",");
//			para.append(option.getFare()); // double 
//			para.append(",");
//			para.append((int)(new Double(option.getRoute().getDuration().getValue())/60)); // second
//			para.append(",");
//			para.append(option.getChanges());
//			para.append(",");
//			para.append(""+option.getRequest().getPrefered_options()[0]+"");
//			para.append(",");
//			para.append(""+option.getRequest().getPrefered_options()[1]+"");
//			para.append(",");
//			para.append(""+option.getRequest().getPrefered_options()[2]+"");
////			para.append(",");
////			para.append("");
////			para.append(""+ option.getRequest().getPrefered_types_of_operators()[0]+"");
////			para.append(";");
////			para.append(""+ option.getRequest().getPrefered_types_of_operators()[1]+"");
////			para.append(";");
////			para.append(""+ option.getRequest().getPrefered_types_of_operators()[2]+"");
////			para.append(";");
////			para.append(""+ option.getRequest().getPrefered_types_of_operators()[3]+"");
//			para.append("");
//			para.append("#");
//		}
//		
//		System.out.println(para.toString());
//		return para.toString();
//		
//	}
//	
//	
//	private List<DirectionsResponse> parseJessResult(List<DirectionsResponse> responses, String result){
//		
//		ArrayList<DirectionsResponse> ranking = new ArrayList<DirectionsResponse>();
//		
//		StringTokenizer st = new StringTokenizer(result, "#");
//		
//		String temp = null;
//		
//		DirectionsResponse response = null;
//		
//		String[] sourceID_and_score = new String[2];
//		
//		HashSet<String> sourceIDs = new HashSet<String>();
//		
//		while(st.hasMoreTokens()){
//			temp = st.nextToken().trim();
//			sourceID_and_score = temp.split(",");
//			if(sourceID_and_score.length == 2){ 
//				if(!sourceIDs.contains(sourceID_and_score[0].trim())){
//					sourceIDs.add(sourceID_and_score[0].trim());
//					response = getResponseBySourceID(responses, sourceID_and_score[0].trim(), sourceID_and_score[1].trim());
//					if(response != null){
//						ranking.add(response);
//					}
//				}
//				
//			}
//		}
//		
//		Collections.sort(ranking, new MyDirectionsResponseComparable());
//		
//		return ranking;
//	}
//	
//	
//	private DirectionsResponse getResponseBySourceID(List<DirectionsResponse> responses, String sourceID, String score){
//		
//		for(int i=0; i< responses.size(); i++){
//			
//			if(responses.get(i).getSourceID().equals(sourceID)){
//				
//				responses.get(i).setScore(new Double(score));
//				
//				return responses.get(i);
//			}
//			
//		}
//		return null;
//	}
//	
//	private double calculate_fare(DirectionsResponse response){
//		
//		double fare = 0;
//		
//		if(response.getSourceType().equals("google_transit")){
//			
//			Leg leg = response.getRoutes()[0].getLegs()[0];
//			
//			for(int i=0; i< leg.getSteps().length; i++){
//				
//				if(leg.getSteps()[i].getTravel_mode().equals("TRANSIT")){
//					fare += calculateFare_bus(leg.getSteps()[i].getTransit_details().getNum_stops());
//				}
//			}
//		}else if(response.getSourceType().equals("operators")){
//			
//			if(response.getRoutes()[0].getOperator().getName().equals("BABS car service")){
//				
//				fare = calculateFare_hire_car(response.getRoutes()[0].getLegs()[0].getDistance().getValue());
//				
//			}else{
//
//				fare = calculateFare_DRT(new Integer(response.getRoutes()[0].getLegs()[0].getDistance().getValue()));
//			}
//			
//		}else if(response.getSourceType().equals("taxi")){
//			
//			fare = calculateFare_taxi(response.getRoutes()[0].getLegs()[0].getDistance().getValue());
//		}
//		
//		return fare;
//	}
//	
//	private double calculateFare_bus(int numOfStops){
//		
//		double fare;
//		
//		if(numOfStops <=3){
//			fare = 1.20;
//		}else if(numOfStops <=8 ){
//			
//			return 2.80;
//		}else if(numOfStops <=12){
//			return 3.60;
//		}else {
//			return 4.20;
//		}
//		
//		return fare;
//	}
//	
//	private double calculateFare_DRT(int meters){
//		
//		int miles = (int)(meters * 0.00062137);
//		
//		if(miles < 20){
//			return 6.80;
//		}else{
//			return 15.0;
//		}
//		
//	}
//	
//	private double calculateFare_hire_car(String meters){
//		
//		double m = new Double(meters);
//		
//		m *= 0.00062137;
//		
//		int miles = ((int)m) +1;
//		
//		return (7 + 0.45*(miles));
//	}
//	
//	private double calculateFare_taxi(String meters){
//		
//		double m = new Double(meters);
//		
//		m *= 0.00062137;
//		
//		int miles = ((int)m) +1;
//		
//		return (2.4 + 1.80*(miles));
//	}
//	
}


//class MyDirectionsResponseComparable implements Comparator<DirectionsResponse>{
//	 
//    @Override
//    public int compare(DirectionsResponse o1, DirectionsResponse o2) {
//        return (o2.getScore()>o1.getScore() ? -1 : (o2.getScore()==o1.getScore() ? 0 : 1));
//    }
//} 
