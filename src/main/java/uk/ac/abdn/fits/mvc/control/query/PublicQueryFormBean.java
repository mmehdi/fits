package uk.ac.abdn.fits.mvc.control.query;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.Date;
import java.util.Map;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class PublicQueryFormBean {

	
	private String street_number_f;
	
	private String route_f;
	
	@NotEmpty(message="*")
	private String locality_f;
	
	@NotEmpty(message="*")
	private String postal_code_f;
	
	@NotEmpty(message="*")
	private String country_f;
	
	
	private String street_number_t;
	

	private String route_t;
	
	@NotEmpty(message="*")
	private String locality_t;
	
	@NotEmpty(message="*")
	private String postal_code_t;
	
	@NotEmpty(message="*")
	private String country_t;
	
	
	private String time_constraint;
	
	@NotEmpty(message="*")
	private String datepicker;
	
	@NotEmpty(message="*")
	private String timepicker;
	
	
	private String age_group;
	private String mobility_status;
	private String journey_purpose;
	
	private int passenger_num;
	
	
	public String getStreet_number_f(){
		return street_number_f;
	}
	
	public void setStreet_number_f(String street_number_f){
		this.street_number_f = street_number_f;
	}
	
	public String getRoute_f(){
		return route_f;
	}
	
	public void setRoute_f(String route_f){
		this.route_f = route_f;
	}
	
	public String getPostal_code_f(){
		return postal_code_f;
	}
	
	public void setPostal_code_f(String postal_code_f){
		this.postal_code_f = postal_code_f;
	}
	
	public String getLocality_f(){
		return locality_f;
	}
	
	public void setLocality_f(String locality_f){
		this.locality_f = locality_f;
	}
	
	public String getCountry_f(){
		return country_f;
	}
	
	public void setCountry_f(String country_f){
		this.country_f = country_f;
	}
	
	
	public String getStreet_number_t(){
		return street_number_t;
	}
	
	public void setStreet_number_t(String street_number_t){
		this.street_number_t = street_number_t;
	}
	
	public String getRoute_t(){
		return route_t;
	}
	
	public void setRoute_t(String route_t){
		this.route_t = route_t;
	}
	
	public String getPostal_code_t(){
		return postal_code_t;
	}
	
	public void setPostal_code_t(String postal_code_t){
		this.postal_code_t = postal_code_t;
	}
	
	public String getLocality_t(){
		return locality_t;
	}
	
	public void setLocality_t(String locality_t){
		this.locality_t = locality_t;
	}
	
	public String getCountry_t(){
		return country_t;
	}
	
	public void setCountry_t(String country_t){
		this.country_t = country_t;
	}
	
	public String getTime_constraint() {
		return time_constraint;
	}

	public void setTime_constraint(String time_constraint) {
		this.time_constraint = time_constraint;
	}
	
	public String getDatepicker() {
		return datepicker;
	}

	public void setDatepicker(String datepicker) {
		this.datepicker = datepicker;
	}
	
	
	public String getTimepicker() {
		return timepicker;
	}

	public void setTimepicker(String timepicker) {
		this.timepicker = timepicker;
	}
	
	public String getAge_group(){
		return age_group;
	}
	
	public void setAge_group(String age_group){
		this.age_group = age_group;
	}
	
	public int getPassenger_num(){
		return passenger_num;
	}
	
	public void setPassenger_num(int passenger_num){
		this.passenger_num = passenger_num;
	}
	
	
	public String getMobility_status(){
		return mobility_status;
	}
	
	public void setMobility_status(String mobility_status){
		this.mobility_status = mobility_status;
	}
	
	public String getJourney_purpose(){
		return journey_purpose;
	}
	
	public void setJourney_purpose(String journey_purpose){
		this.journey_purpose = journey_purpose;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
        sb.append("properties name=");
        if (postal_code_f != null) {
        	sb.append("'").append(postal_code_f).append("', ");
        } else {
        	sb.append(street_number_f).append(", ");
        }
        sb.append("country_f=").append(country_f).append(", ");
        sb.append("postal_code_t=").append(postal_code_t).append(", ");
        sb.append("country_t=");
        if (country_t != null) {
        	sb.append("'").append(country_t).append("', ");
        } else {
        	sb.append(country_t).append(", ");
        }
       
        sb.append("time_constraint=").append(time_constraint).append(", ");
        sb.append("datepicker=").append(datepicker.toString()).append(", ");
        sb.append("timepicker=").append(timepicker).append(", ");
       
        return sb.toString();
	}
	
}
