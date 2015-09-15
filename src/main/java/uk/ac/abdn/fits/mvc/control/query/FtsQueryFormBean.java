package uk.ac.abdn.fits.mvc.control.query;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import org.hibernate.validator.constraints.NotEmpty;

public class FtsQueryFormBean {

	
	private String title;
	

	private String first_name;
	private String last_name;
	private String email;
	private String addr_line1;
	private String addr_line2;
	private String addr_line3;
	private String addr_line4;
	private String postcode;
	private String psg_CHI_num;
	@NotEmpty(message="required")
	private String street_number_f_outward;
	@NotEmpty(message="required")
	private String route_f_outward;
	@NotEmpty(message="required")
	private String locality_f_outward;
	@NotEmpty(message="required")
	private String postal_code_f_outward;
	private String country_f_outward = "uk";
	@NotEmpty(message="required")
	private String street_number_t_outward;
	@NotEmpty(message="required")
	private String route_t_outward;
	@NotEmpty(message="required")
	private String locality_t_outward;
	@NotEmpty(message="required")
	private String postal_code_t_outward;
//	@NotEmpty(message="required")
	private String country_t_outward = "uk";
	
	private boolean return_jrny_required;
	
	private String street_number_f_return;
	private String route_f_return;
	private String locality_f_return;
	private String postal_code_f_return;
	private String country_f_return = "uk";
	private String street_number_t_return;
	private String route_t_return;
	private String locality_t_return;
	private String postal_code_t_return;
//	@NotEmpty(message="required")
	private String country_t_return = "uk";
	
	
	private String time_constraint;
	
	private String time_constraint_rtn;
	
	@NotEmpty(message="required")
	private String datepicker;
	
	@NotEmpty(message="required")
	private String timepicker;
	
	private String datepicker_rtn;
	
	private String timepicker_rtn;
	
	
	private String age_group;
	private String mobility_status;
	private String journey_purpose;
	
	private String other_issue;
	
	

	private boolean any_accompanying;
	
	private boolean individual_transport;
	
	private boolean able_travel_outof_wheelchair;
	
//	private int accompany;
	
//	private int passenger_num;
	
	
//	private String passenger_type;
	
	
//	private int wheelchair_space;
	
	
	@NotEmpty
	private String prefered_options;
	
//	@NotEmpty
//	private String prefered_operators;
	
	
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr_line1() {
		return addr_line1;
	}

	public void setAddr_line1(String addr_line1) {
		this.addr_line1 = addr_line1;
	}

	public String getAddr_line2() {
		return addr_line2;
	}

	public void setAddr_line2(String addr_line2) {
		this.addr_line2 = addr_line2;
	}

	public String getAddr_line3() {
		return addr_line3;
	}

	public void setAddr_line3(String addr_line3) {
		this.addr_line3 = addr_line3;
	}

	public String getAddr_line4() {
		return addr_line4;
	}

