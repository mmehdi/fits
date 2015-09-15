/**
 * @author Cheng Zeng, University of Aberdeen
 *
 * 22 Sep 2014
 */
package uk.ac.abdn.fits.query.operator.fare;

import java.util.ArrayList;
import java.util.List;

public class FareCalculator {

	
	public static final String FARE_TYPE_FLAT_RATE = "flat rate";
	public static final String FARE_TYPE_FARE_PER_MILE = "fare per mile";
	
	private double return_fare_multiplier;
	private double discount_per_for_over_60s;
	private double discount_per_for_under_16s;
	private double discount_for_other_concessionary;
	private boolean escorts_charged_a_fare; 
	private boolean provide_escorts;
	private boolean charges_dead_mileage;
	private List<DistanceBand> dist_bands;
	
	
	
	public FareCalculator(double return_fare_multiplier, double discount_per_for_over_60s, 
			double discount_per_for_under_16s, double discount_for_other_concessionary, 
			boolean escorts_charged_a_fare, boolean charges_dead_mileage){
		
		this.setReturn_fare_multiplier(return_fare_multiplier);
		this.setDiscount_per_for_over_60s(discount_per_for_over_60s);
		this.setDiscount_per_for_under_16s(discount_per_for_under_16s);
		this.setDiscount_for_other_concessionary(discount_for_other_concessionary);
		this.setEscorts_charged_a_fare(escorts_charged_a_fare);
		this.setCharges_dead_mileage(charges_dead_mileage);
		dist_bands = new ArrayList<DistanceBand>();
	}

	/**
	 * @param dist
	 * @return fare
	 */
	public double calc(double dist){
		
		double fare = lookupDistBand(dist);
		/*calculate the discount */
		
		
		return fare;
	}
	
	public void addDistanceBand(DistanceBand distBand){
		dist_bands.add(distBand);
	}
	/**
	 * @param dist - distance in meter
	 * 
	 * */
	public double lookupDistBand(double dist){
		
		final double MILE = 1609.344;
		double miles = dist/MILE;
		
		if(dist_bands.size() ==0){
			return 0;
		}
		for(DistanceBand d_band: dist_bands){
			
			if (d_band.getDist1() != d_band.getDist2()){
				if(miles <= d_band.getDist2() && miles > d_band.getDist1()){
					if (d_band.getType().equals(FARE_TYPE_FLAT_RATE)){
						return d_band.getFare(); // just return the fare for now. Don't check the distance band type
					}else{
						return getFlatRate((int)d_band.getDist1()) + ((int)(miles - d_band.getDist1())+1)*d_band.getFare();
					}
				}
			}
		}
		return 0;
	}
	
	public double getFlatRate(int uptomileage){
		
		for(DistanceBand d_band: dist_bands){
			if(d_band.getType().equals("flat rate")){
				if (d_band.getDist1() == d_band.getDist2()){
					if (uptomileage == d_band.getDist1()){
						return d_band.getFare();
					}
				}
			}
		}
		return 0;
	}

	
	public double getReturn_fare_multiplier() {
		return return_fare_multiplier;
	}

	public void setReturn_fare_multiplier(double return_fare_multiplier) {
		this.return_fare_multiplier = return_fare_multiplier;
	}

	public double getDiscount_per_for_over_60s() {
		return discount_per_for_over_60s;
	}

	public void setDiscount_per_for_over_60s(double discount_per_for_over_60s) {
		this.discount_per_for_over_60s = discount_per_for_over_60s;
	}

	public double getDiscount_per_for_under_16s() {
		return discount_per_for_under_16s;
	}

	public void setDiscount_per_for_under_16s(double discount_per_for_under_16s) {
		this.discount_per_for_under_16s = discount_per_for_under_16s;
	}

	
	public boolean isEscorts_charged_a_fare() {
		return escorts_charged_a_fare;
	}

	public void setEscorts_charged_a_fare(boolean escorts_charged_a_fare) {
		this.escorts_charged_a_fare = escorts_charged_a_fare;
	}

	public boolean isCharges_dead_mileage() {
		return charges_dead_mileage;
	}

	public void setCharges_dead_mileage(boolean charges_dead_mileage) {
		this.charges_dead_mileage = charges_dead_mileage;
	}

	public boolean isProvide_escorts() {
		return provide_escorts;
	}

	public void setProvide_escorts(boolean provide_escorts) {
		this.provide_escorts = provide_escorts;
	}


	public double getDiscount_for_other_concessionary() {
		return discount_for_other_concessionary;
	}

	public void setDiscount_for_other_concessionary(
			double discount_for_other_concessionary) {
		this.discount_for_other_concessionary = discount_for_other_concessionary;
	}
	
	
	
}
