package uk.ac.abdn.fits.query.operator;

import java.util.HashMap;

public class EligibilityCalculator {

	private HashMap<PassengerAge, Double> age;
	private HashMap<PassengerMobilityStatus, Double> mobility_status;
	private HashMap<PassengerJourneyPurpose, Double> purpose;
	
	public EligibilityCalculator(HashMap<PassengerAge, Double> age, HashMap<PassengerMobilityStatus, Double> mobility_status,
			HashMap<PassengerJourneyPurpose, Double> purpose){
		this.age = age;
		this.mobility_status = mobility_status;
		this.purpose = purpose;
	}
	
	protected double get_disutil_by_age(PassengerAge psg_age){
		return age.get(psg_age);
	}
	protected void set_disutil_by_age(PassengerAge psg_age, double disutil){
		age.put(psg_age, disutil);
	}
	
	protected double get_disutil_by_mobility_status(PassengerMobilityStatus mobility_status){
		return this.mobility_status.get(mobility_status);
	}
	protected void set_disutil_by_mobility_status(PassengerMobilityStatus mobility_status, double disutil){
		this.mobility_status.put(mobility_status, disutil);
	}
	
	protected double get_disutil_by_purpose(PassengerJourneyPurpose purpose){
		return this.purpose.get(purpose);
	}
	protected void set_disutil_by_purpose(PassengerJourneyPurpose purpose, double disutil){
		this.purpose.put(purpose, disutil);
	}
	
	public double get_disutil(PassengerAge psg_age, PassengerMobilityStatus mobility_status,
			PassengerJourneyPurpose purpose){
		double disutil = 1d;
		disutil *= get_disutil_by_age(psg_age);
		disutil *= get_disutil_by_mobility_status(mobility_status);
		disutil *= get_disutil_by_purpose(purpose);
		return disutil;
	}
	
	public static void main(String[] args) {

	}

}
