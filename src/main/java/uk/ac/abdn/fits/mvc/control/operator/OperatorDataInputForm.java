package uk.ac.abdn.fits.mvc.control.operator;


/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */


public class OperatorDataInputForm {

	private int operator_id;
	private String service_name;
	private String service_description;
	private String how_to_book;
	private String type_of_permit;
	/* operating time */
	private boolean cb_not_avail;
	private String not_valid_from;
  	private String not_valid_to;
  	private String added_service_not_avail;
  	private String tab_operating_hours_field_monday_1;
  	private String tab_operating_hours_field_monday_2;
	private String tab_operating_hours_field_monday_3;
  	private String tab_operating_hours_field_monday_4;
  	private String tab_operating_hours_field_tuesday_1;
	private String tab_operating_hours_field_tuesday_2;
	private String tab_operating_hours_field_tuesday_3;
	private String tab_operating_hours_field_tuesday_4;
	private String tab_operating_hours_field_wednesday_1;
	private String tab_operating_hours_field_wednesday_2;
	private String tab_operating_hours_field_wednesday_3;
	private String tab_operating_hours_field_wednesday_4;
	private String tab_operating_hours_field_thursday_1;
	private String tab_operating_hours_field_thursday_2;
	private String tab_operating_hours_field_thursday_3;
	private String tab_operating_hours_field_thursday_4;
	private String tab_operating_hours_field_friday_1;
	private String tab_operating_hours_field_friday_2;
	private String tab_operating_hours_field_friday_3;
	private String tab_operating_hours_field_friday_4;
	private String tab_operating_hours_field_saturday_1;
	private String tab_operating_hours_field_saturday_2;
	private String tab_operating_hours_field_saturday_3;
	private String tab_operating_hours_field_saturday_4;
	private String tab_operating_hours_field_sunday_1;
	private String tab_operating_hours_field_sunday_2;
	private String tab_operating_hours_field_sunday_3;
	private String tab_operating_hours_field_sunday_4;
	/* vehicle*/
	private String regNum;
	private String vehicle_type;
	private String other_vehicle;
	
	/* operating area */
//	@NotEmpty(message="required")
	private String jsonData;
//	@NotEmpty(message="required")
	private String kmlData;
	
	private String eligible_checkbox_able_bodied;//if checked, the value is 6.
	private String eligible_checkbox_mobility_prevents_PT;//if checked, the value is 7.
	private String eligible_checkbox_disable_wheelchair_user;//if checked, the value is 8.
	private String eligible_checkbox_disable_other;//if checked, the value is 9.
	private String eligible_checkbox_cant_live_on_a_bus_route; // if checked, the value is 17

	private String eligible_checkbox_under16; //if checked, the value is 1.
	private String eligible_checkbox_17_to_21; //if checked, the value is 2.
	private String eligible_checkbox_22_to_54; //if checked, the value is 3.
	private String eligible_checkbox_55_to_59; //if checked, the value is 4.
	private String eligible_checkbox_over60;//if checked, the value is 5.
	
	private String eligible_checkbox_health_appointment;//if checked, the value is 10.
	private String eligible_checkbox_social_care;//if checked, the value is 11.
	private String eligible_checkbox_shopping;//if checked, the value is 12.
	private String eligible_checkbox_leisure_or_visiting_friends;//if checked, the value is 13.
	private String eligible_checkbox_school_or_education; // if checked, the value is 14.
	private String eligible_checkbox_work_or_commuting;//if checked, the value is 15.
	private String eligible_checkbox_other_purpose;//if checked, the value is 16.
	private String other_elig_type;
	private String other_elig;
	private String tab_elig_radioBtns = "0";
	private String explain_opening_up_elig;
	
	private String tab_fare_structure_radioBtns = "0";// if yes, the value is 0. If no, the value is 1. The default is 0.
	private String tab_fare_structure_how_charge_radioBtns = "0";
	private double fare_dist1_mile_2;
	private String fare_dist1_type;
	private double fare_dist1_charge;
	private double fare_dist2_mile_1;
	private double fare_dist2_mile_2;
	private String fare_dist2_type;
	private double fare_dist2_charge;
	private double fare_dist3_mile_1;
	private double fare_dist3_mile_2;
	private String fare_dist3_type;
	private double fare_dist3_charge;
	private double fare_dist4_mile_1;
	private double fare_dist4_mile_2;
	private String fare_dist4_type;
	private double fare_dist4_charge;
	private double return_fare_multiplier;
	private double discount_for_over60;
	private double discount_for_under16;
	private double discount_for_other_concessionary;
	private boolean fare_structure_checkbox_escort;
	private boolean fare_structure_checkbox_charge_for_dead_mileage;
	private String added_fare_dist;
	
