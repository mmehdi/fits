package uk.ac.abdn.fits.gmap.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Time extends TextValuePair{

	private String time_zone;
	
	public Time(){
		
	}
	
	public Time(Time time){
		if(time != null){
			value = time.getValue();
			text = time.getText();
			time_zone = time.getTime_zone();
		}
	}
	
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value = value;
	}
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
	}
	public String getTime_zone(){
		return time_zone;
	}
	public void setTime_zone(String time_zone){
		this.time_zone = time_zone;
	}
	
}
