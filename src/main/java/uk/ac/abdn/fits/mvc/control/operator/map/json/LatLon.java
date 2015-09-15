package uk.ac.abdn.fits.mvc.control.operator.map.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LatLon {

	private String lat;
	private String lng;
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public String toString(){
		return "{lat:"+lat+" lng:"+lng+"}";
	}
}
