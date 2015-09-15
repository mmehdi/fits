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
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="fare_structure")
public class FareStructure {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name="id", nullable = false)
	  private int id;
	  
	  @Column(name="operator_id", nullable = true)
	  private int operator_id;
	  
	  @Column(name="charge_standard_fare", nullable = false)
	  private byte charge_standard_fare;
	  
	  @Column(name="fare_structure_radioBtns", nullable = false)
	  private String fare_structure_radioBtns;
	
	  @Column(name="how_charge_radioBtns", nullable = false)
	  private String how_charge_radioBtns;
	  
	  @Column(name="return_fare_multiplier", nullable = true)
	  private double return_fare_multiplier;
	  
	  @Column(name="discount_for_over60", nullable = true)
	  private double discount_for_over60;
	  
	  @Column(name="discount_for_under16", nullable = true)
	  private double discount_for_under16;
	  
	  @Column(name="discount_for_other_concessionary", nullable = true)
	  private double discount_for_other_concessionary;

	  @Column(name="has_escort", nullable = true)
	  private boolean has_escort;
	  
	  @Column(name="charge_for_dead_mileage", nullable = true)
	  private boolean charge_for_dead_mileage;
	  
	  public int getId() {
		  return id;
	  }
	  public void setId(int id) {
		  this.id = id;
	  }
	  public int getOperator_id() {
		  return operator_id;
	  }
	  public void setOperator_id(int operator_id) {
		  this.operator_id = operator_id;
	  }
	  public byte getCharge_standard_fare() {
		  return charge_standard_fare;
	  }
	  public void setCharge_standard_fare(byte charge_standard_fare) {
		  this.charge_standard_fare = charge_standard_fare;
	  }
	  public double getReturn_fare_multiplier() {
		  return return_fare_multiplier;
	  }
	  public void setReturn_fare_multiplier(double return_fare_multiplier) {
		  this.return_fare_multiplier = return_fare_multiplier;
	  }
	  public double getDiscount_for_over60() {
		  return discount_for_over60;
	  }
	  public void setDiscount_for_over60(double discount_for_over60) {
		  this.discount_for_over60 = discount_for_over60;
	  }
	  public double getDiscount_for_under16() {
		  return discount_for_under16;
	  }
	  public void setDiscount_for_under16(double discount_for_under16) {
		  this.discount_for_under16 = discount_for_under16;
	  }
	  public boolean isHas_escort() {
		  return has_escort;
	  }
	  public void setHas_escort(boolean has_escort) {
		  this.has_escort = has_escort;
	  }
	  public boolean isCharge_for_dead_mileage() {
		  return charge_for_dead_mileage;
	  }
	  public void setCharge_for_dead_mileage(boolean charge_for_dead_mileage) {
		  this.charge_for_dead_mileage = charge_for_dead_mileage;
	  }
	  public String getFare_structure_radioBtns() {
		  return fare_structure_radioBtns;
	  }
	  public void setFare_structure_radioBtns(String fare_structure_radioBtns) {
		  this.fare_structure_radioBtns = fare_structure_radioBtns;
	  }
	  public String getHow_charge_radioBtns() {
		  return how_charge_radioBtns;
	  }
	  public void setHow_charge_radioBtns(String how_charge_radioBtns) {
		  this.how_charge_radioBtns = how_charge_radioBtns;
	  }
	  public double getDiscount_for_other_concessionary() {
		  return discount_for_other_concessionary;
	  }
	  public void setDiscount_for_other_concessionary(
			  double discount_for_other_concessionary) {
		  this.discount_for_other_concessionary = discount_for_other_concessionary;
	}
}
