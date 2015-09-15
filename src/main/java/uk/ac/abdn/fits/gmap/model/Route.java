package uk.ac.abdn.fits.gmap.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {
	
	private String summary;
	private Leg[] legs;
	private String copyrights;
//	private overview_polyline // ignore
	private String[]  warnings;
//	private bounds // ignore
	
	public Route(){
	}
	
	public Route(Route r){
		summary = r.getSummary();
		legs = r.getLegs();
		legs = new Leg[r.getLegs().length];
		for(int i = 0; i< r.getLegs().length; i++){
			legs[i] = new Leg(r.getLegs()[i]);
		}
		copyrights = r.getCopyrights();
		warnings = r.getWarnings();
	}
	
	public String getSummary(){
		return summary;
	}
	public void setSummary(String summary){
		this.summary = summary;
	}
	public Leg[] getLegs(){
		return legs;
	}
	public void setLeg(Leg[] legs){
		this.legs = legs;
	}
	public String getCopyrights(){
		return copyrights;
	}
	public void setCopyrights(String copyrights){
		this.copyrights = copyrights;
	}
	public String[] getWarnings(){
		return warnings;
	}
	public void setWarnings(String[] warnings){
		this.warnings = warnings;
	}
}
