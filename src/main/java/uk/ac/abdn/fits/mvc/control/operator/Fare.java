package uk.ac.abdn.fits.mvc.control.operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.text.DecimalFormat;

public class Fare {
	
	private byte charge_standard_fare;
	private String tab_fare_structure_radioBtns ;// if yes, the value is 0. If no, the value is 1. The default is 0.
	private String tab_fare_structure_how_charge_radioBtns; // 0 = flat rate, 1 = zonal fare, 2 = fare per mile
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
	private String added_fare_dist;
	private double return_fare_multiplier;
	private double discount_for_over60;
	private double discount_for_under16;
	private double discount_for_other_concessionary;
	private boolean fare_structure_checkbox_escort;
	private boolean fare_structure_checkbox_charge_for_dead_mileage;
	
	public byte getCharge_standard_fare() {
		return charge_standard_fare;
	}
	public void setCharge_standard_fare(byte charge_standard_fare) {
		this.charge_standard_fare = charge_standard_fare;
	}
	public double getFare_dist1_mile_2() {
		return fare_dist1_mile_2;
	}
	public void setFare_dist1_mile_2(double fare_distance1_mile) {
		this.fare_dist1_mile_2 = fare_distance1_mile;
	}
	public String getFare_dist1_type() {
		return fare_dist1_type;
	}
	public void setFare_dist1_type(String fare_dist1_type) {
		this.fare_dist1_type = fare_dist1_type;
	}
	public double getFare_dist1_charge() {
		return fare_dist1_charge;
	}
	public void setFare_dist1_charge(double fare_dist1_charge) {
		this.fare_dist1_charge = fare_dist1_charge;
	}
	public double getFare_dist2_mile_1() {
		return fare_dist2_mile_1;
	}
	public void setFare_dist2_mile_1(double fare_dist2_mile_1) {
		this.fare_dist2_mile_1 = fare_dist2_mile_1;
	}
	public double getFare_dist2_mile_2() {
		return fare_dist2_mile_2;
	}
	public void setFare_dist2_mile_2(double fare_dist2_mile_2) {
		this.fare_dist2_mile_2 = fare_dist2_mile_2;
	}
	public String getFare_dist2_type() {
		return fare_dist2_type;
	}
	public void setFare_dist2_type(String fare_dist2_type) {
		this.fare_dist2_type = fare_dist2_type;
	}
	public double getFare_dist2_charge() {
		return fare_dist2_charge;
	}
	public void setFare_dist2_charge(double fare_dist2_charge) {
		this.fare_dist2_charge = fare_dist2_charge;
	}
	public double getFare_dist3_mile_1() {
		return fare_dist3_mile_1;
	}
	public void setFare_dist3_mile_1(double fare_dist3_mile_1) {
		this.fare_dist3_mile_1 = fare_dist3_mile_1;
	}
	public double getFare_dist3_mile_2() {
		return fare_dist3_mile_2;
	}
	public void setFare_dist3_mile_2(double fare_dist3_mile_2) {
		this.fare_dist3_mile_2 = fare_dist3_mile_2;
	}
	public String getFare_dist3_type() {
		return fare_dist3_type;
	}
	public void setFare_dist3_type(String fare_dist3_type) {
		this.fare_dist3_type = fare_dist3_type;
	}
	public double getFare_dist3_charge() {
		return fare_dist3_charge;
	}
	public void setFare_dist3_charge(double fare_dist3_charge) {
		this.fare_dist3_charge = fare_dist3_charge;
	}
	public double getFare_dist4_mile_1() {
		return fare_dist4_mile_1;
	}
	public void setFare_dist4_mile_1(double fare_dist4_mile_1) {
		this.fare_dist4_mile_1 = fare_dist4_mile_1;
	}
	public double getFare_dist4_mile_2() {
		return fare_dist4_mile_2;
	}
	public void setFare_dist4_mile_2(double fare_dist4_mile_2) {
		this.fare_dist4_mile_2 = fare_dist4_mile_2;
	}
	public String getFare_dist4_type() {
		return fare_dist4_type;
	}
	public void setFare_dist4_type(String fare_dist4_type) {
		this.fare_dist4_type = fare_dist4_type;
	}
	public double getFare_dist4_charge() {
		return fare_dist4_charge;
	}
	public void setFare_dist4_charge(double fare_dist4_charge) {
		this.fare_dist4_charge = fare_dist4_charge;
	}
	public double getReturn_fare_multiplier() {
		return return_fare_multiplier;
	}
	public void setReturn_fare_multiplier(double return_fare_multiplier) {
		this.return_fare_multiplier = return_fare_multiplier;
	}
	public String getDiscount_for_over60() {
		return new DecimalFormat("#").format(discount_for_over60).toString();
	}
	public void setDiscount_for_over60(double discount_for_over60) {
		this.discount_for_over60 = discount_for_over60;
	}
	public String getDiscount_for_under16() {
		return new DecimalFormat("#").format(discount_for_under16).toString();
	}
	public void setDiscount_for_under16(double discount_for_under16) {
		this.discount_for_under16 = discount_for_under16;
	}
	public boolean isFare_structure_checkbox_escort() {
		return fare_structure_checkbox_escort;
	}
	public void setFare_structure_checkbox_escort(
			boolean fare_structure_checkbox_escort) {
		this.fare_structure_checkbox_escort = fare_structure_checkbox_escort;
	}
	public boolean isFare_structure_checkbox_charge_for_dead_mileage() {
		return fare_structure_checkbox_charge_for_dead_mileage;
	}
	public void setFare_structure_checkbox_charge_for_dead_mileage(
			boolean fare_structure_checkbox_charge_for_dead_mileage) {
		this.fare_structure_checkbox_charge_for_dead_mileage = fare_structure_checkbox_charge_for_dead_mileage;
	}
	public String getDiscount_for_other_concessionary() {
		return new DecimalFormat("#").format(discount_for_other_concessionary).toString();
	}
	public void setDiscount_for_other_concessionary(
			double discount_for_other_concessionary) {
		this.discount_for_other_concessionary = discount_for_other_concessionary;
	}
	public String getTab_fare_structure_radioBtns() {
		return tab_fare_structure_radioBtns;
	}
	public void setTab_fare_structure_radioBtns(
			String tab_fare_structure_radioBtns) {
		this.tab_fare_structure_radioBtns = tab_fare_structure_radioBtns;
	}
	public String getTab_fare_structure_how_charge_radioBtns() {
		return tab_fare_structure_how_charge_radioBtns;
	}
	public void setTab_fare_structure_how_charge_radioBtns(
			String tab_fare_structure_how_charge_radioBtns) {
		this.tab_fare_structure_how_charge_radioBtns = tab_fare_structure_how_charge_radioBtns;
	}

	
	public void setField(OperatorDataInputForm form ){
		// if yes, the value is 0. If no, the value is 1. The default is 0.
		
		//charge_standard_fare = (byte) (form.getTab_fare_structure_radioBtns().toString().equals("0")?0:1);
		if(form.getTab_fare_structure_radioBtns().equals("0"))
			charge_standard_fare = 0;
		else if(form.getTab_fare_structure_radioBtns().equals("1") || form.getTab_fare_structure_radioBtns().equals("2"))
			charge_standard_fare = 1;
		
		tab_fare_structure_radioBtns = form.getTab_fare_structure_radioBtns();
		tab_fare_structure_how_charge_radioBtns = form.getTab_fare_structure_how_charge_radioBtns();
		fare_dist1_mile_2 = form.getFare_dist1_mile_2();
		fare_dist1_type = form.getFare_dist1_type();
		fare_dist1_charge = form.getFare_dist1_charge();
		fare_dist2_mile_1 = form.getFare_dist2_mile_1();
		fare_dist2_mile_2 = form.getFare_dist2_mile_2();
		fare_dist2_type = form.getFare_dist2_type();
		fare_dist2_charge = form.getFare_dist2_charge();
		added_fare_dist = form.getAdded_fare_dist();
		return_fare_multiplier = form.getReturn_fare_multiplier();
		discount_for_over60= form.getDiscount_for_over60();
		discount_for_under16 = form.getDiscount_for_under16();
		discount_for_other_concessionary = form.getDiscount_for_other_concessionary();
		fare_structure_checkbox_escort = form.isFare_structure_checkbox_escort();
		fare_structure_checkbox_charge_for_dead_mileage = form.isFare_structure_checkbox_charge_for_dead_mileage();
		
		
	}
	
