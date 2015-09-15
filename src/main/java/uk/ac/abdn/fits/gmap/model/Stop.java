package uk.ac.abdn.fits.gmap.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stop {
	
	private String name;
	private Location location;
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public Location getLocation(){
		return location;
	}
	public void setLocation(Location location){
		this.location = location;
	}
	

}
