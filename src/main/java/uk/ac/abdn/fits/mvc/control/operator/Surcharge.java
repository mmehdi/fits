package uk.ac.abdn.fits.mvc.control.operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

public class Surcharge {
	
	
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
	public double getSurcharge_start_1_hour_earlier() {
		return surcharge_start_1_hour_earlier;
	}
	public void setSurcharge_start_1_hour_earlier(
			double surcharge_start_1_hour_earlier) {
		this.surcharge_start_1_hour_earlier = surcharge_start_1_hour_earlier;
	}
	public boolean isSurcharge_checkbox_start_15_hours_earlier() {
		return surcharge_checkbox_start_15_hours_earlier;
	}
	public void setSurcharge_checkbox_start_15_hours_earlier(
			boolean surcharge_checkbox_start_15_hours_earlier) {
		this.surcharge_checkbox_start_15_hours_earlier = surcharge_checkbox_start_15_hours_earlier;
	}
	public double getSurcharge_start_15_hours_earlier() {
		return surcharge_start_15_hours_earlier;
	}
	public void setSurcharge_start_15_hours_earlier(
			double surcharge_start_15_hours_earlier) {
		this.surcharge_start_15_hours_earlier = surcharge_start_15_hours_earlier;
	}
	public boolean isSurcharge_checkbox_finish_05_hours_later() {
		return surcharge_checkbox_finish_05_hours_later;
	}
	public void setSurcharge_checkbox_finish_05_hours_later(
			boolean surcharge_checkbox_finish_05_hours_later) {
		this.surcharge_checkbox_finish_05_hours_later = surcharge_checkbox_finish_05_hours_later;
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
	public boolean isSurcharge_checkbox_finish_15_hours_later() {
		return surcharge_checkbox_finish_15_hours_later;
	}
	public void setSurcharge_checkbox_finish_15_hours_later(
			boolean surcharge_checkbox_finish_15_hours_later) {
		this.surcharge_checkbox_finish_15_hours_later = surcharge_checkbox_finish_15_hours_later;
	}
	public double getSurcharge_finish_15_hours_later() {
		return surcharge_finish_15_hours_later;
	}
	public void setSurcharge_finish_15_hours_later(
			double surcharge_finish_15_hours_later) {
		this.surcharge_finish_15_hours_later = surcharge_finish_15_hours_later;
	}
	
	public void setField(OperatorDataInputForm form ){
		
		surcharge_dist1_mile = form.getSurcharge_dist1_mile();
		surcharge_dist1_type = form.getSurcharge_dist1_type();
		surcharge_dist1_charge = form.getSurcharge_dist1_charge();
		surcharge_dist2_1 = form.getSurcharge_dist2_1();
		surcharge_dist2_2 = form.getSurcharge_dist2_2();
		surcharge_dist2_type = form.getSurcharge_dist2_type();
		surcharge_dist2_charge = form.getSurcharge_dist2_charge();
		surcharge_dist3_mile_1 = form.getSurcharge_dist3_mile_1();
		surcharge_dist3_mile_2= form.getSurcharge_dist3_mile_2();
		surcharge_dist3_type = form.getSurcharge_dist3_type();
	    surcharge_dist3_charge = form.getSurcharge_dist3_charge();
	    surcharge_dist4_mile_1 = form.getSurcharge_dist4_mile_1();
	    surcharge_dist4_mile_2 = form.getSurcharge_dist4_mile_2();
	    surcharge_dist4_type = form.getSurcharge_dist4_type();
	    surcharge_dist4_charge = form.getSurcharge_dist4_charge();
		surcharge_checkbox_under16 = form.isSurcharge_checkbox_under16();
		surcharge_under_16_num = form.getSurcharge_under_16_num();
		surcharge_checkbox_17_to_21 = form.isSurcharge_checkbox_17_to_21();
		surcharge_17_21_years_old_num = form.getSurcharge_17_21_years_old_num();
		surcharge_checkbox_22_to_54 = form.isSurcharge_checkbox_22_to_54();
		surcharge_22_54_years_old_num = form.getSurcharge_22_54_years_old_num();
		surcharge_checkbox_55_to_59 = form.isSurcharge_checkbox_55_to_59();
		surcharge_55_59_years_old_num = form.getSurcharge_55_59_years_old_num();
		surcharge_checkbox_over60 = form.isSurcharge_checkbox_over60();
		surcharge_over_60_years_old_num = form.getSurcharge_over_60_years_old_num();
		surcharge_checkbox_able_bodied = form.isSurcharge_checkbox_able_bodied();
		surcharge_able_bodied_num = form.getSurcharge_able_bodied_num();
		surcharge_checkbox_mobility_prevents_PT = form.isSurcharge_checkbox_mobility_prevents_PT();
		surcharge_mobility_prevents_PT_num = form.getSurcharge_mobility_prevents_PT_num();
		surcharge_checkbox_diable_wheelchair_user = form.isSurcharge_checkbox_diable_wheelchair_user();
		surcharge_disable_wheelchair_user_num = form.getSurcharge_disable_wheelchair_user_num();
		surcharge_checkbox_disable_other = form.isSurcharge_checkbox_disable_other();
		surcharge_disable_other_num = form.getSurcharge_disable_other_num();
		surcharge_checkbox_health_appointment = form.isSurcharge_checkbox_health_appointment();
		surcharge_health_appointment_num = form.getSurcharge_health_appointment_num();
		surcharge_checkbox_social_care = form.isSurcharge_checkbox_social_care();
		surcharge_social_care_num = form.getSurcharge_social_care_num();
		surcharge_checkbox_shopping = form.isSurcharge_checkbox_shopping();
		surcharge_shopping_num = form.getSurcharge_shopping_num();
		surcharge_checkbox_leisure_visiting_friends = form.isSurcharge_checkbox_leisure_visiting_friends();
		surcharge_leisure_visiting_friends_num = form.getSurcharge_leisure_visiting_friends_num();
		surcharge_checkbox_school_education = form.isSurcharge_checkbox_school_education();
		surcharge_school_education_num = form.getSurcharge_school_education_num();
		surcharge_checkbox_work_commuting = form.isSurcharge_checkbox_work_commuting();
		surcharge_work_commuting_num = form.getSurcharge_work_commuting_num();
		surcharge_checkbox_other_purpose = form.isSurcharge_checkbox_other_purpose();
		surcharge_other_purpose_num = form.getSurcharge_other_purpose_num();
		surcharge_checkbox_start_05_hours_earlier = form.isSurcharge_checkbox_start_05_hours_earlier();
		surcharge_start_05_hours_earlier = form.getSurcharge_start_05_hours_earlier();
		surcharge_checkbox_start_1_hour_earlier = form.isSurcharge_checkbox_start_1_hour_earlier();
		surcharge_start_1_hour_earlier = form.getSurcharge_start_1_hour_earlier();
		surcharge_checkbox_start_15_hours_earlier = form.isSurcharge_checkbox_start_15_hours_earlier();
		surcharge_start_15_hours_earlier = form.getSurcharge_start_15_hours_earlier();
		surcharge_checkbox_finish_05_hours_later = form.isSurcharge_checkbox_finish_05_hours_later();
		surcharge_finish_05_hours_later = form.getSurcharge_finish_05_hours_later();
		surcharge_checkbox_finish_1_hour_later = form.isSurcharge_checkbox_finish_1_hour_later();
		surcharge_finish_1_hour_later = form.getSurcharge_finish_1_hour_later();
		surcharge_checkbox_finish_15_hours_later = form.isSurcharge_checkbox_finish_15_hours_later();
		surcharge_finish_15_hours_later = form.getSurcharge_finish_15_hours_later();
		
		
	}
	
	
	public String toString(){
		
		StringBuilder sb = new  StringBuilder();
	    sb.append("surcharge_dist1_mile: "+ surcharge_dist1_mile+"\n");
		sb.append("surcharge_dist1_type: "+ this.surcharge_dist1_type+"\n");
		sb.append("surcharge_dist1_charge: "+ this.surcharge_dist1_charge+"\n");
		sb.append("surcharge_dist2_1: "+ this.surcharge_dist2_1+"\n");
		sb.append("surcharge_dist2_2: "+ this.surcharge_dist2_2+"\n");
		sb.append("surcharge_dist2_type: "+ this.surcharge_dist2_type+"\n");
		sb.append("surcharge_dist2_charge: "+ this.surcharge_dist2_charge+"\n");
		sb.append("surcharge_dist3_mile_1: "+ this.surcharge_dist3_mile_1+"\n");
		sb.append("surcharge_dist3_mile_2: "+ this.surcharge_dist3_mile_2+"\n");
		sb.append("surcharge_dist3_type: "+ this.surcharge_dist3_type+"\n");
		sb.append("surcharge_dist3_charge: "+ this.surcharge_dist3_charge+"\n");
		sb.append("surcharge_dist4_mile_1: "+ this.surcharge_dist4_mile_1+"\n");
		sb.append("surcharge_dist4_mile_2: "+ this.surcharge_dist4_mile_2+"\n");
		sb.append("surcharge_dist4_type: "+ this.surcharge_dist4_type+"\n");
		sb.append("surcharge_dist4_charge: "+ this.surcharge_dist4_charge+"\n");
		sb.append("surcharge_checkbox_under16: "+ this.isSurcharge_checkbox_under16()+"\n");
		sb.append("surcharge_under_16_num: "+ this.getSurcharge_under_16_num()+"\n");
		sb.append("surcharge_checkbox_17_to_21: "+ this.isSurcharge_checkbox_17_to_21()+"\n");
		sb.append("surcharge_17_21_years_old_num: "+ this.getSurcharge_17_21_years_old_num()+"\n");
		sb.append("surcharge_checkbox_22_to_54: "+ this.isSurcharge_checkbox_22_to_54()+"\n");
		sb.append("surcharge_22_54_years_old_num: "+ this.getSurcharge_22_54_years_old_num()+"\n");
		sb.append("surcharge_checkbox_55_to_59: "+ this.isSurcharge_checkbox_55_to_59()+"\n");
		sb.append("surcharge_55_59_years_old_num: "+ this.getSurcharge_55_59_years_old_num()+"\n");
		sb.append("surcharge_checkbox_over60: "+ this.isSurcharge_checkbox_over60()+"\n");
		sb.append("surcharge_over_60_years_old_num: "+ this.getSurcharge_over_60_years_old_num()+"\n");
		sb.append("surcharge_checkbox_able_bodied: "+ this.isSurcharge_checkbox_able_bodied()+"\n");
		sb.append("surcharge_able_bodied_num: "+ this.getSurcharge_able_bodied_num()+"\n");
		sb.append("surcharge_checkbox_mobility_prevents_PT: "+ this.isSurcharge_checkbox_mobility_prevents_PT()+"\n");
		sb.append("surcharge_mobility_prevents_PT_num: "+ this.getSurcharge_mobility_prevents_PT_num()+"\n");
		sb.append("surcharge_checkbox_diable_wheelchair_user: "+ this.isSurcharge_checkbox_diable_wheelchair_user()+"\n");
		sb.append("surcharge_disable_wheelchair_user_num: "+ this.getSurcharge_disable_wheelchair_user_num()+"\n");
		sb.append("surcharge_checkbox_disable_other: "+ this.isSurcharge_checkbox_disable_other()+"\n");
		sb.append("surcharge_disable_other_num: "+ this.getSurcharge_disable_other_num()+"\n");
		sb.append("surcharge_checkbox_health_appointment: "+ this.isSurcharge_checkbox_health_appointment()+"\n");
		sb.append("surcharge_health_appointment_num: "+ this.getSurcharge_health_appointment_num()+"\n");
		sb.append("surcharge_checkbox_social_care: "+ this.isSurcharge_checkbox_social_care()+"\n");
		sb.append("surcharge_social_care_num: "+ this.getSurcharge_social_care_num()+"\n");
		sb.append("surcharge_checkbox_shopping: "+ this.isSurcharge_checkbox_shopping()+"\n");
		sb.append("surcharge_shopping_num: "+ this.getSurcharge_leisure_visiting_friends_num()+"\n");
		sb.append("surcharge_checkbox_leisure_visiting_friends: "+ this.isSurcharge_checkbox_leisure_visiting_friends()+"\n");
		sb.append("surcharge_leisure_visiting_friends_num: "+ this.getSurcharge_leisure_visiting_friends_num()+"\n");
		sb.append("surcharge_checkbox_school_education: "+ this.isSurcharge_checkbox_school_education()+"\n");
		sb.append("surcharge_school_education_num: "+ this.getSurcharge_school_education_num()+"\n");
		sb.append("surcharge_checkbox_work_commuting: "+ this.isSurcharge_checkbox_work_commuting()+"\n");
		sb.append("surcharge_work_commuting_num: "+ this.getSurcharge_work_commuting_num()+"\n");
		sb.append("surcharge_checkbox_other_purpose: "+ this.isSurcharge_checkbox_other_purpose()+"\n");
		sb.append("surcharge_other_purpose_num: "+ this.getSurcharge_other_purpose_num()+"\n");
		sb.append("surcharge_checkbox_start_05_hours_earlier: "+ this.isSurcharge_checkbox_start_05_hours_earlier()+"\n");
		sb.append("surcharge_start_05_hours_earlier: "+ this.getSurcharge_start_05_hours_earlier()+"\n");
		sb.append("surcharge_checkbox_start_1_hour_earlier: "+ this.isSurcharge_checkbox_start_1_hour_earlier()+"\n");
		sb.append("surcharge_start_1_hour_earlier: "+ this.getSurcharge_start_1_hour_earlier()+"\n");
		sb.append("surcharge_checkbox_start_15_hours_earlier: "+ this.isSurcharge_checkbox_start_15_hours_earlier()+"\n");
		sb.append("surcharge_start_15_hours_earlier: "+ this.getSurcharge_start_15_hours_earlier()+"\n");
		sb.append("surcharge_checkbox_finish_05_hours_later: "+ this.isSurcharge_checkbox_finish_05_hours_later()+"\n");
		sb.append("surcharge_finish_05_hours_later: "+ this.getSurcharge_finish_05_hours_later()+"\n");
		sb.append("surcharge_checkbox_finish_1_hour_later: "+ this.isSurcharge_checkbox_finish_1_hour_later()+"\n");
		sb.append("surcharge_finish_1_hour_later: "+ this.getSurcharge_finish_1_hour_later()+"\n");
		sb.append("surcharge_checkbox_finish_15_hours_later: "+ this.isSurcharge_checkbox_finish_15_hours_later()+"\n");
		sb.append("surcharge_finish_15_hours_later: "+ this.getSurcharge_finish_15_hours_later()+"\n");
		return sb.toString();
	}
	public double getSurcharge_dist1_mile() {
		return surcharge_dist1_mile;
	}
	public void setSurcharge_dist1_mile(double surcharge_dist1_mile) {
		this.surcharge_dist1_mile = surcharge_dist1_mile;
	}
	public String getSurcharge_dist1_type() {
		return surcharge_dist1_type;
	}
	public void setSurcharge_dist1_type(String surcharge_dist1_type) {
		this.surcharge_dist1_type = surcharge_dist1_type;
	}
	public double getSurcharge_dist1_charge() {
		return surcharge_dist1_charge;
	}
	public void setSurcharge_dist1_charge(double surcharge_dist1_charge) {
		this.surcharge_dist1_charge = surcharge_dist1_charge;
	}
	public double getSurcharge_dist2_1() {
		return surcharge_dist2_1;
	}
	public void setSurcharge_dist2_1(double surcharge_dist2_1) {
		this.surcharge_dist2_1 = surcharge_dist2_1;
	}
	public double getSurcharge_dist2_2() {
		return surcharge_dist2_2;
	}
	public void setSurcharge_dist2_2(double surcharge_dist2_2) {
		this.surcharge_dist2_2 = surcharge_dist2_2;
	}
	public String getSurcharge_dist2_type() {
		return surcharge_dist2_type;
	}
	public void setSurcharge_dist2_type(String surcharge_dist2_type) {
		this.surcharge_dist2_type = surcharge_dist2_type;
	}
	public double getSurcharge_dist2_charge() {
		return surcharge_dist2_charge;
	}
	public void setSurcharge_dist2_charge(double surcharge_dist2_charge) {
		this.surcharge_dist2_charge = surcharge_dist2_charge;
	}
	public double getSurcharge_dist3_mile_1() {
		return surcharge_dist3_mile_1;
	}
	public void setSurcharge_dist3_mile_1(double surcharge_dist3_mile_1) {
		this.surcharge_dist3_mile_1 = surcharge_dist3_mile_1;
	}
	public double getSurcharge_dist3_mile_2() {
		return surcharge_dist3_mile_2;
	}
	public void setSurcharge_dist3_mile_2(double surcharge_dist3_mile_2) {
		this.surcharge_dist3_mile_2 = surcharge_dist3_mile_2;
	}
	public String getSurcharge_dist3_type() {
		return surcharge_dist3_type;
	}
	public void setSurcharge_dist3_type(String surcharge_dist3_type) {
		this.surcharge_dist3_type = surcharge_dist3_type;
	}
	public double getSurcharge_dist3_charge() {
		return surcharge_dist3_charge;
	}
	public void setSurcharge_dist3_charge(double surcharge_dist3_charge) {
		this.surcharge_dist3_charge = surcharge_dist3_charge;
	}
	public double getSurcharge_dist4_mile_1() {
		return surcharge_dist4_mile_1;
	}
	public void setSurcharge_dist4_mile_1(double surcharge_dist4_mile_1) {
		this.surcharge_dist4_mile_1 = surcharge_dist4_mile_1;
	}
	public double getSurcharge_dist4_mile_2() {
		return surcharge_dist4_mile_2;
	}
	public void setSurcharge_dist4_mile_2(double surcharge_dist4_mile_2) {
		this.surcharge_dist4_mile_2 = surcharge_dist4_mile_2;
	}
	public String getSurcharge_dist4_type() {
		return surcharge_dist4_type;
	}
	public void setSurcharge_dist4_type(String surcharge_dist4_type) {
		this.surcharge_dist4_type = surcharge_dist4_type;
	}
	public double getSurcharge_dist4_charge() {
		return surcharge_dist4_charge;
	}
	public void setSurcharge_dist4_charge(double surcharge_dist4_charge) {
		this.surcharge_dist4_charge = surcharge_dist4_charge;
	}
	
}
