package uk.ac.abdn.fits.reasoner.utils.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TransportOption {
	
	String providerId;
	
	Route route;
	
	Request request;
	
	double disutilities;
	
	double fare;
	
	int changes;
	
	public String getProviderId(){
		return providerId;
	}
	
	public void setProviderId(String providerId){
		
		this.providerId = providerId;
	}
	
	public Route getRoute(){
		return route;
	}
	
	public void setRoute(Route route){
		this.route = route;
	}
	
	public int getChanges(){
		return changes;
	}
	
	public void setChanges(int changes){
		this.changes = changes;
	}
	
	public Request getRequest(){
		return request;
	}
	
	public void setRequest(Request request){
		this.request = request;
	}
	
	public double getDisutilities(){
		return disutilities;
	}
	
	public void setDisutilities(double disutilities){
		this.disutilities = disutilities;
	}
	
	public double getFare(){
		return fare;
	}
	
	public void setFare(double fare){
		
		this.fare = fare;
	}
}