	public void setAddr_line4(String addr_line4) {
		this.addr_line4 = addr_line4;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPsg_CHI_num() {
		return psg_CHI_num;
	}

	public void setPsg_CHI_num(String psg_CHI_num) {
		this.psg_CHI_num = psg_CHI_num;
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
	
	public boolean getAny_accompanying(){
		return any_accompanying;
	}
	
	public void setAny_accompanying(boolean any_accompanying){
		this.any_accompanying = any_accompanying;
	}

	public String getPrefered_options(){
		return prefered_options;
	}
	
	public void setPrefered_options(String prefered_options){
		this.prefered_options = prefered_options;
	}
	
//	public String getPrefered_operators(){
//		return prefered_operators;
//	}
//	
//	public void setPrefered_operators(String prefered_operators){
//		this.prefered_operators = prefered_operators;
//	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
        sb.append("properties name=");
//        if (postal_code_f != null) {
//        	sb.append("'").append(postal_code_f).append("', ");
//        } else {
//        	sb.append(street_number_f).append(", ");
//        }
//        sb.append("country_f=").append(country_f).append(", ");
//        sb.append("postal_code_t=").append(postal_code_t).append(", ");
//        sb.append("country_t=");
//        if (country_t != null) {
//        	sb.append("'").append(country_t).append("', ");
//        } else {
//        	sb.append(country_t).append(", ");
//        }
       
        sb.append("time_constraint=").append(time_constraint).append(", ");
        sb.append("datepicker=").append(datepicker.toString()).append(", ");
        sb.append("timepicker=").append(timepicker).append(", ");
       
        sb.append("prefered_options=").append(prefered_options).append(", ");
//        sb.append("prefered_operators=").append(prefered_operators);
        return sb.toString();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getStreet_number_f_outward() {
		return street_number_f_outward;
	}

	public void setStreet_number_f_outward(String street_number_f_outward) {
		this.street_number_f_outward = street_number_f_outward;
	}

	public String getRoute_f_outward() {
		return route_f_outward;
	}

	public void setRoute_f_outward(String route_f_outward) {
		this.route_f_outward = route_f_outward;
	}

	public String getLocality_f_outward() {
		return locality_f_outward;
	}

	public void setLocality_f_outward(String locality_f_outward) {
		this.locality_f_outward = locality_f_outward;
	}

	public String getPostal_code_f_outward() {
		return postal_code_f_outward;
	}

	public void setPostal_code_f_outward(String postal_code_f_outward) {
		this.postal_code_f_outward = postal_code_f_outward;
	}

	public String getCountry_f_outward() {
		return country_f_outward;
	}

	public void setCountry_f_outward(String country_f_outward) {
		this.country_f_outward = country_f_outward;
	}

	public String getStreet_number_t_outward() {
		return street_number_t_outward;
	}

	public void setStreet_number_t_outward(String street_number_t_outward) {
		this.street_number_t_outward = street_number_t_outward;
	}

	public String getRoute_t_outward() {
		return route_t_outward;
	}

	public void setRoute_t_outward(String route_t_outward) {
		this.route_t_outward = route_t_outward;
	}

	public String getLocality_t_outward() {
		return locality_t_outward;
	}

	public void setLocality_t_outward(String locality_t_outward) {
		this.locality_t_outward = locality_t_outward;
	}

	public String getPostal_code_t_outward() {
		return postal_code_t_outward;
	}

	public void setPostal_code_t_outward(String postal_code_t_outward) {
		this.postal_code_t_outward = postal_code_t_outward;
	}

	public String getCountry_t_outward() {
		return country_t_outward;
	}

	public void setCountry_t_outward(String country_t_outward) {
		this.country_t_outward = country_t_outward;
	}

	public String getStreet_number_f_return() {
		return street_number_f_return;
	}

	public void setStreet_number_f_return(String street_number_f_return) {
		this.street_number_f_return = street_number_f_return;
	}

	public String getRoute_f_return() {
		return route_f_return;
	}

	public void setRoute_f_return(String route_f_return) {
		this.route_f_return = route_f_return;
	}

	public String getLocality_f_return() {
		return locality_f_return;
	}

	public void setLocality_f_return(String locality_f_return) {
		this.locality_f_return = locality_f_return;
	}

	public String getPostal_code_f_return() {
		return postal_code_f_return;
	}

	public void setPostal_code_f_return(String postal_code_f_return) {
		this.postal_code_f_return = postal_code_f_return;
	}

	public String getCountry_f_return() {
		return country_f_return;
	}

	public void setCountry_f_return(String country_f_return) {
		this.country_f_return = country_f_return;
	}

	public String getStreet_number_t_return() {
		return street_number_t_return;
	}

	public void setStreet_number_t_return(String street_number_t_return) {
		this.street_number_t_return = street_number_t_return;
	}

	public String getRoute_t_return() {
		return route_t_return;
	}

	public void setRoute_t_return(String route_t_return) {
		this.route_t_return = route_t_return;
	}

	public String getLocality_t_return() {
		return locality_t_return;
	}

	public void setLocality_t_return(String locality_t_return) {
		this.locality_t_return = locality_t_return;
	}

	public String getPostal_code_t_return() {
		return postal_code_t_return;
	}

	public void setPostal_code_t_return(String postal_code_t_return) {
		this.postal_code_t_return = postal_code_t_return;
	}

	public String getCountry_t_return() {
		return country_t_return;
	}

	public void setCountry_t_return(String country_t_return) {
		this.country_t_return = country_t_return;
	}

	

	public boolean isIndividual_transport() {
		return individual_transport;
	}

	public void setIndividual_transport(boolean individual_transport) {
		this.individual_transport = individual_transport;
	}

	public boolean isAble_travel_outof_wheelchair() {
		return able_travel_outof_wheelchair;
	}

	public void setAble_travel_outof_wheelchair(boolean able_travel_outof_wheelchair) {
		this.able_travel_outof_wheelchair = able_travel_outof_wheelchair;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public boolean isReturn_jrny_required() {
		return return_jrny_required;
	}

	public void setReturn_jrny_required(boolean return_jrny_required) {
		this.return_jrny_required = return_jrny_required;
	}

	public String getDatepicker_rtn() {
		return datepicker_rtn;
	}

	public void setDatepicker_rtn(String datepicker_rtn) {
		this.datepicker_rtn = datepicker_rtn;
	}

	public String getTimepicker_rtn() {
		return timepicker_rtn;
	}

	public void setTimepicker_rtn(String timepicker_rtn) {
		this.timepicker_rtn = timepicker_rtn;
	}

	public String getTime_constraint_rtn() {
		return time_constraint_rtn;
	}

	public void setTime_constraint_rtn(String time_constraint_rtn) {
		this.time_constraint_rtn = time_constraint_rtn;
	}
	
	public String getOther_issue() {
		return other_issue;
	}

	public void setOther_issue(String other_issue) {
		this.other_issue = other_issue;
	}
}