	/* surcharge */
	private double surcharge_dist1_mile;
	private String surcharge_dist1_type;
	private double surcharge_dist1_charge;
	private double surcharge_dist2_1;
	private double surcharge_dist2_2;
	private String surcharge_dist2_type;
	private double surcharge_dist2_charge;
	private double surcharge_dist3_mile_1;
	private double surcharge_dist3_mile_2;
	private String surcharge_dist3_type;
	private double surcharge_dist3_charge;
	private double surcharge_dist4_mile_1;
	private double surcharge_dist4_mile_2;
	private String surcharge_dist4_type;
	private double surcharge_dist4_charge;
	private boolean surcharge_checkbox_under16;
	private double surcharge_under_16_num;
	private boolean surcharge_checkbox_17_to_21;
	private double surcharge_17_21_years_old_num;
	private boolean surcharge_checkbox_22_to_54;
	private double surcharge_22_54_years_old_num;
	private boolean surcharge_checkbox_55_to_59;
	private double surcharge_55_59_years_old_num;
	private boolean surcharge_checkbox_over60;
	private double surcharge_over_60_years_old_num;
	private boolean surcharge_checkbox_able_bodied;
	private double surcharge_able_bodied_num;
	private boolean surcharge_checkbox_mobility_prevents_PT;
	private double surcharge_mobility_prevents_PT_num;
	private boolean surcharge_checkbox_diable_wheelchair_user;
	private double surcharge_disable_wheelchair_user_num;
	private boolean surcharge_checkbox_disable_other;
	private double surcharge_disable_other_num;
	private boolean surcharge_checkbox_health_appointment;
	private double surcharge_health_appointment_num;
	private boolean surcharge_checkbox_social_care;
	private double surcharge_social_care_num;
	private boolean surcharge_checkbox_shopping;
	private double surcharge_shopping_num;
	private boolean surcharge_checkbox_leisure_visiting_friends;
	private double surcharge_leisure_visiting_friends_num;
	private boolean surcharge_checkbox_school_education;
	private double surcharge_school_education_num;
	private boolean surcharge_checkbox_work_commuting;
	private double surcharge_work_commuting_num;
	private boolean surcharge_checkbox_other_purpose;
	private double surcharge_other_purpose_num;
	private boolean surcharge_checkbox_start_05_hours_earlier;
	private double surcharge_start_05_hours_earlier;
	private boolean surcharge_checkbox_start_1_hour_earlier;
	private double surcharge_start_1_hour_earlier;
	private boolean surcharge_checkbox_start_15_hours_earlier;
	private double surcharge_start_15_hours_earlier;
	private boolean surcharge_checkbox_finish_05_hours_later;
	private double surcharge_finish_05_hours_later;
	private boolean surcharge_checkbox_finish_1_hour_later;
	private double surcharge_finish_1_hour_later;
	private boolean surcharge_checkbox_finish_15_hours_later;
	private double surcharge_finish_15_hours_later;
	
	public int getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(int operator_id) {
		this.operator_id = operator_id;
	}
	public String getEligible_checkbox_17_to_21(){
		return eligible_checkbox_17_to_21;
	}
	public void setEligible_checkbox_17_to_21(String eligible_checkbox_17_to_21){
		this.eligible_checkbox_17_to_21 = eligible_checkbox_17_to_21;
	}
	
	public String getEligible_checkbox_22_to_54(){
		return eligible_checkbox_22_to_54;
	}
	public void setEligible_checkbox_22_to_54(String eligible_checkbox_22_to_54){
		this.eligible_checkbox_22_to_54 = eligible_checkbox_22_to_54;
	}
	public String getEligible_checkbox_55_to_59(){
		return eligible_checkbox_55_to_59;
	}
	public void setEligible_checkbox_55_to_59(String eligible_checkbox_55_to_59){
		this.eligible_checkbox_55_to_59 = eligible_checkbox_55_to_59;
	}
	public String getEligible_checkbox_over60(){
		return eligible_checkbox_over60;
	}
	public void setEligible_checkbox_over60(String eligible_checkbox_over60){
		this.eligible_checkbox_over60=eligible_checkbox_over60;
	}
	
	public String getEligible_checkbox_able_bodied(){
		return eligible_checkbox_able_bodied;
	}
	public void setEligible_checkbox_able_bodied(String eligible_checkbox_able_bodied){
		this.eligible_checkbox_able_bodied=eligible_checkbox_able_bodied;
	}
	
	public String getEligible_checkbox_mobility_prevents_PT(){
		return eligible_checkbox_mobility_prevents_PT;
	}
	public void setEligible_checkbox_mobility_prevents_PT(String eligible_checkbox_mobility_prevents_PT){
		this.eligible_checkbox_mobility_prevents_PT=eligible_checkbox_mobility_prevents_PT;
	}
	
	public String getEligible_checkbox_disable_wheelchair_user(){
		return eligible_checkbox_disable_wheelchair_user;
	}
	public void setEligible_checkbox_disable_wheelchair_user(String eligible_checkbox_disable_wheelchair_user){
		this.eligible_checkbox_disable_wheelchair_user = eligible_checkbox_disable_wheelchair_user;
	}
	
	public String getEligible_checkbox_disable_other(){
		return eligible_checkbox_disable_other;
	}
	public void setEligible_checkbox_disable_other(String eligible_checkbox_disable_other){
		this.eligible_checkbox_disable_other=eligible_checkbox_disable_other;
	}
	
	
	public String getEligible_checkbox_health_appointment(){
		return eligible_checkbox_health_appointment;
	}
	public void setEligible_checkbox_health_appointment(String eligible_checkbox_health_appointment){
		this.eligible_checkbox_health_appointment= eligible_checkbox_health_appointment;
	}
	
	public String getEligible_checkbox_social_care(){
		return eligible_checkbox_social_care;
	}
	public void setEligible_checkbox_social_care(String eligible_checkbox_social_care){
		this.eligible_checkbox_social_care=eligible_checkbox_social_care;
	}
	public String getEligible_checkbox_shopping(){
		return eligible_checkbox_shopping;
	}
	public void setEligible_checkbox_shopping(String eligible_checkbox_shopping){
		this.eligible_checkbox_shopping = eligible_checkbox_shopping;
	}
	public String getEligible_checkbox_leisure_or_visiting_friends(){
		return eligible_checkbox_leisure_or_visiting_friends;
	}
	public void setEligible_checkbox_leisure_or_visiting_friends(String eligible_checkbox_leisure_or_visiting_friends){
		this.eligible_checkbox_leisure_or_visiting_friends = eligible_checkbox_leisure_or_visiting_friends;
	}
	public String getEligible_checkbox_school_or_education(){
		return eligible_checkbox_school_or_education;
	}
	public void setEligible_checkbox_school_or_education(String eligible_checkbox_school_or_education){
		this.eligible_checkbox_school_or_education = eligible_checkbox_school_or_education;
	}
	
	public String getEligible_checkbox_work_or_commuting(){
		return eligible_checkbox_work_or_commuting;
	}
	public void setEligible_checkbox_work_or_commuting(String eligible_checkbox_work_or_commuting){
		this.eligible_checkbox_work_or_commuting = eligible_checkbox_work_or_commuting;
	}
	
