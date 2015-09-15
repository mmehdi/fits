package uk.ac.abdn.fits.gmap.model;


/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionsResponse {
	
	private String status;
	private Route[] routes;
	
	public DirectionsResponse(){
	}
	
	public DirectionsResponse(DirectionsResponse directions){
		
		status = directions.getStatus();
		routes = new Route[directions.getRoutes().length];
		for(int i = 0; i< directions.getRoutes().length; i++){
			routes[i] = new Route(directions.getRoutes()[i]);
		}
	}
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
//	public Calendar getTravelling_time(){
//		return travelling_time;
//	}
//	
//	public void setTravelling_time(Calendar travelling_time){
//		
//		this.travelling_time = travelling_time;
//	}
//	
//	public String getSourceType(){
//		return sourceType;
//	}
//	
//	public void setSourceType(String sourceType){
//		
//		this.sourceType = sourceType;
//	}
//	
//	public String getSourceID(){
//		return sourceID;
//	}
//	
//	public void setSourceID(String sourceID){
//		
//		this.sourceID = sourceID;
//	}
//	
//	public double getScore(){
//		return score;
//	}
//	public void setScore(double score){
//		this.score = score;
//	}
	
	public Route[] getRoutes(){
		return routes;
	}
	public void setRoutes(Route[] routes){
		this.routes = routes;
	}
	
	
	
//	public String getDuration(){
//		if(sourceType.equals("google_transit")){
//			return getRoutes()[0].getLegs()[0].getDuration().getText();
//		}
//		return null;
//	}
	
	public String getStart_address(){
		return getRoutes()[0].getLegs()[0].getStart_address();
	}
	public String getEnd_address(){
		return getRoutes()[0].getLegs()[0].getEnd_address();
	}
	
	public boolean isWalkingDir(){
		
		for(Step step: getRoutes()[0].getLegs()[0].getSteps()){
			if(!step.getTravel_mode().equals("WALKING")){
				return false;
			}
		}
		return true;
	}
	
	public int hashcode(){
		return status.hashCode();
	}
	
	public boolean equals(Object o){
		
		if(o instanceof DirectionsResponse){
			DirectionsResponse c = (DirectionsResponse)o;
			if(c.getStatus().equals(status)){
				if(c.getRoutes()[0].getLegs()[0].getDeparture_time() != null){
					if(c.getRoutes()[0].getLegs()[0].getDeparture_time().text.equals(routes[0].getLegs()[0].getDeparture_time().getText())){
						System.out.println("direction response is the same.");
						return true;
					}
				}else{
					return true;
				}
			}
		}
		return false;
	}

}
