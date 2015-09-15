package uk.ac.abdn.fits.reasoner.utils.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import uk.ac.abdn.fits.gmap.model.Distance;
import uk.ac.abdn.fits.gmap.model.Duration;
import uk.ac.abdn.fits.gmap.model.Location;
import uk.ac.abdn.fits.gmap.model.Time;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

	private Step[] steps;
	
	private Duration duration;
	
	private Distance distance;
	
	private Location start_location;
	
	private Location end_location;
	
	private String start_address;
	
	private String end_address;
	
	private Time departure_time;
	
	private Time arrival_time;
	
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
	
	
}