	public String getEligible_checkbox_other_purpose(){
		return eligible_checkbox_other_purpose;
	}
	public void setEligible_checkbox_other_purpose(String eligible_checkbox_other_purpose){
		this.eligible_checkbox_other_purpose = eligible_checkbox_other_purpose;
	}
	
	
	public double getFare_dist1_mile_2(){
		return fare_dist1_mile_2;
	}
	public void setFare_dist1_mile_2(double fare_distance1_mile){
		this.fare_dist1_mile_2 = fare_distance1_mile;
	}
	
	public String getFare_dist1_type(){
		return fare_dist1_type;
	}
	public void setFare_dist1_type(String fare_dist1_type){
		this.fare_dist1_type= fare_dist1_type;
	}
	
	
	public double getFare_dist1_charge(){
		return fare_dist1_charge;
	}
	public void setFare_dist1_charge(double fare_dist1_charge){
		this.fare_dist1_charge = fare_dist1_charge;
	}
	
	public double getFare_dist2_mile_1(){
		return fare_dist2_mile_1;
	}
	public void setFare_dist2_mile_1(double fare_dist2_mile_1){
		this.fare_dist2_mile_1 = fare_dist2_mile_1;
	}
	
	public double getFare_dist2_mile_2(){
		return fare_dist2_mile_2;
	}
	public void setFare_dist2_mile_2(double fare_dist2_mile_2){
		this.fare_dist2_mile_2 = fare_dist2_mile_2;
	}
	
	public String getFare_dist2_type(){
		return fare_dist2_type;
	}
	public void setFare_dist2_type(String fare_dist2_type){
		this.fare_dist2_type= fare_dist2_type;
	}
	
	
	public double getFare_dist2_charge(){
		return fare_dist2_charge;
	}
	public void setFare_dist2_charge(double fare_dist2_charge){
		this.fare_dist2_charge=fare_dist2_charge;
	}

	
	public double getFare_dist3_mile_1(){
		return fare_dist3_mile_1;
	}
	public void setFare_dist3_mile_1(double fare_dist3_mile_1){
		this.fare_dist3_mile_1 = fare_dist3_mile_1;
	}
	
	public double getFare_dist3_mile_2(){
		return fare_dist3_mile_2;
	}
	public void setFare_dist3_mile_2(double fare_dist3_mile_2){
		this.fare_dist3_mile_2 = fare_dist3_mile_2;
	}
	
	
	public String getFare_dist3_type(){
		return fare_dist3_type;
	}
	public void setFare_dist3_type(String fare_dist3_type){
		this.fare_dist3_type = fare_dist3_type;
	}
	public double getFare_dist3_charge(){
		return fare_dist3_charge;
	}
	public void setFare_dist3_charge(double fare_dist3_charge){
		this.fare_dist3_charge = fare_dist3_charge;
	}
	
	public double getFare_dist4_mile_1(){
		return fare_dist4_mile_1;
	}
	public void setFare_dist4_mile_1(double fare_dist4_mile_1){
		this.fare_dist4_mile_1 = fare_dist4_mile_1;
	}
	
	public double getFare_dist4_mile_2(){
		return fare_dist4_mile_2;
	}
	public void setFare_dist4_mile_2(double fare_dist4_mile_2){
		this.fare_dist4_mile_2=fare_dist4_mile_2;
	}
	
	
	public String getFare_dist4_type(){
		return fare_dist4_type;
	}
	public void setFare_dist4_type(String fare_dist4_type){
		this.fare_dist4_type=fare_dist4_type;
	}
	
	
	public double getFare_dist4_charge(){
		return fare_dist4_charge;
	}
	public void setFare_dist4_charge(double fare_dist4_charge){
		this.fare_dist4_charge = fare_dist4_charge;
	}
	
	
	public double getReturn_fare_multiplier(){
		return return_fare_multiplier;
	}
	public void setReturn_fare_multiplier(double return_fare_multiplier){
		this.return_fare_multiplier=return_fare_multiplier;
	}
	
	public double getDiscount_for_over60(){
		return discount_for_over60;
	}
	public void setDiscount_for_over60(double discount_for_over60){
		this.discount_for_over60=discount_for_over60;
	}
	
	
	public double getDiscount_for_under16(){
		return discount_for_under16;
	}
	public void setDiscount_for_under16(double discount_for_under16){
		this.discount_for_under16 = discount_for_under16;
	}
	
	public boolean isFare_structure_checkbox_escort(){
		return fare_structure_checkbox_escort;
	}
	public void setFare_structure_checkbox_escort(boolean fare_structure_checkbox_escort){
		this.fare_structure_checkbox_escort=fare_structure_checkbox_escort;
	}
	
	
	public boolean isFare_structure_checkbox_charge_for_dead_mileage(){
		return fare_structure_checkbox_charge_for_dead_mileage;
	}
	public void setFare_structure_checkbox_charge_for_dead_mileage(boolean fare_structure_checkbox_charge_for_dead_mileage){
		this.fare_structure_checkbox_charge_for_dead_mileage = fare_structure_checkbox_charge_for_dead_mileage;
	}
	
	
	public double getSurcharge_dist1_mile(){
		return surcharge_dist1_mile;
	}
	public void setSurcharge_dist1_mile(double surcharge_dist1_mile){
		this.surcharge_dist1_mile = surcharge_dist1_mile;
	}
	
	public String getSurcharge_dist1_type(){
		return surcharge_dist1_type;
	}
	public void setSurcharge_dist1_type(String surcharge_dist1_type){
		this.surcharge_dist1_type=surcharge_dist1_type;
	}
	
	public double getSurcharge_dist1_charge(){
		return surcharge_dist1_charge;
	}
	public void setSurcharge_dist1_charge(double surcharge_dist1_charge){
		this.surcharge_dist1_charge = surcharge_dist1_charge;
	}
	
	public double getSurcharge_dist2_1(){
		return surcharge_dist2_1;
	}
	public void setSurcharge_dist2_1(double surcharge_dist2_1){
		this.surcharge_dist2_1 = surcharge_dist2_1;
	}
	
	public double getSurcharge_dist2_2(){
		return surcharge_dist2_2;
	}
	public void setSurcharge_dist2_2(double surcharge_dist2_2){
		this.surcharge_dist2_2=surcharge_dist2_2;
	}
	