	public String toString(){
		StringBuilder sb = new  StringBuilder();
		sb.append("charge_standard_fare: "+ this.getCharge_standard_fare()+"\n");
		sb.append("fare_distance1_mile: "+ this.getFare_dist1_mile_2()+"\n");
		sb.append("fare_dist1_type: "+ this.getFare_dist1_type()+"\n");
		sb.append("fare_dist1_charge: "+ this.getFare_dist1_charge()+"\n");
		sb.append("fare_dist2_mile_1: "+ this.getFare_dist2_mile_1()+"\n");
		sb.append("fare_dist2_mile_2: "+ this.getFare_dist2_mile_2()+"\n");
		sb.append("fare_dist2_type: "+ this.getFare_dist2_type()+"\n");
		sb.append("fare_dist2_charge: "+ this.getFare_dist2_charge()+"\n");
		sb.append("fare_dist3_mile_1: "+ this.getFare_dist3_mile_1()+"\n");
		sb.append("fare_dist3_mile_2: "+ this.getFare_dist3_mile_2()+"\n");
		sb.append("fare_dist3_type: "+ this.getFare_dist3_type()+"\n");
		sb.append("fare_dist3_charge: "+ this.getFare_dist3_charge()+"\n");
		sb.append("fare_dist4_mile_1: "+ this.getFare_dist4_mile_1()+"\n");
		sb.append("fare_dist4_mile_2: "+ this.getFare_dist4_mile_2()+"\n");
		sb.append("fare_dist4_type: "+ this.getFare_dist4_type()+"\n");
		sb.append("fare_dist4_charge: "+ this.getFare_dist4_charge()+"\n");
		sb.append("return_fare_multiplier: "+ this.getReturn_fare_multiplier()+"\n");
		sb.append("discount_for_over60: "+ this.getDiscount_for_over60()+"\n");
		sb.append("discount_for_under16: "+ this.getDiscount_for_under16()+"\n");
		sb.append("fare_structure_checkbox_escort: "+ this.isFare_structure_checkbox_escort()+"\n");
		sb.append("fare_structure_checkbox_charge_for_dead_mileage: "+ this.isFare_structure_checkbox_charge_for_dead_mileage()+"\n");
		return sb.toString();
	}
	public String getAdded_fare_dist() {
		return added_fare_dist;
	}
	public void setAdded_fare_dist(String added_fare_dist) {
		this.added_fare_dist = added_fare_dist;
	}
	
	
	    
}
