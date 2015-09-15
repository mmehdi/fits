package uk.ac.abdn.fits.reasoner.utils.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {
	
	String origin_address;
	
	String destn_address;
	
	Date logged_time;
	
	int passenger_num;
	
	int wheelchair_space;
	
	String[] prefered_options;
	
	String[] prefered_types_of_operators;
	
	public String getOrigin_address(){
		return origin_address;
	}
	
	public void setOrigin_address(String origin_address){
		this.origin_address = origin_address;
	}
	
	public String getDestn_address(){
		return destn_address;
	}
	
	public void setDestn_address(String destn_address){
		this.destn_address = destn_address;
	}
	
	public Date getLogged_time(){
		return logged_time;
	}
	
	public void setLogged_time(Date logged_time){
		this.logged_time = logged_time;
	}
	
	public int getPassenger_num(){
		return passenger_num;
	}
	
	public void setPassenger_num(int passenger_num){
		this.passenger_num = passenger_num;
	}
	
	public int getWheelchair_space(){
		return wheelchair_space;
	}
	
	public void setWheelchair_space(int wheelchair_space){
		this.wheelchair_space = wheelchair_space;
	}
	
	public String[] getPrefered_options(){
		
		return prefered_options;
	}
	
	public void setPrefered_options(String[] prefered_options){
		this.prefered_options = prefered_options;
	}
	
	public String[] getPrefered_types_of_operators(){
		return prefered_types_of_operators;
	}
	
	public void setPrefered_types_of_operators(String[] prefered_types_of_operators){
		this.prefered_types_of_operators = prefered_types_of_operators;
	}
}