	public String getSurcharge_dist2_type(){
		return surcharge_dist2_type;
	}
	public void setSurcharge_dist2_type(String surcharge_dist2_type){
		this.surcharge_dist2_type = surcharge_dist2_type;
	}
	
	
	public double getSurcharge_dist2_charge(){
		return surcharge_dist2_charge;
	}
	public void setSurcharge_dist2_charge(double surcharge_dist2_charge){
		this.surcharge_dist2_charge = surcharge_dist2_charge;
	}
	
	
	public double getSurcharge_dist3_mile_1(){
		return surcharge_dist3_mile_1;
	}
	public void setSurcharge_dist3_mile_1(double surcharge_dist3_mile_1){
		this.surcharge_dist3_mile_1 = surcharge_dist3_mile_1;
	}
	
	
	public double getSurcharge_dist3_mile_2(){
		return surcharge_dist3_mile_2;
	}
	public void setSurcharge_dist3_mile_2(double surcharge_dist3_mile_2){
		this.surcharge_dist3_mile_2=surcharge_dist3_mile_2;
	}
	
	
	public String getSurcharge_dist3_type(){
		return surcharge_dist3_type;
	}
	public void setSurcharge_dist3_type(String surcharge_dist3_type){
		this.surcharge_dist3_type = surcharge_dist3_type;
	}
	
	
	public double getSurcharge_dist3_charge(){
		return surcharge_dist3_charge;
	}
	public void setSurcharge_dist3_charge(double surcharge_dist3_charge){
		this.surcharge_dist3_charge=surcharge_dist3_charge;
	}
	
	
	public double getSurcharge_dist4_mile_1(){
		return surcharge_dist4_mile_1;
	}
	public void setSurcharge_dist4_mile_1(double surcharge_dist4_mile_1){
		this.surcharge_dist4_mile_1 = surcharge_dist4_mile_1;
	}
	
	public double getSurcharge_dist4_mile_2(){
		return surcharge_dist4_mile_2;
	}
	public void setSurcharge_dist4_mile_2(double surcharge_dist4_mile_2){
		this.surcharge_dist4_mile_2 =surcharge_dist4_mile_2;
	}
	
	
	public String getSurcharge_dist4_type(){
		return surcharge_dist4_type;
	}
	public void setSurcharge_dist4_type(String surcharge_dist4_type){
		this.surcharge_dist4_type=surcharge_dist4_type;
	}
	
	
	public double getSurcharge_dist4_charge(){
		return surcharge_dist4_charge;
	}
	public void setSurcharge_dist4_charge(double surcharge_dist4_charge){
		this.surcharge_dist4_charge=surcharge_dist4_charge;
	}
	
	
	
	
	
	
	public String getService_name(){
		return service_name;
	}
	public void setService_name(String service_name){
		this.service_name = service_name;
	}
	
	public String getJsonData(){
		return jsonData;
	}
	
	public void setJsonData(String jsonStr){
		this.jsonData = jsonStr;
	}
	
	public String getKmlData(){
		return kmlData;
	}
	
	public void setKmlData(String kmlStr){
		this.kmlData = kmlStr;
	}
	
	public String getEligible_checkbox_under16(){
		return eligible_checkbox_under16;
	}
	
