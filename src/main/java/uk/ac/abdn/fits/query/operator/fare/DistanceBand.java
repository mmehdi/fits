/**
 *  @author Cheng Zeng, University of Aberdeen
 *
 * 22 Oct 2014
 */
package uk.ac.abdn.fits.query.operator.fare;


public class DistanceBand{
	
	private double dist1;
	private double dist2;
	private String type;
	private double fare;
	
	public DistanceBand(double dist1, double dist2, String type, double fare){
		
		this.dist1 = dist1;
		this.dist2 = dist2;
		this.type = type;
		this.fare = fare;
	}
	
	
	public double getDist1() {
		return dist1;
	}
	public void setDist1(double dist1) {
		this.dist1 = dist1;
	}
	public double getDist2() {
		return dist2;
	}
	public void setDist2(double dist2) {
		this.dist2 = dist2;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	
}
