package uk.ac.abdn.fits.gmap.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Line {
	
	private String name;
	
	private String short_name;
	
	private Vehicle vehicle;
	
	private Agency[] agencies;
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getShort_name(){
		return short_name;
	}
	public void setShort_name(String short_name){
		this.short_name = short_name;
	}
	public Vehicle getVehicle(){
		return vehicle;
	}
	public void setVehicle(Vehicle v){
		this.vehicle = v;
	}
	public Agency[] getAgencies(){
		return this.agencies;
	}
	public void setAgencies(Agency[] agencies){
		this.agencies = agencies;
	}
	
	public String getAgency(){
		
		if(agencies.length>0){
			return agencies[0].getName();
		}else{
			return "n/a";
		}
		 
	}
	
	public String toString(){
		
		return "line name: " + name + "| vehicle: "+ vehicle.getName()+" | agency: "+ getAgency();
		
		
	}
	
}
