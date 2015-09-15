package uk.ac.abdn.fits.business.client.option;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.text.DecimalFormat;
import java.util.Calendar;

import uk.ac.abdn.fits.gmap.model.DirectionsResponse;
import uk.ac.abdn.fits.gmap.model.Step;
import uk.ac.abdn.fits.query.operator.Operator;


public class TOption {

	private String sourceType;
	private String sourceID;
	private double score;
	private double fare;
	private DirectionsResponse dir_response;
	private Operator operator;
	private boolean isRelaxed = false;
	private String relaxing_info = null;
	private String comments = "";
	
	public String getSourceType(){
		return sourceType;
	}
	
	public void setSourceType(String sourceType){
		this.sourceType = sourceType;
	}
	
	public String getSourceID(){
		return sourceID;
	}
	
	public void setSourceID(String sourceID){
		this.sourceID = sourceID;
	}
	
	public double getScore(){
		return score;
	}
	
	public void setScore(double score){
		this.score = score;
	}
	
	public double getFare(){
		return fare;
	}
	
	public void setFare(double fare){
		this.fare = fare;
	}
	
	public Operator getOperator(){
		return operator;
	}
	
	public void setOperator(Operator operator){
		this.operator = operator;
	}
	
	public void setDirectionsResponse(DirectionsResponse dr){
		this.dir_response = dr;
	}
	
	public DirectionsResponse getDirectionsResponse(){
		return dir_response;
	}
	
	public String getStart_address(){
		return dir_response.getStart_address();
	}
	
	public String getEnd_address(){
		return dir_response.getEnd_address();
	}
	
	public String getDepartureTime(){
		
		if(sourceType.equals("operators") || sourceType.equals("taxi")){
			return null;
		}else if(sourceType.equals("google_transit")){
			if(dir_response.getRoutes()[0].getLegs()[0].getDeparture_time() != null){
				return dir_response.getRoutes()[0].getLegs()[0].getDeparture_time().getText();
			}else{
				return "n/a";
			}
		}else if(sourceType.equals("registry_operator")){
			if(dir_response.getRoutes()[0].getLegs()[0].getDeparture_time() != null){
				return dir_response.getRoutes()[0].getLegs()[0].getDeparture_time().getText();
			}else{
				return "n/a";
			}
		}
		return null;
	}
	
	public String getArrivalTime(){
		
		if(sourceType.equals("operators") || sourceType.equals("taxi")){
			return null;
		}else if(sourceType.equals("google_transit")){
			if(dir_response.getRoutes()[0].getLegs()[0].getArrival_time() != null){
				return dir_response.getRoutes()[0].getLegs()[0].getArrival_time().getText();
			}else{
				return "n/a";
			}
		}else if(sourceType.equals("registry_operator")){
			if(dir_response.getRoutes()[0].getLegs()[0].getDeparture_time() != null){
				return dir_response.getRoutes()[0].getLegs()[0].getDeparture_time().getText();
			}else{
				return "n/a";
			}
		}
		return null;
	}
	
	public int getChanges(){
		
		if(sourceType.equals("google_transit")){
			int count = -1;
			for(Step step: dir_response.getRoutes()[0].getLegs()[0].getSteps()){
				if(step.getTravel_mode().equals("TRANSIT")){
					count += 1;
				}
			}
			return count;
		}
		return 0;
	}
	
	public String getFareInfo(){
		
		DecimalFormat df = new DecimalFormat("#.00");
		if(sourceType.equals("google_transit")){
			return "standard bus fares";
		}else if(sourceType.equals("taxi")){
			return "approximately &#163;"+df.format(fare);
		}else if(sourceType.equals("registry_operator")){
			if(fare == -100){
				return "free of charge";
			}else{
				return "approximately &#163;"+df.format(fare);
			}
		}else{
			if(fare == -1){
				return "Free";
			}else if(fare == -2){
				return "Equivalent to standard bus fares. Concession passes valid";
			}else if(fare == -3){
				return "Standard bus fares";
			}else{ // hire car
				return "approximately &#163;"+ df.format(fare) +" ("+ getOperator().getFare()+")";	
			}
		}
	}