	public void setEligible_checkbox_under16(String eligible_checkbox_under16){
		this.eligible_checkbox_under16 = eligible_checkbox_under16;
	}
	
	
	public String toString(){
		return "json:\n"+jsonData +" \nkml:\n"+ kmlData;
	}
	public boolean isSurcharge_checkbox_under16() {
		return surcharge_checkbox_under16;
	}
	public void setSurcharge_checkbox_under16(boolean surcharge_checkbox_under16) {
		this.surcharge_checkbox_under16 = surcharge_checkbox_under16;
	}
	public double getSurcharge_under_16_num() {
		return surcharge_under_16_num;
	}
	public void setSurcharge_under_16_num(double surcharge_under_16_num) {
		this.surcharge_under_16_num = surcharge_under_16_num;
	}
	public boolean isSurcharge_checkbox_17_to_21() {
		return surcharge_checkbox_17_to_21;
	}
	public void setSurcharge_checkbox_17_to_21(boolean surcharge_checkbox_17_to_21) {
		this.surcharge_checkbox_17_to_21 = surcharge_checkbox_17_to_21;
	}
	public double getSurcharge_17_21_years_old_num() {
		return surcharge_17_21_years_old_num;
	}
	public void setSurcharge_17_21_years_old_num(
			double surcharge_17_21_years_old_num) {
		this.surcharge_17_21_years_old_num = surcharge_17_21_years_old_num;
	}
	public boolean isSurcharge_checkbox_22_to_54() {
		return surcharge_checkbox_22_to_54;
	}
	public void setSurcharge_checkbox_22_to_54(boolean surcharge_checkbox_22_to_54) {
		this.surcharge_checkbox_22_to_54 = surcharge_checkbox_22_to_54;
	}
	public double getSurcharge_22_54_years_old_num() {
		return surcharge_22_54_years_old_num;
	}
	public void setSurcharge_22_54_years_old_num(
			double surcharge_22_54_years_old_num) {
		this.surcharge_22_54_years_old_num = surcharge_22_54_years_old_num;
	}
	public boolean isSurcharge_checkbox_55_to_59() {
		return surcharge_checkbox_55_to_59;
	}
	public void setSurcharge_checkbox_55_to_59(boolean surcharge_checkbox_55_to_59) {
		this.surcharge_checkbox_55_to_59 = surcharge_checkbox_55_to_59;
	}
	public double getSurcharge_55_59_years_old_num() {
		return surcharge_55_59_years_old_num;
	}
	public void setSurcharge_55_59_years_old_num(
			double surcharge_55_59_years_old_num) {
		this.surcharge_55_59_years_old_num = surcharge_55_59_years_old_num;
	}
	public boolean isSurcharge_checkbox_over60() {
		return surcharge_checkbox_over60;
	}
	public void setSurcharge_checkbox_over60(boolean surcharge_checkbox_over60) {
		this.surcharge_checkbox_over60 = surcharge_checkbox_over60;
	}
	public double getSurcharge_over_60_years_old_num() {
		return surcharge_over_60_years_old_num;
	}
	public void setSurcharge_over_60_years_old_num(
			double surcharge_over_60_years_old_num) {
		this.surcharge_over_60_years_old_num = surcharge_over_60_years_old_num;
	}
	public boolean isSurcharge_checkbox_able_bodied() {
		return surcharge_checkbox_able_bodied;
	}
	public void setSurcharge_checkbox_able_bodied(
			boolean surcharge_checkbox_able_bodied) {
		this.surcharge_checkbox_able_bodied = surcharge_checkbox_able_bodied;
	}
	public double getSurcharge_able_bodied_num() {
		return surcharge_able_bodied_num;
	}
	public void setSurcharge_able_bodied_num(double surcharge_able_bodied_num) {
		this.surcharge_able_bodied_num = surcharge_able_bodied_num;
	}
	public boolean isSurcharge_checkbox_mobility_prevents_PT() {
		return surcharge_checkbox_mobility_prevents_PT;
	}
	public void setSurcharge_checkbox_mobility_prevents_PT(
			boolean surcharge_checkbox_mobility_prevents_PT) {
		this.surcharge_checkbox_mobility_prevents_PT = surcharge_checkbox_mobility_prevents_PT;
	}
	public double getSurcharge_mobility_prevents_PT_num() {
		return surcharge_mobility_prevents_PT_num;
	}
	public void setSurcharge_mobility_prevents_PT_num(
			double surcharge_mobility_prevents_PT_num) {
		this.surcharge_mobility_prevents_PT_num = surcharge_mobility_prevents_PT_num;
	}
	public boolean isSurcharge_checkbox_diable_wheelchair_user() {
		return surcharge_checkbox_diable_wheelchair_user;
	}
	public void setSurcharge_checkbox_diable_wheelchair_user(
			boolean surcharge_checkbox_diable_wheelchair_user) {
		this.surcharge_checkbox_diable_wheelchair_user = surcharge_checkbox_diable_wheelchair_user;
	}
	public double getSurcharge_disable_wheelchair_user_num() {
		return surcharge_disable_wheelchair_user_num;
	}
	public void setSurcharge_disable_wheelchair_user_num(
			double surcharge_disable_wheelchair_user_num) {
		this.surcharge_disable_wheelchair_user_num = surcharge_disable_wheelchair_user_num;
	}
	public boolean isSurcharge_checkbox_disable_other() {
		return surcharge_checkbox_disable_other;
	}
	public void setSurcharge_checkbox_disable_other(
			boolean surcharge_checkbox_disable_other) {
		this.surcharge_checkbox_disable_other = surcharge_checkbox_disable_other;
	}
	public double getSurcharge_disable_other_num() {
		return surcharge_disable_other_num;
	}
	public void setSurcharge_disable_other_num(double surcharge_disable_other_num) {
		this.surcharge_disable_other_num = surcharge_disable_other_num;
	}
	public boolean isSurcharge_checkbox_health_appointment() {
		return surcharge_checkbox_health_appointment;
	}
	public void setSurcharge_checkbox_health_appointment(
			boolean surcharge_checkbox_health_appointment) {
		this.surcharge_checkbox_health_appointment = surcharge_checkbox_health_appointment;
	}
	public double getSurcharge_health_appointment_num() {
		return surcharge_health_appointment_num;
	}
	public void setSurcharge_health_appointment_num(
			double surcharge_health_appointment_num) {
		this.surcharge_health_appointment_num = surcharge_health_appointment_num;
	}
	public boolean isSurcharge_checkbox_social_care() {
		return surcharge_checkbox_social_care;
	}
	public void setSurcharge_checkbox_social_care(
			boolean surcharge_checkbox_social_care) {
		this.surcharge_checkbox_social_care = surcharge_checkbox_social_care;
	}
	public double getSurcharge_social_care_num() {
		return surcharge_social_care_num;
	}
	public void setSurcharge_social_care_num(double surcharge_social_care_num) {
		this.surcharge_social_care_num = surcharge_social_care_num;
	}
	public boolean isSurcharge_checkbox_shopping() {
		return surcharge_checkbox_shopping;
	}
	public void setSurcharge_checkbox_shopping(boolean surcharge_checkbox_shopping) {
		this.surcharge_checkbox_shopping = surcharge_checkbox_shopping;
	}
	public double getSurcharge_shopping_num() {
		return surcharge_shopping_num;
	}
	public void setSurcharge_shopping_num(double surcharge_shopping_num) {
		this.surcharge_shopping_num = surcharge_shopping_num;
	}
	public boolean isSurcharge_checkbox_leisure_visiting_friends() {
		return surcharge_checkbox_leisure_visiting_friends;
	}
	public void setSurcharge_checkbox_leisure_visiting_friends(
			boolean surcharge_checkbox_leisure_visiting_friends) {
		this.surcharge_checkbox_leisure_visiting_friends = surcharge_checkbox_leisure_visiting_friends;
	}
	public double getSurcharge_leisure_visiting_friends_num() {
		return surcharge_leisure_visiting_friends_num;
	}
	public void setSurcharge_leisure_visiting_friends_num(
			double surcharge_leisure_visiting_friends_num) {
		this.surcharge_leisure_visiting_friends_num = surcharge_leisure_visiting_friends_num;
	}
	public boolean isSurcharge_checkbox_school_education() {
		return surcharge_checkbox_school_education;
	}
	public void setSurcharge_checkbox_school_education(
			boolean surcharge_checkbox_school_education) {
		this.surcharge_checkbox_school_education = surcharge_checkbox_school_education;
	}
	public double getSurcharge_school_education_num() {
		return surcharge_school_education_num;
	}
	public void setSurcharge_school_education_num(
			double surcharge_school_education_num) {
		this.surcharge_school_education_num = surcharge_school_education_num;
	}
	public boolean isSurcharge_checkbox_work_commuting() {
		return surcharge_checkbox_work_commuting;
	}
	public void setSurcharge_checkbox_work_commuting(
			boolean surcharge_checkbox_work_commuting) {
		this.surcharge_checkbox_work_commuting = surcharge_checkbox_work_commuting;
	}
	public double getSurcharge_work_commuting_num() {
		return surcharge_work_commuting_num;
	}
	public void setSurcharge_work_commuting_num(double surcharge_work_commuting_num) {
		this.surcharge_work_commuting_num = surcharge_work_commuting_num;
	}
	public boolean isSurcharge_checkbox_other_purpose() {
		return surcharge_checkbox_other_purpose;
	}
	public void setSurcharge_checkbox_other_purpose(
			boolean surcharge_checkbox_other_purpose) {
		this.surcharge_checkbox_other_purpose = surcharge_checkbox_other_purpose;
	}
	public double getSurcharge_other_purpose_num() {
		return surcharge_other_purpose_num;
	}
	public void setSurcharge_other_purpose_num(double surcharge_other_purpose_num) {
		this.surcharge_other_purpose_num = surcharge_other_purpose_num;
	}
	public boolean isSurcharge_checkbox_start_05_hours_earlier() {
		return surcharge_checkbox_start_05_hours_earlier;
	}
	public void setSurcharge_checkbox_start_05_hours_earlier(
			boolean surcharge_checkbox_start_05_hours_earlier) {
		this.surcharge_checkbox_start_05_hours_earlier = surcharge_checkbox_start_05_hours_earlier;
	}
	public double getSurcharge_start_05_hours_earlier() {
		return surcharge_start_05_hours_earlier;
	}
	public void setSurcharge_start_05_hours_earlier(
			double surcharge_start_05_hours_earlier) {
		this.surcharge_start_05_hours_earlier = surcharge_start_05_hours_earlier;
	}
	public boolean isSurcharge_checkbox_start_1_hour_earlier() {
		return surcharge_checkbox_start_1_hour_earlier;
	}
	public void setSurcharge_checkbox_start_1_hour_earlier(
			boolean surcharge_checkbox_start_1_hour_earlier) {
		this.surcharge_checkbox_start_1_hour_earlier = surcharge_checkbox_start_1_hour_earlier;
	}
	public boolean isSurcharge_checkbox_start_15_hours_earlier() {
		return surcharge_checkbox_start_15_hours_earlier;
	}
	public void setSurcharge_checkbox_start_15_hours_earlier(
			boolean surcharge_checkbox_start_15_hours_earlier) {
		this.surcharge_checkbox_start_15_hours_earlier = surcharge_checkbox_start_15_hours_earlier;
	}
	public double getSurcharge_start_1_hour_earlier() {
		return surcharge_start_1_hour_earlier;
	}
	public void setSurcharge_start_1_hour_earlier(
			double surcharge_start_1_hour_earlier) {
		this.surcharge_start_1_hour_earlier = surcharge_start_1_hour_earlier;
	}
	public double getSurcharge_start_15_hours_earlier() {
		return surcharge_start_15_hours_earlier;
	}
	public void setSurcharge_start_15_hours_earlier(
			double surcharge_start_15_hours_earlier) {
		this.surcharge_start_15_hours_earlier = surcharge_start_15_hours_earlier;
	}
	public double getSurcharge_finish_05_hours_later() {
		return surcharge_finish_05_hours_later;
	}
	public void setSurcharge_finish_05_hours_later(
			double surcharge_finish_05_hours_later) {
		this.surcharge_finish_05_hours_later = surcharge_finish_05_hours_later;
	}
	public boolean isSurcharge_checkbox_finish_1_hour_later() {
		return surcharge_checkbox_finish_1_hour_later;
	}
	public void setSurcharge_checkbox_finish_1_hour_later(
			boolean surcharge_checkbox_finish_1_hour_later) {
		this.surcharge_checkbox_finish_1_hour_later = surcharge_checkbox_finish_1_hour_later;
	}
	public double getSurcharge_finish_1_hour_later() {
		return surcharge_finish_1_hour_later;
	}
	public void setSurcharge_finish_1_hour_later(
			double surcharge_finish_1_hour_later) {
		this.surcharge_finish_1_hour_later = surcharge_finish_1_hour_later;
	}
	public boolean isSurcharge_checkbox_finish_05_hours_later() {
		return surcharge_checkbox_finish_05_hours_later;
	}
	public void setSurcharge_checkbox_finish_05_hours_later(
			boolean surcharge_checkbox_finish_05_hours_later) {
		this.surcharge_checkbox_finish_05_hours_later = surcharge_checkbox_finish_05_hours_later;
	}
	public boolean isSurcharge_checkbox_finish_15_hours_later() {
		return surcharge_checkbox_finish_15_hours_later;
	}
	public void setSurcharge_checkbox_finish_15_hours_later(
			boolean surcharge_checkbox_15_hours_later) {
		this.surcharge_checkbox_finish_15_hours_later = surcharge_checkbox_15_hours_later;
	}
	public double getSurcharge_finish_15_hours_later() {
		return surcharge_finish_15_hours_later;
	}
	public void setSurcharge_finish_15_hours_later(
			double surcharge_finish_15_hours_later) {
		this.surcharge_finish_15_hours_later = surcharge_finish_15_hours_later;
	}
	public String getService_description() {
		return service_description;
	}
	public void setService_description(String service_description) {
		this.service_description = service_description;
	}
	
