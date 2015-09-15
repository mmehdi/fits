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
@Table(name="surcharge_mileage_bands")
public class SurchargeMileageBands {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;
	  
	@Column(name="surcharge_distance_id", nullable = false)
	private int surcharge_distance_id;
	  
	@Column(name="mile_1", nullable = true)
	private double mile_1;
	  
	@Column(name="mile_2", nullable = true)
	private double mile_2;
	  
	@Column(name="type", nullable = true)
	private String type;
	  
	@Column(name="surcharge", nullable = true)
	private double surcharge;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getSurcharge_distance_id() {
		return surcharge_distance_id;
	}

	public void setSurcharge_distance_id(int surcharge_distance_id) {
		this.surcharge_distance_id = surcharge_distance_id;
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

	public double getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(double surcharge) {
		this.surcharge = surcharge;
	}

}