	public String getChangesInfo(){
		StringBuilder sb = new StringBuilder();
		Step[] steps = dir_response.getRoutes()[0].getLegs()[0].getSteps();
		sb.append("<table>");
		for(int i=0; i< steps.length; i++){
			sb.append("<tr><td>");
			sb.append("Step "+ (i+1));
			sb.append("</td><td>");
			if(i < steps.length && steps[i].getTravel_mode().equals("WALKING")){
				if(i == 0){ // first step so the departure time is the journey's departure time
					sb.append("<p class=\"upper\">");
					if(dir_response.getRoutes()[0].getLegs()[0].getDeparture_time() != null){
						sb.append(dir_response.getRoutes()[0].getLegs()[0].getDeparture_time().getText());
						sb.append(", ");
					}else{
						sb.append("Depart from ");
					}
					sb.append(dir_response.getRoutes()[0].getLegs()[0].getStart_address());
					sb.append("</p>");
				}else if(steps[i-1].getTravel_mode().equals("TRANSIT")){ // if the previous leg is transit, using the arrival time of the previous leg
					sb.append("<p class=\"upper\">");
					sb.append(steps[i-1].getTransit_details().getArrival_time().getText());
					sb.append(", arrive at ");
					sb.append(steps[i-1].getTransit_details().getArrival_stop().getName());
					sb.append("</p>");
				}else if(!steps[i-1].getTravel_mode().equals("TRANSIT")){
					System.err.println("There must be some error. The previous step is not the Transit mode. There is no arrival time.");
				}
				sb.append("<p class=\"mid\">");
				sb.append("<img class=\"icon\" src=\"//maps.gstatic.com/mapfiles/transit/iw2/6/walk.png\"> </img>");
				sb.append(steps[i].getHtml_instructions().replaceFirst("Walk", "make your way "));
				sb.append("</p>");
				sb.append("<p class=\"lower\">");
				sb.append("about "+steps[i].getDuration().getText()+", "+steps[i].getDistance().getText());
				sb.append("</p>");
			}else if(i < steps.length && steps[i].getTravel_mode().equals("TRANSIT")){
				sb.append("<p class=\"upper\">");
				sb.append(steps[i].getTransit_details().getDeparture_time().getText());
				sb.append(", ");
				sb.append(steps[i].getTransit_details().getDeparture_stop().getName());
				sb.append("</p>");
				sb.append("<p class=\"mid\">");
				if(steps[i].getTransit_details().getLine().getVehicle().getType().equals("BUS")){
					sb.append("<img class=\"icon\" src=\"//maps.gstatic.com/mapfiles/transit/iw2/6/bus.png\"> </img>");
				}else if(steps[i].getTransit_details().getLine().getVehicle().getType().equals("HEAVY_RAIL")){ // train
					sb.append("<img class=\"icon\" src=\"//maps.gstatic.com/mapfiles/transit/iw2/6/uk-rail.png\"> </img>");
				}
				sb.append("take "+steps[i].getTransit_details().getLine().getVehicle().getName());
				sb.append(" ");
				sb.append("<b>"+steps[i].getTransit_details().getLine().getShort_name()+"</b>");
				sb.append("(<a href = \""+steps[i].getTransit_details().getLine().getAgencies()[0].getUrl()+"\" target = \"_blank\">");
				sb.append(steps[i].getTransit_details().getLine().getAgencies()[0].getName());
				sb.append("</a>) ");
				sb.append(" ");
				sb.append("towards ");
				sb.append(steps[i].getTransit_details().getHeadsign());
				sb.append("</p>");
				sb.append("<p class=\"lower\">");
				sb.append(steps[i].getDuration().getText()+"("+steps[i].getTransit_details().getNum_stops()+" stops)");
				sb.append("</p>");
			}else{
				if(steps[i-1].getTravel_mode().equals("WALKING")){
					sb.append("<p class=\"upper\">");
					if(dir_response.getRoutes()[0].getLegs()[0].getArrival_time() != null){
						sb.append(dir_response.getRoutes()[0].getLegs()[0].getArrival_time().getText());
						sb.append(", ");
					}else{
						sb.append("");
					}
					sb.append(dir_response.getRoutes()[0].getLegs()[0].getEnd_address());
					sb.append("</p>");
				}else{
					System.out.println("the prevous step is TRANSIT mode. There is no setting for this mode.");
				}
			}
			sb.append("</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	public boolean isRelaxed(){
		return isRelaxed;
	}
	
	public void setIsRelaxed(boolean isRelaxed){
		this.isRelaxed = isRelaxed;
	}
	
	public String getRelaxingInfo(){
		if(relaxing_info != null){
			return "Could be available if " + relaxing_info;
		}
			
		return null;
	}
	
	public void setRelaxingInfo(String info){
		this.relaxing_info = info;
	}
	
	public void addRelaxingInfo(String info){
		if(relaxing_info != null){
			relaxing_info += "; " + info;
		}
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}