	public String getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	public String getTab_elig_radioBtns() {
		return tab_elig_radioBtns;
	}
	public void setTab_elig_radioBtns(String tab_elig_radioBtns) {
		this.tab_elig_radioBtns = tab_elig_radioBtns;
	}
	public String getTab_fare_structure_how_charge_radioBtns() {
		return tab_fare_structure_how_charge_radioBtns;
	}
	public void setTab_fare_structure_how_charge_radioBtns(
			String tab_fare_structure_how_charge_radioBtns) {
		this.tab_fare_structure_how_charge_radioBtns = tab_fare_structure_how_charge_radioBtns;
	}
	public String getOther_elig_type() {
		return other_elig_type;
	}
	public void setOther_elig_type(String other_elig_type) {
		this.other_elig_type = other_elig_type;
	}
	public String getHow_to_book() {
		return how_to_book;
	}
	public void setHow_to_book(String how_to_book) {
		this.how_to_book = how_to_book;
	}
	public boolean isCb_not_avail() {
		return cb_not_avail;
	}
	public void setCb_not_avail(boolean cb_not_avail) {
		this.cb_not_avail = cb_not_avail;
	}
	public String getNot_valid_from() {
		return not_valid_from;
	}
	public void setNot_valid_from(String not_valid_from) {
		this.not_valid_from = not_valid_from;
	}
	public String getNot_valid_to() {
		return not_valid_to;
	}
	public void setNot_valid_to(String not_valid_to) {
		this.not_valid_to = not_valid_to;
	}
	public String getTab_operating_hours_field_monday_1() {
		return tab_operating_hours_field_monday_1;
	}
	public void setTab_operating_hours_field_monday_1(
			String tab_operating_hours_field_monday_1) {
		this.tab_operating_hours_field_monday_1 = tab_operating_hours_field_monday_1;
	}
	public String getTab_operating_hours_field_monday_2() {
		return tab_operating_hours_field_monday_2;
	}
	public void setTab_operating_hours_field_monday_2(
			String tab_operating_hours_field_monday_2) {
		this.tab_operating_hours_field_monday_2 = tab_operating_hours_field_monday_2;
	}
	public String getTab_operating_hours_field_monday_3() {
		return tab_operating_hours_field_monday_3;
	}
	public void setTab_operating_hours_field_monday_3(
			String tab_operating_hours_field_monday_3) {
		this.tab_operating_hours_field_monday_3 = tab_operating_hours_field_monday_3;
	}
	public String getTab_operating_hours_field_monday_4() {
		return tab_operating_hours_field_monday_4;
	}
	public void setTab_operating_hours_field_monday_4(
			String tab_operating_hours_field_monday_4) {
		this.tab_operating_hours_field_monday_4 = tab_operating_hours_field_monday_4;
	}
	public String getTab_operating_hours_field_tuesday_1() {
		return tab_operating_hours_field_tuesday_1;
	}
	public void setTab_operating_hours_field_tuesday_1(
			String tab_operating_hours_field_tuesday_1) {
		this.tab_operating_hours_field_tuesday_1 = tab_operating_hours_field_tuesday_1;
	}
	public String getTab_operating_hours_field_tuesday_2() {
		return tab_operating_hours_field_tuesday_2;
	}
	public void setTab_operating_hours_field_tuesday_2(
			String tab_operating_hours_field_tuesday_2) {
		this.tab_operating_hours_field_tuesday_2 = tab_operating_hours_field_tuesday_2;
	}
	public String getTab_operating_hours_field_tuesday_3() {
		return tab_operating_hours_field_tuesday_3;
	}
	public void setTab_operating_hours_field_tuesday_3(
			String tab_operating_hours_field_tuesday_3) {
		this.tab_operating_hours_field_tuesday_3 = tab_operating_hours_field_tuesday_3;
	}
	public String getTab_operating_hours_field_tuesday_4() {
		return tab_operating_hours_field_tuesday_4;
	}
	public void setTab_operating_hours_field_tuesday_4(
			String tab_operating_hours_field_tuesday_4) {
		this.tab_operating_hours_field_tuesday_4 = tab_operating_hours_field_tuesday_4;
	}
	public String getTab_operating_hours_field_wednesday_1() {
		return tab_operating_hours_field_wednesday_1;
	}
	public void setTab_operating_hours_field_wednesday_1(
			String tab_operating_hours_field_wednesday_1) {
		this.tab_operating_hours_field_wednesday_1 = tab_operating_hours_field_wednesday_1;
	}
	public String getTab_operating_hours_field_wednesday_2() {
		return tab_operating_hours_field_wednesday_2;
	}
	public void setTab_operating_hours_field_wednesday_2(
			String tab_operating_hours_field_wednesday_2) {
		this.tab_operating_hours_field_wednesday_2 = tab_operating_hours_field_wednesday_2;
	}
	public String getTab_operating_hours_field_wednesday_3() {
		return tab_operating_hours_field_wednesday_3;
	}
	public void setTab_operating_hours_field_wednesday_3(
			String tab_operating_hours_field_wednesday_3) {
		this.tab_operating_hours_field_wednesday_3 = tab_operating_hours_field_wednesday_3;
	}
	public String getTab_operating_hours_field_wednesday_4() {
		return tab_operating_hours_field_wednesday_4;
	}
	public void setTab_operating_hours_field_wednesday_4(
			String tab_operating_hours_field_wednesday_4) {
		this.tab_operating_hours_field_wednesday_4 = tab_operating_hours_field_wednesday_4;
	}
	public String getTab_operating_hours_field_thursday_1() {
		return tab_operating_hours_field_thursday_1;
	}
	public void setTab_operating_hours_field_thursday_1(
			String tab_operating_hours_field_thursday_1) {
		this.tab_operating_hours_field_thursday_1 = tab_operating_hours_field_thursday_1;
	}
	public String getTab_operating_hours_field_thursday_2() {
		return tab_operating_hours_field_thursday_2;
	}
	public void setTab_operating_hours_field_thursday_2(
			String tab_operating_hours_field_thursday_2) {
		this.tab_operating_hours_field_thursday_2 = tab_operating_hours_field_thursday_2;
	}
	public String getTab_operating_hours_field_thursday_3() {
		return tab_operating_hours_field_thursday_3;
	}
	public void setTab_operating_hours_field_thursday_3(
			String tab_operating_hours_field_thursday_3) {
		this.tab_operating_hours_field_thursday_3 = tab_operating_hours_field_thursday_3;
	}
	public String getTab_operating_hours_field_thursday_4() {
		return tab_operating_hours_field_thursday_4;
	}
	public void setTab_operating_hours_field_thursday_4(
			String tab_operating_hours_field_thursday_4) {
		this.tab_operating_hours_field_thursday_4 = tab_operating_hours_field_thursday_4;
	}
	public String getTab_operating_hours_field_friday_1() {
		return tab_operating_hours_field_friday_1;
	}
	public void setTab_operating_hours_field_friday_1(
			String tab_operating_hours_field_friday_1) {
		this.tab_operating_hours_field_friday_1 = tab_operating_hours_field_friday_1;
	}
	public String getTab_operating_hours_field_friday_2() {
		return tab_operating_hours_field_friday_2;
	}
	public void setTab_operating_hours_field_friday_2(
			String tab_operating_hours_field_friday_2) {
		this.tab_operating_hours_field_friday_2 = tab_operating_hours_field_friday_2;
	}
	public String getTab_operating_hours_field_friday_3() {
		return tab_operating_hours_field_friday_3;
	}
	public void setTab_operating_hours_field_friday_3(
			String tab_operating_hours_field_friday_3) {
		this.tab_operating_hours_field_friday_3 = tab_operating_hours_field_friday_3;
	}
	public String getTab_operating_hours_field_friday_4() {
		return tab_operating_hours_field_friday_4;
	}
	public void setTab_operating_hours_field_friday_4(
			String tab_operating_hours_field_friday_4) {
		this.tab_operating_hours_field_friday_4 = tab_operating_hours_field_friday_4;
	}
	public String getTab_operating_hours_field_saturday_1() {
		return tab_operating_hours_field_saturday_1;
	}
	public void setTab_operating_hours_field_saturday_1(
			String tab_operating_hours_field_saturday_1) {
		this.tab_operating_hours_field_saturday_1 = tab_operating_hours_field_saturday_1;
	}
	public String getTab_operating_hours_field_saturday_2() {
		return tab_operating_hours_field_saturday_2;
	}
	public void setTab_operating_hours_field_saturday_2(
			String tab_operating_hours_field_saturday_2) {
		this.tab_operating_hours_field_saturday_2 = tab_operating_hours_field_saturday_2;
	}
	public String getTab_operating_hours_field_saturday_3() {
		return tab_operating_hours_field_saturday_3;
	}
	public void setTab_operating_hours_field_saturday_3(
			String tab_operating_hours_field_saturday_3) {
		this.tab_operating_hours_field_saturday_3 = tab_operating_hours_field_saturday_3;
	}
	public String getTab_operating_hours_field_saturday_4() {
		return tab_operating_hours_field_saturday_4;
	}
	public void setTab_operating_hours_field_saturday_4(
			String tab_operating_hours_field_saturday_4) {
		this.tab_operating_hours_field_saturday_4 = tab_operating_hours_field_saturday_4;
	}

