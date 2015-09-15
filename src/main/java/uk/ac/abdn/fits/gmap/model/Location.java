package uk.ac.abdn.fits.gmap.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
	
	private String lat;
	
	private String lng;
	
	public String getLat(){
		return lat;
	}
	public void setLat(String lat){
		this.lat = lat;
	}
	public String getLng(){
		return lng;
	}
	public void setLng(String lng){
		this.lng = lng;
	}
	
}
