package uk.ac.abdn.fits.gmap.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "polyline", "maneuver" })
public class Step {
	
	private String travel_mode;
	private Location start_location;
	private Location end_location;
//	private polyline // ignore
	private Duration duration;
	private String html_instructions;
	private Distance distance;
	private TransitDetails transit_details; // for transit mode
	private Step[] steps; // for walking mode
	
	public String getTravel_mode(){
		return travel_mode;
	}
	public void setTravel_mode(String travel_mode){
		this.travel_mode = travel_mode;
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
	public Duration getDuration(){
		return duration;
	}
	public void setDuration(Duration duration){
		this.duration = duration;
	}
	public String getHtml_instructions(){
		return html_instructions;
	}
	public void setHtml_instructions(String html_instructions){
		this.html_instructions = html_instructions;
	}
	public Distance getDistance(){
		return distance;
	}
	public void setDistance(Distance distance){
		this.distance = distance;
	}
	public TransitDetails getTransit_details(){
		return transit_details;
	}
	public void setTransit_details(TransitDetails td){
		this.transit_details = td;
	}
	public Step[] getSteps(){
		return steps;
	}
	public void setSteps(Step[] steps){
		this.steps = steps;
	}
	public String toString(){
		
		String step_detail = "";
		step_detail += "\t\ttravel mode: "+ travel_mode+" ";
		if(transit_details != null){
			step_detail += transit_details.toString();
		}else if(steps != null && steps.length>0){
			step_detail += "\t\t";
			step_detail += html_instructions;
		}
		return step_detail;
	}
	
}