	public String getTab_operating_hours_field_sunday_1() {
		return tab_operating_hours_field_sunday_1;
	}
	public void setTab_operating_hours_field_sunday_1(
			String tab_operating_hours_field_sunday_1) {
		this.tab_operating_hours_field_sunday_1 = tab_operating_hours_field_sunday_1;
	}
	public String getTab_operating_hours_field_sunday_2() {
		return tab_operating_hours_field_sunday_2;
	}
	public void setTab_operating_hours_field_sunday_2(
			String tab_operating_hours_field_sunday_2) {
		this.tab_operating_hours_field_sunday_2 = tab_operating_hours_field_sunday_2;
	}
	public String getTab_operating_hours_field_sunday_3() {
		return tab_operating_hours_field_sunday_3;
	}
	public void setTab_operating_hours_field_sunday_3(
			String tab_operating_hours_field_sunday_3) {
		this.tab_operating_hours_field_sunday_3 = tab_operating_hours_field_sunday_3;
	}
	public String getTab_operating_hours_field_sunday_4() {
		return tab_operating_hours_field_sunday_4;
	}
	public void setTab_operating_hours_field_sunday_4(
			String tab_operating_hours_field_sunday_4) {
		this.tab_operating_hours_field_sunday_4 = tab_operating_hours_field_sunday_4;
	}
	public String getOther_vehicle() {
		return other_vehicle;
	}
	public void setOther_vehicle(String other_vehicle) {
		this.other_vehicle = other_vehicle;
	}
	public String getEligible_checkbox_cant_live_on_a_bus_route() {
		return eligible_checkbox_cant_live_on_a_bus_route;
	}
	public void setEligible_checkbox_cant_live_on_a_bus_route(
			String eligible_checkbox_cant_live_on_a_bus_route) {
		this.eligible_checkbox_cant_live_on_a_bus_route = eligible_checkbox_cant_live_on_a_bus_route;
	}
	public String getOther_elig() {
		return other_elig;
	}
	public void setOther_elig(String other_elig) {
		this.other_elig = other_elig;
	}
	public String getExplain_opening_up_elig() {
		return explain_opening_up_elig;
	}
	public void setExplain_opening_up_elig(String explain_opening_up_elig) {
		this.explain_opening_up_elig = explain_opening_up_elig;
	}
	public double getDiscount_for_other_concessionary() {
		return discount_for_other_concessionary;
	}
	public void setDiscount_for_other_concessionary(
			double discount_for_other_concessionary) {
		this.discount_for_other_concessionary = discount_for_other_concessionary;
	}
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public String getAdded_fare_dist() {
		return added_fare_dist;
	}
	public void setAdded_fare_dist(String added_fare_dist) {
		this.added_fare_dist = added_fare_dist;
	}
	public String getType_of_permit() {
		return type_of_permit;
	}
	public void setType_of_permit(String type_of_permit) {
		this.type_of_permit = type_of_permit;
	}
	public String getAdded_service_not_avail() {
		return added_service_not_avail;
	}
	public void setAdded_service_not_avail(String added_service_not_avail) {
		this.added_service_not_avail = added_service_not_avail;
	}
	public String getTab_fare_structure_radioBtns() {
		return tab_fare_structure_radioBtns;
	}
	public void setTab_fare_structure_radioBtns(String tab_fare_structure_radioBtns) {
		this.tab_fare_structure_radioBtns = tab_fare_structure_radioBtns;
	}
}
