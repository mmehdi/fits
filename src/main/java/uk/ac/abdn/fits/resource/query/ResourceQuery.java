package uk.ac.abdn.fits.resource.query;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import uk.ac.abdn.fits.gmap.GoogleTransit;
import uk.ac.abdn.fits.gmap.model.DirectionsResponse;
import uk.ac.abdn.fits.gmap.model.Duration;
import uk.ac.abdn.fits.gmap.model.Route;
import uk.ac.abdn.fits.gmap.model.Time;
import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.query.operator.*;
import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;



public class ResourceQuery {
	
	
	public static ResourceQuery instance = null;
	
	private GoogleTransit google_transit = new GoogleTransit();

	private ArrayList<Operator> operators;
	
	private TaxiOperator taxi;
	
	public static ResourceQuery getInstance(){
		
		if(instance == null){
			instance = new ResourceQuery();
			return instance;
		}
		return instance;
	}
	
	
	private ResourceQuery(){
		
		operators = new ArrayList<Operator>();
		init_operators();
		
	}
	
	private void init_operators(){
		Operator operator = null;
//		operator = new BABSBusService();
//		operators.add(operator);
		operator = new MforMorayBuckieService();
		operators.add(operator);
		operator = new MforMorayForresService();
		operators.add(operator);
		operator = new MforMorayKeithService();
		operators.add(operator);
		operator = new MforMoraySpeysideService();
		operators.add(operator);
		operator = new BanffshirePartnershipDialABus();
		operators.add(operator);
//		operator = new TurriffA2BService();
//		operators.add(operator);
//		operator =  new BABSCarService();
//		operators.add(operator);
//		operator =  new CentralBuchanA2BService();
//		operators.add(operator);
		
		taxi = new TaxiOperator();
	}
	
	public List<DirectionsResponse> search_google_transit(String origin_address, String destn_address, 
			Calendar calendar){
		ArrayList<DirectionsResponse> responses = new ArrayList<DirectionsResponse>();
		responses.addAll(google_transit(origin_address, destn_address, calendar, "transit"));
		return responses;
	}
	
	public List<DirectionsResponse> search_for_fts(String origin_address, String destn_address, 
			Calendar calendar, PassengerAge age_group, PassengerMobilityStatus mobility_status, 
			PassengerJourneyPurpose journey_purpose){
		
//		ArrayList<DirectionsResponse> responses = new ArrayList<DirectionsResponse>();
//		
//		String mode = "drive";
//		
//		DirectionsResponse response = google_transit(origin_address, destn_address, ""+calendar.get(Calendar.YEAR), 
//				""+(calendar.get(Calendar.MONTH)), ""+calendar.get(Calendar.DAY_OF_MONTH), 
//				""+calendar.get(Calendar.HOUR_OF_DAY), ""+calendar.get(Calendar.MINUTE), ""+calendar.get(Calendar.SECOND), mode);
//		
//		DirectionsResponse reprocessed = null;
//		
//		if(response.getStatus().equals("OK")){
//			for(Operator operator: operators){
//				reprocessed = reprocess(operator, response, calendar, age_group, mobility_status, journey_purpose);
//				if(reprocessed != null){
//					responses.add(reprocessed);
//				}
//			}
//		}
//		
//		System.out.println("operators: " + responses.size());
//		
//		/**
//		 * Add taxi service
//		 * */
//		response.setSourceType("taxi");
//		response.setSourceID(taxi.getName());
//		response.setTravelling_time(calendar);
//		response.getRoutes()[0].setOperator(taxi);
//		responses.add(response);
//		
//		return responses;
		return null;
	}
	
	private DirectionsResponse reprocess(Operator operator, DirectionsResponse response, Calendar calendar, 
			PassengerAge age_group, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose journey_purpose){
		
//		DirectionsResponse new_response = new DirectionsResponse(response);
//		
//		if(check_operating_time(operator, calendar)){
//			Route route = new_response.getRoutes()[0];
//			if(check_operating_area(operator, route)){
//				if(checkEligibility(operator, age_group, mobility_status, journey_purpose)){
//					route.setOperator(operator);
//					new_response.setSourceType("operators");
//					new_response.setSourceID(operator.getName());
//					if(!(operator instanceof BABSCarService)){
//						/*adapt travelling time to flexible transport service; factor * 2*/
////						Time arrival_time = route.getLegs()[0].getArrival_time();
//						Duration duration = route.getLegs()[0].getDuration();
//						int seconds = new Integer(duration.getValue());
//						seconds *= 0.5;
////						duration = increaseDuration(duration, 0);
////						postponeArrival_time(arrival_time, seconds);
//						route.getLegs()[0].setDuration(duration);
//					}
//					return new_response;
//				}
//			}
//		}
		
		return null;
	}
	
	private boolean check_operating_time(Operator operator, Calendar calendar){
		
		return operator.checkOperatingTime(calendar);
	}
	
