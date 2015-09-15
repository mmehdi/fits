package uk.ac.abdn.fits.gmap.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.text.SimpleDateFormat;

import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Leg {
	
	private Step[] steps;
	
	private Duration duration;
	
	private Distance distance;
	
	private Location start_location;
	
	private Location end_location;
	
	private String start_address;
	
	private String end_address;
	
	private Time departure_time;
	
	private Time arrival_time;
	
	public Leg(){
		
	}
	
	public Leg(Leg leg){
		
		steps = leg.getSteps();
		if(leg.getDuration() != null){
			duration = new Duration();
			duration.setText(leg.getDuration().getText());
			duration.setValue(leg.getDuration().getValue());
		}else{
			duration = leg.getDuration();
		}
		distance = leg.getDistance();
		start_location = leg.getStart_location();
		end_location = leg.getEnd_location();
		start_address = leg.getStart_address();
		end_address = leg.getEnd_address();
		departure_time = new Time(leg.getDeparture_time());
		arrival_time = new Time(leg.getArrival_time());
	}
	
	
	public Step[] getSteps(){
		return steps;
	}
	public void setSteps(Step[] steps){
		this.steps = steps;
	}
	public Duration getDuration(){
		return duration;
	}
	
	
	
	
	public void setDuration(Duration duration){
		this.duration = duration;
	}
	public Distance getDistance(){
		return distance;
	}
	public void setDistance(Distance distance){
		this.distance = distance;
	}
	public Location getStart_location(){
		return start_location;
	}
	public void setStart_location(Location start_location){
		
		this.start_location = start_location;
	}
	public Location getEnd_location(){
		return end_location;
	}
	public void setEnd_location(Location end_location){
		this.end_location = end_location;
	}
	public String getStart_address(){
		return start_address;
	}
	public void setStart_address(String start_address){
		this.start_address = start_address;
		
	}
	public String getEnd_address(){
		return end_address;
	}
	public void setEnd_address(String end_address){
		this.end_address = end_address;
	}
	public Time getDeparture_time(){
		return departure_time;
	}
	public void setDeparture_time(Time departure_time){
		this.departure_time = departure_time;
	}
	public Time getArrival_time(){
		return arrival_time;
	}
	public void setArrival_time(Time arrival_time){
		this.arrival_time = arrival_time;
	}
	
	public String toString(){
		
//		String leg_detail = "leg: ";
		String leg_detail = "";
		leg_detail += "start_address: "+ start_address;
		leg_detail += ""; 
		leg_detail += "\tend_address " + end_address;
		leg_detail += "";
		leg_detail += "\tdepart at "+ departure_time.getText()+"\tarrive at "+ arrival_time.getText();
		leg_detail += "";
		leg_detail += "\tdistance: "+ distance.getText()+ " | duration: "+ duration.getText();
		leg_detail += " | number of changes: " + steps.length;
//		leg_detail += "\ntransport operators:";
//		for(int i=0; i< steps.length; i++){
//			if(!steps[i].getTravel_mode().equals("WALKING")){
//				
//			}
//			leg_detail += "\t"+steps[i].getTransit_details().getLine().getAgency() ;
//		}
		
		
		return leg_detail;
	}
	
	
	
	
	
	

}
