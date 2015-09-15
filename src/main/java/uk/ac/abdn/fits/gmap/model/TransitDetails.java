package uk.ac.abdn.fits.gmap.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransitDetails {
	
	private Stop departure_stop;
	private Stop arrival_stop;
	private Line line;
	private int num_stops;
	private String headsign;
	private Time departure_time;
	private Time arrival_time;
	
	public Stop getDeparture_stop(){
		return departure_stop;
	}
	public void setDeparture_stop(Stop departure_stop){
		this.departure_stop = departure_stop;
	}
	public Stop getArrival_stop(){
		return arrival_stop;
	}
	public void setArrival_stop(Stop arrival_stop){
		this.arrival_stop = arrival_stop;
	}
	public Line getLine(){
		return line;
	}
	public void setLine(Line line){
		this.line = line;
	}
	public int getNum_stops(){
		return num_stops;
	}
	public void setNum_stops(int num_stops){
		this.num_stops = num_stops;
	}
	public String getHeadsign(){
		return headsign;
	}
	public void setHeadsign(String headsign){
		this.headsign = headsign;
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
		return "| departure_stop: "+ departure_stop.getName()
				+ " | arrival_stop: " + arrival_stop.getName()
				+ " | "+ departure_time.getText()
				+ " - " + arrival_time.getText()
				+ " | num of stops: " + num_stops 
				+ " | line: "+line.toString()
				;
	}
	
}
