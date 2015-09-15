package uk.ac.abdn.fits.gmap.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Agency {

	private String name;
	
	private String url;
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	
	
}