	private boolean check_operating_area(Operator operator, Route route){
		
		boolean isCovered = false;
		Point origin = null, destn = null;
		String lat = null, lng = null;
		lng = route.getLegs()[0].getStart_location().getLng();
		lat = route.getLegs()[0].getStart_location().getLat();
		LatLng latLng = null;
		OSRef osRef = null;
		latLng = new LatLng(new Double(lat), new Double(lng));//  lat lng
		osRef = latLng.toOSRef();
		origin = new Point(osRef.getEasting(), osRef.getNorthing()); 
		lng = route.getLegs()[0].getEnd_location().getLng();
		lat = route.getLegs()[0].getEnd_location().getLat();
		latLng = new LatLng(new Double(lat), new Double(lng));//  lat lng
		osRef = latLng.toOSRef();
		destn =new Point(osRef.getEasting(), osRef.getNorthing()); 
		isCovered = operator.isCovered(origin) && operator.isCovered(destn);
		return isCovered;
	}
	
	private boolean checkEligibility(Operator operator, PassengerAge age_group, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose journey_purpose){
		return operator.checkEligibility(age_group, mobility_status, journey_purpose);
	}
	
	public List<DirectionsResponse> google_transit(String origin_address, String destn_address, 
			Calendar c, String mode){
		 
//		ArrayList<DirectionsResponse> responses = new ArrayList<DirectionsResponse>();
//		
//		int index = 0;
//		
//		DirectionsResponse response = null;
//		
//		Calendar calendar = Calendar.getInstance();
//		
//		calendar.setTimeInMillis(c.getTimeInMillis());
//		
//		for(int i=0; i<10; i++){
//			
//			if(responses.size() >= 2){
//				break;
//			}
//			
//			response = google_transit(origin_address, destn_address, ""+calendar.get(Calendar.YEAR), 
//					""+(calendar.get(Calendar.MONTH)), ""+calendar.get(Calendar.DAY_OF_MONTH), 
//					""+calendar.get(Calendar.HOUR_OF_DAY), ""+calendar.get(Calendar.MINUTE), ""+calendar.get(Calendar.SECOND), mode);
//			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			if(response.getStatus().equals("OK")){
//				if(response.isWalkingDir()){
//					continue;
//				}else if(!responses.contains(response)){
//					response.setSourceID("google_transit"+index);
////					response.setSourceType("google_transit"); // has been set 
//					responses.add(response);
////					System.out.println("has been added: "+response.toString());
//					index++;
//				}
//			}
//			
//			calendar.add(Calendar.MINUTE, 30);
//		}
//		
//		System.out.println("google transit: " + responses.size());
		
//		return responses;
		return null;
	}
	
	public DirectionsResponse google_transit(String origin_address, String destn_address, 
			String year, String month, String day, String hour, String min, String second, 
			String mode){
		
		DirectionsResponse response = null;
		Date departure_time = convertTime(year, month, day, hour, min, second);
		String url = google_transit.getURL(origin_address, destn_address, departure_time.getTime(), mode);
		
		int count = 0;
		while(response == null && count < 10){
			google_transit.saveGoogleTransitJsonFile(url);
			response = google_transit.getDataBinding();
			if(response == null){
				count ++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
//		response.setSourceType("google_transit");
		return response;
//		return null;
	}
	
	
	public List<Operator> getOperators(){
		return operators;
	}
	
	private Date convertTime(String year, String month, String day, String hour, String min, String second){
		
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.MILLISECOND, 0);
		
		int yyyy = new Integer(year);
		int mm = new Integer(month); 
		int dd = new Integer(day);
		int hh = new Integer(hour);
		int mimi = new Integer(min);
		int ss = new Integer(second);
		
		c.set(Calendar.YEAR, yyyy);
		c.set(Calendar.MONTH, mm);
		c.set(Calendar.DAY_OF_MONTH, dd);
		c.set(Calendar.HOUR_OF_DAY, hh);
		c.set(Calendar.MINUTE, mimi);
		c.set(Calendar.SECOND, ss);
		
		System.out.println("google transit setting departure time "+ c.getTime().toGMTString());
		return c.getTime();
		
	}
	
	
	
	private Duration increaseDuration(Duration duration, int sec){
		
		String text = duration.getText(); // 28 mins
		String value = duration.getValue(); // 1680
		
		Integer dur = new Integer(value);
		
		dur += sec;
		
		value = ""+dur;
		text = convertTime(dur);
		
		duration = new Duration();
		
		duration.setValue(value);
		duration.setText(text);
		return duration;
	}
	
	private String convertTime(int sec){
		
		int hour = 0;
		int min = 0;
		
		hour = (int)(sec/3600);
		if(hour > 0){
			
			sec -= hour * 3600;
			min = (int)(sec/60);
			return hour + "hour "+ min +" mins";
			
		}else{
			min = (int)(sec/60);
			return min +" mins";
		}
		
		
	}
	
	private Time postponeArrival_time(Time arrival_time, int sec){
		
		String value = arrival_time.getValue(); // 1386248400
		
		Calendar c = Calendar.getInstance();
		
		c.setTimeInMillis(new Long(value)*1000);
		c.add(Calendar.SECOND, sec);
		
		arrival_time.setValue(""+(int)(c.getTimeInMillis()/1000));
		
		SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
		arrival_time.setText(formatter.format(c.getTime()).toLowerCase().replace(" ", ""));
		
		return null;
	}

}
