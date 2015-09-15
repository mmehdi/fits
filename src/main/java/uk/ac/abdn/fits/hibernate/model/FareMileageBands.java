package uk.ac.abdn.fits.hibernate.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="fare_mileage_bands")
public class FareMileageBands {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name="id", nullable = false)
	  private int id;
	  
	  @Column(name="fare_structure_id", nullable = false)
	  private int fare_structure_id;
	  
	  @Column(name="mile_1", nullable = true)
	  private double mile_1;
	  
	  @Column(name="mile_2", nullable = true)
	  private double mile_2;
	  
	  @Column(name="type", nullable = true)
	  private String type;
	  
	  @Column(name="charge", nullable = true)
	  private double charge;

	  
	  public int getId() {
		  return id;
	  }
	  public void setId(int id) {
		  this.id = id;
	  }
	  public int getFare_structure_id() {
		  return fare_structure_id;
	  }
	  public void setFare_structure_id(int fare_structure_id) {
		  this.fare_structure_id = fare_structure_id;
	  }
	  public double getMile_1() {
		  return mile_1;
	  }
	  public void setMile_1(double mile_1) {
		  this.mile_1 = mile_1;
	  }
	  public double getMile_2() {
		  return mile_2;
	  }
	  public void setMile_2(double mile_2) {
		  this.mile_2 = mile_2;
	  }
	  public String getType() {
		  return type;
	  }
	  public void setType(String type) {
		  this.type = type;
	  }
	  public double getCharge() {
		  return charge;
	  }
	  public void setCharge(double charge) {
		  this.charge = charge;
	  }
	  
}
