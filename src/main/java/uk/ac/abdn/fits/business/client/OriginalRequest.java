package uk.ac.abdn.fits.business.client;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.Calendar;

import uk.ac.abdn.fits.query.operator.PassengerAge;
import uk.ac.abdn.fits.query.operator.PassengerJourneyPurpose;
import uk.ac.abdn.fits.query.operator.PassengerMobilityStatus;


public class OriginalRequest {

	private String id;
	private String origin_address;
	private String destn_address;
	private Calendar arrival_time;
	private Calendar departure_time;
	private PassengerAge age_group;
	private PassengerMobilityStatus mobility_status;
	private PassengerJourneyPurpose purpose;
	private String otherEligIssue;
	
	private String[] preference; 
	private long logged_time;
	private boolean hasReturn;
	
	public OriginalRequest(){
		
	}
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public void setArriveTime(Calendar c){
		this.arrival_time = c;
	}
	public Calendar getArriveTime(){
		return arrival_time;
	}
	public void setDepartureTime(Calendar d){
		this.departure_time = d;
	}
	public Calendar getDepartureTime(){
		return departure_time;
	}
	public String getOriginAddress(){
		return origin_address;
	}
	public void setOriginAddress(String origin_address){
		this.origin_address = origin_address;
	}
	public String getDestnAddress(){
		return destn_address;
	}
	public void setDestnAddress(String destn_address){
		this.destn_address = destn_address;
	}
	public long getLoggedTime(){
		return logged_time;
	}
	public void setLoggedTime(long logged_time){
		this.logged_time = logged_time;
	}
	public void setAgeGroup(PassengerAge age_group){
		this.age_group = age_group;
	}
	public PassengerAge getAgeGroup(){
		return age_group;
	}
	public PassengerMobilityStatus getMobilityStatus(){
		return mobility_status;
	}
	public void setMobilityStatus(PassengerMobilityStatus mobility_status){
		this.mobility_status = mobility_status;
	}
	public PassengerJourneyPurpose getPurpose(){
		return purpose;
	}
	public void setPurpose(PassengerJourneyPurpose purpose){
		this.purpose = purpose;
	}
	public void setPreference(String[] preference){
		this.preference = preference;
	}
	public String[] getPreference(){
		return preference;
	}
	public String getOtherEligIssue() {
		return otherEligIssue;
	}
	public void setOtherEligIssue(String otherEligIssue) {
		this.otherEligIssue = otherEligIssue;
	}
	public boolean isHasReturn() {
		return hasReturn;
	}
	public void setHasReturn(boolean hasReturn) {
		this.hasReturn = hasReturn;
	}

	
}
